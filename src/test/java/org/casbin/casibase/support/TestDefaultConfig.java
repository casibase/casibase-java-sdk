package org.casbin.casibase.support;

import org.casbin.casibase.config.Config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class TestDefaultConfig {

    private static final String TEST_CASIBASE_ENDPOINT = "https://demo-admin.casibase.com";
    private static final String TEST_CLIENT_ID = "af6b5aa958822fb9dc33";
    private static final String TEST_CLIENT_SECRET = "124140638b4f9de7e78e79ba22d451c17bfa9688";
    private static final String TEST_CASIBASE_ORGANIZATION = "casbin";
    private static final String TEST_CASIBASE_APPLICATION = "app-casibase";

    public static String getRandomCode(int length) {
        byte[] stdNums = "0123456789".getBytes();
        byte[] result = new byte[length];
        Random r = new Random(System.nanoTime());
        for (int i = 0; i < length; i++) {
            result[i] = stdNums[r.nextInt(stdNums.length)];
        }
        return new String(result);
    }

    public static String getRandomName(String prefix) {
        return prefix + "_" + getRandomCode(6);
    }

    public static Config InitConfig() {
        return new Config(TEST_CASIBASE_ENDPOINT, TEST_CLIENT_ID, TEST_CLIENT_SECRET, TEST_CASIBASE_ORGANIZATION, TEST_CASIBASE_APPLICATION);
    }

    public static File convert(byte[] data) throws IOException {

        File file = File.createTempFile("temp", ".tmp");

        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(data);
        }

        return file;
    }
}
