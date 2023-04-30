package com.form.fiche.services;

import com.form.fiche.entities.ResetPasswordToken;
import com.form.fiche.repositories.ResetPasswordTokenRepository;
import com.form.fiche.services.ResetPasswordTokenService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@Transactional
@Service
public class ResetPasswordTokenServiceImpl implements ResetPasswordTokenService {

    ResetPasswordTokenRepository resetPasswordTokenRepository;
    @Override
    public ResetPasswordToken save(ResetPasswordToken resetPasswordToken) {
        return resetPasswordTokenRepository.save(resetPasswordToken);
    }

    @Override
    public Optional<ResetPasswordToken> getResetPasswordTokenBy(String token) {
        return resetPasswordTokenRepository.getByToken(token);
    }

    @Override
    public int updateConfirmedAt(LocalDateTime confirmedAt, String token) {
        return resetPasswordTokenRepository.updateConfirmedAt(confirmedAt,token);
    }
}
