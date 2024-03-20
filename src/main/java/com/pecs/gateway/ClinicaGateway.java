package com.pecs.gateway;

import com.pecs.model.dto.Clinica;
import com.pecs.model.repository.ClinicaRepository;
import com.pecs.model.schema.ClinicaSchema;
import com.pecs.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The Class ClinicaGateway
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @sincer 20/03/2024
 */

@Component
public class ClinicaGateway {
    private final Mapper mapper = new Mapper();

    private final ClinicaRepository clinicaRepository;

    @Autowired
    public ClinicaGateway(ClinicaRepository clinicaRepository) {
        this.clinicaRepository = clinicaRepository;
    }

    public Clinica save(Clinica clinica){
        return mapper.converter(clinicaRepository.save(mapper.converter(clinica, ClinicaSchema.class)),Clinica.class);
    }
}
