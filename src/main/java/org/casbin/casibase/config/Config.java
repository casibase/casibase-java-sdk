package org.casbin.casibase.config;

public class Config {
    public String endpoint;
    public String clientId;
    public String clientSecret;
    public String organizationName;
    public String applicationName;

    public Config(String endpoint, String clientId, String clientSecret, String organizationName, String applicationName) {
        this.endpoint = endpoint;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.organizationName = organizationName;
        this.applicationName = applicationName;
    }
}