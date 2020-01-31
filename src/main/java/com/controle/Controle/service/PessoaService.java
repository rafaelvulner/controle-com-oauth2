package com.controle.Controle.service;

import com.controle.Controle.model.Pessoa;
import com.controle.Controle.repository.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa atualizar(Long codigo, Pessoa pessoa){
        Optional<Pessoa> pessoa1 = pessoaRepository.findById(codigo);

        BeanUtils.copyProperties(pessoa, pessoa1.get(), "codigo");

        return pessoaRepository.save(pessoa1.get());
    }

    public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo){

        Optional<Pessoa> pessoa1 = pessoaRepository.findById(codigo);

        pessoa1.get().setAtivo(ativo);

        pessoaRepository.save(pessoa1.get());


    }

}
