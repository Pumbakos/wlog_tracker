package wlog_tracker.taskmodule;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.jayway.jsonpath.spi.json.GsonJsonProvider;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import wlog_tracker.TaskModule.Priority;
import wlog_tracker.TaskModule.Task;
import wlog_tracker.TaskModule.TaskController;

import java.time.LocalDate;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;

@WebMvcTest(controllers = {TaskController.class})
public abstract class TaskControllerTest {
    private GsonJsonProvider gsonJsonProvider = new GsonJsonProvider(new Gson());
    private ObjectMapper mapper = new ObjectMapper();

    @MockBean
    public TaskController taskController;
    @Autowired
    public MockMvc mockMvc;

    @Before
    public Task setupTask() {
        Task task = new Task();
        task.setName("Zakupy");
        task.setDescription("Kupienie ananasa");
        task.setPriority(Priority.HIGH);
        task.setStartDate(LocalDate.of(2021, 8, 9));
        task.setDueDate(LocalDate.of(2021, 8, 12));
        task.setEndDate(LocalDate.of(2021, 8, 11));

        return task;
    }

    @SneakyThrows
    @Test
    public void getTask() {
        Task task = setupTask();
        String json = gsonJsonProvider.toJson(task);


        Mockito.when(taskController.getTask(Mockito.anyLong())).thenReturn(task);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/task/1")
                        .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect((ResultMatcher) content().json(json));

        Task taskRes = mapper.readValue(resultActions.andReturn().
                getResponse().getContentAsString(), Task.class);

        Assert.assertEquals(task.getName(), taskRes.getName());
        Assert.assertEquals(task.getDescription(), taskRes.getDescription());

    }

    @Test
    @SneakyThrows
    public void saveTaskId() {
        Task saveTask = new Task();
        saveTask.setName("Zakupy");
        saveTask.setDescription("Kupienie ananasa");
        saveTask.setPriority(Priority.HIGH);

        GsonJsonProvider gsonJsonProvider = new GsonJsonProvider(new Gson());
        String gson = gsonJsonProvider.toJson(saveTask);

        Mockito.when(taskController.addTask(Mockito.any(Task.class))).thenReturn(saveTask);
        mockMvc.perform(MockMvcRequestBuilders.post("/task")
                .contentType(MediaType.APPLICATION_JSON));

    }

}

