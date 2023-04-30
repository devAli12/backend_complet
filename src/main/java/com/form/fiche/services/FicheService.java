package com.form.fiche.services;

import com.form.fiche.dto.FicheFormDTO;
import com.form.fiche.dto.RessourceDTO;
import com.form.fiche.entities.*;
import com.form.fiche.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FicheService {
    private final FicheRepository ficheRepository;
    private final  FicheGoutteursRepository ficheGoutteursRepository;
    private final FicheMaraichageRepository ficheMaraichageRepository;
    private final FicheArboricultureRepository ficheArboricultureRepository;
    private final FicheDocumentsRepository ficheDocumentsRepository;
    private final BorneIrrigationRepository borneIrrigationRepository;
    private final PuitRepository puitRepository;
    private final ForageRepository forageRepository;
    private final FicheRessourcesEnEauRepository ficheRessourcesEnEauRepository;
    private final BassinRepository bassinRepository;
    private final IrrigationConfigurationRepository irrigationConfigurationRepository;
    private final UserService userService;

    private final FicheDocumentFilesRepository ficheDocumentFilesRepository;
    private final ModelMapper modelMapper;
    public List<Fiche> getAllFichesWithoutDocuments() {
        List<Fiche> fiches = ficheRepository.findAll();
        for (Fiche fiche : fiches) {
            fiche.setFicheDocumentFiles(null);
        }
        return fiches;
    }
    public void deleteFicheById(Long id) {
        ficheRepository.deleteById(id);
    }
    public Optional<Fiche> getFicheById(Long id) {
        return ficheRepository.findById(id);
    }
    public Fiche createFiche(int id ,FicheFormDTO ficheFormDto, Map<String, MultipartFile> fileMap) throws IOException {
            return saveFiche(id,ficheFormDto, fileMap);
    }
    public Fiche updateFiche(Long id, FicheFormDTO ficheFormDto, Map<String, MultipartFile> fileMap) throws IOException {
        Fiche fiche = ficheRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Fiche not found"));

        // Update the fields of the fiche object with the new values from the form DTO
        modelMapper.map(ficheFormDto, fiche);

        // Update any related objects that have been modified
        // FicheDocuments
        FicheDocuments ficheDocuments = Optional.ofNullable(fiche.getFicheDocuments())
            .orElse(new FicheDocuments());
        modelMapper.map(ficheFormDto, ficheDocuments);
        ficheDocumentsRepository.save(ficheDocuments);

        // FicheDocumentFiles
        FicheDocumentFiles ficheDocumentFiles = Optional.ofNullable(fiche.getFicheDocumentFiles())
            .orElse(new FicheDocumentFiles());
        setFicheDocumentFiles(fileMap,ficheDocumentFiles);
        ficheDocumentFilesRepository.save(ficheDocumentFiles);


        // FicheArboriculture
        FicheArboriculture ficheArboriculture = Optional.ofNullable(fiche.getFicheArboriculture())
            .orElse(new FicheArboriculture());
        modelMapper.map(ficheFormDto, ficheArboriculture);
        ficheArboricultureRepository.save(ficheArboriculture);

        // FicheMaraichage
        FicheMaraichage ficheMaraichage = Optional.ofNullable(fiche.getFicheMaraichage())
            .orElse(new FicheMaraichage());
        modelMapper.map(ficheFormDto, ficheMaraichage);
        ficheMaraichageRepository.save(ficheMaraichage);

        // FicheGoutteurs
        FicheGoutteurs ficheGoutteurs = Optional.ofNullable(fiche.getFicheGoutteurs())
            .orElse(new FicheGoutteurs());
        modelMapper.map(ficheFormDto, ficheGoutteurs);
        ficheGoutteursRepository.save(ficheGoutteurs);

        // IrrigationConfiguration
        IrrigationConfiguration irrigationConfiguration = Optional.ofNullable(fiche.getIrrigationConfiguration())
            .orElse(new IrrigationConfiguration());
        modelMapper.map(ficheFormDto, irrigationConfiguration);
        irrigationConfigurationRepository.save(irrigationConfiguration);

        // Ressource En eau
        FicheRessourcesEnEau ficheRessourcesEnEau = Optional.ofNullable(fiche.getFicheRessourcesEnEau())
            .orElse(new FicheRessourcesEnEau());
        modelMapper.map(ficheFormDto, ficheRessourcesEnEau);
        ficheRessourcesEnEauRepository.save(ficheRessourcesEnEau);
        saveFicheRessourcesEnEau(ficheFormDto,ficheRessourcesEnEau);
        return getFiche(fiche, ficheDocuments, ficheArboriculture, ficheMaraichage, ficheGoutteurs, irrigationConfiguration, ficheRessourcesEnEau, ficheDocumentFiles);
    }

    private Fiche saveFiche(int id ,FicheFormDTO ficheFormDto, Map<String, MultipartFile> fileMap) {
        FicheDocuments ficheDocuments = modelMapper.map(ficheFormDto, FicheDocuments.class);
        ficheDocumentsRepository.save(ficheDocuments);

        FicheDocumentFiles ficheDocumentFiles = new FicheDocumentFiles();
        setFicheDocumentFiles(fileMap, ficheDocumentFiles);
        ficheDocumentFilesRepository.save(ficheDocumentFiles);

        FicheArboriculture ficheArboriculture = modelMapper.map(ficheFormDto, FicheArboriculture.class);
        ficheArboricultureRepository.save(ficheArboriculture);

        FicheMaraichage ficheMaraichage = modelMapper.map(ficheFormDto, FicheMaraichage.class);
        ficheMaraichageRepository.save(ficheMaraichage);

        FicheGoutteurs ficheGoutteurs = modelMapper.map(ficheFormDto, FicheGoutteurs.class);
        ficheGoutteursRepository.save(ficheGoutteurs);

        IrrigationConfiguration irrigationConfiguration = modelMapper.map(ficheFormDto, IrrigationConfiguration.class);
        irrigationConfigurationRepository.save(irrigationConfiguration);

        FicheRessourcesEnEau ficheRessourcesEnEau = modelMapper.map(ficheFormDto, FicheRessourcesEnEau.class);
        ficheRessourcesEnEauRepository.save(ficheRessourcesEnEau);
        saveFicheRessourcesEnEau(ficheFormDto,ficheRessourcesEnEau);

        Fiche fiche = modelMapper.map(ficheFormDto, Fiche.class);
        fiche.setUser(userService.findById((long) id).get());
        return getFiche(fiche, ficheDocuments, ficheArboriculture, ficheMaraichage, ficheGoutteurs, irrigationConfiguration, ficheRessourcesEnEau,ficheDocumentFiles);
    }
    private void setFicheDocumentFiles(Map<String, MultipartFile> fileMap, FicheDocumentFiles ficheDocumentFiles) {
        for (Map.Entry<String, MultipartFile> entry : fileMap.entrySet()) {
            String fieldName = entry.getKey();
            try {
                MultipartFile file = entry.getValue();
                Field field = ficheDocumentFiles.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                byte[] bytes = file != null && !file.isEmpty() ? file.getBytes() : null;
                field.set(ficheDocumentFiles, bytes);
            } catch (NoSuchFieldException | IllegalAccessException | IOException e) {
                // Handle exception appropriately
            }
        }
    }

    public List<Fiche> getAllFicheByCommercial(String commercial) {
        List<Fiche> fiches = ficheRepository.findByCommercial(commercial);
        for (Fiche fiche : fiches) {
            fiche.setFicheDocumentFiles(null);
        }
        return fiches;
    }

    private Fiche getFiche(Fiche fiche, FicheDocuments ficheDocuments, FicheArboriculture ficheArboriculture, FicheMaraichage ficheMaraichage, FicheGoutteurs ficheGoutteurs, IrrigationConfiguration irrigationConfiguration, FicheRessourcesEnEau updatedFicheRessourcesEnEau, FicheDocumentFiles ficheDocumentFiles) {
        fiche.setFicheGoutteurs(ficheGoutteurs);
        fiche.setFicheMaraichage(ficheMaraichage);
        fiche.setFicheArboriculture(ficheArboriculture);
        fiche.setFicheDocuments(ficheDocuments);
        fiche.setFicheRessourcesEnEau(updatedFicheRessourcesEnEau);
        fiche.setIrrigationConfiguration(irrigationConfiguration);
        fiche.setFicheDocumentFiles(ficheDocumentFiles);
        fiche = ficheRepository.save(fiche);
        return fiche;
    }

    private void saveFicheRessourcesEnEau(FicheFormDTO ficheFormDto,FicheRessourcesEnEau ficheRessourcesEnEau) {

        // Clear the list of existing borneIrrigations, forages, and puits
        List<BorneIrrigation> borneIrrigationList = ficheRessourcesEnEau.getBornesIrrigation();
        if (borneIrrigationList != null) {
            borneIrrigationRepository.deleteAll(borneIrrigationList);
        }

        List<Forage> forageList = ficheRessourcesEnEau.getForages();
        if (forageList != null) {
            forageRepository.deleteAll(forageList);
        }

        List<Puit> puitList = ficheRessourcesEnEau.getPuits();
        if (puitList != null) {
            puitRepository.deleteAll(puitList);
        }
        borneIrrigationList = createResources(ficheFormDto.getBorneIrrigationData(), ficheRessourcesEnEau, BorneIrrigation.class, borneIrrigationRepository);
        puitList = createResources(ficheFormDto.getPuitData(), ficheRessourcesEnEau, Puit.class, puitRepository);
        forageList = createResources(ficheFormDto.getForageData(), ficheRessourcesEnEau, Forage.class, forageRepository);
        Bassin bassin = Optional.ofNullable(ficheRessourcesEnEau.getBassin()).orElse(new Bassin());
        modelMapper.map(ficheFormDto, bassin);
        bassinRepository.save(bassin);

        ficheRessourcesEnEau.setBassin(bassin);
        ficheRessourcesEnEau.setBornesIrrigation(borneIrrigationList);
        ficheRessourcesEnEau.setPuits(puitList);
        ficheRessourcesEnEau.setForages(forageList);
        ficheRessourcesEnEauRepository.save(ficheRessourcesEnEau);
    }

    private <T extends Ressource> List<T> createResources(List<RessourceDTO> dtoList, FicheRessourcesEnEau savedFicheRessourcesEnEau, Class<T> resourceClass, JpaRepository<T, Long> repository) {
        List<T> resourceList = new ArrayList<>();
        for (RessourceDTO dto : dtoList) {
            T resource = modelMapper.map(dto, resourceClass);
            resource.setCoordonnee(new Coordonnee(dto.getLatitude(), dto.getLongitude()));
            resource.setDebitSurPression(dto.getDebitSurPression());
            resource.setFicheRessourcesEnEau(savedFicheRessourcesEnEau);
            resourceList.add(resource);
        }
        return repository.saveAll(resourceList);
    }


    public List<Fiche> getAllFicheByUser(int idUser) {
        List<Fiche> fiches = ficheRepository.getAllFichesByUser(userService.findById((long) idUser).get());
        for (Fiche fiche : fiches) {
            fiche.setUser(null);
        }
        return fiches;
    }
}
