package wlog_tracker.taskmodule;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@NoArgsConstructor
@Getter@Setter
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
    @JsonFormat(pattern = "MMM dd, yyyy, HH:mm:ss a")
//    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(nullable = false)
    private Date dueDate;

    @NotNull
    @JsonFormat(pattern = "MMM dd, yyyy, HH:mm:ss a")
//    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(nullable = false)
    private Date startDate;
    
    @NotNull
    @JsonFormat(pattern = "MMM dd, yyyy, HH:mm:ss a")
//    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(nullable = false)
    private Date endDate;

    @NotNull
    @JsonFormat(pattern = "MMM dd, yyyy, HH:mm:ss a")
//    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date trackedTime;
}

