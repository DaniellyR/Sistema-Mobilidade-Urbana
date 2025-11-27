package excecoes;

public class MotoristaInvalidoException extends MobilidadeUrbanaException{
	private static final long serialVersionUID = 1L;
	
	public MotoristaInvalidoException(String mensagem) {
		super(mensagem);
	}
}
