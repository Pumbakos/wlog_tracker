package wlog_tracker.projectmodule;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import wlog_tracker.projectmodule.controllers.ProjectController;
import wlog_tracker.projectmodule.model.Project;
import wlog_tracker.resources.DateFormat;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(controllers = ProjectController.class)
public class ProjectControllerTest {
    private Gson gson = new GsonBuilder().setDateFormat(DateFormat.DATE_PATTERN).create();
    private ObjectMapper mapper= new ObjectMapper();

    @MockBean
    private ProjectController projectController;

    @Autowired
    private MockMvc mockMvc;

    @SneakyThrows
    @Test
    public void getAll(){
        Project completeProject = ProjectGenerator.createCompleteProject();
        Project projectWithoutClient = ProjectGenerator.createProjectWithoutClient();

        List<Project> projects = Arrays.asList(completeProject, projectWithoutClient);

        Mockito.when(projectController.getAll()).thenReturn(projects);

        String contentAsString = mockMvc.perform(get("/project/all"))
                .andReturn().getResponse().getContentAsString();

        List<Project> resultProjects = mapper.readValue(contentAsString, new TypeReference<>() {
        });

        Assert.assertEquals(completeProject.getDescription(), resultProjects.get(0).getDescription());
        Assert.assertEquals(projectWithoutClient.getDescription(), resultProjects.get(1).getDescription());
    }

    @SneakyThrows
    @Test
    public void getExistingProject() {
        Project completeProject = ProjectGenerator.createCompleteProject();

        Mockito.when(projectController.get(Mockito.anyLong())).thenReturn(completeProject);

        String content = mockMvc.perform(get("/project/1"))
                .andReturn().getResponse().getContentAsString();

        Project redundantProject = mapper.readValue(content, Project.class);

        Assert.assertEquals(completeProject.getName(), redundantProject.getName());
        Assert.assertEquals(completeProject.getDescription(), redundantProject.getDescription());
    }
    @Test
    @SneakyThrows
    public void postCompleteProject() {
        Project completeProject = new ProjectGenerator().createCompleteProject();
        String json = gson.toJson(completeProject);

        Mockito.when(projectController.save(Mockito.any(Project.class))).thenReturn(completeProject);

        String responseContent = mockMvc.perform(post("/project")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(content().json(json))
                .andReturn().getResponse().getContentAsString();

        Project result = mapper.readValue(responseContent, Project.class);

        Assert.assertEquals(completeProject.getName(), result.getName());
        Assert.assertEquals(completeProject.getDescription(), result.getDescription());
    }

    @Test
    @SneakyThrows
    public void postBlankProject() {
        Project blankProject = ProjectGenerator.createBlankProject();
        String json = gson.toJson(blankProject);

        Mockito.when(projectController.save(Mockito.nullable(Project.class))).thenReturn(null);

        int contentLength = mockMvc.perform(post("/project")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andReturn().getResponse().getContentLength();

        Assert.assertEquals(contentLength, 0);
    }

    @SneakyThrows
    @Test
    public void postEmptyProject() {
        Project saveEmptyProject = ProjectGenerator.createEmptyProject();
        String json = gson.toJson(saveEmptyProject);

        Mockito.when(projectController.save(Mockito.nullable(Project.class))).thenReturn(null);

        MockHttpServletResponse resultActions = mockMvc.perform(post("/project")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andReturn().getResponse();

        Assert.assertEquals(resultActions.getContentLength(), 0);
    }

    @SneakyThrows
    @Test
    public void updateEmptyProject() {
        Project updateEmptyProject = ProjectGenerator.createEmptyProject();
        String response = gson.toJson(updateEmptyProject);

        Mockito.when(projectController.update(Mockito.nullable(Project.class), Mockito.anyLong()))
                .thenReturn(null);

        int contentLength = mockMvc.perform(put("/project/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(response)).andReturn().getResponse().getContentLength();

        Assert.assertEquals(contentLength, 0);
    }

    @SneakyThrows
    @Test
    public void updateCompleteProject() {
        Project completeProject = new ProjectGenerator().createCompleteProject();
        String response = gson.toJson(completeProject);

        Mockito.when(projectController.update(Mockito.any(Project.class), Mockito.anyLong()))
                .thenReturn(completeProject);

        String responseContent = mockMvc.perform(put("/project/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(response))
                .andExpect(content().json(response))
                .andReturn().getResponse().getContentAsString();

        Project result = mapper.readValue(responseContent, Project.class);

        Assert.assertEquals(completeProject.getName(), result.getName());
        Assert.assertEquals(completeProject.getDescription(), result.getDescription());
    }

    @SneakyThrows
    @Test
    public void deleteExistingProject() {
        Project completeProject = new ProjectGenerator().createCompleteProject();

        Mockito.when(projectController.delete(Mockito.anyLong())).thenReturn(completeProject);

        String responseContent = mockMvc.perform(delete("/project/1")
                        .contentType(MediaType.APPLICATION_JSON)).
                andReturn().getResponse().getContentAsString();

        Project response = mapper.readValue(responseContent, Project.class);

        Assert.assertEquals(completeProject.getName(), response.getName());
        Assert.assertEquals(completeProject.getDescription(), response.getDescription());
    }

    @SneakyThrows
    @Test
    public void deleteNoExistingProject() {
        Mockito.when(projectController.delete(Mockito.anyLong())).thenReturn(null);

        int contentLength = mockMvc.perform(delete("/project/1")
                        .contentType(MediaType.APPLICATION_JSON)).
                andReturn().getResponse().getContentLength();

        Assert.assertEquals(contentLength, 0);
    }

}