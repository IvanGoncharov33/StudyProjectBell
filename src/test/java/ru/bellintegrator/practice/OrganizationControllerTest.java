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
import ru.bellintegrator.practice.dao.OrganizationDao;
import ru.bellintegrator.practice.dto.OrganizationShortDto;
import ru.bellintegrator.practice.model.Organization;

import javax.persistence.EntityManager;
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

    @Autowired
    OrganizationDao organizationDao;

    @Autowired
    EntityManager entityManager;

    @Test
    public void shouldKeepOrganization() throws Exception{

        Organization organization = new Organization("AО Сэйв", "Акционерное общество Сэйв",
                "1111111111", "111111111", "г.Москва ул. Свободы д.1");
        this.mockMvc.perform(post("/api/organization/save")
                .content(objectMapper.writeValueAsString(organization))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateOrganization() throws Exception{

        Organization updatedOrganization = createTestOrganization();
        updatedOrganization.setName("АО Апдэйт");
        updatedOrganization.setFullName("Акционерное общество Апдэйт");
        updatedOrganization.setInn("0001112223");
        updatedOrganization.setKpp("999888777");

        this.mockMvc.perform(post("/api/organization/update")
                .content(objectMapper.writeValueAsString(updatedOrganization))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void shouldGetByIdOrganization() throws Exception {

        Organization organization = createTestOrganization();
        Long id = organization.getId();
        this.mockMvc.perform(get("/api/organization/{id}", id)
                .content(objectMapper.writeValueAsString(organization))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(id));
    }

    @Test
    public void shouldGetListOrganizationsWithNameFilter() throws Exception{

        Organization organization = createTestOrganization();
        OrganizationShortDto organizationShortDto = new OrganizationShortDto();
        organizationShortDto.setName(organization.getName());

        this.mockMvc.perform(post("/api/organization/list")
                .content(objectMapper.writeValueAsString(organizationShortDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data[0].name").value(organizationShortDto.getName()));
    }

    @Test
    public void shouldGetListOrganizationsWithNameAndInnFilter() throws Exception{

        Organization organization = createTestOrganization();
        OrganizationShortDto organizationShortDto = new OrganizationShortDto();
        organizationShortDto.setName(organization.getName());
        organizationShortDto.setInn(organization.getInn());

        this.mockMvc.perform(post("/api/organization/list")
                .content(objectMapper.writeValueAsString(organizationShortDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data[0].name").value(organizationShortDto.getName()))
                .andExpect(jsonPath("$.data[0].inn").value(organizationShortDto.getInn()));
    }

    @Test
    public void shouldGetListOrganizationsWithNameAndInnAndIsActiveFilter() throws Exception{

        Organization organization = createTestOrganization();
        OrganizationShortDto organizationShortDto = new OrganizationShortDto();
        organizationShortDto.setName(organization.getName());
        organizationShortDto.setInn(organization.getInn());
        organizationShortDto.setIsActive(organization.getIsActive());

        this.mockMvc.perform(post("/api/organization/list")
                .content(objectMapper.writeValueAsString(organizationShortDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data[0].name").value(organizationShortDto.getName()))
                .andExpect(jsonPath("$.data[0].inn").value(organizationShortDto.getInn()))
                .andExpect(jsonPath("$.data[0].isActive").value(organizationShortDto.getIsActive()));
    }

    public Organization createTestOrganization(){

        Organization organization = new  Organization("АО Хлеб", "Акционерное общество Хлеб",
                "0123456789","123456789", "г.Москва, ул. Лубянка, д.1");
        organization.setIsActive(true);
        entityManager.persist(organization);
        return organization;
    }
}
