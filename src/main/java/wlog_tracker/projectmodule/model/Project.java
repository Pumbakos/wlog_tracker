package wlog_tracker.projectmodule.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import wlog_tracker.taskmodule.model.Task;
import wlog_tracker.usermodule.model.User;
import wlog_tracker.resources.DateFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter@Setter
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(nullable = false)
    private String description;

    @NotNull
    @Column(nullable = false)
    @JoinColumn(name = "user.id")
    private Long projectManagerId;

    @NotNull
    @Column(nullable = false)
    @OneToMany
    @JoinColumn(name = "task.id")
    private List<Task> taskList;

    @NotBlank
    @Column(nullable = false)
    private String client;

    @NotNull
    @Column(nullable = false)
    @ManyToMany(mappedBy = "projects")
    private List<User> assignedEmployees;

    @NotNull
    @JsonFormat(pattern = DateFormat.DATE_PATTERN)
    @Column(nullable = false)
    private Date startDate;

    @NotNull
    @JsonFormat(pattern = DateFormat.DATE_PATTERN)
    @Column(nullable = false)
    private Date endDate;
}