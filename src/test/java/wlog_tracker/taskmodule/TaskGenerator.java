package wlog_tracker.taskmodule;

import lombok.NoArgsConstructor;
import wlog_tracker.TaskModule.Priority;
import wlog_tracker.TaskModule.Task;

import java.time.Instant;
import java.util.Date;


@NoArgsConstructor
public class TaskGenerator {
    Task createTask(String name, Priority priority, String description,
                    Date dueDate, Date startDate, Date endDate){
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
        task.setStartDate(Date.from(Instant.parse("2021-08-09T12:00:00.00Z")));
        task.setEndDate(Date.from(Instant.parse("2021-08-11T12:00:00.00Z")));
        task.setDueDate(Date.from(Instant.parse("2021-08-10T12:00:00.00Z")));
        return task;
    }
    Task createCompleteTaskHigh(){
        Task task = new Task();
        task.setName("Zabieg");
        task.setPriority(Priority.HIGH);
        task.setDescription("Operacja zrenicy");
        task.setStartDate(Date.from(Instant.parse("2021-08-09T12:00:00.00Z")));
        task.setEndDate(Date.from(Instant.parse("2021-08-11T12:00:00.00Z")));
        task.setDueDate(Date.from(Instant.parse("2021-08-10T12:00:00.00Z")));
        return task;
    }
}
