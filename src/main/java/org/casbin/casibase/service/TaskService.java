package org.casbin.casibase.service;

import com.fasterxml.jackson.core.type.TypeReference;
import org.casbin.casibase.config.Config;
import org.casbin.casibase.entity.Task;
import org.casbin.casibase.util.Map;
import org.casbin.casibase.util.TaskOperations;
import org.casbin.casibase.util.http.CasibaseResponse;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class TaskService extends Service {

    public TaskService(Config config) {
        super(config);
    }

    public Task getTask(String name) throws IOException {
        CasibaseResponse<Task, Object> response = doGet(TaskOperations.GET_Task.getOperation(),
                Map.of("id", config.organizationName + "/" + name), new TypeReference<CasibaseResponse<Task, Object>>() {
                });
        return response.getData();
    }

    public List<Task> getTasks() throws IOException {
        CasibaseResponse<List<Task>, Object> response = doGet(TaskOperations.GET_Tasks.getOperation(),
                Map.of("owner", config.organizationName), new TypeReference<CasibaseResponse<List<Task>, Object>>() {
                });
        return response.getData();
    }

    public java.util.Map<String, Object> getPaginationTasks(int p, int pageSize, @Nullable java.util.Map<String, String> queryMap) throws IOException {
        CasibaseResponse<Task[], Object> response = doGet(TaskOperations.GET_Task.getOperation(),
                Map.mergeMap(Map.of("owner", config.organizationName,
                        "p", Integer.toString(p),
                        "pageSize", Integer.toString(pageSize)), queryMap), new TypeReference<CasibaseResponse<Task[], Object>>() {
                });
        return Map.of("casibaseTasks", response.getData(), "data2", response.getData2());
    }

    public CasibaseResponse<String, Object> addTask(Task task) throws IOException {
        return modifyTask(TaskOperations.ADD_Task, task, null);
    }

    public CasibaseResponse<String, Object> deletedTask(Task task) throws IOException {
        return modifyTask(TaskOperations.DELETE_Task, task, null);
    }

    public CasibaseResponse<String, Object> updateTask(Task task) throws IOException {
        HashMap<String, String> queryMap = new HashMap<>();
        queryMap.put("id", config.organizationName + "/" + task.name);
        return modifyTask(TaskOperations.UPDATE_Task, task, queryMap);
    }

    private <T1, T2> CasibaseResponse modifyTask(TaskOperations method, Task task, java.util.Map<String, String> queryMap) throws IOException {
        task.owner = config.organizationName;
        String payload = objectMapper.writeValueAsString(task);

        return doPost(method.getOperation(), queryMap, payload,
                new TypeReference<CasibaseResponse<T1, T2>>() {
                });
    }
}
