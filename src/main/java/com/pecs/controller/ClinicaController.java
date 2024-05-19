package com.pecs.controller;

import com.pecs.business.ClinicaBusiness;
import com.pecs.exception.PecsExceptionController;
import com.pecs.model.dto.Clinica;
import com.pecs.model.dto.UsuarioClinico;
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

/**
 * The Class ClinicaController
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @sincer 20/03/2024
 */

@RestController
@RequestMapping("/clinica")
@Tag(name = "Modulo de Clinica")
public class ClinicaController {

    private final ClinicaBusiness clinicaBusiness;

    @Autowired
    public ClinicaController(ClinicaBusiness clinicaBusiness) {
        this.clinicaBusiness = clinicaBusiness;
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(schema = @Schema(implementation = PecsExceptionController.ErrorHandling.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
                    @Content(schema = @Schema(implementation = PecsExceptionController.ErrorHandling.class))}),
            @ApiResponse(responseCode = "409", description = "Conflict", content = {
                    @Content(schema = @Schema(implementation = PecsExceptionController.ErrorHandling.class))}),
    })
    @Operation(summary = "Endpoint responsável por realizar a criação de Clinica")
    @PostMapping("create-clinica")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Clinica> save(
            @RequestBody @Valid final Clinica clinica,
            JwtAuthenticationToken token) {

        Clinica clinicaSalva = clinicaBusiness.save(clinica, token);

        return ResponseEntity.status(HttpStatus.CREATED).body(clinicaSalva);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(schema = @Schema(implementation = PecsExceptionController.ErrorHandling.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
                    @Content(schema = @Schema(implementation = PecsExceptionController.ErrorHandling.class))}),
            @ApiResponse(responseCode = "409", description = "Conflict", content = {
                    @Content(schema = @Schema(implementation = PecsExceptionController.ErrorHandling.class))}),
    })
    @Operation(summary = "Endpoint responsável por resgatar Clinica")
    @GetMapping()
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Clinica> findClinica(
            JwtAuthenticationToken token) {

        Clinica clinicaSalva = clinicaBusiness.findByUsuario(token);

        return ResponseEntity.status(HttpStatus.CREATED).body(clinicaSalva);
    }
}
