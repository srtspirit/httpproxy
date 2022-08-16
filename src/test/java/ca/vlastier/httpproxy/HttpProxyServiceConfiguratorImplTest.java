package ca.vlastier.httpproxy;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.function.Consumer;

@ExtendWith(MockitoExtension.class)
public abstract class HttpProxyServiceConfiguratorImplTest
{
	@Mock
	protected Consumer<HttpRequestWrapper> mockRequestConsumer;
	@Mock
	protected Consumer<HttpResponseWrapper> mockResponseConsumer;
	@Mock
	protected HttpRequestExecutor httpRequestExecutor;

	protected HttpRequestWrapper defaultRequest = HttpRequestWrapper.builder().build();
	protected HttpResponseWrapper defaultResponse = HttpResponseWrapper.builder().build();
}
