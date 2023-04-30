package com.form.fiche.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class ResetPasswordToken {
    @SequenceGenerator(name = "reset_token_sequence",
                        sequenceName="reset_token_sequence",
                        allocationSize = 1)
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE,
                     generator = "reset_token_sequence")
     private Long id;
    @Column(nullable = false)
    private String token ;
    @Column(nullable = false)
     private LocalDateTime createdAt;
    @Column(nullable = false)
     private LocalDateTime expireAt;
     private LocalDateTime confirmedAt;
     @ManyToOne
     @JoinColumn(nullable = false, name = "userId_tokenId")
     private User user;

     public ResetPasswordToken(String token, LocalDateTime createdAt, LocalDateTime expireAt, User user){
         this.token=token;
         this.createdAt=createdAt;
         this.expireAt=expireAt;
         this.user=user;
     }

}
