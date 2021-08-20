package wlog_tracker.taskmodule.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter @Setter
@ToString(exclude = "Id")
@Entity
@Table(name="tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="TaskID", columnDefinition = "int")
    private Long Id;

    @Column(nullable = false, name="Priority")
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @NotBlank
    @Column(nullable = false, name="Name")
    private String name;

    @NotBlank
    @Column(nullable = false, name="Description")
    private String description;

    @NotNull
    @Column(nullable = false, name="DueDate")
    private Date dueDate;

    @NotNull
    @Column(nullable = false, name="StartDate")
    private Date startDate;

    @NotNull
    @Column(nullable = false, name="EndDate")
    private Date endDate;
    
    @Column(name="TrackedTime")
    private Date trackedTime;
}
