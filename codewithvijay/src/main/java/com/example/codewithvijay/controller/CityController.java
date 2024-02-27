package com.example.codewithvijay.controller;

import com.example.codewithvijay.service.TotalChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class CityController {

    private final TotalChargeService totalChargeService;

    @Autowired
    public CityController(TotalChargeService totalChargeService) {
        this.totalChargeService = totalChargeService;
    }

    @GetMapping("/getCities")
    public ResponseEntity<Set<String>> getToCities() {
        Map<String, Integer> cityValues = totalChargeService.getCityValues();
        Set<String> cities = cityValues.keySet();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }
}
