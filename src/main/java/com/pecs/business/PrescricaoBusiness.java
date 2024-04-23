package com.pecs.business;

import com.pecs.model.dto.Prescricao;
import com.pecs.model.schema.PrescricaoSchema;
import com.pecs.gateway.PrescricaoGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class PrescricaoBusiness {

    @Autowired
    private PrescricaoGateway prescricaoGateway;

    public List<Prescricao> findByConsultaId(UUID consultaId) {
        return prescricaoGateway.findPrescricoesByConsulta(consultaId);
    }

    public Prescricao save(Prescricao prescricao) {
        return prescricaoGateway.save(prescricao);
    }
}
