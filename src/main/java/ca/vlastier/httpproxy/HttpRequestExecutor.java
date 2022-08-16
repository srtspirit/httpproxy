package ca.vlastier.httpproxy;

public interface HttpRequestExecutor
{
	HttpResponseWrapper executeRequest(HttpRequestWrapper request);
}
