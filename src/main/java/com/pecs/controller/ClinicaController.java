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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Class ClinicaController
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @sincer 20/03/2024
 */

@RestController
@RequestMapping("/clinina")
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
    @PostMapping("create-user")
    public ResponseEntity<Clinica> save(
            @RequestBody @Valid final Clinica clinica) {

        Clinica clinicaSalva = clinicaBusiness.save(clinica);

        return ResponseEntity.status(HttpStatus.CREATED).body(clinicaSalva);
    }
}
