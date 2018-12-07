package by.intexsoft.importexport.service.impl;

import by.intexsoft.importexport.Util.StringUtil;
import by.intexsoft.importexport.pojo.TypeEvent;
import by.intexsoft.importexport.service.ConvertService;
import by.intexsoft.importexport.service.CsvService;
import by.intexsoft.importexport.service.ImportEventService;
import lombok.AllArgsConstructor;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Component
@AllArgsConstructor
public class IImportEventService implements ImportEventService {
    private final CsvService csvService;
    private final ConvertService convertService;

    @Override
    public void checkAndImport(final String path) throws IOException {
        StringUtil.checkFileExtension(path);
        TypeEvent type = TypeEvent.valueOf(StringUtil.getStringType(path).toUpperCase());
        List<CSVRecord> csvRecords = csvService.readCsvAndConvertToListRecords(new FileReader(path), type);
        convertService.chooseEventService(type).convertOfCsvRecordToEventAndSave(csvRecords);
    }
}
