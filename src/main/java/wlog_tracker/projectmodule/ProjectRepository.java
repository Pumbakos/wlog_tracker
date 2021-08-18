package wlog_tracker.projectmodule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import wlog_tracker.projectmodule.model.Project;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query(value = "SELECT * FROM Project", nativeQuery = true)
    List<Project> findAll();
}
