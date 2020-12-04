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
import ru.bellintegrator.practice.dto.OfficeDto;

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
public class OfficeControllerTest{

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void shouldKeepOffice() throws Exception{

        this.mockMvc.perform(post("/api/office/save")
                .content(objectMapper.writeValueAsString(createOfficeSaveData()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("success"));
    }

    @Test
    public void shouldUpdateOffice() throws Exception{

        this.mockMvc.perform(post("/api/office/update")
                .content(objectMapper.writeValueAsString(createOfficeUpdateData()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("success"));
    }

    @Test
    public void shouldGetByIdOffice() throws Exception {

        OfficeDto officeDto = new OfficeDto();
        officeDto.setId(1L);
        this.mockMvc.perform(get("/api/office/{id}", officeDto.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(officeDto.getId()));
    }

    @Test
    public void shouldGetListOfficesWithRequiredFieldsFilter() throws Exception{

        OfficeDto officeDto = new OfficeDto();
        officeDto.setOrganizationId(1L);
        this.mockMvc.perform(post("/api/office/list")
                .content(objectMapper.writeValueAsString(officeDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", notNullValue()));
    }

    @Test
    public void shouldGetListOfficeAllFieldsFilter() throws Exception{

        OfficeDto officeDto = createTestOfficeDto();
        this.mockMvc.perform(post("/api/office/list")
                .content(objectMapper.writeValueAsString(officeDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data[*].name").value(officeDto.getName()))
                .andExpect(jsonPath("$.data[*].phone").value(officeDto.getPhone()))
                .andExpect(jsonPath("$.data[*].isActive").value(officeDto.getIsActive()));
    }

    private OfficeDto createTestOfficeDto(){
        OfficeDto office = new OfficeDto();
        office.setOrganizationId(1L);
        office.setName("АО НЦВ Миль и Камов");
        office.setAddress("Московская обл., г. Люберцы, рп. Томилино, ул. Гаршина, д. 26/1");
        office.setPhone("84996535199");
        office.setIsActive(true);
        return office;
    }

    private OfficeDto createOfficeSaveData(){
        OfficeDto officeDto = new OfficeDto();
        officeDto.setOrganizationId(1L);
        officeDto.setName("Офис для сейва");
        officeDto.setAddress("г.Сэйв");
        officeDto.setPhone("89998885533");
        officeDto.setIsActive(true);
        return officeDto;
    }

    private OfficeDto createOfficeUpdateData(){
        OfficeDto officeDto = new OfficeDto();
        officeDto.setId(1L);
        officeDto.setName("Update office");
        officeDto.setAddress("Update address");
        officeDto.setPhone("Update phone");
        officeDto.setIsActive(false);
        return officeDto;
    }
}