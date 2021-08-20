package wlog_tracker.vacationmodule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import wlog_tracker.vacationmodule.model.Vacation;

import java.util.List;

@Repository
public interface VacationRepository extends JpaRepository<Vacation, Long> {
    @Query(value = "SELECT v FROM Vacation v")
    List<Vacation> findAll();

    @Query(value = "SELECT v FROM Vacation v WHERE v.id = :userId")
    List<Vacation> findAllByUserId(@Param(value = "userId") Long userId);
}
