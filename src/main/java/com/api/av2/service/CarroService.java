package com.api.av2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.av2.model.Carro;
import com.api.av2.repository.CarroRepository;

@Service
public class CarroService {
	
    private final CarroRepository carroRepository;
    
    @Autowired
    public CarroService(CarroRepository carroRepository) {
        this.carroRepository = carroRepository;
    }

    public Carro createCarro(Carro carro) {
        return carroRepository.save(carro);
    }

    public List<Carro> listarCarro(String cidade, String marca) {
        return carroRepository.findByCidadeContainingAndMarcaContaining(
                cidade != null ? cidade : "", marca != null ? marca : "");
    }

    public Optional<Carro> buscarCarroPorId(Long id) {
        return carroRepository.findById(id);
    }

    public Carro updateCarro(Long id, Carro carroAtualizado) {
        return carroRepository.findById(id)
                .map(carro -> {
                    carro.setNome(carroAtualizado.getNome());
                    carro.setMarca(carroAtualizado.getMarca());
                    carro.setAno(carroAtualizado.getAno());
                    carro.setPreco(carroAtualizado.getPreco());
                    carro.setPlaca(carroAtualizado.getPlaca());
                    carro.setCidade(carroAtualizado.getCidade());
                    return carroRepository.save(carro);
                })
                .orElseThrow(() -> new RuntimeException("Carro não encontrado"));
    }
    

    public void deleteCarro(Long id) {
        carroRepository.deleteById(id);
    }
}
