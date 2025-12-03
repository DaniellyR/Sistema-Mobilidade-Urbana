package entidades;

import excecoes.PagamentoRecusadoException;
import excecoes.SaldoInsuficienteException;

public class Pix implements MetodoPagamento{
	
	private String chavePix;
	
	public Pix(String chavePix) {
		this.chavePix = chavePix;
	}

	@Override
	//caso a chave pix falhe
	public void processarPagamento(double valor) throws PagamentoRecusadoException, SaldoInsuficienteException {
		if(chavePix == null || chavePix.isEmpty()) {
			throw new PagamentoRecusadoException("Chave inválida ou não informada.");
		}
		System.out.println("Pagamento realizado de R$" + valor + " via PIX (Chave: " + chavePix + ").");
	}
//set e get
	public String getChavePix() {
		return chavePix;
	}

	public void setChavePix(String chavePix) {
		this.chavePix = chavePix;
	}

}
