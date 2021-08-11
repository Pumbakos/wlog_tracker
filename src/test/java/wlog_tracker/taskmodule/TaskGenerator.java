package wlog_tracker.taskmodule;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TaskGenerator {
    StringToDate stringToDate = new StringToDate();

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

    Task createCompleteTaskLow(){
        Task task = new Task();
        task.setId(5L);
        task.setName("Zakupy");
        task.setPriority(Priority.LOW);
        task.setDescription("Kupienie ananasa");
        task.setStartDate(stringToDate.StringToDate("2021-08-11 12:00:00"));
        task.setEndDate(stringToDate.StringToDate("2021-08-13 12:00:00"));
        task.setDueDate(stringToDate.StringToDate("2021-08-15 12:00:00"));
        task.setTrackedTime(stringToDate.StringToDate("2021-08-13 12:00:00"));
        return task;
    }
    Task createCompleteTaskHigh(){
        Task task = new Task();
        task.setId(2L);
        task.setName("Zabieg");
        task.setPriority(Priority.HIGH);
        task.setDescription("Operacja zrenicy");
        task.setStartDate(stringToDate.StringToDate("2021-08-11 12:00:00"));
        task.setEndDate(stringToDate.StringToDate("2021-08-13 12:00:00"));
        task.setDueDate(stringToDate.StringToDate("2021-08-16 12:00:00"));
        task.setTrackedTime(stringToDate.StringToDate("2021-08-13 12:00:00"));
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
