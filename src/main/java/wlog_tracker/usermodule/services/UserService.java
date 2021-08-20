package wlog_tracker.usermodule.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wlog_tracker.usermodule.UserRepository;
import wlog_tracker.usermodule.model.User;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public User get(Long id) {
        return repository.findById(id).orElse(null);
    }

    public User save(User user) {
        return repository.save(user);
    }

    public User update(User user, Long id) {
        Optional<User> userToGet = repository.findById(id);
        if (userToGet.isPresent()) {
            User userToUpdate = userToGet.get();

            userToUpdate.setName(user.getName());
            userToUpdate.setSurname(user.getSurname());
            userToUpdate.setPesel(user.getPesel());
            userToUpdate.setImage(user.getImage());
            userToUpdate.setTitle(user.getTitle());

            return repository.save(userToUpdate);
        }
        return null;
    }

    public User delete(Long id) {
        Optional<User> user = repository.findById(id);
        if (user.isPresent()) {
            User ret = user.get();
            repository.delete(ret);
            return ret;
        }
        return null;
    }
}
