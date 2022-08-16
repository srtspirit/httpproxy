package ca.vlastier.httpproxy;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class HttpRequestWrapper extends HttpWrapper {
    private String method;
    private String url;
    private Object body;

    @lombok.Builder
    public HttpRequestWrapper(final HttpHeaders headers, final String method, final String url, final Object body) {
        super(headers);
        this.method = method;
        this.url = url;
        this.body = body;
    }
}
