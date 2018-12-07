package by.intexsoft.importexport.service;

import by.intexsoft.importexport.pojo.TypeEvent;
import org.apache.commons.csv.CSVRecord;

import java.util.List;

public interface EventService<T> {
    void saveList(final List<T> list);
    void save(final T t);
    List<T> getAll();
    TypeEvent getType();
    void convertOfCsvRecordToEventAndSave(final List<CSVRecord> list);
    T buildEventByType(final CSVRecord record);
    List<List<String>> convertToListString();
}
