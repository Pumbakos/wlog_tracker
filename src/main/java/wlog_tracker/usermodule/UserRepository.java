package wlog_tracker.usermodule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import wlog_tracker.projectmodule.model.Project;
import wlog_tracker.usermodule.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM User", nativeQuery = true)
    List<User> findAll();

    @Query(value = "SELECT * FROM User WHERE pesel = :pesel", nativeQuery = true)
    Optional<User> findByPesel(@Param(value = "pesel") String pesel);
}
