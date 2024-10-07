package com.portal.awesome_pizza.controller;

import com.portal.awesome_pizza.model.OrdinePojo;
import com.portal.awesome_pizza.model.ResponsePojo;
import com.portal.awesome_pizza.service.OrdineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class OrdiniController {
    /**
     * Come pizzeria "Awesome Pizza" voglio creare il mio nuovo portale per gestire gli ordini dei miei clienti.
     * Il portale non richiede la registrazione dell'utente per poter ordinare le sue pizze.
     * Il pizzaiolo vede la coda degli ordini e li può prendere in carico uno alla volta.
     * Quando la pizza è pronta, il pizzaiolo passa all'ordine successivo.
     * L'utente riceve il suo codice d'ordine e può seguire lo stato dell'ordine fino all'evasione.
     * Come team, procediamo allo sviluppo per iterazioni.
     * Decidiamo che nella prima iterazione non sarà disponibile un'interfaccia grafica, ma verranno create delle API al fine di ordinare le pizze e aggiornarne lo stato.
     * Decidiamo di utilizzare il framework Spring e Java (versione a tua scelta).
     * Decidiamo di progettare anche i test di unità sul codice oggetto di sviluppo
     */

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
