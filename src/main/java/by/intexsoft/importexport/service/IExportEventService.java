package by.intexsoft.importexport.service;

import java.io.IOException;

public interface IExportEventService {
    void exportToCsv(final String typeStr) throws IOException;
}
