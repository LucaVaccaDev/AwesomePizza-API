package com.portal.awesome_pizza.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdinePojo {
    public String nomeCliente;
    public String mailCliente;
    public Timestamp ordineTS;
    public String itemsOrdinati;
    public String statoOrdine;
    public String codOrdine;
    private Long idOrdini;
}
