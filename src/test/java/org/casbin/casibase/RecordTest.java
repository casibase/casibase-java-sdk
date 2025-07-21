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

import org.casbin.casibase.entity.Record;
import org.casbin.casibase.service.RecordService;
import org.casbin.casibase.support.TestDefaultConfig;
import org.casbin.casibase.util.AuthTypeEnum;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class RecordTest {
    private final RecordService recordService = new RecordService(TestDefaultConfig.InitConfig(), AuthTypeEnum.BEARER);

    @Test
    public void testRecord() {
        String name = TestDefaultConfig.getRandomName("record");
        Record record = new Record(
                "casbin",
                name,
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                "casbin",
                "120.85.97.21",
                "admin",
                "POST",
                "/api/add-store?id=casbin/record_904871",
                "add-store",
                "",
                "en",
                "",
                "{\"status\":\"ok\",\"msg\":\"\"}", 
                true,
                "",
                "",
                "",
                "",
                true
        );
        assertDoesNotThrow(() -> recordService.addRecord(record));

        List<Record> records;
        try{
            records = recordService.getRecords();
        } catch (IOException e) {
            fail("Failed to get objects: " + e.getMessage());
            return;
        }

        boolean found = records.stream().anyMatch(item -> item.name.equals(name));
        assertTrue(found, "Added object not found in list");

        // Get the object
        Record retrieveRecord;
        try{
            retrieveRecord = recordService.getRecord(name);
        } catch (IOException e) {
            fail("Failed to get object: " + e.getMessage());
            return;
        }
        assertEquals(name, retrieveRecord.name, "Retrieved object does not match added object");

        // Update the object
        String updatedUser = "Updated Casibase Website";
        retrieveRecord.user = updatedUser;
        assertDoesNotThrow(() -> recordService.updateRecord(retrieveRecord));

        // Validate the update
        Record updatedRecord;
        try{
            updatedRecord = recordService.getRecord(name);
        } catch (IOException e) {
            fail("Failed to get updated object: " + e.getMessage());
            return;
        }
        assertEquals(updatedUser, updatedRecord.user, "Failed to update object, User mismatch");

        // Delete the object
        assertDoesNotThrow(() -> recordService.deleteRecord(record));

        // Validate the deletion
        Record deletedRecord;
        try{
            deletedRecord = recordService.getRecord(name);
        } catch (IOException e) {
            fail("Failed to delete object: " + e.getMessage());
            return;
        }
        assertNull(deletedRecord, "Failed to delete object, it's still retrievable");
    }
}
