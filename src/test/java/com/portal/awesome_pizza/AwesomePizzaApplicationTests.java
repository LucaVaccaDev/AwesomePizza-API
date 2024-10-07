package com.portal.awesome_pizza;

import com.portal.awesome_pizza.entity.OrdineEntity;
import com.portal.awesome_pizza.model.OrdinePojo;
import com.portal.awesome_pizza.model.ResponsePojo;
import com.portal.awesome_pizza.repository.OrdiniRepository;
import com.portal.awesome_pizza.serviceImpl.OrdineServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = AwesomePizzaApplication.class)
@RunWith(MockitoJUnitRunner.class)
class AwesomePizzaApplicationTests {

    OrdinePojo ordinePojo = new OrdinePojo("Luca Vacca", "lucavacca94@gmail.com", null, "Margherita,capricciosa", null, null, null);


    @InjectMocks
    private OrdineServiceImpl service;
    @Mock
    private OrdiniRepository repository;
    @Mock
    private OrdineEntity ordineEntity;

    @Test
    void ordinaTest() {
        OrdineEntity ordineEntity = new OrdineEntity();
        ordineEntity.setIdOrdini(Long.valueOf(1));
        when(repository.save(any(OrdineEntity.class))).thenReturn(ordineEntity);

        ResponsePojo response = service.ordina(ordinePojo);
        Assert.assertTrue(response.getMessaggio() != null);
        Assert.assertTrue(response.getStatus() != null);
        Assert.assertEquals("OK", response.getStatus());
    }


    @Test
    void getOrdiniTest() {
        List<OrdineEntity> entityList = new ArrayList<>();

        OrdineEntity ordineEntity1 = new OrdineEntity();
        ordineEntity1.setNomeCliente("Luca Vacca");
        ordineEntity1.setMailCliente("luca@mail.it");
        ordineEntity1.setOrdineTS(Timestamp.from(Instant.now()));
        ordineEntity1.setMailCliente("Margherita, marinara, capricciosa");
        ordineEntity1.setStatoOrdine("Ordine ricevuto");
        ordineEntity1.setCodOrdine("AW_c45");
        ordineEntity1.setIdOrdini(Long.valueOf(1));
        entityList.add(ordineEntity1);


        OrdineEntity ordineEntity2 = new OrdineEntity();
        ordineEntity2.setNomeCliente("Pippo Vacca");
        ordineEntity2.setMailCliente("pippo@mail.it");
        ordineEntity2.setOrdineTS(Timestamp.from(Instant.now()));
        ordineEntity2.setMailCliente("Margherita, marinara, capricciosa");
        ordineEntity2.setStatoOrdine("Ordine ricevuto");
        ordineEntity2.setCodOrdine("AW_c46");
        ordineEntity2.setIdOrdini(Long.valueOf(1));
        entityList.add(ordineEntity2);

        when(repository.findAll()).thenReturn(entityList);

        ResponsePojo response = service.getOrdini(null, null);
        Assert.assertTrue(response.getMessaggio() != null);
        Assert.assertTrue(response.getStatus() != null);
        Assert.assertTrue(response.getData() != null);
        Assert.assertEquals("OK", response.getStatus());
    }


    @Test
    void getOrdiniByStatoTest() {
        List<OrdineEntity> entityList = new ArrayList<>();

        OrdineEntity ordineEntity2 = new OrdineEntity();
        ordineEntity2.setNomeCliente("Pippo Vacca");
        ordineEntity2.setMailCliente("pippo@mail.it");
        ordineEntity2.setOrdineTS(Timestamp.from(Instant.now()));
        ordineEntity2.setMailCliente("Margherita, marinara, capricciosa");
        ordineEntity2.setStatoOrdine("In lavorazione");
        ordineEntity2.setCodOrdine("AW_c46");
        ordineEntity2.setIdOrdini(Long.valueOf(1));
        entityList.add(ordineEntity2);

        when(repository.findByStatoOrdine("In lavorazione")).thenReturn(entityList);

        ResponsePojo<List<OrdinePojo>> response = service.getOrdini("In lavorazione", null);
        Assert.assertTrue(response.getMessaggio() != null);
        Assert.assertTrue(response.getStatus() != null);
        Assert.assertTrue(response.getData() != null);
        Assert.assertEquals("In lavorazione", response.getData().get(0).getStatoOrdine());
        Assert.assertEquals("OK", response.getStatus());
    }


    @Test
    void getOrdineById() {
        OrdineEntity ordineEntity2 = new OrdineEntity();
        ordineEntity2.setNomeCliente("Pippo Vacca");
        ordineEntity2.setMailCliente("pippo@mail.it");
        ordineEntity2.setOrdineTS(Timestamp.from(Instant.now()));
        ordineEntity2.setMailCliente("Margherita, marinara, capricciosa");
        ordineEntity2.setStatoOrdine("In lavorazione");
        ordineEntity2.setCodOrdine("AW_c46");
        ordineEntity2.setIdOrdini(Long.valueOf(1));

        when(repository.findByCodOrdine("AW_c46")).thenReturn(ordineEntity2);

        ResponsePojo<OrdinePojo> response = service.getOrdineById("AW_c46");
        Assert.assertTrue(response.getMessaggio() != null);
        Assert.assertTrue(response.getStatus() != null);
        Assert.assertTrue(response.getData() != null);
        Assert.assertEquals("AW_c46", response.getData().getCodOrdine());
        Assert.assertEquals("OK", response.getStatus());
    }


}
