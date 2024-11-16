package com.api.av2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.av2.model.Carro;


public interface CarroRepository extends JpaRepository<Carro, Long> {
    List<Carro> findByCidadeContainingAndMarcaContaining(String cidade, String marca);
}
