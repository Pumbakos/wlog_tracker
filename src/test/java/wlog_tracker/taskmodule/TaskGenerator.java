package wlog_tracker.taskmodule;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TaskGenerator {
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

    Task createTask(){
        Task task = new Task();
        task.setId(task.getId());
        task.setName(task.getName());
        task.setPriority(task.getPriority());
        task.setDescription(task.getDescription());
        task.setDueDate(task.getDueDate());
        task.setStartDate(task.getStartDate());
        task.setEndDate(task.getEndDate());
        return task;
    }

    Task createCompleteTaskLow() throws ParseException{
        Task task = new Task();
        task.setId(5L);
        task.setName("Zakupy");
        task.setPriority(Priority.LOW);
        task.setDescription("Kupienie ananasa");
        task.setStartDate(dateFormat.parse("2021-08-11T12:00:00.000 UTC"));
        task.setEndDate(dateFormat.parse("2021-08-13T12:00:00.000 UTC"));
        task.setDueDate(dateFormat.parse("2021-08-13T12:00:00.000 UTC"));
        task.setTrackedTime(dateFormat.parse("2021-08-13T12:00:00.000 UTC"));
        return task;
    }
    Task createCompleteTaskHigh() throws ParseException{
        Task task = new Task();
        task.setId(2L);
        task.setName("Zabieg");
        task.setPriority(Priority.HIGH);
        task.setDescription("Operacja zrenicy");
        task.setStartDate(dateFormat.parse("2021-08-11T12:00:00.000 UTC"));
        task.setEndDate(dateFormat.parse("2021-08-13T12:00:00.000 UTC"));
        task.setDueDate(dateFormat.parse("2021-08-16T12:00:00.000 UTC"));
        task.setTrackedTime(dateFormat.parse("2021-08-13T12:00:00.000 UTC"));
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
