package wlog_tracker.usermodule.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import wlog_tracker.projectmodule.model.Project;
import wlog_tracker.taskmodule.model.Task;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@NoArgsConstructor
@Getter@Setter
@ToString(exclude = {"id"})
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotBlank(message = "Imię jest wymagane")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Nazwisko jest wymagane")
    @Column(nullable = false)
    private String surname;

    @Basic
    private String imageUrl;

    @Basic
    @Column
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Employee_Project",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "project_id") }
    )
    private List<Project> projects;

    @Basic
    @Column
    @OneToMany
    @JoinColumn(name = "task.id")
    private List<Task> tasks;

    @Size(min = 11, max = 11)
    @NotNull(message = "PESEL jest wymagany")
    @Column(nullable = false, unique = true, columnDefinition = "varchar(11)")
    private String pesel;

    @NotNull(message = "Tytuł jest wymagany")
    @Column(nullable = false)
    private Title title;
}