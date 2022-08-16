package ca.vlastier.httpproxy.configuration;

public interface RequestTransformationConfigurator
        extends RequestCondition, RequestTransformation, ResponseCondition, ResponseTransformation, Builder {
}
