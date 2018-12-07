package by.intexsoft.importexport.service;

import java.io.IOException;

public interface ExportEventService {
    void exportToCsv(final String typeStr) throws IOException;
}
