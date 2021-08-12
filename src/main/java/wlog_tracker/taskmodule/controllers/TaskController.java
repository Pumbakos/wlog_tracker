package wlog_tracker.taskmodule.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import wlog_tracker.taskmodule.model.Task;
import wlog_tracker.taskmodule.services.TaskService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<Task> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Task get(@PathVariable(name = "id") Long id) {
        return service.get(id);
    }

    @PostMapping
    public Task save(@Valid @RequestBody Task task) {
        return service.save(task);
    }

    @PutMapping("/{id}}")
    public Task update(@RequestBody Task task, @PathVariable(name = "id") Long id) {
        return service.update(task, id);
    }

    @DeleteMapping("/{id}")
    public Task delete(@PathVariable(name = "id") Long id){
        return service.delete(id);
    }

}
