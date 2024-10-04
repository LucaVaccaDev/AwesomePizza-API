package com.portal.awesome_pizza.service;

import com.portal.awesome_pizza.model.OrdinePojo;
import com.portal.awesome_pizza.model.ResponsePojo;

public interface OrdineService {


    ResponsePojo ordina(OrdinePojo ordinePojo);

    ResponsePojo getOrdini(String stato, String data);

    ResponsePojo getOrdineById(String codOrdine);

    ResponsePojo aggiornaStato(String codOrdine, String stato);

    ResponsePojo updateOrdine(OrdinePojo ordinePojo);

    ResponsePojo deleteOrdineById(String codOrdine);
}
