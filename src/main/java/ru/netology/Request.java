package ru.netology;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Request {
    private final String path;
    private final Map<String, String> queryParams;

    public Request(String rawPath) {
        String[] parts = rawPath.split("\\?", 2);
        this.path = parts[0];

        if (parts.length > 1) {
            this.queryParams = parseQueryString(parts[1]);
        } else {
            this.queryParams = new HashMap<>();
        }
    }

    public String getPath() {
        return path;
    }

    public String getQueryParam(String name) {
        return queryParams.get(name);
    }

    public Map<String, String> getQueryParams() {
        return queryParams;
    }

    private Map<String, String> parseQueryString(String query) {
        List<NameValuePair> params = URLEncodedUtils.parse(query, StandardCharsets.UTF_8);
        return params.stream().collect(Collectors.toMap(NameValuePair::getName, NameValuePair::getValue));
    }
}