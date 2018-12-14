package by.intexsoft.importexport.service.impl;

import by.intexsoft.importexport.pojo.TypeEvent;
import by.intexsoft.importexport.service.ICsvService;
import by.intexsoft.importexport.service.IExportEventService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ExportEventServiceTest {
    private IExportEventService exportEventService;
    @Mock
    private ICsvService csvService;
    private TypeEvent type;
    private String path;

    @BeforeEach
    void setUp() {
        exportEventService = new ExportEventService(csvService);
        type = TypeEvent.CALL;
        path = String.format("D:/%s_%s.csv", type.toString(), LocalDate.now());
    }

    @AfterEach
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void tearDown() {
        File file = new File(path);
        if(file.isFile() || file.exists()) {
            file.delete();
        }
    }

    @Test
    void exportToCsv() throws IOException {
        exportEventService.exportToCsv(type);
        FileWriter writer = new FileWriter(path);
        assertNotNull(writer);
        verify(csvService, times(1)).writeCsv(any(), any());
    }
}