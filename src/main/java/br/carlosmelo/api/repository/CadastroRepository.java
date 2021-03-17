package br.carlosmelo.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.carlosmelo.api.model.Cadastro;

public interface CadastroRepository extends JpaRepository<Cadastro, Long>{

}
