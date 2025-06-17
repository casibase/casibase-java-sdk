package org.casbin.casibase;

import org.casbin.casibase.entity.Record;
import org.casbin.casibase.service.RecordService;
import org.casbin.casibase.support.TestDefaultConfig;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class RecordTest {
    private final RecordService recordService = new RecordService(TestDefaultConfig.InitConfig());

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
