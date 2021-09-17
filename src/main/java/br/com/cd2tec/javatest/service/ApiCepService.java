package br.com.cd2tec.javatest.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.cd2tec.javatest.controller.dto.RetornoApiCepDto;

@Service
public class ApiCepService {

	    public RetornoApiCepDto chamarApiExterna(String cep) {
	    	String URI = "https://viacep.com.br/ws/"+ cep +"/json/";
	        RestTemplate rest = new RestTemplate();
	        RetornoApiCepDto retorno = rest.getForEntity(URI, RetornoApiCepDto.class).getBody();

	        return retorno;
	    }
}
