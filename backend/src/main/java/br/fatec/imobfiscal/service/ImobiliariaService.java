package br.fatec.imobfiscal.service;

import br.fatec.imobfiscal.model.Imobiliaria;
import br.fatec.imobfiscal.enums.PlanoAssinatura;
import br.fatec.imobfiscal.repository.ImobiliariaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImobiliariaService {

    private final ImobiliariaRepository repository;

    public Optional<Imobiliaria> buscarPorId(UUID id) {
        return repository.findByIdAndDeletedAtIsNull(id);
    }

    public boolean existePorCnpj(String cnpj) {
        return repository.existsByCnpj(cnpj);
    }

    public Imobiliaria criar(String nome, String cnpj, String email) {
        Imobiliaria imobiliaria = new Imobiliaria();
        imobiliaria.setRazao(nome);
        imobiliaria.setNomeFantasia(nome);
        imobiliaria.setCnpj(cnpj);
        imobiliaria.setEmail(email);
        imobiliaria.setPlano(PlanoAssinatura.BASICO);
        return repository.save(imobiliaria);
    }
}
