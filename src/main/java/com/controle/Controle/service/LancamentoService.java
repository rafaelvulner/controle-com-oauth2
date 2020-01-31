package com.controle.Controle.service;

import com.controle.Controle.exceptionHandler.exceptions.PessoaInexistenteOuInativaException;
import com.controle.Controle.model.Lancamento;
import com.controle.Controle.model.Pessoa;
import com.controle.Controle.repository.LancamentoRepository;
import com.controle.Controle.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LancamentoService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private LancamentoRepository lancamentoRepository;

    public Lancamento salvar(Lancamento lancamento) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo());
        if (pessoa.isPresent() || pessoa.get().getAtivo()) {
            throw new PessoaInexistenteOuInativaException();
        }

        return lancamentoRepository.save(lancamento);
    }
}
