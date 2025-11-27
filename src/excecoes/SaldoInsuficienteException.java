package excecoes;

public class SaldoInsuficienteException extends MobilidadeUrbanaException {
	private static final long serialVersionUID = 1L;
	  
	public SaldoInsuficienteException(String mensagem) {
		super(mensagem);
	  }
}
