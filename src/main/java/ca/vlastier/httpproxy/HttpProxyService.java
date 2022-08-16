package ca.vlastier.httpproxy;

import ca.vlastier.httpproxy.configuration.*;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class HttpProxyService
{
	private final HttpRequestExecutor httpRequestExecutor;
	private final List<WebSurferRule> rules;

	HttpProxyService(final HttpRequestExecutor httpRequestExecutor, final List<WebSurferRule> rules)
	{
		this.httpRequestExecutor = httpRequestExecutor;
		this.rules = rules;
	}

	public static WebSurferConfigurator configure(@NonNull final HttpRequestExecutor httpRequestExecutor)
	{
		return new HttpProxyServiceConfiguratorImpl(httpRequestExecutor);
	}

	public HttpResponseWrapper surf(@NonNull final HttpRequestWrapper requestWrapper)
	{
		final List<WebSurferRule> triggeredRulesByRequest = rules.stream()
				.filter(rule -> {
					final boolean result = rule.getRequestPredicate().test(requestWrapper);
					log.debug("Request testing rule \"{}\"... Passed: {}", rule.getName(), result);
					return result;
				})
				.collect(Collectors.toList());

		triggeredRulesByRequest.stream()
				.filter(rule -> rule.getRequestTransformation() != null)
				.peek(rule -> log.info("Rule \"{}\". Applying request transformations.", rule.getName()))
				.map(WebSurferRule::getRequestTransformation)
				.forEach(transformations -> transformations.accept(requestWrapper));

		final HttpResponseWrapper responseWrapper = httpRequestExecutor.executeRequest(requestWrapper);

		// find and apply response rules. Search amongst request rules as they are stored in the same collection.
		// Solely response rules have null requestTransformation and must have requestPredicate always true in order to end up in this collection. Read docs for more info
		final List<WebSurferRule> triggeredRulesByResponse = triggeredRulesByRequest.stream()
				.filter(rule -> {
					boolean result = rule.getResponsePredicate().test(responseWrapper);
					log.debug("Response testing rule \"{}\"... Passed: {}", rule.getName(), result);
					return result;
				})
				.collect(Collectors.toList());

		triggeredRulesByResponse.stream()
				.filter(rule -> rule.getResponseTransformation() != null)
				.peek(rule -> log.info("Rule \"{}\". Applying response transformations.", rule.getName()))
				.map(WebSurferRule::getResponseTransformation)
				.forEach(transformations -> transformations.accept(responseWrapper));

		return responseWrapper;
	}

}
