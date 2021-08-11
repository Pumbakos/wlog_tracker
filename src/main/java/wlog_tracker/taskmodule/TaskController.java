package wlog_tracker.taskmodule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/task")
public class TaskController {
    private final TaskRepository repository;

    @Autowired
    public TaskController(TaskRepository repository){
        this.repository = repository;
    }

    @GetMapping("/all")
    public List<Task> getAll(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Task get(@PathVariable(name = "id") Long id) {
        Optional<Task> byId = repository.findById(id);
        return byId.orElse(null);
    }

    @PostMapping
    public Task save(@Valid @RequestBody Task task){
        return repository.save(task);
    }

    @PutMapping("/{id}")
    public Task update(@RequestBody Task task, @PathVariable(name = "id") Long id){
        Optional<Task> taskToGet = repository.findById(id);
        if (taskToGet.isPresent()){
            Task taskToUpdate = taskToGet.get();

            taskToUpdate.setName(task.getName());
            taskToUpdate.setDescription(task.getDescription());
            taskToUpdate.setPriority(task.getPriority());
            taskToUpdate.setStartDate(task.getStartDate());
            taskToUpdate.setEndDate(task.getEndDate());
            taskToUpdate.setDueDate(task.getDueDate());

            return repository.save(taskToUpdate);
        }
        return null;
    }
}
