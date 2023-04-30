package com.form.fiche.controllers;

import com.form.fiche.entities.HeaderForm;
import com.form.fiche.services.HeaderFormService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/headerForm")
public class HeaderFormControler {
    final private HeaderFormService headerFormService;

    public HeaderFormControler(HeaderFormService headerFormService) {
        this.headerFormService = headerFormService;
    }

    @GetMapping("/header")
    public HeaderForm getHeader(){
        return headerFormService.getHeader();
    }

    @PutMapping("/updateHeader")
    public HeaderForm updateUser(@RequestBody HeaderForm headerForm) {
        return headerFormService.updateHeader(headerForm) ;
    }

}
