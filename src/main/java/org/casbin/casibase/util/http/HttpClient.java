/*
 * 	Copyright 2025 The casbin Authors. All Rights Reserved.
 *
 * 	Licensed under the Apache License, Version 2.0 (the "License");
 * 	you may not use this file except in compliance with the License.
 * 	You may obtain a copy of the License at
 *
 * 	     http://www.apache.org/licenses/LICENSE-2.0
 *
 * 	Unless required by applicable law or agreed to in writing, software
 * 	distributed under the License is distributed on an "AS IS" BASIS,
 * 	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * 	See the License for the specific language governing permissions and
 * 	limitations under the License.
 */

package org.casbin.casibase.util.http;

import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class HttpClient {
    private static OkHttpClient okHttpClient = new OkHttpClient();

    /**
     * Sends a synchronous HTTP GET request to the specified URL with the provided credentials for authentication.
     *
     * @param url The target URL to which the request will be sent.
     * @param credential The credential used for authentication, typically a token or API key.
     * @return If the request is successful, returns the response body as a string; otherwise, returns null.
     * @throws IOException If an I/O error occurs during the request (e.g., network issues).
     */
    public static String syncGet(String url, String credential) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", credential)
                .build();
        Response casibaseResponse = okHttpClient.newCall(request).execute();
        if (casibaseResponse.isSuccessful()) {
            return casibaseResponse.body().string();
        }
        return null;
    }

    /**
     * Sends a synchronous HTTP POST request to the specified URL with a string as the request body and the provided credentials for authentication.
     *
     * @param url The target URL to which the request will be sent.
     * @param objStr The string content to be sent as the request body.
     * @param credential The credential used for authentication, typically a token or API key.
     * @return If the request is successful, returns the response body as a string; otherwise, returns null.
     * @throws IOException If an I/O error occurs during the request (e.g., network issues).
     */
    public static String postString(String url, String objStr, String credential) throws IOException {
        MediaType MEDIA_TYPE = MediaType.parse("text/plain;charset=UTF-8");
        Request request = new Request.Builder().url(url)
                .post(RequestBody.create(MEDIA_TYPE, objStr))
                .header("Authorization", credential)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        }
        return null;
    }

    /**
     * Sends a synchronous HTTP POST request to the specified URL with a file as the request body and the provided credentials for authentication.
     *
     * @param url The target URL to which the request will be sent.
     * @param file The file to be uploaded.
     * @param credential The credential used for authentication, typically a token or API key.
     * @return If the request is successful, returns the response body as a string.
     * @throws IOException If an I/O error occurs during the request (e.g., network issues) or if the server returns an unsuccessful response.
     */
    public static String postFile(String url, File file, String credential) throws IOException {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(),
                        RequestBody.create(MediaType.parse("multipart/form-data"), file))
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .header("Authorization", credential)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        }
        return response.body().string();
    }

    /**
     * Post a request of type "application/x-www-form-urlencoded"
     * @param url url
     * @param fromData form data stored in Map
     * @param credential credential
     * @return result as String
     * @throws IOException when request fails
     */
    public static String postForm(String url, Map<String, String> fromData, String credential) throws IOException {

        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        fromData.forEach(formBodyBuilder::addEncoded);
        RequestBody formBody = formBodyBuilder.build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .header("Authorization", credential)
                .build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            return Objects.requireNonNull(response.body()).string();
        }

    }

    /**
     * SetHttpClient sets custom http Client.
     * @param customClient custom http client
     */
    public static void setHttpClient(OkHttpClient customClient) {
        okHttpClient = customClient;
    }
}
