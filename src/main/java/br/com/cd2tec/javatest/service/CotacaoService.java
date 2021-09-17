package br.com.cd2tec.javatest.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.cd2tec.javatest.controller.dto.CotacaoDto;
import br.com.cd2tec.javatest.controller.dto.CotacaoFormDto;
import br.com.cd2tec.javatest.controller.dto.RetornoApiCepDto;
import br.com.cd2tec.javatest.controller.dto.StatusDto;

@Service
public class CotacaoService {

	@Autowired
	ApiCepService cepService;

	public CotacaoDto getFreteDataEntrega(BigDecimal peso, String cepOrigem, String cepDestino) {
		
		BigDecimal vlFrete;
		LocalDate dataEntrega;
		LocalDate dataConsulta = LocalDate.now();
		
		RetornoApiCepDto detalhesCepOrigem = cepService.chamarApiExterna(cepOrigem);
		RetornoApiCepDto detalhesCepDestino = cepService.chamarApiExterna(cepDestino);
		
		if(detalhesCepOrigem.getDdd().equals(detalhesCepDestino.getDdd())) {
			dataEntrega = LocalDate.now().plusDays(1);
			vlFrete = peso.multiply(new BigDecimal(0.5));
		}
		else if(detalhesCepOrigem.getUf().equals(detalhesCepDestino.getUf())){
			dataEntrega = LocalDate.now().plusDays(3);
			vlFrete = peso.multiply(new BigDecimal(0.25));
		}else {
			dataEntrega = LocalDate.now().plusDays(10);
			vlFrete = peso.multiply(new BigDecimal(1));
		}
		return new CotacaoDto(vlFrete, dataEntrega, dataConsulta);
	}

	public ResponseEntity<StatusDto> validaCep(CotacaoFormDto form) {
		String bairroOrigem = cepService.chamarApiExterna(form.getCepOrigem().toString()).getBairro();
		if(bairroOrigem == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StatusDto("CEP de origem inválido."));
		}
		
		String bairroDestino = cepService.chamarApiExterna(form.getCepDestino().toString()).getBairro();
		if(bairroDestino == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StatusDto("CEP de destino inválido."));
			
		}
		return null;
	}
	
}
