package by.intexsoft.importexport.service;

import by.intexsoft.importexport.pojo.Event;
import by.intexsoft.importexport.pojo.TypeEvent;
import org.apache.commons.csv.CSVRecord;

import java.time.LocalDate;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public interface IEventService<T extends Event> {
    void saveList(final List<T> events);
    void save(final T event);
    List<T> getAll();
    TypeEvent getType();
    void clearTable();
    void convertOfCsvRecordToEventAndSave(final List<CSVRecord> csvRecords);
    T buildEventByTypeOfCsvRecord(final CSVRecord record);
    T buildEventByType(String code, final LocalDate localDate);

    default List<List<String>> convertToListString(final List<T> events){
        List<List<String>> listStr = newArrayList();
        events.forEach(event -> {
            List<String> strings = newArrayList();
            strings.add(event.getCode());
            strings.add(event.getDate().toString());
            listStr.add(strings);
        });
        return listStr;
    }
}
