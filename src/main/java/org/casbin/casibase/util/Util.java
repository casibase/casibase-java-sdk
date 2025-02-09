package org.casbin.casibase.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;


public class Util {

    private String endpoint;

    public Util(String endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * Constructs a URL by appending query parameters to a base endpoint and action path.
     *
     * @param action The action path to append to the endpoint (e.g., "login" or "upload").
     * @param queryMap A map of key-value pairs representing the query parameters.
     * @return A formatted URL string in the format "{endpoint}/api/{action}?{query}".
     */
    public String getUrl(String action, Map<String, String> queryMap) {
        String query = queryMap.entrySet()
                .stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));

        return String.format("%s/api/%s?%s", endpoint, action, query);
    }

    /**
     * Creates a multipart/form-data request body for file uploads.
     * Each entry in the map represents a file or binary data to be included in the form.
     *
     * @param formData A map where the key is the form field name and the value is the binary data (file content).
     * @return A {@link MultipartFormData} object containing the content type and the request body.
     * @throws IOException If an I/O error occurs while writing the form data.
     */
    public static MultipartFormData createFormFile(Map<String, byte[]> formData) throws IOException {
        ByteArrayOutputStream body = new ByteArrayOutputStream();
        String boundary = "----WebKitFormBoundary" + System.currentTimeMillis();
        String contentType = "multipart/form-data; boundary=" + boundary;

        try (OutputStream outputStream = body) {
            for (Map.Entry<String, byte[]> entry : formData.entrySet()) {
                String key = entry.getKey();
                byte[] value = entry.getValue();

                outputStream.write(("--" + boundary + "\r\n").getBytes(StandardCharsets.UTF_8));
                outputStream.write(("Content-Disposition: form-data; name=\"" + key + "\"; filename=\"file\"\r\n").getBytes(StandardCharsets.UTF_8));
                outputStream.write("Content-Type: application/octet-stream\r\n\r\n".getBytes(StandardCharsets.UTF_8));
                outputStream.write(value);
                outputStream.write("\r\n".getBytes(StandardCharsets.UTF_8));
            }
            outputStream.write(("--" + boundary + "--\r\n").getBytes(StandardCharsets.UTF_8));
        }

        return new MultipartFormData(contentType, new ByteArrayInputStream(body.toByteArray()));
    }

    /**
     * Creates a multipart/form-data request body for file uploads.
     * Each entry in the map represents a file or binary data to be included in the form.
     *
     * @param formData A map where the key is the form field name and the value is the binary data (file content).
     * @return A {@link MultipartFormData} object containing the content type and the request body.
     * @throws IOException If an I/O error occurs while writing the form data.
     */
    public static MultipartFormData createForm(Map<String, String> formData) throws IOException {
        ByteArrayOutputStream body = new ByteArrayOutputStream();
        String boundary = "----WebKitFormBoundary" + System.currentTimeMillis();
        String contentType = "multipart/form-data; boundary=" + boundary;

        try (OutputStream outputStream = body) {
            for (Map.Entry<String, String> entry : formData.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();

                outputStream.write(("--" + boundary + "\r\n").getBytes(StandardCharsets.UTF_8));
                outputStream.write(("Content-Disposition: form-data; name=\"" + key + "\"\r\n\r\n").getBytes(StandardCharsets.UTF_8));
                outputStream.write(value.getBytes(StandardCharsets.UTF_8));
                outputStream.write("\r\n".getBytes(StandardCharsets.UTF_8));
            }
            outputStream.write(("--" + boundary + "--\r\n").getBytes(StandardCharsets.UTF_8));
        }

        return new MultipartFormData(contentType, new ByteArrayInputStream(body.toByteArray()));
    }

    /**
     * Represents a multipart/form-data request body and its content type.
     */
    public static class MultipartFormData {
        private final String contentType;
        private final ByteArrayInputStream body;

        public MultipartFormData(String contentType, ByteArrayInputStream body) {
            this.contentType = contentType;
            this.body = body;
        }

        public String getContentType() {
            return contentType;
        }

        public ByteArrayInputStream getBody() {
            return body;
        }
    }
}
