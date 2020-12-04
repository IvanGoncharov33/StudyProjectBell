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
import ru.bellintegrator.practice.dto.OrganizationFullDto;
import ru.bellintegrator.practice.dto.OrganizationShortDto;

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
public class OrganizationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;



    @Test
    public void shouldKeepOrganization() throws Exception{

        this.mockMvc.perform(post("/api/organization/save")
                .content(objectMapper.writeValueAsString(createOrganizationSaveData()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("success"));
    }

    @Test
    public void shouldUpdateOrganization() throws Exception{

        this.mockMvc.perform(post("/api/organization/update")
                .content(objectMapper.writeValueAsString(createOrganizationUpdateData()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("success"));
    }

    @Test
    public void shouldGetByIdOrganization() throws Exception {

        this.mockMvc.perform(get("/api/organization/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(1));
    }

    @Test
    public void shouldGetListOrganizationsWithRequiredFieldsFilter() throws Exception{

        OrganizationShortDto organizationFilter = new OrganizationShortDto();
        organizationFilter.setName("АО \"Вертолеты России\"");

        this.mockMvc.perform(post("/api/organization/list")
                .content(objectMapper.writeValueAsString(organizationFilter))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data[*].name").value(organizationFilter.getName()));
    }

    @Test
    public void shouldGetListOrganizationsWithAllFilter() throws Exception{

        OrganizationShortDto organizationFilter = new OrganizationShortDto();
        organizationFilter.setName("АО \"Вертолеты России\"");
        organizationFilter.setInn("7731559044");
        organizationFilter.setIsActive(true);

        this.mockMvc.perform(post("/api/organization/list")
                .content(objectMapper.writeValueAsString(organizationFilter))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data[*].name").value(organizationFilter.getName()))
                .andExpect(jsonPath("$.data[*].isActive").value(organizationFilter.getIsActive()));
    }

    private OrganizationFullDto createOrganizationSaveData(){

        OrganizationFullDto organizationTest = new OrganizationFullDto();
        organizationTest.setName("AО Сэйв");
        organizationTest.setFullName("Акционерное общество Сэйв");
        organizationTest.setInn("1111111111");
        organizationTest.setKpp("111111111");
        organizationTest.setAddress("г.Москва ул. Свободы д.1");
        organizationTest.setPhone("89995553322");
        organizationTest.setIsActive(false);
        return organizationTest;
    }

    private OrganizationFullDto createOrganizationUpdateData(){

        OrganizationFullDto organizationFullDto = new OrganizationFullDto();
        organizationFullDto.setId(1L);
        organizationFullDto.setName("АО Апдэйт");
        organizationFullDto.setFullName("Акционерное общество Апдэйт");
        organizationFullDto.setInn("0001112223");
        organizationFullDto.setKpp("999888777");
        organizationFullDto.setAddress("г. Апдейт");
        return organizationFullDto;
    }
}
