package by.intexsoft.importexport.service;

import by.intexsoft.importexport.pojo.TypeEvent;

import java.io.IOException;
import java.util.List;

/**
 * Import data in data base
 */
@SuppressWarnings("JavaDoc")
public interface IImportEventService {
    /**
     * Read file and convert data in {@link List <org.apache.commons.csv.CSVRecord>} and send in {@link IEventService}
     *
     * @param path      path to file
     * @param typeEvent event type
     * @throws IOException
     */
    void importOfCsv(final String path, final TypeEvent typeEvent) throws IOException;
}
