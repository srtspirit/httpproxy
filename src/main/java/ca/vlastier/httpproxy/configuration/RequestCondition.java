package ca.vlastier.httpproxy.configuration;

public interface RequestCondition {
    RequestConditionConfigurator get(String url);

    RequestConditionConfigurator get();

    RequestConditionConfigurator post(String url);

    RequestConditionConfigurator post();

    RequestConditionConfigurator put(String url);

    RequestConditionConfigurator put();

    RequestConditionConfigurator delete(String url);

    RequestConditionConfigurator delete();

    RequestConditionConfigurator requestHasHeader(String header);

    RequestConditionConfigurator requestHasNoHeader(String header);

    RequestConditionConfigurator requestHasHeaderWithValue(String header, String value);

    RequestConditionConfigurator anyRequest();
}
