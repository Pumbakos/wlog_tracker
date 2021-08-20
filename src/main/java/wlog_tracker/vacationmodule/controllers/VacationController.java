package wlog_tracker.vacationmodule.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wlog_tracker.vacationmodule.model.Vacation;
import wlog_tracker.vacationmodule.service.VacationService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:8081"})
@RestController
@RequestMapping("/vacation")
public class VacationController {
    @Autowired
    private VacationService service;

    @GetMapping("/all")
    public List<Vacation> getAll() {
        return service.getAll();
    }

    @GetMapping("/{userId}")
    public List<Vacation> getByUserId(@Valid @PathVariable(name = "userId") Long userId) {
        return service.getByUserId(userId);
    }

    @PostMapping("/{userId}")
    public Vacation save(@Valid @RequestBody Vacation vacation, @PathVariable(name = "userId") Long userId) {
        return service.save(vacation, userId);
    }

    @PutMapping("/{id}")
    public Vacation update(@RequestBody Vacation vacation, @PathVariable(name = "id") Long id) {
        return service.update(vacation, id);
    }

    @DeleteMapping("/{id}")
    public Vacation delete(@PathVariable(name = "id") Long id) {
        return service.delete(id);
    }
}
