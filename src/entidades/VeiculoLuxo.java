package entidades;

public class VeiculoLuxo implements TipoVeiculo{

	@Override
	public double getValorBase() {
		return 9.00;
	}

	@Override
	public double getValorPorKm() {
		return 2.20;
	}

}
