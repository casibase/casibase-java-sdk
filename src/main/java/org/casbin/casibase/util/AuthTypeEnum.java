package org.casbin.casibase.util;

public enum AuthTypeEnum {
    BASIC("basic"),
    BEARER("bearer");
    private final String authType;

    AuthTypeEnum(String authType) {
        this.authType = authType;
    }

    public String getAuthType() {
        return authType;
    }
}
