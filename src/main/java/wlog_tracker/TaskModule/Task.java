package wlog_tracker.TaskModule;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor // do zmiany na jakiś TaskGenerator.java
@Getter@Setter
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

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
    private LocalDate dueDate;

    @NotNull
    @Column(nullable = false)
    private LocalDate startDate;

    @NotNull
    @Column(nullable = false)
    private LocalDate endDate;

    @NotNull
    private LocalDate trackedTime;
    }

