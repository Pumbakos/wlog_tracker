package wlog_tracker.taskmodule;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TaskGenerator {
    StringToDate stringToDate = new StringToDate();

    Task createTask(Task taskForExample){
        Task task = new Task();
        task.setId(taskForExample.getId());
        task.setName(taskForExample.getName());
        task.setPriority(taskForExample.getPriority());
        task.setDescription(taskForExample.getDescription());
        task.setDueDate(taskForExample.getDueDate());
        task.setStartDate(taskForExample.getStartDate());
        task.setEndDate(taskForExample.getEndDate());
        return task;
    }

    Task createCompleteTaskLow(){
        Task task = new Task();
        task.setName("Zakupy");
        task.setPriority(Priority.LOW);
        task.setDescription("Kupienie ananasa");
        task.setStartDate(stringToDate.StringToDate("2021-08-11 12:00:00"));
        task.setEndDate(stringToDate.StringToDate("2021-08-13 12:00:00"));
        task.setDueDate(stringToDate.StringToDate("2021-08-15 12:00:00"));
        return task;
    }
    Task createCompleteTaskHigh(){
        Task task = new Task();
        task.setName("Zabieg");
        task.setPriority(Priority.HIGH);
        task.setDescription("Operacja zrenicy");
        task.setStartDate(stringToDate.StringToDate("2021-08-11 12:00:00"));
        task.setEndDate(stringToDate.StringToDate("2021-08-13 12:00:00"));
        task.setDueDate(stringToDate.StringToDate("2021-08-16 12:00:00"));
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
