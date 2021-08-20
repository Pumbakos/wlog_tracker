package wlog_tracker.taskmodule.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import wlog_tracker.projectmodule.model.Project;
import wlog_tracker.resources.DateFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter@Setter
@ToString(exclude = "id")
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Priority priority;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(nullable = false)
    private String description;

    @NotNull
    @Column(nullable = false)
    @JsonFormat(pattern = DateFormat.DATE_PATTERN, timezone = "UTC")
    private Date dueDate;

    @NotNull
    @Column(nullable = false)
    @JsonFormat(pattern = DateFormat.DATE_PATTERN, timezone = "UTC")
    private Date startDate;

    @NotNull
    @Column(nullable = false)
    @JsonFormat(pattern = DateFormat.DATE_PATTERN, timezone = "UTC")
    private Date endDate;

    @Column
    private Date trackedTime;
}
