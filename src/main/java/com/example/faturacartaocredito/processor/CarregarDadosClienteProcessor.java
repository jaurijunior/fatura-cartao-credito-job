package com.example.faturacartaocredito.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.faturacartaocredito.domain.Cliente;
import com.example.faturacartaocredito.domain.FaturaCartaoCredito;

@Component
public class CarregarDadosClienteProcessor implements ItemProcessor<FaturaCartaoCredito, FaturaCartaoCredito>{
	

	@Override
	public FaturaCartaoCredito process(FaturaCartaoCredito item) throws Exception {
		
		RestTemplate restTemplate = new RestTemplate(); 
		
		String uri = String.format("http://my-json-server.typicode.com/jaurijunior/demo/clientes/%d", item.getCliente().getId());
		ResponseEntity<Cliente> response = restTemplate.getForEntity(uri, Cliente.class);
		
		if (response.getStatusCode() != HttpStatus.OK) 
			throw new ValidationException("Cliente não encontrado");
			
		item.setCliente(response.getBody());
		return item;
	}

}
