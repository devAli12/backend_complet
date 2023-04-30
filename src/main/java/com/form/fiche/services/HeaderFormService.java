package com.form.fiche.services;

import com.form.fiche.entities.HeaderForm;
import com.form.fiche.repositories.HeaderFormRepository;
import org.springframework.stereotype.Service;


@Service
public class HeaderFormService {
    final private HeaderFormRepository headerFormRepository;

    public HeaderFormService(HeaderFormRepository headerFormRepository) {
        this.headerFormRepository = headerFormRepository;
    }

    public HeaderForm getHeader(){
        return headerFormRepository.findAll().get(0);
    }

    public HeaderForm updateHeader(HeaderForm headerForm){
        HeaderForm existingHeader = getHeader();
        if(headerForm.getCodification() != "")
            existingHeader.setCodification(headerForm.getCodification());
        if(headerForm.getVersion() != "")
            existingHeader.setVersion(headerForm.getVersion());
        existingHeader.setDateEtablir(headerForm.getDateEtablir());

        HeaderForm updatedHeaderForm = headerFormRepository.save(existingHeader);
        return updatedHeaderForm ;
    }
}
