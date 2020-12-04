package ru.bellintegrator.practice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.bellintegrator.practice.dto.EmployeeDto;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void shouldKeepEmployee() throws Exception{

        this.mockMvc.perform(post("/api/user/save")
                .content(objectMapper.writeValueAsString(createEmployeeSaveData()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("success"));
    }

    @Test
    public void shouldUpdateEmployee() throws Exception{

        this.mockMvc.perform(post("/api/user/update")
                .content(objectMapper.writeValueAsString(createEmployeeUpdateData()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("success"));
    }

    @Test
    public void shouldGetByIdEmployee()throws Exception{

        this.mockMvc.perform(get("/api/user/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(1));
    }

    @Test
    public void shouldGetListEmployeeWithRequiredFieldsFilter() throws Exception{

        this.mockMvc.perform(post("/api/user/list")
                .content(objectMapper.writeValueAsString(createEmployeeFilterWithRequiredFields()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", notNullValue()));
    }

    @Test
    public void shouldGetListEmployeeWithAllFieldsFilter() throws Exception{

        EmployeeDto employeeDto = createEmployeeFilterWithAllFields();
        this.mockMvc.perform(post("/api/user/list")
                .content(objectMapper.writeValueAsString(employeeDto))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data[*].firstName").value(employeeDto.getFirstName()))
                .andExpect(jsonPath("$.data[*].position").value(employeeDto.getPosition()));
    }

    private EmployeeDto createEmployeeFilterWithRequiredFields(){
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setOfficeId(1L);
        return employeeDto;
    }

    private EmployeeDto createEmployeeFilterWithAllFields(){
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setOfficeId(1L);
        employeeDto.setFirstName("Виктор");
        employeeDto.setLastName("Баринов");
        employeeDto.setMiddleName("Викторович");
        employeeDto.setPosition("Инженер");
        employeeDto.setPhone("79998885522");
        employeeDto.setDocumentCode("21");
        employeeDto.setCitizenShipCode("643");
        return employeeDto;
    }

    private EmployeeDto createEmployeeUpdateData() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(1L);
        employeeDto.setOfficeId(2L);
        employeeDto.setFirstName("Апдейт");
        employeeDto.setLastName("Апдейтов");
        employeeDto.setMiddleName("Апдейтвич");
        employeeDto.setPosition("Апдейтер");
        employeeDto.setPhone("89990008877");
        employeeDto.setDocumentName("Военный билет");
        employeeDto.setDocumentNumber("7777000");
        employeeDto.setDocumentDate("21-01-2001");
        employeeDto.setCitizenShipCode("804");
        employeeDto.setIsIdentified(false);
        return employeeDto;
    }

    private EmployeeDto createEmployeeSaveData(){
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setOfficeId(1L);
        employeeDto.setFirstName("Сейв");
        employeeDto.setLastName("Сейвов");
        employeeDto.setMiddleName("Сейвович");
        employeeDto.setPosition("Сэйвер");
        employeeDto.setPhone("89990008877");
        employeeDto.setDocumentCode("21");
        employeeDto.setDocumentName("Паспорт гражданина Российской Федерации");
        employeeDto.setDocumentNumber("7777000");
        employeeDto.setDocumentDate("21-01-2001");
        employeeDto.setCitizenShipCode("804");
        employeeDto.setIsIdentified(true);
        return employeeDto;
    }
}