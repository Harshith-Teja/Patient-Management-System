package com.ps.patientservice.mapper;

import com.ps.patientservice.dto.PatientRequestDTO;
import com.ps.patientservice.dto.PatientResponseDTO;
import com.ps.patientservice.model.Patient;

import java.time.LocalDate;

public class PatientMapper {

    public static PatientResponseDTO toDTO(Patient p) {
        PatientResponseDTO patientDto = new PatientResponseDTO();

        patientDto.setId(p.getId().toString());
        patientDto.setName(p.getName());
        patientDto.setEmail(p.getEmail());
        patientDto.setAddress(p.getAddress());
        patientDto.setDateOfBirth(p.getDateOfBirth().toString());

        return patientDto;
    }

    public static Patient toModel(PatientRequestDTO patientRequestDTO) {

        Patient patient = new Patient();

        patient.setName(patientRequestDTO.getName());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
        patient.setRegisteredDate(LocalDate.parse(patientRequestDTO.getRegisteredDate()));

        return patient;
    }
}
