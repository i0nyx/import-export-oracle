package by.intexsoft.importexport.service;

import by.intexsoft.importexport.pojo.TypeEvent;
import org.apache.commons.csv.CSVRecord;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
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
     * @param type    type event
     * @return {@link List<CSVRecord>}
     * @throws IOException
     */
    List<CSVRecord> readCsvAndConvertToListRecords(Reader csvFile, TypeEvent type) throws IOException;

    /**
     * writes data of event in file
     *
     * @param writer    file
     * @param typeEvent event type
     * @throws IOException
     */
    void writeCsv(FileWriter writer, final TypeEvent typeEvent) throws IOException;
}
