package entidades;

import java.time.LocalDate;

import entidades.CNH;
import entidades.CartaoDeCredito;
import entidades.CarteiraApp;
import entidades.Corrida;
import entidades.MetodoPagamento;
import entidades.Motorista;
import entidades.Passageiro;
import entidades.TipoVeiculo;
import entidades.Veiculo;
import entidades.VeiculoComum;
import entidades.VeiculoLuxo;
import excecoes.MobilidadeUrbanaException;
import servicos.CentralDeCorridas;

public class Main {

    public static void main(String[] args) {
        
        System.out.println("=== SISTEMA DE RIDE-SHARING INICIADO ===\n");

        // instanciar a central de corridas
        CentralDeCorridas central = new CentralDeCorridas();

        // criar os tipos de veiculo
        TipoVeiculo tipoComum = new VeiculoComum();
        TipoVeiculo tipoLuxo = new VeiculoLuxo();

        try {
            // cadastro de motoristas
            
            // motorista 1: Augusto (Carro Comum / UberX)
            System.out.println("> Cadastrando Motorista...");
            CNH cnhAugusto = new CNH("11112222", "B", LocalDate.of(2030, 5, 20));
            Veiculo carroAugusto = new Veiculo("ABC-1234", "Fiat Uno", "Prata", 2019, tipoComum);
            Motorista augusto = new Motorista("Augusto Garcia", "111.111.111-11", "augustomedeirosdf@gmail.com", "123", "9999-0000", cnhAugusto, carroAugusto);
            
            // valida e coloca online
            augusto.ficarOnline(); 
            central.cadastrarMotorista(augusto);

            // motorista 2: Danielly (Carro Luxo / UberBlack)
            System.out.println("> Cadastrando Motorista...");
            CNH cnhDanielly = new CNH("33334444", "AB", LocalDate.of(2028, 10, 10));
            Veiculo carroDanielly = new Veiculo("XYZ-9999", "Toyota Corolla", "Preto", 2023, tipoLuxo);
            Motorista danielly = new Motorista("Danielly Reis", "222.222.222-22", "danielly@email.com", "123", "9999-1111", cnhDanielly, carroDanielly);
            
            danielly.ficarOnline();
            central.cadastrarMotorista(danielly);
            
            System.out.println("--------------------------------------------------");

            // cadastro do passageiro
            
            System.out.println("> Cadastrando Passageiro Júlia...");
            Passageiro julia = new Passageiro("Júlia Pêgo", "555.555.555-55", "Julia@email.com", "123", "9888-8888");
            
            // adicionando metodos de pagamento a Julia
            // Cartão de credito
            MetodoPagamento cartaoJulia = new CartaoDeCredito("Julia Pêgo", "4000123456789010", "123", null);
            julia.adicionarMetodoPagamento(cartaoJulia);
            
            // colocando R$100 na carteira do App
            CarteiraApp carteiraJulia = new CarteiraApp(0);
            carteiraJulia.adicionarSaldo(100.00); 
            julia.adicionarMetodoPagamento(carteiraJulia);

            central.cadastrarPassageiro(julia);
            
            System.out.println("--------------------------------------------------");

            // inicio da corrida
            
            System.out.println("\n>>> INICIANDO SIMULAÇÃO DE CORRIDA <<<");
            
            // julia solicita uma corrida de LUXO pagando com CARTEIRA
            // sobrecarga(override)
            Corrida corrida1 = central.solicitarCorrida(
                julia, 
                "Aeroporto", 
                "Hotel Central", 
                carteiraJulia, // paga com saldo do app
                tipoLuxo       // Quer ir de carro luxuoso
            );
            System.out.println("\n--- ANDAMENTO DA VIAGEM ---");
            // a central achou a motorista Danielly que tem carro luxuoso
            // simulação da viagem acontecendo
            
            // motorista aceita a corrida, mudando o estado para "em_corrida"
            Motorista motoristaDaVez = corrida1.getMotorista();
            motoristaDaVez.aceitarCorrida(corrida1);
            
            // iniciar a viagem
            System.out.println("\n[Passageiro entrou no carro]");
            corrida1.iniciarViagem();
            
            // simula o trajetto
            corrida1.setDistancia(15.5); // 15.5 Km
            
            // finaliza a corrida
            System.out.println("\n[Chegaram ao destino]");
            
            // o motorista encerra a corrida, calcula o preço e processa o pagamento
            motoristaDaVez.finalizarCorrida(); 
            // avaliação após a corrida

            System.out.println("\n>>> AVALIAÇÃO <<<");
            corrida1.coletarFeedback(5, 5); // Julia deu 5, Danielly deu 5
            //calcula a media do usuário
            System.out.println("Média do Motorista (" + motoristaDaVez.getNome() + "): " + motoristaDaVez.getMediaNota());
            System.out.println("Média do Passageiro (" + julia.getNome() + "): " + julia.getMediaNota());
            
            // verifica o saldo final
            System.out.println("\nSaldo final da carteira do Pedro: R$ " + carteiraJulia.getSaldo());
            System.out.println("Status do Passageiro (Tem pendência?): " + julia.getPossuiPendencia());

        } catch (MobilidadeUrbanaException e) {
            // captura as exceções personalizadas
            System.err.println("\n[ERRO DE NEGÓCIO]: " + e.getMessage());
        
        } catch (Exception e) {
            // captura erros genericos
            System.err.println("\n[ERRO INESPERADO]: " + e.getMessage());
            e.printStackTrace();
        }
    }
}