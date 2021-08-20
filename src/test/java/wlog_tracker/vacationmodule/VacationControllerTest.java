package wlog_tracker.vacationmodule;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import wlog_tracker.usermodule.controllers.UserController;
import wlog_tracker.vacationmodule.controllers.VacationController;
import wlog_tracker.vacationmodule.model.Vacation;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = VacationController.class)
public class VacationControllerTest {
    private ObjectMapper mapper = new ObjectMapper();
    private Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a z").create();

    @MockBean
    private UserController userController;

    @MockBean
    private VacationController vacationController;

    @Autowired
    private MockMvc mock;

//    @SneakyThrows
//    @Test
//    @DisplayName("GET all vacations")
//    public void getAll() {
//        Vacation completeVacation = VacationGenerator.createCompleteVacation();
//        Vacation completeAdditionalVacation = VacationGenerator.createCompleteVacation();
//
//        completeVacation.setId(1L);
//        completeAdditionalVacation.setId(2L);
//
//        List<Vacation> vacations = Arrays.asList(completeVacation, completeAdditionalVacation);
//
//        Mockito.when(vacationController.getAll()).thenReturn(vacations);
//
//        String contentAsString = mock.perform(get("/vacation/all"))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse().getContentAsString();
//
//        List<Vacation> resultVacations = mapper.readValue(contentAsString, new TypeReference<>() {
//        });
//
//        Assert.assertEquals(completeVacation.getId(), resultVacations.get(0).getId());
//        Assert.assertEquals(completeAdditionalVacation.getId(), resultVacations.get(1).getId());
//    }
//
//    @SneakyThrows
//    @Test
//    @DisplayName("GET complete Vacations")
//    public void getCompleteVacationsByUserId() {
//        Vacation completeVacation = VacationGenerator.createCompleteVacation();
//        Vacation anotherCompleteVacation = VacationGenerator.createAnotherCompleteVacation();
//
//        List<Vacation> vacations = Arrays.asList(completeVacation, anotherCompleteVacation);
//        Mockito.when(vacationController.getByUserId(Mockito.anyLong())).thenReturn(vacations);
//
//        String contentAsString = mock.perform(get("/vacation/1"))
//                .andReturn().getResponse().getContentAsString();
//
//        List<Vacation> resultVacations = mapper.readValue(contentAsString, new TypeReference<>() {
//        });
//
//        Assert.assertEquals(completeVacation.getStartDate(), resultVacations.get(0).getStartDate());
//        Assert.assertEquals(completeVacation.getEndDate(), resultVacations.get(0).getEndDate());
//        Assert.assertEquals(anotherCompleteVacation.getStartDate(), resultVacations.get(1).getStartDate());
//        Assert.assertEquals(anotherCompleteVacation.getEndDate(), resultVacations.get(1).getEndDate());
//    }
//
//    @SneakyThrows
//    @Test
//    @DisplayName("GET blank Vacation")
//    public void getBlankVacationByUserId() {
//        mock.perform(get("/vacation")).andReturn().getResponse().getContentAsString();
//
//        Mockito.when(vacationController.getByUserId(Mockito.anyLong())).thenReturn(List.of());
//
//        int contentLength = mock.perform(get("/vacation/1"))
//                .andReturn().getResponse().getContentLength();
//
//        Assert.assertEquals(contentLength, 0);
//    }
//
//    @SneakyThrows
//    @Test
//    @DisplayName("POST, save vacation in DB and link it to User")
//    public void saveCompleteVacation() {
//        Vacation completeVacation = VacationGenerator.createAnotherCompleteVacation();
//        String json = gson.toJson(completeVacation);
//
//        Mockito.when(vacationController.save(Mockito.any(Vacation.class), Mockito.anyLong()))
//                .thenReturn(completeVacation);
//
//        String contentAsString = mock.perform(post("/vacation/1")
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .content(json))
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn().getResponse().getContentAsString();
//
//        Vacation result = mapper.readValue(contentAsString, Vacation.class);
//
//        Assert.assertEquals(completeVacation.getStartDate(), result.getStartDate());
//        Assert.assertEquals(completeVacation.getEndDate(), result.getEndDate());
//    }
//
//    @SneakyThrows
//    @Test
//    @DisplayName("POST, save blank vacation in DB")
//    public void saveBlankVacation() {
//        Vacation blankVacation = VacationGenerator.createBlankVacation();
//        String json = gson.toJson(blankVacation);
//
//        Mockito.when(vacationController.save(Mockito.nullable(Vacation.class), Mockito.anyLong()))
//                .thenReturn(null);
//
//        int contentLength = mock.perform(post("/vacation/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(json))
//                .andReturn().getResponse().getContentLength();
//
//        Assert.assertEquals(contentLength, 0);
//    }
//
//    @SneakyThrows
//    @Test
//    @DisplayName("UPDATE complete project with proper values")
//    public void updateCompleteProjectWithProperValues() {
//        Vacation anotherCompleteVacation = VacationGenerator.createAnotherCompleteVacation();
//        String json = gson.toJson(anotherCompleteVacation);
//
//        Mockito.when(vacationController.update(Mockito.any(Vacation.class), Mockito.anyLong()))
//                .thenReturn(anotherCompleteVacation);
//
//        String contentAsString = mock.perform(put("/vacation/1")
//                        .content(json)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse().getContentAsString();
//
//        Vacation result = mapper.readValue(contentAsString, Vacation.class);
//
//        Assert.assertEquals(anotherCompleteVacation.getStartDate(), result.getStartDate());
//        Assert.assertEquals(anotherCompleteVacation.getEndDate(), result.getEndDate());
//    }
//
//    @SneakyThrows
//    @Test
//    @DisplayName("UPDATE complete project with empty values")
//    public void updateCompleteProjectWithEmptyValues() {
//        Vacation blankVacation = VacationGenerator.createBlankVacation();
//        String json = gson.toJson(blankVacation);
//
//        Mockito.when(vacationController.update(Mockito.nullable(Vacation.class), Mockito.anyLong()))
//                .thenReturn(null);
//
//        int contentLength = mock.perform(put("/vacation/1")
//                        .content(json)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse().getContentLength();
//
//        Assert.assertEquals(contentLength, 0);
//    }
//
//    @SneakyThrows
//    @Test
//    @DisplayName("DELETE existing vacation")
//    public void deleteExistingVacation() {
//        Vacation completeVacation = VacationGenerator.createCompleteVacation();
//        String json = gson.toJson(completeVacation);
//
//        Mockito.when(vacationController.delete(Mockito.anyLong())).thenReturn(completeVacation);
//
//        String contentAsString = mock.perform(delete("/vacation/1")
//                        .content(json)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse().getContentAsString();
//
//        Vacation result = mapper.readValue(contentAsString, Vacation.class);
//
//        Assert.assertEquals(completeVacation.getStartDate(), result.getStartDate());
//        Assert.assertEquals(completeVacation.getEndDate(), result.getEndDate());
//    }
//
//    @SneakyThrows
//    @Test
//    @DisplayName("DELETE not existing vacation")
//    public void deleteNotExistingVacation() {
//        Vacation completeVacation = VacationGenerator.createCompleteVacation();
//        String json = gson.toJson(completeVacation);
//
//        Mockito.when(vacationController.delete(Mockito.anyLong())).thenReturn(null);
//
//        int contentLength = mock.perform(delete("/vacation/1")
//                        .content(json)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse().getContentLength();
//
//        Assert.assertEquals(contentLength, 0);
//    }
}
