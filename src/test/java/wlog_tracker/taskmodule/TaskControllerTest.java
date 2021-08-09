package wlog_tracker.taskmodule;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.spi.json.GsonJsonProvider;
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
import org.springframework.test.web.servlet.ResultMatcher;
import wlog_tracker.TaskModule.Priority;
import wlog_tracker.TaskModule.Task;
import wlog_tracker.TaskModule.TaskController;

import java.time.Instant;
import java.util.Date;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = {TaskController.class})
public class TaskControllerTest {
    private GsonJsonProvider gsonJsonProvider = new GsonJsonProvider();
    private ObjectMapper mapper = new ObjectMapper();

    private Task task = new TaskGenerator().createCompleteTaskHigh();
    private String taskJson = gsonJsonProvider.toJson(task);

    @MockBean
    private TaskController taskController;

    @Autowired
    public MockMvc mockMvc;

    @SneakyThrows
    @Test
    public void getTask() {
        Mockito.when(taskController.getTask(Mockito.anyLong())).thenReturn(task);

        String resultActions = mockMvc.perform(get("/task/all")).
                andReturn().getResponse().getContentAsString();

        Task taskRes = mapper.readValue(resultActions, Task.class);

        Assert.assertEquals(task.getName(), taskRes.getName());
        Assert.assertEquals(task.getDescription(), taskRes.getDescription());

    }

    @Test
    @SneakyThrows
    public void saveTask() {
        Mockito.when(taskController.addTask(Mockito.any(Task.class))).thenReturn(task);

        String resultActions = mockMvc.perform(post("/task/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(taskJson))
                .andExpect((ResultMatcher) content().json(taskJson))
                .andReturn().getResponse().getContentAsString();

        Task result = mapper.readValue(resultActions, Task.class);

        Assert.assertEquals(task.getName(), result.getName());
    }

    @Test
    @SneakyThrows
    public void verificationLowTask(){
        Task verification = new TaskGenerator().createTask("Zakupy", Priority.LOW,
                "Odnow subskrypcje", Date.from(Instant.parse("2021-08-09T12:00:00.00Z")),
                Date.from(Instant.parse("2021-08-12T12:00:00.00Z")),
                Date.from(Instant.parse("2021-08-11T12:00:00.00Z")));
        String json =  gsonJsonProvider.toJson(verification);
        Mockito.when(taskController.addTask(Mockito.any(Task.class))).thenReturn(task);
        MockHttpServletResponse responseAction = mockMvc.perform(get(("/task"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andReturn().getResponse();
        Assert.assertEquals(responseAction.getContentLength(), 0);

    }

}

