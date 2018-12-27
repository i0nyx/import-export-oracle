package by.intexsoft.importexport.service.impl;

import by.intexsoft.importexport.pojo.TypeEvent;
import by.intexsoft.importexport.service.IConvertService;
import by.intexsoft.importexport.service.ICsvService;
import by.intexsoft.importexport.service.IEventService;
import by.intexsoft.importexport.service.IGoogleService;
import by.intexsoft.importexport.util.CSVUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

import static by.intexsoft.importexport.constant.Constant.*;
import static by.intexsoft.importexport.util.StringUtil.createFileName;

/**
 * {@inheritDoc}
 */
@Slf4j
@Service
@AllArgsConstructor
public class CsvService implements ICsvService {
    private final IConvertService convertService;
    private final IGoogleService googleService;
    private final String[] header = {EVENT_FIELD_CODE, EVENT_FIELD_DATE};

    @Override
    public List<CSVRecord> readCsvAndConvertToListRecords(final Reader csvFile) throws IOException {
        CSVFormat format = CSVFormat.DEFAULT.withHeader(header).withSkipHeaderRecord(true);
        CSVParser parser = new CSVParser(csvFile, format);
        return parser.getRecords();
    }

    @Override
    public void writeCsv(final TypeEvent typeEvent, final boolean bool) throws IOException, GeneralSecurityException {

        FileWriter writer = new FileWriter(PATH_FOR_SAVE.concat(createFileName(typeEvent)));
        CSVUtil.writeLine(writer, Arrays.asList(header));
        IEventService eventService = convertService.chooseEventService(typeEvent);
        eventService.convertToListString(eventService.getAll()).forEach(list ->
            CSVUtil.writeLine(writer, list)
        );
        closeWriter(writer);
        if(bool) {
            byte[] bytes = Files.readAllBytes(Paths.get(PATH_FOR_SAVE.concat(createFileName(typeEvent))));
            googleService.createAndSaveFileInGoogleDrive(null, CONTENT_TYPE, createFileName(typeEvent), bytes);
        }
    }

    private void closeWriter(FileWriter writer) throws IOException {
        writer.flush();
        writer.close();
    }
}