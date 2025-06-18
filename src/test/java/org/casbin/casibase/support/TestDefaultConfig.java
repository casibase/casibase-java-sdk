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

package org.casbin.casibase.support;

import org.casbin.casibase.config.Config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class TestDefaultConfig {

    private static final String TEST_CASIBASE_ENDPOINT = "https://demo-admin.casibase.com";
    private static final String TEST_CLIENT_ID = "af6b5aa958822fb9dc33";
    private static final String TEST_CLIENT_SECRET = "8bc3010c1c951c8d876b1f311a901ff8deeb93bc";
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
