package by.intexsoft.importexport.service.impl;

import by.intexsoft.importexport.pojo.TypeEvent;
import by.intexsoft.importexport.service.CsvService;
import by.intexsoft.importexport.service.ExportEventService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

@Service
@AllArgsConstructor
public class IExportServiceService implements ExportEventService {
    private final CsvService csvService;

    public void exportToCsv(final String typeStr) throws IOException {
        String csvName = "D:/"+typeStr.toUpperCase()+"_"+ LocalDate.now()+".csv";
        FileWriter writer = new FileWriter(csvName);
        csvService.writeCsv(writer, TypeEvent.valueOf(typeStr.toUpperCase()));
    }
}
