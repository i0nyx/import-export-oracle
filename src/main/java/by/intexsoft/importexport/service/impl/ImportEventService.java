package by.intexsoft.importexport.service.impl;

import by.intexsoft.importexport.pojo.TypeEvent;
import by.intexsoft.importexport.service.IConvertService;
import by.intexsoft.importexport.service.ICsvService;
import by.intexsoft.importexport.service.IImportEventService;
import lombok.AllArgsConstructor;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Component
@AllArgsConstructor
public class ImportEventService implements IImportEventService {
    private final ICsvService csvService;
    private final IConvertService convertService;

    @Override
    public void importOfCsv(final String path, final TypeEvent typeEvent) throws IOException {
        List<CSVRecord> csvRecords = csvService.readCsvAndConvertToListRecords(new FileReader(path), typeEvent);
        convertService.chooseEventService(typeEvent).convertOfCsvRecordToEventAndSave(csvRecords);
    }
}
