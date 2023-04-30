package com.form.fiche.services;



import com.form.fiche.entities.ResetPasswordToken;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ResetPasswordTokenService {
    ResetPasswordToken save(ResetPasswordToken resetPasswordToken);

    Optional<ResetPasswordToken> getResetPasswordTokenBy(String token);
    int updateConfirmedAt(LocalDateTime confirmedAt, String token);
}
