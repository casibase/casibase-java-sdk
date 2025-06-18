/*
 * 	Copyright 2025 The Casibase Authors. All Rights Reserved.
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

package org.casbin.casibase;

import org.casbin.casibase.entity.*;
import org.casbin.casibase.service.StoreService;
import org.casbin.casibase.support.TestDefaultConfig;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class StoreTest {
    private final StoreService storeService = new StoreService(TestDefaultConfig.InitConfig());

    @Test
    public void testStore() {
        String name = TestDefaultConfig.getRandomName("store");
        List<String> list = new ArrayList<>();
        Map<String, UsageInfo> map = new HashMap<>();
        List<Prompt> prompts = new ArrayList<>();
        Map<String, Properties> propertiesMap = new HashMap<>();

        Store store = new Store(
                "admin",
                name,
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                "casbin",
                "test_storage_provider",
                "test_image_provider",
                "test_split_provider",
                "test_model_provider",
                "test_embedding_provider",
                list,
                list,
                map,
                map,
                5,
                5,
                60,
                20,
                "test_welcom",
                "test_prompt",
                prompts,
                "",
                "test_avatar",
                "test_title",
                false,
                null,
                propertiesMap
        );
        assertDoesNotThrow(() -> storeService.addStore(store));

        List<Store> stores;
        try{
            stores = storeService.getStores();
        } catch (IOException e) {
            fail("Failed to get objects: " + e.getMessage());
            return;
        }

        boolean found = stores.stream().anyMatch(item -> item.name.equals(name));
        assertTrue(found, "Added object not found in list");

        // Get the object
        Store retrieveStore;
        try {
            retrieveStore = storeService.getStore(name);
        } catch (IOException e) {
            fail("Failed to get object: " + e.getMessage());
            return;
        }
        assertEquals(name, retrieveStore.name, "Retrieved object does not match added object");

        // Update the object
        String updateProvider = "test_updated_provider";
        retrieveStore.storageProvider = updateProvider;
        assertDoesNotThrow(() -> storeService.updateStore(retrieveStore));

        // Validate the update
        Store updatedStore;
        try {
            updatedStore = storeService.getStore(name);
        } catch (IOException e) {
            fail("Failed to get updated object: " + e.getMessage());
            return;
        }
        assertEquals(updateProvider, retrieveStore.storageProvider, "Failed to update object, Path mismatch");

        // Delete the object
        assertDoesNotThrow(() -> storeService.deletedStore(updatedStore));

        // Validate the deletion
        Store deletedStore;
        try {
            deletedStore = storeService.getStore(name);
        } catch (IOException e) {
            fail("Failed to delete object: " + e.getMessage());
            return;
        }
        assertNull(deletedStore, "Failed to delete object, it's still retrievable");
    }
}
