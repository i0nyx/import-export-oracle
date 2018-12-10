package by.intexsoft.importexport.service;

import by.intexsoft.importexport.pojo.Event;
import by.intexsoft.importexport.pojo.TypeEvent;
import org.apache.commons.csv.CSVRecord;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public interface IEventService<T extends Event> {
    void saveList(final List<T> list);
    void save(final T t);
    List<T> getAll();
    TypeEvent getType();
    void clearTable();
    void convertOfCsvRecordToEventAndSave(final List<CSVRecord> list);
    T buildEventByType(final CSVRecord record);

    default List<List<String>> convertToListString(List<T> events){
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
