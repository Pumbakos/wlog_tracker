package wlog_tracker.vacationmodule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wlog_tracker.usermodule.UserRepository;
import wlog_tracker.usermodule.model.User;
import wlog_tracker.vacationmodule.VacationRepository;
import wlog_tracker.vacationmodule.model.Vacation;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@Service
public class VacationService {
    @Autowired
    private final VacationRepository repository;

    @Autowired
    private final UserRepository userRepository;

    public VacationService(VacationRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public List<Vacation> getAll() {
        return repository.findAll();
    }

    public List<Vacation> getByUserId(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.map(User::getVacations).orElse(null);
    }

    public Vacation save(Vacation vacation, Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            vacation.setUser(user);
            return repository.save(vacation);
        }
        return null;
    }

    public Vacation update(Vacation vacation, Long id) {
        Optional<Vacation> optionalVacation = repository.findById(id);
        if (optionalVacation.isPresent()) {
            Vacation vacationToUpdate = optionalVacation.get();

            Field[] fields = Vacation.class.getDeclaredFields();

            for (Field field : fields) {
                try {
                    if (field.getName().equalsIgnoreCase("id"))
                        continue;

                    Field taskToUpdateField = vacationToUpdate.getClass().getDeclaredField(field.getName());
                    Field taskField = vacation.getClass().getDeclaredField(field.getName());

                    taskToUpdateField.setAccessible(true);
                    taskField.setAccessible(true);

                    taskToUpdateField.set(vacationToUpdate, taskField.get(vacation));

                    taskToUpdateField.setAccessible(false);
                    taskField.setAccessible(false);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    return null;
                }
            }

            return repository.save(vacationToUpdate);
        }
        return null;
    }

    public Vacation delete(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Vacation lastVacation = user.removeLastVacation();
            repository.delete(lastVacation);
            return lastVacation;
        }
        return null;
    }
}
