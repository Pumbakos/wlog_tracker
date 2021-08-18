package wlog_tracker.projectmodule;

import lombok.SneakyThrows;
import wlog_tracker.projectmodule.model.Project;
import wlog_tracker.resources.DateFormat;
import wlog_tracker.usermodule.UserGenerator;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ProjectGenerator {
    static SimpleDateFormat dateFormat = new SimpleDateFormat(DateFormat.DATE_PATTERN);

    public static Project createProjectForTests(Project emptyProject){
        Project project = new Project();
        project.setName(emptyProject.getName());
        project.setDescription(emptyProject.getDescription());
        project.setProjectManagerId(emptyProject.getProjectManagerId());
        project.setTaskList(emptyProject.getTaskList());
        project.setClient(emptyProject.getClient());
        project.setAssignedEmployees(emptyProject.getAssignedEmployees());
        project.setStartDate(emptyProject.getStartDate());
        project.setEndDate(emptyProject.getEndDate());
        return project;
    }
    public static Project createProject (String name, String description, Long ProjectManagerId,
                           List taskList, String client, List assignedEmployees, Date startDate,
                           Date endDate) {
        Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        project.setProjectManagerId(ProjectManagerId);
        project.setTaskList(taskList);
        project.setClient(client);
        project.setAssignedEmployees(assignedEmployees);
        project.setStartDate(startDate);
        project.setEndDate(endDate);
        return project;
    }

    @SneakyThrows
    public static Project createCompleteProject(){
        Project project = new Project();
        project.setId(2L);
        project.setName("Akwarium");
        project.setDescription("Zakup akwarium do domu");
        project.setProjectManagerId(8L);
        project.setClient("Idaaran");
        project.setAssignedEmployees(
                Arrays.asList(
                        UserGenerator.createCompleteUser(),
                        UserGenerator.createCompleteUserWithoutImageUrl()
                )
        );
        project.setStartDate(dateFormat.parse("Aug 16, 2021, 12:00:00 PM UTC"));
        project.setEndDate(dateFormat.parse("Aug 18, 2021, 12:00:00 PM UTC"));
        return project;
    }

    @SneakyThrows
    public static Project createProjectWithoutClient(){
        Project project = new Project();
        project.setId(4L);
        project.setName("Fender");
        project.setDescription("Wymiana podstrunnicy w Stractocasterze");
        project.setProjectManagerId(5L);
        project.setAssignedEmployees(
                Arrays.asList(
                        UserGenerator.createCompleteUser(),
                        UserGenerator.createCompleteUserWithoutImageUrl()
                )
        );
        project.setStartDate(dateFormat.parse("Aug 17, 2021 12:00:00 PM UTC"));
        project.setEndDate(dateFormat.parse("Aug 19, 2021 12:00:00 PM UTC"));
        return project;
    }
    public static Project createBlankProject(){
        return new Project();
    }

    public static Project createEmptyProject(){
        Project project = new Project();
        project.setName("");
        project.setDescription("");
        return project;
    }
}
