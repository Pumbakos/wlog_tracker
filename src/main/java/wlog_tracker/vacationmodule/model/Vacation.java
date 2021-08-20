package wlog_tracker.vacationmodule.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import wlog_tracker.usermodule.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="vacations")
@Getter
@Setter
@NoArgsConstructor
public class Vacation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="VacationId", columnDefinition="int")
    @ToString.Exclude
    private Long id;

    public Vacation(Date startDate, Date endDate, VacationType vacationType) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.vacationType = vacationType;
        this.isAccepted = false;
    }

    @NotNull
    @Column(nullable = false, name="StartDate")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private Date startDate;

    @NotNull
    @Column(nullable = false, name="EndDate")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private Date endDate;

    @NotNull
    @Column(nullable = false, name="VacationType")
    @Enumerated(EnumType.STRING)
    private VacationType vacationType;

    @NotNull
    @Column(nullable = false, name="IsAccepted")
    private boolean isAccepted;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="EmployeeId_fk")
    private User user;
}
