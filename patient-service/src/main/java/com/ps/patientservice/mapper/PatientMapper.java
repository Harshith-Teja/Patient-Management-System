package com.ps.patientservice.mapper;

import com.ps.patientservice.dto.PatientResponseDTO;
import com.ps.patientservice.model.Patient;

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
}
