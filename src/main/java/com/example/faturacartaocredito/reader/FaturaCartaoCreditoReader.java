package com.example.faturacartaocredito.reader;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.example.faturacartaocredito.domain.FaturaCartaoCredito;
import com.example.faturacartaocredito.domain.Transacao;

public class FaturaCartaoCreditoReader implements ItemStreamReader<FaturaCartaoCredito>{
	
	private ItemStreamReader<Transacao> delegate;
	private Transacao transacaoAtual;
	
	

	public FaturaCartaoCreditoReader(ItemStreamReader<Transacao> delegate) {
		this.delegate = delegate;
	}

	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		delegate.open(executionContext);
		
	}

	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
		delegate.update(executionContext);
		
	}

	@Override
	public void close() throws ItemStreamException {
		delegate.close();
		
	}

	@Override
	public FaturaCartaoCredito read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

		if (transacaoAtual == null) 
			transacaoAtual = delegate.read();
		
		
		FaturaCartaoCredito faturaCartaoCredito = null;
		Transacao transacao = transacaoAtual;
		transacaoAtual = null;
		
		if (transacao != null) {
			faturaCartaoCredito = new FaturaCartaoCredito();
			faturaCartaoCredito.setCartaoCredito(transacao.getCartaoCredito());
			faturaCartaoCredito.setCliente(transacao.getCartaoCredito().getCliente());
			faturaCartaoCredito.getTransacaos().add(transacao);
			
			while(isTransacaoRelacionada(transacao)) {
				faturaCartaoCredito.getTransacaos().add(transacaoAtual);
				
			}
			
		}
		
		

		return faturaCartaoCredito;
	}

	private boolean isTransacaoRelacionada(Transacao transacao) throws Exception {
		return peek() != null && transacao.getCartaoCredito().getNumeroCartaoCredito() == transacaoAtual.getCartaoCredito().getNumeroCartaoCredito();
	}

	private Transacao peek() throws Exception {
		transacaoAtual = delegate.read();
		return transacaoAtual;
	}

}
