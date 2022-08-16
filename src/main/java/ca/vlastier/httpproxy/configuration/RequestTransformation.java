package ca.vlastier.httpproxy.configuration;

import ca.vlastier.httpproxy.HttpRequestWrapper;

import java.util.function.Consumer;
import java.util.function.Function;

public interface RequestTransformation {
    RequestTransformationConfigurator setRequestHeader(String name, String value);

    RequestTransformationConfigurator removeHeaderFromRequest(String name);

    RequestTransformationConfigurator changeUrl(String newUrl);

    RequestTransformationConfigurator changeUrl(Function<HttpRequestWrapper, String> urlValueEvaluator);

    RequestTransformationConfigurator customRequestTransformation(Consumer<HttpRequestWrapper> consumer);
}
