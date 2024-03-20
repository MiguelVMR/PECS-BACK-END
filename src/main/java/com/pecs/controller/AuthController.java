package com.pecs.controller;

import java.util.Map;

import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pecs.business.AutenticacaoBusiness;
import com.pecs.exception.PecsExceptionController;
import com.pecs.model.dto.UsuarioClinico;
import com.pecs.model.record.LoginRecord;
import com.pecs.model.record.ResetSenhaFirstLogin;
import com.pecs.model.record.ResetarSenhaExterna;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@Tag(name = "Modulo de Autenticação")
public class AuthController {
    private final AutenticacaoBusiness autenticacaoBusiness;

    @Autowired
    public AuthController(AutenticacaoBusiness autenticacaoBusiness) {
        this.autenticacaoBusiness = autenticacaoBusiness;
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
    @Operation(summary = "Endpoint responsável por realizar a criação de usuario")
    @PostMapping("create-user")
    public ResponseEntity<String> save(
            @RequestBody @Valid final UsuarioClinico usuario) {

        autenticacaoBusiness.criarUsuario(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario Criado");
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(schema = @Schema(implementation = PecsExceptionController.ErrorHandling.class))}),
    })
    @Operation(summary = "Endpoint responsável por realizar o login do usuário")
    @PostMapping("login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRecord loginRecord) {

        Map<String, Object> response = autenticacaoBusiness.login(loginRecord);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(schema = @Schema(implementation = PecsExceptionController.ErrorHandling.class))}),
    })
    @Operation(summary = "Endpoint responsável por enviar o token de recuperação de senha")
    @PostMapping("enviar-token")
    public ResponseEntity<String> enviaTokenResetSenha(@RequestParam(name = "email") final String email) {

        autenticacaoBusiness.geraTokenResetSenha(email);

        return ResponseEntity.status(HttpStatus.OK).body("Token enviado ao email com sucesso!");
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(schema = @Schema(implementation = PecsExceptionController.ErrorHandling.class))}),
    })
    @Operation(summary = "Endpoint responsável por realizar a recuperação de senha do usuário")
    @PostMapping("resetar-senha")
    public ResponseEntity<AccessTokenResponse> resetPassword(@RequestBody ResetarSenhaExterna resetarSenhaExterna) {

        AccessTokenResponse response = autenticacaoBusiness.recuperarSenhaDeslogado(resetarSenhaExterna);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(schema = @Schema(implementation = PecsExceptionController.ErrorHandling.class))}),
    })
    @Operation(summary = "Endpoint responsável por realizar a recuperação de senha do usuário quando for primeiro Login")
    @PostMapping("resetar-senha-first-login")
    public ResponseEntity<AccessTokenResponse> resetPasswordFirsLogin(
            @RequestBody ResetSenhaFirstLogin firstLogin) {

        AccessTokenResponse response = autenticacaoBusiness.recuperarSenhaFirstLogin(firstLogin);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
