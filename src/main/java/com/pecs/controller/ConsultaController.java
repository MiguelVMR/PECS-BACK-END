package com.pecs.controller;

import com.pecs.business.ConsultaBusiness;
import com.pecs.exception.PecsExceptionController;
import com.pecs.model.dto.Consulta;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consulta")
@Tag(name = "Modulo de Consulta")
public class ConsultaController {

        private final ConsultaBusiness consultaBusiness;

        @Autowired
        public ConsultaController(ConsultaBusiness consultaBusiness) {
                this.consultaBusiness = consultaBusiness;
        }

        @ApiResponses({
                        @ApiResponse(responseCode = "201", description = "Created"),
                        @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                                        @Content(schema = @Schema(implementation = PecsExceptionController.ErrorHandling.class)) }),
                        @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
                                        @Content(schema = @Schema(implementation = PecsExceptionController.ErrorHandling.class)) }),
                        @ApiResponse(responseCode = "409", description = "Conflict", content = {
                                        @Content(schema = @Schema(implementation = PecsExceptionController.ErrorHandling.class)) }),
        })
        @Operation(summary = "Endpoint responsável por realizar a criação de Consulta")
        @PostMapping("create-consulta")
        @PreAuthorize("isAuthenticated()")
        public ResponseEntity<Consulta> save(
                        @RequestBody @Valid final Consulta consulta,
                        JwtAuthenticationToken token) {

                Consulta consultaSalva = consultaBusiness.save(consulta, token);

                return ResponseEntity.status(HttpStatus.CREATED).body(consultaSalva);
        }

        @GetMapping("paciente/{pacienteId}")
        @Operation(summary = "Obter todas as consultas de um paciente")
        @ApiResponse(responseCode = "200", description = "OK")
        public ResponseEntity<List<Consulta>> getConsultasByPacienteId(@PathVariable UUID pacienteId) {
                List<Consulta> consultas = consultaBusiness.findByPacienteId(pacienteId);
                return ResponseEntity.ok(consultas);
        }

        @GetMapping("user")
        @Operation(summary = "Obter todas as consultas de um paciente")
        @ApiResponse(responseCode = "200", description = "OK")
        public ResponseEntity<List<Consulta>> getConsultasByUserId(JwtAuthenticationToken token) {
                List<Consulta> consultas = consultaBusiness.findByUser(token);
                return ResponseEntity.ok(consultas);
        }
}
