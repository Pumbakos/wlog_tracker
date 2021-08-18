package wlog_tracker.projectmodule.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wlog_tracker.projectmodule.services.ProjectService;
import wlog_tracker.projectmodule.model.Project;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/project")
@CrossOrigin(origins = {"http://localhost:8081/"})
public class ProjectController {
    @Autowired
    private final ProjectService projectService;

    public ProjectController( ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/all")
    public List<Project> getAll() {
        return projectService.getAll();
    }

    @GetMapping("/{id}")
    public Project get(@PathVariable(name = "id") Long id) {
        return projectService.get(id);
    }

    @PostMapping
    public Project save(@Valid @RequestBody Project project) {
        return projectService.save(project);
    }

    @PutMapping("/{id}")
    public Project update(@RequestBody Project project, @PathVariable(name = "id") Long id) {
        return projectService.update(project, id);
    }

    @DeleteMapping("/{id}")
    public Project delete(@PathVariable(name = "id") Long id) {
        return projectService.delete(id);
    }
}