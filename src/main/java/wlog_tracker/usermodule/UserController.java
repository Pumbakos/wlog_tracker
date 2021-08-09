package wlog_tracker.usermodule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository repository;

    @Autowired
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/all")
    public List<User> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public User get(@PathVariable(name = "id") Long id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping
    public User save(@Valid @RequestBody User user) {
        return repository.save(user);
    }

    @PutMapping("/{id}")
    public User update(@RequestBody User user, @PathVariable(name = "id") Long id) {
        Optional<User> userToGet = repository.findById(id);
        if (userToGet.isPresent()) {
            User userToUpdate = userToGet.get();

            userToUpdate.setName(user.getName());
            userToUpdate.setSurname(user.getSurname());
            userToUpdate.setPesel(user.getPesel());
            userToUpdate.setImageUrl(user.getImageUrl());
            userToUpdate.setTitle(user.getTitle());

            return repository.save(userToUpdate);
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public User delete(@PathVariable(name = "id") Long id){
        Optional<User> user = repository.findById(id);
        if (user.isPresent()){
            User ret = user.get();
            repository.delete(ret);
            return ret;
        }

        return null;
    }
}