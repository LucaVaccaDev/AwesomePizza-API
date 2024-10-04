package com.portal.awesome_pizza.model;

import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class OrdinePojo {
    public String nomeCliente;
    public String mailCliente;
    public Timestamp ordineTS;
    public String itemsOrdinati;
    public String statoOrdine;
    public String codOrdine;
    private Long idOrdini;
}
