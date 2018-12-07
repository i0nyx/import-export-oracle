package by.intexsoft.importexport.service;

import by.intexsoft.importexport.pojo.TypeEvent;
import org.apache.commons.csv.CSVRecord;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

public interface CsvService {
    List<CSVRecord> readCsvAndConvertToListRecords(Reader csvFile, TypeEvent type) throws IOException;
    void writeCsv(FileWriter writer, final TypeEvent typeEvent) throws IOException;
}
