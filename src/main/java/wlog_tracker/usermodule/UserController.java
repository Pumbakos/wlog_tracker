package wlog_tracker.usermodule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository repository;

    @Autowired
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/all")
    public List<User> getUsers() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable(name = "id") Long id) {
        return repository.findById(id);
    }

    @PostMapping
    public HttpStatus addUser(@Valid @RequestBody User user) {
        repository.save(user);
        return HttpStatus.OK;
    }

    @PutMapping("/{id}")
    public HttpStatus updateUser(@RequestBody User user, @PathVariable(name = "id") Long id) {
        Optional<User> userToGet = repository.findById(id);
        if (userToGet.isPresent()) {
            User userToUpdate = userToGet.get();

            if (!userToUpdate.copyProperties(user))
                return HttpStatus.BAD_REQUEST;

            repository.save(userToUpdate);

            return HttpStatus.OK;
        }

        repository.save(user);

        return HttpStatus.NOT_ACCEPTABLE;
    }
}
