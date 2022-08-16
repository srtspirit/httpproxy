package ca.vlastier.httpproxy;

import ca.vlastier.httpproxy.configuration.WebSurferConfigurator;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Class encapsulating a proxy server rule. Each rule has a  predicate(condition) and transformation (what needs to be done).
 * There are two types of predicates: request and response predicates. Request predicate are invoked against requests; response predicates are invoked against responses.
 * Similarly, there are two types of transformations: request and response transformation. Request transformations are run on requests; response transformations are run on responses.
 * A simple flow of transformation might be represented in the following way:
 * <ul>
 *     <li>get request from the user</li>
 *     <li>find transformations matching the request (requestPredicate returns true when is run against the request) and apply them</li>
 *     <li>execute the request ang get a response from server</li>
 *     <li>find transformations matching the response (responsePredicate returns true when is run against the response <b>AND requestPredicate returns true when is run against the original request</b>)</li>
 * <ul/>
 * <p>
 * Important: it is possible to execute a response transformation having a request condition. For example if the request contains a specific header add a html tag to the response body.
 * That's why for response transformation requires both predicates to be true: request predicated on request and response predicate on response.
 * So if a response transformation is required, and it has only a request condition then response predicate must always return true and vice versa.
 * If a response transformation is required, and it has only a response condition then request predicate must always return true.
 * All this logic is transparent if use {@link WebSurferConfigurator}. Get one by calling {@link HttpProxyService#configure(HttpRequestExecutor)}
 */
@AllArgsConstructor
@lombok.Builder
@Data
public class WebSurferRule {
    private String name;
    private Predicate<HttpRequestWrapper> requestPredicate;
    private Consumer<HttpRequestWrapper> requestTransformation;
    private Predicate<HttpResponseWrapper> responsePredicate;
    private Consumer<HttpResponseWrapper> responseTransformation;
}
