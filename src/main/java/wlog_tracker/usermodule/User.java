package wlog_tracker.usermodule;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    public String name;

    @NotBlank
    @Column(nullable = false)
    private String surname;

    @Basic
    private String imageUrl;

    @NotNull
    @Column(nullable = false)
    private Long pesel;

    @NotBlank
    @Column(nullable = false)
    private String title;

    public User() {
    }

    public boolean copyProperties(User user) {
        boolean allowUpdate = false;

        if (user.getName() != null && !user.getName().isBlank()) {
            name = user.getName();
            allowUpdate = true;
        }

        if (user.getSurname() != null && !user.getSurname().isBlank()) {
            surname = user.getSurname();
            allowUpdate = true;
        }

        if (user.getPesel() != null) {
            pesel = user.getPesel();
            allowUpdate = true;
        }

        if (user.getTitle() != null && !user.getTitle().isBlank()) {
            title = user.getTitle();
            allowUpdate = true;
        }

        if (user.getImageUrl() != null && !user.getImageUrl().isBlank()) {
            imageUrl = user.getImageUrl();
            allowUpdate = true;
        }

        return allowUpdate;
    }
}
