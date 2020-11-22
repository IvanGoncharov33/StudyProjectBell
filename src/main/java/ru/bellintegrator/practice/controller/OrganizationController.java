package ru.bellintegrator.practice.controller;


import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.bellintegrator.practice.response.SuccessResponseBody;
import ru.bellintegrator.practice.service.OrganizationService;
import ru.bellintegrator.practice.view.OrganizationFullView;
import ru.bellintegrator.practice.view.OrganizationShortView;
import ru.bellintegrator.practice.view.validation.group.ListView;
import ru.bellintegrator.practice.view.validation.group.SaveView;
import ru.bellintegrator.practice.view.validation.group.UpdateView;


import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/organization")
public class OrganizationController {


   private final OrganizationService organizationService;


    @PostMapping("/list")
    public List<OrganizationShortView>getListOrganization(@Validated(ListView.class) @RequestBody OrganizationShortView organizationView){

        List<OrganizationShortView> organizationShortViewList = organizationService.list(organizationView);
        return organizationShortViewList;
    }

    @GetMapping("/{id}")
    public OrganizationFullView getOrgById(@PathVariable long id) {

        OrganizationFullView organizationFullView = organizationService.getById(id);
        System.out.println(organizationFullView.toString());
           return organizationFullView ;
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@Validated(SaveView.class) @RequestBody OrganizationFullView organizationView){
        organizationService.save(organizationView);
        return ResponseEntity.ok(SuccessResponseBody.SUCCESS_RESPONSE_BODY);
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@Validated(UpdateView.class) @RequestBody OrganizationFullView organizationView){
        organizationService.update(organizationView);
        return ResponseEntity.ok(SuccessResponseBody.SUCCESS_RESPONSE_BODY);
    }
}
