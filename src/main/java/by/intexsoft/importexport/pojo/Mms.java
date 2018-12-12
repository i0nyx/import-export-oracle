package by.intexsoft.importexport.pojo;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Class containing properties of mms
 */
@Data
@Table
@Entity
@Builder
@EqualsAndHashCode(callSuper = true)
public class Mms extends Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "code")
    private String code;
    @Column(name = "dates")
    private LocalDate date;
}
