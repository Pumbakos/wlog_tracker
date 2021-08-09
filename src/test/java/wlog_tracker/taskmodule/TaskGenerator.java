package wlog_tracker.taskmodule;

import lombok.NoArgsConstructor;
import wlog_tracker.TaskModule.Priority;
import wlog_tracker.TaskModule.Task;

import java.time.LocalDate;

@NoArgsConstructor
public class TaskGenerator {
    public Task task;

    Task createTask(String name, Priority priority,String description,
                    LocalDate dueDate, LocalDate startDate, LocalDate endDate){
        task.setName(name);
        task.setPriority(priority);
        task.setDescription(description);
        task.setDueDate(dueDate);
        task.setStartDate(startDate);
        task.setEndDate(endDate);

        return task;
    }

    Task createCompleteTaskLow(){
        task.setName("Zakupy");
        task.setPriority(Priority.LOW);
        task.setDescription("Kupienie ananasa");
        task.setDueDate(LocalDate.of(2021, 8, 11));
        task.setStartDate(LocalDate.of(2021, 8, 9));
        task.setEndDate(LocalDate.of(2021,8,12));
        return task;
    }
    Task createCompleteTaskHigh(){
        task.setName("Zabieg");
        task.setPriority(Priority.HIGH);
        task.setDescription("Operacja źrenicy");
        task.setDueDate(LocalDate.of(2021, 8, 12));
        task.setStartDate(LocalDate.of(2021, 8, 10));
        task.setEndDate(LocalDate.of(2021,8,13));
        return task;
    }
}
