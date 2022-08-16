package ca.vlastier.httpproxy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpHeaders;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class HttpWrapper {
    private HttpHeaders headers;

    public HttpWrapper addHeader(final String headerName, final List<String> headerValues) {
        getHeaders().put(headerName, headerValues);
        return this;
    }

    public HttpHeaders getHeaders() {
        if (headers == null) {
            headers = new HttpHeaders();
        }

        return headers;
    }
}
