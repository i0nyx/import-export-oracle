package by.intexsoft.importexport.service.impl;

import by.intexsoft.importexport.pojo.TypeEvent;
import by.intexsoft.importexport.service.IConvertService;
import by.intexsoft.importexport.service.ICsvService;
import by.intexsoft.importexport.service.IEventService;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ImportEventServiceTest {
    @Mock
    private ICsvService csvService;
    @Mock
    private IConvertService convertService;
    @Mock
    private IEventService eventService;
    private TypeEvent type;
    private FileReader reader;

    @BeforeEach
    void setUp() throws FileNotFoundException {
        type = TypeEvent.CALL;
        reader = new FileReader("D:/file.csv");
    }

    @Test
    void importOfCsv() throws IOException {
        List<CSVRecord> records = csvService.readCsvAndConvertToListRecords(reader);
        assertNotNull(records);
        verify(csvService, times(1)).readCsvAndConvertToListRecords(any());

        convertService.chooseEventService(type);
        verify(convertService, times(1)).chooseEventService(any());

        eventService.convertOfCsvRecordToEventAndSave(records);
        verify(eventService, times(1)).convertOfCsvRecordToEventAndSave(any());
    }
}