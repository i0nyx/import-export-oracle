package by.intexsoft.importexport.pojo;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Table
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Call extends Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "code")
    private String code;
    @Column(name = "dates")
    private LocalDate date;
}
