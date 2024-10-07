package com.portal.awesome_pizza.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ordini")
public class OrdineEntity {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_ordini", nullable = false)
    private Long idOrdini;
    private String nomeCliente;
    private String mailCliente;
    private Timestamp ordineTS;
    private String itemsOrdinati;
    private String statoOrdine;
    private String codOrdine;


}
