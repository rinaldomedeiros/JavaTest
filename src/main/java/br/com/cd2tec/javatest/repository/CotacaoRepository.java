package br.com.cd2tec.javatest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cd2tec.javatest.model.Cotacao;

@Repository
public interface CotacaoRepository extends JpaRepository<Cotacao, Long> {

}
