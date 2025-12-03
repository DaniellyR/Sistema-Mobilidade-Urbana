package entidades;
import excecoes.SaldoInsuficienteException;

public class CarteiraApp implements MetodoPagamento{

	private double saldo;
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
				System.out.println("Pagamento válido. Saldo restante: R$ " + String.format("%.2f", this.saldo));
	}
	public void adicionarSaldo(double valor) {
        if (valor > 0) {
            this.saldo += valor;
            System.out.println("Recarga de R$" + valor + " efetuada na Carteira.");
        }
    }

    public double getSaldo() {
        return this.saldo;
    }
	
}
