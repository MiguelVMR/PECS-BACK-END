package com.pecs.controller;

import com.pecs.model.dto.Prescricao;
import com.pecs.model.schema.PrescricaoSchema;
import com.pecs.business.PrescricaoBusiness;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/prescricao")
public class PrescricaoController {

    @Autowired
    private PrescricaoBusiness prescricaoBusiness;

    @GetMapping("/consulta/{consultaId}")
    public List<Prescricao> findPrescricoesByConsulta(@PathVariable UUID consultaId) {
        return prescricaoBusiness.findByConsultaId(consultaId);
    }

    @PostMapping("/consulta/{consultaId}")
    public ResponseEntity<Prescricao> save(@PathVariable UUID consultaId, @RequestBody Prescricao prescricao) {
        Prescricao precricaoSalva = prescricaoBusiness.save(prescricao);

        return ResponseEntity.status(HttpStatus.CREATED).body(precricaoSalva);
    }
}
