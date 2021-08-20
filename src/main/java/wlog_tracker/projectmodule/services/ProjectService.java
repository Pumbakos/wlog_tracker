package wlog_tracker.projectmodule.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wlog_tracker.projectmodule.ProjectRepository;
import wlog_tracker.projectmodule.model.Project;
import wlog_tracker.taskmodule.TaskRepository;
import wlog_tracker.taskmodule.model.Task;
import wlog_tracker.usermodule.UserRepository;
import wlog_tracker.usermodule.model.User;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    private final ProjectRepository repository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final TaskRepository taskRepository;

    public ProjectService(ProjectRepository repository, UserRepository userRepository, TaskRepository taskRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    public List<Project> getAll() {
        return repository.findAll();
    }

    public Project get(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Project save(Project project) {
//        List<User> temp = new ArrayList<>();
//        for (User assignedEmployee : project.getAssignedEmployees()) {
//            Optional<User> byPesel = userRepository.findByPesel(assignedEmployee.getPesel());
//            if (byPesel.isEmpty()){
//                User save = userRepository.save(assignedEmployee);
//                temp.add(save);
//            }
//        }
//        if (!temp.isEmpty())
//            project.setAssignedEmployees(temp);
        userRepository.saveAll(project.getAssignedEmployees());


        for (Task task : project.getTaskList()) {
            taskRepository.save(task);
        }

        return repository.save(project);
    }

    public Project update(Project project, Long id) {
        List<User> assignedEmployees = project.getAssignedEmployees();
        List<Task> taskList = project.getTaskList();

        userRepository.saveAll(assignedEmployees);
        taskRepository.saveAll(taskList);

        Optional<Project> projectToGet = repository.findById(id);
        if (projectToGet.isPresent()) {
            Project projectToUpdate = projectToGet.get();

            Field[] fields = Project.class.getDeclaredFields();

            for (Field field : fields) {
                try {
                    if(field.getName().equalsIgnoreCase("id"))
                        continue;

                    Field projectToUpdateField = projectToUpdate.getClass()
                            .getDeclaredField(field.getName());
                    Field projectField = project.getClass().getDeclaredField(field.getName());

                    projectToUpdateField.setAccessible(true);
                    projectField.setAccessible(true);

                    projectToUpdateField.set(projectToUpdate, projectField.get(project));

                    projectToUpdateField.setAccessible(false);
                    projectField.setAccessible(false);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    return null;
                }
            }
            return repository.save(projectToUpdate);
        }
        return null;

    }

    public Project delete(Long id) {
        Optional<Project> projectToGet = repository.findById(id);
        if (projectToGet.isPresent()) {
            Project project = projectToGet.get();
            repository.delete(project);
            return project;
        }
        return null;
    }
}


