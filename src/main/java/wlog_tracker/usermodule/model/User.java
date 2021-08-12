package wlog_tracker.usermodule.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter@Setter
@ToString(exclude = {"id"})
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Imię jest wymagane")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Nazwisko jest wymagane")
    @Column(nullable = false)
    private String surname;

    @Basic
    private String imageUrl;

    @Size(min = 11, max = 11)
    @NotNull(message = "PESEL jest wymagany")
    @Column(nullable = false)
    private String pesel;

    @NotNull(message = "Tytuł jest wymagany")
    @Column(nullable = false)
    private Title title;
}