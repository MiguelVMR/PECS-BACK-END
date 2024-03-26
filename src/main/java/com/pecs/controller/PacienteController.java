package com.pecs.controller;

import com.pecs.business.PacienteBusiness;
import com.pecs.exception.PecsExceptionController;
import com.pecs.model.dto.Clinica;
import com.pecs.model.dto.Paciente;
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

/**
 * The Class PacienteController
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @sincer 26/03/2024
 */

@RestController
@RequestMapping("/paciente")
@Tag(name = "Modulo de Paciente")
public class PacienteController {

    private final PacienteBusiness pacienteBusiness;

    @Autowired
    public PacienteController(PacienteBusiness pacienteBusiness) {
        this.pacienteBusiness = pacienteBusiness;
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(schema = @Schema(implementation = PecsExceptionController.ErrorHandling.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
                    @Content(schema = @Schema(implementation = PecsExceptionController.ErrorHandling.class))}),
    })
    @Operation(summary = "Endpoint responsável por realizar a criação de paciente")
    @PostMapping("create-paciente")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Paciente> save(
            @RequestBody @Valid final Paciente paciente,
            JwtAuthenticationToken token) {

        Paciente pacienteSalvo = pacienteBusiness.salvarPacienteVinculadoUsuario(paciente, token);

        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteSalvo);
    }
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(schema = @Schema(implementation = PecsExceptionController.ErrorHandling.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
                    @Content(schema = @Schema(implementation = PecsExceptionController.ErrorHandling.class))}),
    })
    @Operation(summary = "Endpoint responsável por realizar a busca de todos os pacientes do Usuario Logado")
    @GetMapping()
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Paciente>> findAllPacientes(
            JwtAuthenticationToken token) {

        List<Paciente> pacientes = pacienteBusiness.findAllPacientesUser( token);

        return ResponseEntity.status(HttpStatus.CREATED).body(pacientes);
    }


}
