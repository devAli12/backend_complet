package com.form.fiche.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RessourceDTO {
    private Double debitSurPression;
    private Double latitude;
    private Double longitude;
}
