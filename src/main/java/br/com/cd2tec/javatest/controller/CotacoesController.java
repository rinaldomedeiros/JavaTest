package br.com.cd2tec.javatest.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.cd2tec.javatest.controller.dto.CotacaoDto;
import br.com.cd2tec.javatest.controller.dto.CotacaoFormDto;
import br.com.cd2tec.javatest.controller.dto.StatusDto;
import br.com.cd2tec.javatest.model.Cotacao;
import br.com.cd2tec.javatest.repository.CotacaoRepository;
import br.com.cd2tec.javatest.service.ApiCepService;
import br.com.cd2tec.javatest.service.CotacaoService;

@RestController
@RequestMapping("/cotacoes")
public class CotacoesController {

	@Autowired
	private CotacaoRepository cotacaoRepository;
	
	@Autowired
	private CotacaoService cotacaoService;
	
	@GetMapping
	public List<CotacaoDto> listar(){
		List<Cotacao> cotacoes = cotacaoRepository.findAll();
		return CotacaoDto.converter(cotacoes);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar (@Valid @RequestBody CotacaoFormDto form, UriComponentsBuilder uriBuilder){
		if(cotacaoService.validaCep(form) != null) {
			return cotacaoService.validaCep(form);
		}
		
		Cotacao cotacao = form.converter(cotacaoService.getFreteDataEntrega(form.getPeso(), form.getCepOrigem(), form.getCepDestino()));
		cotacaoRepository.save(cotacao);
		URI uri = uriBuilder.path("/cotacoes/{id}").buildAndExpand(cotacao.getId()).toUri();
		return ResponseEntity.created(uri).body(new CotacaoDto(cotacao));
	}

	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> detalhar(@PathVariable Long id) {
		Optional<Cotacao> cotacao = cotacaoRepository.findById(id);
		if(cotacao.isPresent()) {
			return ResponseEntity.ok(new CotacaoDto(cotacao.get()));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StatusDto("Cotação não encontrada."));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> atualizar (@PathVariable(value = "id") Long id, @Valid @RequestBody CotacaoFormDto form){
		Optional<Cotacao> optional = cotacaoRepository.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StatusDto("Categoria não encontrada."));
		}
		
		if(cotacaoService.validaCep(form) != null) {
			return cotacaoService.validaCep(form);
		}
		
		Cotacao cotacao = form.converter(cotacaoService.getFreteDataEntrega(form.getPeso(), form.getCepOrigem(), form.getCepDestino()));
		Cotacao cotacaoAtualizada = form.atualizar(id, cotacaoRepository);
		cotacaoAtualizada.setDataConsulta(cotacao.getDataConsulta());
		cotacaoAtualizada.setDataPrevistaEntrega(cotacao.getDataPrevistaEntrega());
		cotacaoAtualizada.setVlTotalFrete(cotacao.getVlTotalFrete());
		return ResponseEntity.ok(new CotacaoDto(cotacaoAtualizada));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id){
		Optional<Cotacao> optional = cotacaoRepository.findById(id);
		if(optional.isPresent()) {
			cotacaoRepository.delete(optional.get());
			return ResponseEntity.status(HttpStatus.OK).body(new StatusDto("Excluído com sucesso."));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StatusDto("Erro ao excluir."));
	}
}
