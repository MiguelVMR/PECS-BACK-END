package com.pecs.model.record;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ResetSenhaFirstLogin(
        @NotBlank(message = "A senha nova é obrigatória") String senha,
        @NotBlank(message = "O email é obrigatório")
        @Email(message = "Email está no formado invalido") String email) {

}
