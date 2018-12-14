package by.intexsoft.importexport.service.impl;

import by.intexsoft.importexport.pojo.TypeEvent;
import by.intexsoft.importexport.service.IConvertService;
import by.intexsoft.importexport.service.ICsvService;
import by.intexsoft.importexport.service.IEventService;
import by.intexsoft.importexport.util.CSVUtil;
import lombok.AllArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;

import static by.intexsoft.importexport.constant.Constant.EVENT_FIELD_CODE;
import static by.intexsoft.importexport.constant.Constant.EVENT_FIELD_DATE;

/**
 * {@inheritDoc}
 */
@Service
@AllArgsConstructor
public class CsvService implements ICsvService {
    private final IConvertService convertService;
    private final String[] header = {EVENT_FIELD_CODE, EVENT_FIELD_DATE};

    public List<CSVRecord> readCsvAndConvertToListRecords(final Reader csvFile) throws IOException {
        CSVFormat format = CSVFormat.DEFAULT.withHeader(header).withSkipHeaderRecord(true);
        CSVParser parser = new CSVParser(csvFile, format);
        return parser.getRecords();
    }

    public void writeCsv(FileWriter writer, final TypeEvent typeEvent) throws IOException {
        CSVUtil.writeLine(writer, Arrays.asList(header));
        IEventService eventService = convertService.chooseEventService(typeEvent);
        eventService.convertToListString(eventService.getAll()).forEach(list ->
            CSVUtil.writeLine(writer, list)
        );
        writer.flush();
        writer.close();
    }
}