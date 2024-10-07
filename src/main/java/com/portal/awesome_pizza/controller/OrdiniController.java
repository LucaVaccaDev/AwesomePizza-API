package com.portal.awesome_pizza.controller;

import com.portal.awesome_pizza.model.OrdinePojo;
import com.portal.awesome_pizza.model.ResponsePojo;
import com.portal.awesome_pizza.service.OrdineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class OrdiniController {

    @Autowired
    OrdineService service;


    @PostMapping("/ordina")
    public ResponsePojo ordina(@RequestBody OrdinePojo ordinePojo) {
        return service.ordina(ordinePojo);
    }

    @GetMapping("/ordini")
    public ResponsePojo getOrdini(@RequestParam(name = "stato", required = false) String stato, @RequestParam(name = "data", required = false) String data) {
        return service.getOrdini(stato, data);
    }

    @GetMapping("/ordine/{codOrdine}")
    public ResponsePojo getOrdineById(@PathVariable("codOrdine") String codOrdine) {
        return service.getOrdineById(codOrdine);
    }

    @PutMapping("/ordine")
    public ResponsePojo updateOrdine(@RequestBody OrdinePojo ordinePojo) {
        return service.updateOrdine(ordinePojo);
    }

    @DeleteMapping("/ordine/{codOrdine}")
    public ResponsePojo deleteOrdineById(@PathVariable("codOrdine") String codOrdine) {
        return service.deleteOrdineById(codOrdine);
    }

    @PostMapping("/aggiornaStato/{codOrdine}/{stato}")
    public ResponsePojo aggiornaStato(@PathVariable("codOrdine") String codOrdine, @PathVariable("stato") String stato) {
        return service.aggiornaStato(codOrdine, stato);
    }

}
