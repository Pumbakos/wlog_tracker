package wlog_tracker.usermodule.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wlog_tracker.usermodule.UserRepository;
import wlog_tracker.usermodule.model.User;
import wlog_tracker.usermodule.services.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:8081/"})
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User get(@PathVariable(name = "id") Long id) {
        return userService.get(id);
    }

    @PostMapping
    public User save(@Valid @RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping("/{id}")
    public User update(@RequestBody User user, @PathVariable(name = "id") Long id) {
        return userService.update(user, id);
    }

    @DeleteMapping("/{id}")
    public User delete(@PathVariable(name = "id") Long id) {
      return userService.delete(id);
    }
}