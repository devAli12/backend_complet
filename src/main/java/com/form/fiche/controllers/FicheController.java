package com.form.fiche.controllers;

import com.form.fiche.entities.Fiche;
import com.form.fiche.dto.FicheFormDTO;
import com.form.fiche.services.FicheService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/fiches")
@RequiredArgsConstructor
public class FicheController {


    private final FicheService ficheService;

    @GetMapping
    public ResponseEntity<List<Fiche>> getAllFiches() {
        List<Fiche> fiches = ficheService.getAllFichesWithoutDocuments();
        return ResponseEntity.ok(fiches);
    }





    @PostMapping("/createFiche")
    public ResponseEntity<Fiche> createFiche(@RequestParam("idUser") int id ,@RequestParam("ficheFormDto") String ficheFormDtoJson,
                                             @RequestParam Map<String, MultipartFile> fileMap) {
        FicheFormDTO ficheFormDto = new Gson().fromJson(ficheFormDtoJson, FicheFormDTO.class);
        try {
            Fiche fiche = ficheService.createFiche(id,ficheFormDto, fileMap);
            return ResponseEntity.status(HttpStatus.CREATED).body(fiche);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fiche> updateFiche(@PathVariable Long id,
                                             @RequestParam("ficheFormDto") String ficheFormDtoJson,
                                             @RequestParam Map<String, MultipartFile> fileMap) {
        FicheFormDTO ficheFormDto = new Gson().fromJson(ficheFormDtoJson, FicheFormDTO.class);
        Fiche updatedFiche;
        try {
            updatedFiche = ficheService.updateFiche(id, ficheFormDto, fileMap);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (updatedFiche == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedFiche);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFiche(@PathVariable Long id) {
        ficheService.deleteFicheById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Fiche>> getFicheById(@PathVariable Long id) {
        Optional<Fiche> fiche = ficheService.getFicheById(id);

        if (fiche == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(fiche);
        }
    }
    @GetMapping("/fiches/commercial/{commercial}")
    public List<Fiche> getAllFicheByCommercial(@PathVariable String commercial) {
        return ficheService.getAllFicheByCommercial(commercial);
    }

    @GetMapping("/commercial/{idUser}")
    public List<Fiche> getAllFichesByUser(@PathVariable int idUser){
        return ficheService.getAllFicheByUser(idUser);
    }




}
