package ca.vlastier.httpproxy;

import ca.vlastier.httpproxy.HttpProxyService.HttpRequestWrapper;
import ca.vlastier.httpproxy.HttpProxyService.HttpResponseWrapper;

public interface HttpRequestExecutor
{
	HttpResponseWrapper executeRequest(HttpRequestWrapper request);
}
