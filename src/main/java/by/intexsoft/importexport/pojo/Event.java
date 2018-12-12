package by.intexsoft.importexport.pojo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Abstract class events
 */
public abstract class Event {
    @Getter
    @Setter
    private String code;
    @Getter
    @Setter
    private LocalDate date;
}
