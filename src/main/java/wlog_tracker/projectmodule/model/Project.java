package wlog_tracker.projectmodule.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import wlog_tracker.taskmodule.model.Task;
import wlog_tracker.usermodule.model.User;
import wlog_tracker.resources.DateFormat;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
@Getter@Setter
@ToString(exclude = {"id"})
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
    private Long ProjectManagerId;

    @NotNull
    @Column(nullable = false)
    private List<Task> taskList;

    @NotBlank
    @Column(nullable = false)
    private String client;

    @NotNull
    @Column(nullable = false)
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