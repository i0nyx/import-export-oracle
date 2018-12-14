package by.intexsoft.importexport.service.impl;

import by.intexsoft.importexport.pojo.TypeEvent;
import by.intexsoft.importexport.service.ICsvService;
import by.intexsoft.importexport.service.IExportEventService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

/**
 * {@inheritDoc}
 */
@Service
@AllArgsConstructor
public class ExportEventService implements IExportEventService {
    private final ICsvService csvService;

    public void exportToCsv(final TypeEvent type) throws IOException {
        String csvName = String.format("D:/%s_%s.csv", type.toString(), LocalDate.now());
        FileWriter writer = new FileWriter(csvName);
        csvService.writeCsv(writer, type);
    }
}
