package wlog_tracker.taskmodule.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wlog_tracker.taskmodule.TaskRepository;
import wlog_tracker.taskmodule.model.Task;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> getAll(){
        return repository.findAll();
    }

    public Task get(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Task save(Task task){
        return repository.save(task);
    }

    public Task update(Task task, Long id){
        Optional<Task> taskToGet = repository.findById(id);
        if (taskToGet.isPresent()){
            Task taskToUpdate = taskToGet.get();

            Field[] fields = Task.class.getDeclaredFields();

            for (Field field : fields) {
                try {
                    if (field.getName().equals("id")){
                        continue;
                    }

                    Field taskToUpdateField = taskToUpdate.getClass().getDeclaredField(field.getName());
                    Field taskField = task.getClass().getDeclaredField(field.getName());

                    taskToUpdateField.setAccessible(true);
                    taskField.setAccessible(true);

                    taskToUpdateField.set(taskToUpdate, taskField.get(task));

                    taskToUpdateField.setAccessible(false);
                    taskField.setAccessible(false);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    return null;
                }
            }

            return repository.save(taskToUpdate);
        }
        return null;
    }

    public Task delete(Long id) {
        Optional<Task> taskToGet = repository.findById(id);
        if (taskToGet.isPresent()){
            Task task = taskToGet.get();
            repository.delete(task);
            return task;
        }
        return null;
    }
}
