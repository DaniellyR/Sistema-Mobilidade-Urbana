package entidades;

import java.util.Date;
import excecoes.PagamentoRecusadoException;
import excecoes.SaldoInsuficienteException;
public class CartaoDeCredito implements MetodoPagamento{
	private String nomeTitular;
	private String numero;
	private String cvc;
	private Date dataValidade;
	
	public CartaoDeCredito(String nomeTitular, String numero, String cvc, Date dataValidade) {
		
		this.nomeTitular = nomeTitular;
		this.numero = numero;
		this.cvc = cvc;
		this.dataValidade = dataValidade;
	}

	@Override
	public void processarPagamento(double valor) throws PagamentoRecusadoException, SaldoInsuficienteException {
		//verifica se o cartão falhou 
		if(!autorizar()) {
		//se sim, ele pega os ultimos 4 digitos e mostra para o usuário, se o cartão tinha menos de 4 digitos ele mostra "xxxx"
			String finalCartao = (numero.length() >= 4) ? numero.substring(numero.length() - 4) : "xxxx";
		throw new PagamentoRecusadoException("Operadora recusou o cartão com final " + finalCartao);
		}
		System.out.println("Pagamento de R$" + valor);
	}

	private boolean autorizar() {
		return Math.random() < 0.9;
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
