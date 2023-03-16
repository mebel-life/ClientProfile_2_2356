package org.client.common.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

public class AuthUtil {
    private RestTemplate restTemplate = new RestTemplate();
    private static final String AUTH_URL = "http://localhost:8084/checkAuth";

    public void checkAuth(HttpServletRequest request) {
        HttpMethod method;
        switch (request.getMethod()) {
            case "POST":
                method = HttpMethod.POST;
                break;
            case "PUT":
                method = HttpMethod.PUT;
                break;
            case "DELETE":
                method = HttpMethod.DELETE;
                break;
            default:
                method = HttpMethod.GET;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.set("username", request.getHeader("username"));
        headers.set("password", request.getHeader("password"));
        restTemplate.exchange(AUTH_URL, method, new HttpEntity<>(headers), Void.class);
    }
}
