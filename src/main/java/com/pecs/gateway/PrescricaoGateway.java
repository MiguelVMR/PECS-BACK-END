package com.pecs.gateway;

import com.pecs.model.dto.Prescricao;
import com.pecs.model.repository.PrescricaoRepository;
import com.pecs.model.schema.PrescricaoSchema;
import com.pecs.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class PrescricaoGateway {
    private final Mapper mapper = new Mapper();

    private final PrescricaoRepository prescricaoRepository;

    @Autowired
    public PrescricaoGateway(PrescricaoRepository prescricaoRepository) {
        this.prescricaoRepository = prescricaoRepository;
    }

    public List<Prescricao> findPrescricoesByConsulta(UUID consultaId) {
        List<PrescricaoSchema> prescricoes = prescricaoRepository.findAllByConsultas_Id(consultaId);
        return prescricoes.stream()
                .map(prescricao -> mapper.converter(prescricao, Prescricao.class))
                .collect(Collectors.toList());
    }

    public Prescricao save(Prescricao prescricao) {
        return mapper.converter(prescricaoRepository.save(mapper.converter(prescricao, PrescricaoSchema.class)),
                Prescricao.class);

    }
}
