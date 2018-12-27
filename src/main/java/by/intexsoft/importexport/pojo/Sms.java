package by.intexsoft.importexport.pojo;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Class containing properties of sms
 */
@Data
@Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Sms extends Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "code")
    private String code;
    @Column(name = "dates")
    private LocalDate date;
}

