package by.intexsoft.importexport.pojo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public abstract class Event {
    @Getter
    @Setter
    private String code;
    @Getter
    @Setter
    private LocalDate date;
}
