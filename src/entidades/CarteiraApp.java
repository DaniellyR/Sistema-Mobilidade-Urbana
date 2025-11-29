package entidades;
import excecoes.SaldoInsuficienteException;

public class CarteiraApp implements MetodoPagamento{

	double saldo;
	public CarteiraApp(double saldoInicial) {
		this.saldo = saldoInicial;
	}
	@Override
	public void processarPagamento(double valor) throws SaldoInsuficienteException{
		if(this.saldo < valor) //se o saldo for menor que o valor da corrida, lança o erro
			{
			throw new SaldoInsuficienteException("Saldo insuficiente");
		}
		//desconta no saldo o valor da corrida feita
		this.saldo -= valor;
				System.out.println("Pagamento válido");
	}
}
