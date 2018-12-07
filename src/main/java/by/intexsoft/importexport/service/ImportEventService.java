package by.intexsoft.importexport.service;

import java.io.IOException;

public interface ImportEventService {
    void checkAndImport(final String path) throws IOException;
}
