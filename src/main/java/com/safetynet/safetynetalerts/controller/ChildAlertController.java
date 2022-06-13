package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.model.viewmodel.ChildAlertViewModel;
import com.safetynet.safetynetalerts.service.JSonPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/childAlert")
public class ChildAlertController {

    @Autowired
    private JSonPersonService jSonPersonService;

    @GetMapping
    public ChildAlertViewModel getChildAlert(@RequestParam String address) {
        return jSonPersonService.getChildAlert(address);
    }
}
