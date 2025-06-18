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

import org.casbin.casibase.entity.Task;
import org.casbin.casibase.service.TaskService;
import org.casbin.casibase.support.TestDefaultConfig;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    private final TaskService taskService = new TaskService(TestDefaultConfig.InitConfig());

    @Test
    public void testTask() {
        String name = TestDefaultConfig.getRandomName("task");
        List<String> list = new ArrayList<>();
        Task task = new Task(
                "true",
                "casbin",
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                "casbin",
                "test_example",
                "test_grade",
                list,
                "test_log",
                name,
                "casbin",
                "test_path",
                "test_provider",
                "test_result",
                "test_subject",
                "test_text",
                "test_topic",
                "test_type");
        assertDoesNotThrow(() -> taskService.addTask(task));

        List<Task> tasks;
        try {
            tasks = taskService.getTasks();
        } catch (IOException e) {
            fail("Failed to get objects: " + e.getMessage());
            return;
        }

        boolean found = tasks.stream().anyMatch(item -> item.name.equals(name));
        assertTrue(found, "Added object not found in list");

        // Get the object
        Task retrieveTask;
        try {
            retrieveTask = taskService.getTask(name);
        } catch (IOException e) {
            fail("Failed to get object: " + e.getMessage());
            return;
        }
        assertEquals(name, retrieveTask.name, "Retrieved object does not match added object");

        // Update the object
        String updatePath = "updatedCasibasePath/test_path";
        retrieveTask.path = updatePath;
        assertDoesNotThrow(() -> taskService.updateTask(retrieveTask));

        // Validate the update
        Task updatedTask;
        try {
            updatedTask = taskService.getTask(name);
        } catch (IOException e) {
            fail("Failed to get updated object: " + e.getMessage());
            return;
        }
        assertEquals(updatePath, retrieveTask.path, "Failed to update object, Path mismatch");
        //
        // Delete the object
        assertDoesNotThrow(() -> taskService.deletedTask(updatedTask));

        // Validate the deletion
        Task deletedTask;
        try {
            deletedTask = taskService.getTask(name);
        } catch (IOException e) {
            fail("Failed to delete object: " + e.getMessage());
            return;
        }
        assertNull(deletedTask, "Failed to delete object, it's still retrievable");
    }
}
