package com.example.faturacartaocredito.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.faturacartaocredito.domain.FaturaCartaoCredito;

@Configuration
public class FaturaCartaoCreditoStepConfig {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Step faturaCartaoCreditoStep(
			ItemReader<FaturaCartaoCredito> lerTRansacoesReader,
			ItemProcessor<FaturaCartaoCredito, FaturaCartaoCredito> carregaDadosClienteProcessor,
			ItemWriter<FaturaCartaoCredito> escreverFaturaCartaoCredito) {
		return stepBuilderFactory
				.get("faturaCartaoCreditoStep")
				.<FaturaCartaoCredito, FaturaCartaoCredito>chunk(1)
				.reader(lerTRansacoesReader)
				.processor(carregaDadosClienteProcessor)
				.writer(escreverFaturaCartaoCredito)
				.build();
		
	}
	
	
	
}
