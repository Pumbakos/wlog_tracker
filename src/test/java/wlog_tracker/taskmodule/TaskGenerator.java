package wlog_tracker.taskmodule;

import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;


@NoArgsConstructor
public class TaskGenerator {
    Task createTask(String name, Priority priority, String description,
                    LocalDateTime dueDate, LocalDateTime startDate, LocalDateTime endDate){
        Task task = new Task();
        task.setName(name);
        task.setPriority(priority);
        task.setDescription(description);
        task.setDueDate(dueDate);
        task.setStartDate(startDate);
        task.setEndDate(endDate);
        return task;
    }

    Task createCompleteTaskLow(){
        Task task = new Task();
        task.setName("Zakupy");
        task.setPriority(Priority.LOW);
        task.setDescription("Kupienie ananasa");
        task.setStartDate(LocalDateTime.parse("2021-08-09T12:00:00.00Z"));
        task.setEndDate(LocalDateTime.parse("2021-08-11T12:00:00.00Z"));
        task.setDueDate(LocalDateTime.parse("2021-08-10T12:00:00.00Z"));
        return task;
    }
    Task createCompleteTaskHigh(){
        Task task = new Task();
        task.setName("Zabieg");
        task.setPriority(Priority.HIGH);
        task.setDescription("Operacja zrenicy");
        task.setStartDate(LocalDateTime.parse("2021-08-09T12:00:00.00Z"));
        task.setEndDate(LocalDateTime.parse("2021-08-11T12:00:00.00Z"));
        task.setDueDate(LocalDateTime.parse("2021-08-10T12:00:00.00Z"));
        return task;
    }

    Task createBlankTask(){
        return new Task();
    }

    Task createEmptyTask(){
        Task task = new Task();
        task.setName("");
        task.setDescription("");
        return  task;
    }
}
