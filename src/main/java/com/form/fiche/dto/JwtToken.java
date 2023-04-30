package com.form.fiche.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtToken {
      private String token;

      private Instant createdAt;

      private Instant expireIn;

}
