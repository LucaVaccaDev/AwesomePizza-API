package com.portal.awesome_pizza.serviceImpl;

import com.mysql.cj.util.StringUtils;
import com.portal.awesome_pizza.Utility.Constants;
import com.portal.awesome_pizza.entity.OrdineEntity;
import com.portal.awesome_pizza.model.OrdinePojo;
import com.portal.awesome_pizza.model.ResponsePojo;
import com.portal.awesome_pizza.repository.OrdiniRepository;
import com.portal.awesome_pizza.service.OrdineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class OrdineServiceImpl implements OrdineService {

    @Autowired
    OrdiniRepository repository;

    ResponsePojo response;
    OrdineEntity ordineEntity;

    @Override
    public ResponsePojo ordina(OrdinePojo ordinePojo) {
        log.info("OrdineServiceImpl.ordina - Start ");
        ordineEntity = new OrdineEntity();

        try {
            if (ordinePojo != null) {
                /**
                 *Per un Implementazione futura, salvo il TimeStamp nel database, poichè se un giorno si volesse, si potrebbe fare analisi sugli ordini effettuati nei giorni della settimana, in modo da reperire informazioni importanti quali:
                 * - Pizze ordinate con maggior frequenza in modo da essere pronti con gli ingredienti
                 * - Clienti affezzionati quindi implementare soluzioni di fidelizzazione per i clienti
                 * - Periodi di lavoro più scarico in modo da ottimizzare le risorse energetiche e alimentari
                 * Gli scenari possono essere infiniti, quindi mi sembrava giusto avere uno storico degli ordini e dei clienti.
                 */
                ordinePojo.setOrdineTS(Timestamp.from(Instant.now()));
                ordinePojo.setStatoOrdine(Constants.RICEVUTO);
                ordinePojo.setCodOrdine("AW_" + UUID.randomUUID().toString().substring(0, 3));

                BeanUtils.copyProperties(ordinePojo, ordineEntity);
                ordineEntity = repository.save(ordineEntity);
                if (ordineEntity.getIdOrdini() != null) {
                    response = new ResponsePojo().success("L'ordine per : " + ordinePojo.getNomeCliente() + " con l'id ordine " + ordinePojo.getCodOrdine() + " è stato genetato con successo", null);
                }

            } else {
                response = new ResponsePojo().error("Nessun ordine inserito", null);
                log.error("ERROR ::: OrdineServiceImpl.ordina   :::  Nessun ordine disponibile");
            }

        } catch (Exception e) {
            log.error("ERROR ::: OrdineServiceImpl.ordina  :::  " + e.getCause());
            response = new ResponsePojo().error(e.getMessage(), null);
        }
        log.info("OrdineServiceImpl.ordina - End ");

        return response;
    }

    @Override
    public ResponsePojo getOrdini(String stato, String data) {
        log.info("OrdineServiceImpl.getOrdini - Start ");
        try {
            List<OrdineEntity> entityList;
            if (!StringUtils.isNullOrEmpty(stato) && !StringUtils.isNullOrEmpty(data)) {
                entityList = repository.findByDateAndStato(data, stato);
            } else if (!StringUtils.isNullOrEmpty(stato)) {
                entityList = repository.findByStatoOrdine(stato);
            } else if (!StringUtils.isNullOrEmpty(data)) {
                entityList = repository.findByDate(data);
            } else {
                entityList = repository.findAll();
            }

            List<OrdinePojo> pojoList = new ArrayList<>();
            for (OrdineEntity item : entityList) {
                OrdinePojo ordine = new OrdinePojo();
                BeanUtils.copyProperties(item, ordine);
                pojoList.add(ordine);
            }
            response = new ResponsePojo().success("Elenco ordini disponibili", pojoList);
        } catch (Exception e) {
            log.error("ERROR ::: OrdineServiceImpl.getOrdini  :::  " + e.getCause());
            response = new ResponsePojo().error(e.getMessage(), null);
        }
        log.info("OrdineServiceImpl.getOrdini - End ");
        return response;
    }


    @Override
    public ResponsePojo getOrdineById(String codOrdine) {
        log.info("OrdineServiceImpl.getOrdineById - Start ");
        ordineEntity = new OrdineEntity();
        try {

            if (codOrdine != null) {
                ordineEntity = repository.findByCodOrdine(codOrdine);
                OrdinePojo ordine = new OrdinePojo();
                BeanUtils.copyProperties(ordineEntity, ordine);
                response = new ResponsePojo().success("Ordine " + ordine.getCodOrdine(), ordine);
            } else {
                response = new ResponsePojo().error("Nessun ordine trovato per il codice ordine : " + codOrdine, null);
            }

        } catch (Exception e) {
            log.error("ERROR ::: OrdineServiceImpl.getOrdineById  :::  " + e.getCause());
            response = new ResponsePojo().error(e.getMessage(), null);
        }
        log.info("OrdineServiceImpl.getOrdineById - End ");
        return response;
    }


    @Override
    public ResponsePojo updateOrdine(OrdinePojo ordinePojo) {
        log.info("OrdineServiceImpl.updateOrdine - Start ");
        ordineEntity = new OrdineEntity();

        try {
            if (ordinePojo != null) {
                ordinePojo.setOrdineTS(Timestamp.from(Instant.now()));
                BeanUtils.copyProperties(ordinePojo, ordineEntity);
                ordineEntity = repository.save(ordineEntity);
                if (ordineEntity.getIdOrdini() != null) {
                    response = new ResponsePojo().success("L'ordine per : " + ordinePojo.getNomeCliente() + " con l'id ordine " + ordinePojo.getCodOrdine() + " è stato modificato con successo", null);
                }

            } else {
                response = new ResponsePojo().error("Nessun ordine inserito", null);
                log.error("ERROR ::: OrdineServiceImpl.updateOrdine   :::  Nessun ordine disponibile");
            }

        } catch (Exception e) {
            log.error("ERROR ::: OrdineServiceImpl.updateOrdine  :::  " + e.getCause());
            response = new ResponsePojo().error(e.getMessage(), null);
        }
        log.info("OrdineServiceImpl.updateOrdine - End ");

        return response;
    }

    @Override
    public ResponsePojo deleteOrdineById(String codOrdine) {
        log.info("OrdineServiceImpl.deleteOrdineById - Start ");
        ordineEntity = new OrdineEntity();
        try {
            if (!StringUtils.isNullOrEmpty(codOrdine)) {
                ordineEntity = repository.findByCodOrdine(codOrdine);
                repository.deleteById(ordineEntity.getIdOrdini());
                Optional<OrdineEntity> ord = repository.findById(ordineEntity.getIdOrdini());
                if (!ord.isPresent()) {
                    response = new ResponsePojo().success("Ordine " + codOrdine + " cancellato con successo!", null);
                } else {
                    response = new ResponsePojo().error("Errore durante la cancellazione dell'ordine " + codOrdine, null);
                }
            } else {
                response = new ResponsePojo().error("Errore durante la cancellazione dell'ordine " + codOrdine, null);
            }
        } catch (Exception e) {
            log.error("ERROR ::: OrdineServiceImpl.deleteOrdineById  :::  " + e.getCause());
            response = new ResponsePojo().error(e.getMessage(), null);
        }
        log.info("OrdineServiceImpl.deleteOrdineById - Start ");
        return response;
    }

    @Override
    public ResponsePojo aggiornaStato(String codOrdine, String stato) {
        log.info("OrdineServiceImpl.aggiornaStato - Start ");
        ordineEntity = new OrdineEntity();
        try {
            if (!StringUtils.isNullOrEmpty(codOrdine) && !StringUtils.isNullOrEmpty(stato)) {
                ordineEntity = repository.findByCodOrdine(codOrdine);
                ordineEntity.setStatoOrdine(stato);
                repository.save(ordineEntity);
                response = new ResponsePojo().success("Ordine " + codOrdine + " aggiornato con successo!", null);
            } else {
                response = new ResponsePojo().error("Nessun ordine presente", null);
                log.error("ERROR ::: OrdineServiceImpl.updateOrdine   :::  Nessun ordine presente");
            }

        } catch (Exception e) {
            log.error("ERROR ::: OrdineServiceImpl.aggiornaStato  :::  " + e.getCause());
            response = new ResponsePojo().error(e.getMessage(), null);
        }
        log.info("OrdineServiceImpl.aggiornaStato - End ");
        return response;
    }


}
