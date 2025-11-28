package entidades;

public class VeiculoComum implements TipoVeiculo{

	@Override
	public double getValorBase() {
		return 5.00;
	}

	@Override
	public double getValorPorKm() {
		return 1.00;
	}

}
