package thymeleaf.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import thymeleaf.model.enam.StudyFormat;

import java.util.List;

@Entity
@Table(name = "students")
@Getter@Setter
@NoArgsConstructor
@ToString
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String email;
    private String lastName;
    private StudyFormat studyFormat;

    @ManyToOne
    private Group group;

}
