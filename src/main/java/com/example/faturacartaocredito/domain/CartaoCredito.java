package com.example.faturacartaocredito.domain;

public class CartaoCredito {

	private int numeroCartaoCredito;
	private Cliente cliente;

	public int getNumeroCartaoCredito() {
		return numeroCartaoCredito;
	}

	public void setNumeroCartaoCredito(int numeroCartaoCredito) {
		this.numeroCartaoCredito = numeroCartaoCredito;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "CartaoCredito [numeroCartaoCredito=" + numeroCartaoCredito + ", cliente=" + cliente + "]";
	}

}
