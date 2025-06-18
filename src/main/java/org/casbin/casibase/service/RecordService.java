package org.casbin.casibase.service;

import com.fasterxml.jackson.core.type.TypeReference;
import org.casbin.casibase.config.Config;
import org.casbin.casibase.entity.Record;
import org.casbin.casibase.util.Map;
import org.casbin.casibase.util.RecordOperations;
import org.casbin.casibase.util.http.CasibaseResponse;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.List;

public class RecordService extends Service{

    public RecordService(Config config) {
        super(config);
    }

    public Record getRecord(String name) throws IOException {
        CasibaseResponse<Record, Object> response = doGet(RecordOperations.GET_Record.getOperation(),
                Map.of("id", config.organizationName + "/" + name), new TypeReference<CasibaseResponse<Record, Object>>() {
                });
        return response.getData();
    }

    public List<Record> getRecords() throws IOException {
        CasibaseResponse<List<Record>, Object> response = doGet(RecordOperations.GET_Records.getOperation(),
                Map.of("owner", config.organizationName), new TypeReference<CasibaseResponse<List<Record>, Object>>() {
                });
        return response.getData();
    }

    public java.util.Map<String, Object> getPaginationRecords(int p, int pageSize, @Nullable java.util.Map<String, String> queryMap) throws IOException {
        CasibaseResponse<Record[], Object> response = doGet(RecordOperations.GET_Record.getOperation(),
                Map.mergeMap(Map.of("owner", config.organizationName,
                        "p", Integer.toString(p),
                        "pageSize", Integer.toString(pageSize)), queryMap), new TypeReference<CasibaseResponse<Record[], Object>>() {
                });
        return Map.of("casibaseRecords", response.getData(), "data2", response.getData2());
    }

    public CasibaseResponse<String, Object> addRecord(Record record) throws IOException {
        return modifyRecord(RecordOperations.ADD_Record,record,null);
    }

    public CasibaseResponse<String, Object> deleteRecord(Record record) throws IOException {
        return modifyRecord(RecordOperations.DELETE_Record,record,null);
    }

    public CasibaseResponse<String, Object> updateRecord(Record record) throws IOException {
        return modifyRecord(RecordOperations.UPDATE_Record,record,null);
    }

    private <T1, T2> CasibaseResponse modifyRecord(RecordOperations method, Record record, java.util.Map<String, String> queryMap) throws IOException {
        String id = record.owner + "/" + record.name;
        record.owner = config.organizationName;
        String payload = objectMapper.writeValueAsString(record);

        return doPost(method.getOperation(), Map.mergeMap(Map.of("id", id), queryMap), payload,
                new TypeReference<CasibaseResponse<T1, T2>>() {
                });
    }
}
