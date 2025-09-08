package com.ps.patientservice.service;

import com.ps.patientservice.dto.PatientRequestDTO;
import com.ps.patientservice.dto.PatientResponseDTO;
import com.ps.patientservice.exceptions.EmailAlreadyExistsException;
import com.ps.patientservice.mapper.PatientMapper;
import com.ps.patientservice.model.Patient;
import com.ps.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {

    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();

        List<PatientResponseDTO> patientResponseDTOS = patients.stream().map(PatientMapper::toDTO).toList();

        return patientResponseDTOS;
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {

        if(patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("A patient with this email already exists : " + patientRequestDTO.getEmail());
        }

        Patient newPatient = patientRepository.save(PatientMapper.toModel(patientRequestDTO));

        return PatientMapper.toDTO(newPatient);
    }
}
