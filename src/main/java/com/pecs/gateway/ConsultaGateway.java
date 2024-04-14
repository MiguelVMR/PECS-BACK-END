package com.pecs.gateway;

import com.pecs.model.dto.Consulta;
import com.pecs.model.repository.ConsultaRepository;
import com.pecs.model.schema.ConsultaSchema;
import com.pecs.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ConsultaGateway {

    private final Mapper mapper = new Mapper();
    private final ConsultaRepository consultaRepository;

    @Autowired
    public ConsultaGateway(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    public List<Consulta> findAllByUser_Id(UUID clinicaUuid) {
        List<ConsultaSchema> consultaSchemas = consultaRepository.findAllByUsuarioClinico_Id(clinicaUuid);
        return consultaSchemas.stream()
                .map(consultaSchema -> mapper.converter(consultaSchema, Consulta.class))
                .collect(Collectors.toList());
    }

    public List<Consulta> findAllByPacienteId(UUID pacienteUuid) {
        List<ConsultaSchema> consultaSchemas = consultaRepository.findAllByPaciente_Id(pacienteUuid);
        return consultaSchemas.stream()
                .map(consultaSchema -> mapper.converter(consultaSchema, Consulta.class))
                .collect(Collectors.toList());
    }

    public Consulta save(Consulta consulta) {

        return mapper.converter(consultaRepository.save(mapper.converter(consulta, ConsultaSchema.class)),
                Consulta.class);
    }
}