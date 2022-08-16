package ca.vlastier.httpproxy.configuration;

import ca.vlastier.httpproxy.HttpResponseWrapper;
import org.jsoup.nodes.Element;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public interface ResponseTransformation {
    ResponseTransformationConfigurator appendHtmlElementToBody(String tagName, Supplier<String> htmlContentSupplier);

    ResponseTransformationConfigurator removeHeaderFromResponse(String name);

    ResponseTransformationConfigurator setResponseHeader(String name, String value);

    ResponseTransformationConfigurator setResponseHeader(String name, Function<HttpResponseWrapper, String> headerValueEvaluator);

    ResponseTransformationConfigurator customResponseTransformation(Consumer<HttpResponseWrapper> consumer);

    ResponseTransformationConfigurator changeHtmlElement(String tagName, Predicate<Element> filter, Consumer<Element> action);

    default ResponseTransformationConfigurator changeHtmlElement(String tagName, Consumer<Element> action) {
        return changeHtmlElement(tagName, elem -> true, action);
    }

    ResponseTransformationConfigurator changeAllHtmlElements(String tagName, Predicate<Element> filter, Consumer<Element> action);

    default ResponseTransformationConfigurator changeAllHtmlElements(String tagName, Consumer<Element> action) {
        return changeAllHtmlElements(tagName, elem -> true, action);
    }
}
