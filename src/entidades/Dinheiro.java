package entidades;

public class Dinheiro implements MetodoPagamento {

    @Override
    public void processarPagamento(double valor) {

        System.out.println("Pagamento de R$ " + valor + " será realizado em dinheiro diretamente ao motorista.");
    }
}