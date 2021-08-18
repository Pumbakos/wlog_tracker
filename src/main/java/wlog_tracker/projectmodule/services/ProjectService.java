package wlog_tracker.projectmodule.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wlog_tracker.projectmodule.ProjectRepository;
import wlog_tracker.projectmodule.model.Project;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private final ProjectRepository repository;

    public ProjectService(ProjectService repository) {
        this.repository = (ProjectRepository) repository;
    }

    public List<Project> getAll() {
        return repository.findAll();
    }

    public Project get(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Project save(Project project) {
        return repository.save(project);
    }

    public Project update(Project project, Long id) {
        Optional<Project> projectToGet = repository.findById(id);
        if (projectToGet.isPresent()) {
            Project projectToUpdate = projectToGet.get();

            Field[] fields = Project.class.getDeclaredFields();

            for (Field field : fields) {
                try {
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
            if (projectToGet.isPresent()){
                Project project = projectToGet.get();
                repository.delete(project);
                return project;
            }
            return null;
        }
    }


