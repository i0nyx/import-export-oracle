package by.intexsoft.importexport.service;

import by.intexsoft.importexport.pojo.TypeEvent;

import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * Export data of data base in file
 */
@SuppressWarnings("ALL")
public interface IExportEventService {
    /**
     * Method create {@link java.io.FileWriter} and send in {@link ICsvService}
     *
     * @param type event type
     * @throws IOException
     */
    void exportToCsv(TypeEvent type, boolean b) throws IOException, GeneralSecurityException;
}
