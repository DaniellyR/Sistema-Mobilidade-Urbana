package entidades;

import java.util.Date;
import excecoes.PagamentoRecusadoException;
import excecoes.SaldoInsuficienteException;

public class CartaoDeDebito implements MetodoPagamento{
	private String nomeTitular;
	private String numero;
	private String cvc;
	private Date dataValidade;
	
	public CartaoDeDebito(String nomeTitular, String numero, String cvc, Date dataValidade) {
		
		this.nomeTitular = nomeTitular;
		this.numero = numero;
		this.cvc = cvc;
		this.dataValidade = dataValidade;
	}

	@Override
	public void processarPagamento(double valor) throws PagamentoRecusadoException, SaldoInsuficienteException {
		if(!autorizar()) {
			String finalCartao = (numero.length() >= 4) ? numero.substring(numero.length() - 4) : "xxxx";
		
			throw new PagamentoRecusadoException("operadora recusou o cartão com final " + finalCartao);
		}
		System.out.println("Pagamento de R$" + valor);
	}

	private boolean autorizar() {
		return true;
	}

	public String getNomeTitular() {
		return nomeTitular;
	}

	public void setNomeTitular(String nomeTitular) {
		this.nomeTitular = nomeTitular;
	}

	public String getCvc() {
		return cvc;
	}

	public void setCvc(String cvc) {
		this.cvc = cvc;
	}

	public Date getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}
	
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
}
