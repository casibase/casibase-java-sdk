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

package org.casbin.casibase.service;

import com.fasterxml.jackson.core.type.TypeReference;
import org.casbin.casibase.config.Config;
import org.casbin.casibase.entity.Store;
import org.casbin.casibase.entity.Store;
import org.casbin.casibase.util.AuthTypeEnum;
import org.casbin.casibase.util.Map;
import org.casbin.casibase.util.StoreOperations;
import org.casbin.casibase.util.http.CasibaseResponse;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class StoreService extends Service{

    public StoreService(Config config){
        super(config);
    }

    public StoreService(Config config, AuthTypeEnum authType) {
        super(config, authType);
    }

    public Store getStore(String name) throws IOException {
        CasibaseResponse<Store, Object> response = doGet(StoreOperations.GET_Store.getOperation(),
                Map.of("id", config.organizationName + "/" + name), new TypeReference<CasibaseResponse<Store, Object>>() {
                });
        return response.getData();
    }

    public List<Store> getStores() throws IOException {
        CasibaseResponse<List<Store>, Object> response = doGet(StoreOperations.GET_Stores.getOperation(),
                Map.of("owner", config.organizationName), new TypeReference<CasibaseResponse<List<Store>, Object>>() {
                });
        return response.getData();
    }

    public java.util.Map<String, Object> getPaginationStores(int p, int pageSize, @Nullable java.util.Map<String, String> queryMap) throws IOException {
        CasibaseResponse<Store[], Object> response = doGet(StoreOperations.GET_Store.getOperation(),
                Map.mergeMap(Map.of("owner", config.organizationName,
                        "p", Integer.toString(p),
                        "pageSize", Integer.toString(pageSize)), queryMap), new TypeReference<CasibaseResponse<Store[], Object>>() {
                });
        return Map.of("casibaseStores", response.getData(), "data2", response.getData2());
    }

    public CasibaseResponse<String, Object> addStore(Store Store) throws IOException {
        return modifyStore(StoreOperations.ADD_Store, Store, null);
    }

    public CasibaseResponse<String, Object> deletedStore(Store Store) throws IOException {
        return modifyStore(StoreOperations.DELETE_Store, Store, null);
    }

    public CasibaseResponse<String, Object> updateStore(Store Store) throws IOException {
        HashMap<String, String> queryMap = new HashMap<>();
        queryMap.put("id", config.organizationName + "/" + Store.name);
        return modifyStore(StoreOperations.UPDATE_Store, Store, queryMap);
    }

    private <T1, T2> CasibaseResponse modifyStore(StoreOperations method, Store Store, java.util.Map<String, String> queryMap) throws IOException {
        Store.owner = config.organizationName;
        String payload = objectMapper.writeValueAsString(Store);

        return doPost(method.getOperation(), queryMap, payload,
                new TypeReference<CasibaseResponse<T1, T2>>() {
                });
    }
}
