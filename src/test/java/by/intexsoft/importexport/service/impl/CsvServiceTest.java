package by.intexsoft.importexport.service.impl;

import by.intexsoft.importexport.pojo.TypeEvent;
import by.intexsoft.importexport.service.IConvertService;
import by.intexsoft.importexport.service.ICsvService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import static by.intexsoft.importexport.constant.Constant.EVENT_FIELD_CODE;
import static by.intexsoft.importexport.constant.Constant.EVENT_FIELD_DATE;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class CsvServiceTest {
    private ICsvService csvService;
    @Mock
    private IConvertService convertService;
    private FileReader reader;
    private FileWriter writer;
    private File file;
    private TypeEvent type;

    @BeforeEach
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void setUp() throws IOException {
        csvService = new CsvService(convertService);
        type = TypeEvent.CALL;
        file = new File("D:/CALL_" + LocalDate.now() + ".csv");
        file.createNewFile();
        reader = new FileReader(file);
        writer = new FileWriter(file);
    }

    @AfterEach
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void tearDown() {
        file.delete();
    }

    @Test
    void readCsvAndConvertToListRecords() throws IOException {
        csvService.readCsvAndConvertToListRecords(reader);
        String[] header = {EVENT_FIELD_CODE, EVENT_FIELD_DATE};
        CSVFormat format = CSVFormat.DEFAULT.withHeader(header).withSkipHeaderRecord(true);
        CSVParser parser = new CSVParser(reader, format);
        assertNotNull(format);
        assertNotNull(parser);
        assertNotNull(parser.getRecords());
    }

//    @Test
//    void writeCsv() {
//
//    }
}