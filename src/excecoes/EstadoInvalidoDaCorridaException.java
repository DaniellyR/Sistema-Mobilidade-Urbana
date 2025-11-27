package excecoes;

public class EstadoInvalidoDaCorridaException extends MobilidadeUrbanaException{
	private static final long serialVersionUID = 1L;
	
	public EstadoInvalidoDaCorridaException(String mensagem) {
		super(mensagem);
	}
}
