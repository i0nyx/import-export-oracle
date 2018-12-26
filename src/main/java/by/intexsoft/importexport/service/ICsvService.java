package by.intexsoft.importexport.service;

import by.intexsoft.importexport.pojo.TypeEvent;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.security.GeneralSecurityException;
import java.util.List;

/**
 * Describes methods for reading and writing csv files
 */
@SuppressWarnings("JavaDoc")
public interface ICsvService {
    /**
     * Method read csv file and convert in {@link List<CSVRecord>}
     *
     * @param csvFile file
     * @return {@link List<CSVRecord>}
     * @throws IOException
     */
    List<CSVRecord> readCsvAndConvertToListRecords(Reader csvFile) throws IOException;

    /**
     * writes data of event in file
     *
     * @param typeEvent event type
     * @throws IOException
     */
    void writeCsv(TypeEvent typeEvent, boolean b) throws IOException, GeneralSecurityException;
}
