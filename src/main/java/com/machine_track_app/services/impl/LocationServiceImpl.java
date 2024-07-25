package com.machine_track_app.services.impl;

import com.machine_track_app.entities.Country;
import com.machine_track_app.entities.Department;
import com.machine_track_app.entities.Municipality;
import com.machine_track_app.repositories.CountryRepository;
import com.machine_track_app.repositories.DepartmentRepository;
import com.machine_track_app.repositories.MunicipalityRepository;
import com.machine_track_app.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {


    private final CountryRepository countryRepository;
    private final DepartmentRepository departmentRepository;
    private final MunicipalityRepository municipalityRepository;

    @Autowired
    public LocationServiceImpl(CountryRepository countryRepository, DepartmentRepository departmentRepository, MunicipalityRepository municipalityRepository) {
        this.countryRepository = countryRepository;
        this.departmentRepository = departmentRepository;
        this.municipalityRepository = municipalityRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Department> getDepartmentsByCountryId(Long countryId) {
        return departmentRepository.findByCountryIdCountry(countryId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Municipality> getMunicipalitiesByDepartmentId(Long departmentId) {
        return municipalityRepository.findByDepartmentIdDepartment(departmentId);
    }
}
