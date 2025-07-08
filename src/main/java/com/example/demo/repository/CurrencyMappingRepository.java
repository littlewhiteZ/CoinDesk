package com.example.demo.repository;

import com.example.demo.entity.CurrencyMapping;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CurrencyMappingRepository extends JpaRepository<CurrencyMapping, Long> {

    Optional<CurrencyMapping> findByCode(String code);

    @Query("SELECT c FROM CurrencyMapping c WHERE c.code IN :codes")
    List<CurrencyMapping> findByCodes(@Param("codes") Set<String> codes);
}
