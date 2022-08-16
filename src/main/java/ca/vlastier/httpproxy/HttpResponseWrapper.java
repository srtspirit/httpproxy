package ca.vlastier.httpproxy;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class HttpResponseWrapper extends HttpWrapper {
    private JsonNode jsonBody;
    private Document domBody;
    private HttpStatus httpStatus;
    private ResponseEntity<String> originalResponse;
    private HttpRequestWrapper originalRequest;

    @lombok.Builder
    public HttpResponseWrapper(final HttpHeaders headers, final JsonNode jsonBody, final Document domBody, final HttpStatus httpStatus,
                               final ResponseEntity<String> originalResponse, final HttpRequestWrapper originalRequest) {
        super(headers);
        this.jsonBody = jsonBody;
        this.domBody = domBody;
        this.httpStatus = httpStatus;
        this.originalResponse = originalResponse;
        this.originalRequest = originalRequest;
    }

    public ResponseEntity<Object> buildResponse() {

        final ResponseEntity.BodyBuilder response = ResponseEntity.status(httpStatus).headers(getHeaders());

        if (jsonBody != null) {
            return response.body(jsonBody);
        } else if (domBody != null) {
            return response.body(Parser.unescapeEntities(domBody.html(), true));
        } else {
            return response.body(originalResponse.getBody());
        }
    }
}
