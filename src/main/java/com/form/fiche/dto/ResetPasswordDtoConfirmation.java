package com.form.fiche.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResetPasswordDtoConfirmation {
    private String email;

    private String newPassword;

    private String token;
}
