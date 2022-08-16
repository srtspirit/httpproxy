package ca.vlastier.httpproxy.configuration;

public interface ResponseCondition {
    ResponseConditionConfigurator anyResponse();

    ResponseConditionConfigurator responseHasHeader(String header);

    ResponseConditionConfigurator responseHasNoHeader(String header);

    ResponseConditionConfigurator responseHasHeaderWithValue(String header, String value);

    ResponseConditionConfigurator hasStatus(int statusCode);
}
