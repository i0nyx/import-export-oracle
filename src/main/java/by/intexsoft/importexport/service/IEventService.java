package by.intexsoft.importexport.service;

import by.intexsoft.importexport.pojo.Event;
import by.intexsoft.importexport.pojo.TypeEvent;
import org.apache.commons.csv.CSVRecord;

import java.time.LocalDate;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Interface describing the work with events
 *
 * @param <T> type event
 */
public interface IEventService<T extends Event> {
    /**
     * Method save list events
     *
     * @param events list events
     */
    void saveList(final List<T> events);

    /**
     * Method save one events
     *
     * @param event object event
     */
    void save(final T event);

    /**
     * Method get all events of table
     *
     * @return {@link List<Event>}
     */
    List<T> getAll();

    /**
     * Method return event type
     *
     * @return {@link TypeEvent}
     */
    TypeEvent getType();

    /**
     * Method cleans the table for a specific event
     */
    void clearTable();

    /**
     * Method convert file data in object event and their save
     *
     * @param csvRecords list data
     */
    void convertOfCsvRecordToEventAndSave(final List<CSVRecord> csvRecords);

    /**
     * Method build event object of {@link CSVRecord} data
     *
     * @param record contains data of file
     * @return {@link Event}
     */
    T buildEventByTypeOfCsvRecord(final CSVRecord record);

    /**
     * Method build event object from the data
     *
     * @param code      string parameter
     * @param localDate local date
     * @return {@link Event}
     */
    T buildEventByType(String code, final LocalDate localDate);

    /**
     * Default method convert list events in to {@link List<String>}
     *
     * @param events list events
     * @return {@link List<String>}
     */
    default List<List<String>> convertToListString(final List<T> events) {
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
