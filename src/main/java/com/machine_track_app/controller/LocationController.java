package com.machine_track_app.controller;

import com.machine_track_app.entities.Country;
import com.machine_track_app.entities.Department;
import com.machine_track_app.entities.Municipality;
import com.machine_track_app.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/locations")
public class LocationController {

    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/countries")
    public ResponseEntity<List<Country>> getAllCountries() {
        List<Country> countries = locationService.getAllCountries();
        return ResponseEntity.ok(countries);
    }

    @GetMapping("/departments")
    public ResponseEntity<List<Department>> getDepartmentsByCountryId(@RequestParam Long idCountry) {
        List<Department> departments = locationService.getDepartmentsByCountryId(idCountry);
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/municipalities")
    public ResponseEntity<List<Municipality>> getMunicipalitiesByDepartmentId(@RequestParam Long idDepartment) {
        List<Municipality> municipalities = locationService.getMunicipalitiesByDepartmentId(idDepartment);
        return ResponseEntity.ok(municipalities);
    }
}
