package excecoes;

public class NenhumMotoristaDisponivelException extends MobilidadeUrbanaException{
	private static final long serialVersionUID = 1L;
	
	public NenhumMotoristaDisponivelException(String mensagem) {
		super(mensagem);
	}
    
}
