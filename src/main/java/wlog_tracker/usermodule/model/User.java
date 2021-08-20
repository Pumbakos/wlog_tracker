package wlog_tracker.usermodule.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import wlog_tracker.vacationmodule.model.Vacation;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "employees")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EmployeeID", columnDefinition = "INT")
    @ToString.Exclude
    private Long id;

    @NotBlank(message = "Imię jest wymagane")
    @Column(nullable = false, name = "FirstName", columnDefinition = "varchar(100)")
    private String name;

    @NotBlank(message = "Nazwisko jest wymagane")
    @Column(nullable = false, name = "LastName", columnDefinition = "varchar(100)")
    private String surname;

    @Size(min = 11, max = 11)
    @NotNull(message = "PESEL jest wymagany")
    @Column(nullable = false, unique = true,
            name = "PESEL", columnDefinition = "varchar(11)")
    private String pesel;

    @NotNull(message = "Tytuł jest wymagany")
    @Column(nullable = false, name = "JobTitle", columnDefinition = "varchar(50)")
    private Title title;

    @Basic
    @Column(name = "ImageUrl", columnDefinition = "varchar(max)")
    private String image;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Vacation> vacations;

    public Vacation removeLastVacation() {
        return vacations.remove(vacations.size() - 1);
    }
}