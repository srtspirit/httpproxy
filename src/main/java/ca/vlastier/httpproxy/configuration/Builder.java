package ca.vlastier.httpproxy.configuration;

import ca.vlastier.httpproxy.HttpProxyService;

public interface Builder {
    WebSurferConfigurator ruleName(String name);

    HttpProxyService build();
}
