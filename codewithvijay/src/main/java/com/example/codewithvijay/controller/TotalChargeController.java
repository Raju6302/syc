package com.example.codewithvijay.controller;

import com.example.codewithvijay.model.TotalCharge;
import com.example.codewithvijay.service.TotalChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TotalChargeController {

    private final TotalChargeService totalChargeService;

    @Autowired
    public TotalChargeController(TotalChargeService totalChargeService) {
        this.totalChargeService = totalChargeService;
    }

    @PostMapping("/totalcharge")
    public ResponseEntity<TotalCharge> saveTotalCharge(@RequestBody TotalCharge totalCharge) {
        try {
            TotalCharge savedTotalCharge = totalChargeService.saveTotalCharge(totalCharge);
            return new ResponseEntity<>(savedTotalCharge, HttpStatus.CREATED);
        } catch (Exception e) {
            // Log the error for debugging purposes
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
