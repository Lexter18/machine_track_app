package com.machine_track_app.controller;

import com.machine_track_app.entities.Country;
import com.machine_track_app.entities.Department;
import com.machine_track_app.entities.Municipality;
import com.machine_track_app.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationController {


    @Autowired
    private LocationService locationService;

    @GetMapping("/countries")
    public ResponseEntity<List<Country>> getAllCountries() {
        List<Country> countries = locationService.getAllCountries();
        return ResponseEntity.ok(countries);
    }

    @GetMapping("/departments/{countryId}")
    public ResponseEntity<List<Department>> getDepartmentsByCountryId(@PathVariable Long countryId) {
        List<Department> departments = locationService.getDepartmentsByCountryId(countryId);
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/municipalities/{departmentId}")
    public ResponseEntity<List<Municipality>> getMunicipalitiesByDepartmentId(@PathVariable Long departmentId) {
        List<Municipality> municipalities = locationService.getMunicipalitiesByDepartmentId(departmentId);
        return ResponseEntity.ok(municipalities);
    }
}
