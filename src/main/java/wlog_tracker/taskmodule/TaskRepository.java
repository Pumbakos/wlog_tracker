package wlog_tracker.taskmodule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import wlog_tracker.taskmodule.model.Task;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository <Task, Long>{
    @Query(value = "SELECT t FROM Task t")
    List<Task> findAll();
}
