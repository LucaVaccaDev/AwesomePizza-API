package com.portal.awesome_pizza.repository;

import com.portal.awesome_pizza.entity.OrdineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdiniRepository extends JpaRepository<OrdineEntity, Long> {

    @Query(value = "SELECT * FROM ordini where  DATE(ordini.ordinets ) = :data and ordini.stato_ordine = :statoOrdine", nativeQuery = true)
    List<OrdineEntity> findByDateAndStato(@Param("data") String data, @Param("statoOrdine") String statoOrdine);

    @Query(value = "SELECT * FROM ordini where ordini.stato_ordine = :statoOrdine", nativeQuery = true)
    List<OrdineEntity> findByStatoOrdine(@Param("statoOrdine") String statoOrdine);

    @Query(value = "SELECT * FROM ordini where DATE(ordini.ordinets ) = :data ", nativeQuery = true)
    List<OrdineEntity> findByDate(@Param("data") String data);

    @Query(value = "SELECT * FROM ordini where ordini.cod_ordine = :codOrdine ", nativeQuery = true)
    OrdineEntity findByCodOrdine(@Param("codOrdine") String codOrdine);

    @Query(value = "DELETE FROM ordini where ordini.cod_ordine = :codOrdine ", nativeQuery = true)
    void deleteByCodOrdine(@Param("codOrdine") String codOrdine);
}
