package wlog_tracker.TaskModule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public List<Task> getTasks(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable(name = "id") Long id) {
        Optional<Task> byId = repository.findById(id);
        return byId.orElse(null);
    }

    @PostMapping
    public Task addTask(@Valid @RequestBody Task task){
        return repository.save(task);
    }

    @PutMapping("/{id}}")
    public HttpStatus updateTask(@RequestBody Task task, @PathVariable(name = "id") Long id){
        Optional<Task> taskToGet = repository.findById(id);
        if (taskToGet.isPresent()){
            Task taskToUpdate = taskToGet.get();
            repository.save(taskToUpdate);

            return HttpStatus.OK;
        }
        return HttpStatus.NOT_ACCEPTABLE;
    }
}
