package org.casbin.casibase.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Credentials;
import org.casbin.casibase.config.Config;
import org.casbin.casibase.exception.Exception;
import org.casbin.casibase.util.Map;
import org.casbin.casibase.util.http.CasibaseResponse;
import org.casbin.casibase.util.http.HttpClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Service {
    protected final ObjectMapper objectMapper = new ObjectMapper(){{
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }};

    protected final Config config;
    protected final String credential;
    protected Service(Config config) {
        this.config = config;
        this.credential = Credentials.basic(config.clientId, config.clientSecret);
    }

    protected <T1, T2> CasibaseResponse<T1, T2> doGet(@NotNull String action, @Nullable java.util.Map<String, String> queryParams, TypeReference<CasibaseResponse<T1, T2>> typeReference) throws IOException {
        String url = String.format("%s/api/%s?%s", config.endpoint, action, Map.mapToUrlParams(queryParams));
        String response = HttpClient.syncGet(url, credential);
        CasibaseResponse<T1, T2> resp = objectMapper.readValue(response, typeReference);
        if (!Objects.equals(resp.getStatus(), "ok")) {
            throw new Exception(String.format("Failed fetching %s : %s", url, resp.getMsg()));
        }

        return resp;
    }

    protected <T1, T2> CasibaseResponse<T1, T2> doPost(@NotNull String action, @Nullable java.util.Map<String, String> queryParams, java.util.Map<String, String> postForm, TypeReference<CasibaseResponse<T1, T2>> typeReference) throws IOException {
        String url = String.format("%s/api/%s?%s", config.endpoint, action, Map.mapToUrlParams(queryParams));
        String response = HttpClient.postForm(url, postForm, credential);
        CasibaseResponse<T1, T2> resp = objectMapper.readValue(response, typeReference);
        if (!Objects.equals(resp.getStatus(), "ok")) {
            throw new Exception(String.format("Failed fetching %s : %s", url, resp.getMsg()));
        }

        return resp;
    }

    protected <T1, T2> CasibaseResponse<T1, T2> doPost(@NotNull String action, @Nullable java.util.Map<String, String> queryParams, String postString, TypeReference<CasibaseResponse<T1, T2>> typeReference) throws IOException {
        String url;
        if(queryParams != null){
            url = String.format("%s/api/%s?%s", config.endpoint, action, Map.mapToUrlParams(queryParams));
        }else {
            url = String.format("%s/api/%s", config.endpoint, action);
        }
        String response = HttpClient.postString(url, postString, credential);
        CasibaseResponse<T1, T2> resp = objectMapper.readValue(response, typeReference);
        if (!Objects.equals(resp.getStatus(), "ok")) {
            throw new Exception(String.format("Failed fetching %s : %s", url, resp.getMsg()));
        }

        return resp;
    }

    protected <T1, T2> CasibaseResponse<T1, T2> doPost(String action, @Nullable java.util.Map<String, String> queryParams, File postFile, TypeReference<CasibaseResponse<T1, T2>> typeReference) throws IOException {
        String url = String.format("%s/api/%s?%s", config.endpoint, action, Map.mapToUrlParams(queryParams));
        String response = HttpClient.postFile(url, postFile, credential);
        CasibaseResponse<T1, T2> resp = objectMapper.readValue(response, typeReference);
        if (!Objects.equals(resp.getStatus(), "ok")) {
            throw new Exception(String.format("Failed fetching %s : %s", url, resp.getMsg()));
        }

        return resp;
    }
}
