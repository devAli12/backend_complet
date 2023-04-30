package com.form.fiche.repositories;

import com.form.fiche.entities.ResetPasswordToken;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ResetPasswordTokenRepository extends JpaRepository<ResetPasswordToken,Long> {
       Optional<ResetPasswordToken> getByToken(String token);
       @Modifying
       @Transactional
       @Query("UPDATE ResetPasswordToken r "+ " SET r.confirmedAt= ?1"+" WHERE r.token= ?2")
       int updateConfirmedAt(LocalDateTime confirmedAt,String token);
}
