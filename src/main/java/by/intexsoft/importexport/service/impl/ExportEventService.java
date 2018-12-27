package by.intexsoft.importexport.service.impl;

import by.intexsoft.importexport.pojo.TypeEvent;
import by.intexsoft.importexport.service.ICsvService;
import by.intexsoft.importexport.service.IExportEventService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * {@inheritDoc}
 */
@Service
@AllArgsConstructor
public class ExportEventService implements IExportEventService {
    private final ICsvService csvService;

    public void exportToCsv(final TypeEvent type, boolean b) throws IOException, GeneralSecurityException {
        csvService.writeCsv(type, b);
    }
}
