package org.learning.service;

import org.learning.dto.PatientRequestDTO;
import org.learning.dto.PatientResponseDTO;
import org.learning.mapper.PatientMapper;
import org.learning.model.Patient;
import org.learning.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository){
        this.patientRepository= patientRepository;
    }

    public List<PatientResponseDTO> getPatients(){
        List<Patient> patients = patientRepository.findAll(Sort.by("registeredDate").descending());
        return patients.stream().map(PatientMapper::toDTO).toList();
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO){
        Patient newPatient = patientRepository.save(PatientMapper.toPatient(patientRequestDTO));
        return PatientMapper.toDTO(newPatient);
    }

}
