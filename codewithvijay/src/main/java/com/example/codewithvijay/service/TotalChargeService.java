package com.example.codewithvijay.service;

import com.example.codewithvijay.model.TotalCharge;
import com.example.codewithvijay.repository.TotalChargeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TotalChargeService {

    private final TotalChargeRepository totalChargeRepository;

    private final Map<String, Integer> cityValues;

    @Autowired
    public TotalChargeService(TotalChargeRepository totalChargeRepository) {
        this.totalChargeRepository = totalChargeRepository;
        this.cityValues = initializeCityValues();
    }

    public TotalCharge saveTotalCharge(TotalCharge totalCharge) {
        // Calculate total based on toCity value and weight
        totalCharge.setTotal(getTotalValue(totalCharge.getToCity(), totalCharge.getWeight()));
        return totalChargeRepository.save(totalCharge);
    }

    public Map<String, Integer> getCityValues() {
        return cityValues;
    }

    private int getTotalValue(String toCity, int weight) {
        if (toCity == null) {
            // Handle the case where toCity is null, perhaps by throwing an exception or returning a default value
            return 0; // For example, return 0 as the default value
        }

        // Check if the provided city exists in the map
        if (cityValues.containsKey(toCity.toLowerCase())) {
            // Calculate total by multiplying city value with weight
            return cityValues.get(toCity.toLowerCase()) * weight;
        } else {
            // If city is not found in the map, return default value
            return 0;
        }
    }

    private Map<String, Integer> initializeCityValues() {
        Map<String, Integer> cityValues = new HashMap<>();
        cityValues.put("chennai", 10);
        cityValues.put("tirupati", 30);
        cityValues.put("hyderabad", 40);
        cityValues.put("mysore", 20);
        cityValues.put("cochin", 50);
        cityValues.put("jaipur", 60);
        cityValues.put("kolkatha", 20);
        cityValues.put("delhi", 30);
        cityValues.put("mumbai", 80);
        cityValues.put("kerala", 90);

        return cityValues;
    }
}
