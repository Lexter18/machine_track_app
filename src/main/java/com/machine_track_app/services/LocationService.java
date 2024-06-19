package com.machine_track_app.services;

import com.machine_track_app.entities.Country;
import com.machine_track_app.entities.Department;
import com.machine_track_app.entities.Municipality;

import java.util.List;

public interface LocationService {

    List<Country> getAllCountries();
    List<Department> getDepartmentsByCountryId(Long countryId);
    List<Municipality> getMunicipalitiesByDepartmentId(Long departmentId);

}
