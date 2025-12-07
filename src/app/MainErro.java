package app;

import java.time.LocalDate;

import entidades.CNH;
import entidades.CarteiraApp;
import entidades.Corrida;
import entidades.Motorista;
import entidades.Passageiro;
import entidades.TipoVeiculo;
import entidades.Veiculo;
import entidades.VeiculoComum;
import entidades.VeiculoLuxo;
import servicos.CentralDeCorridas;
import excecoes.MotoristaInvalidoException;
import excecoes.NenhumMotoristaDisponivelException;
import excecoes.PassageiroPendenteException;
import excecoes.EstadoInvalidoDaCorridaException;

public class MainErro {

    public static void main(String[] args) {
        
        System.out.println("INICIANDO TESTES DE ERRO)\n");
        CentralDeCorridas central = new CentralDeCorridas();
        TipoVeiculo tipoComum = new VeiculoComum();
        TipoVeiculo tipoLuxo = new VeiculoLuxo();

        // TESTE 1: MOTORISTA COM CNH VENCIDA
        System.out.println("[TESTE 1] Motorista com CNH Vencida");
        try {
            CNH cnhVencida = new CNH("1111", "B", LocalDate.of(2020, 1, 1));
            Veiculo carroOk = new Veiculo("ABC-1111", "Gol", "Branco", 2020, tipoComum);
            Motorista andreLanna = new Motorista("Andre Lanna", "111", "email", "123", "999", cnhVencida, carroOk);
            
            System.out.println("Tentando ficar online...");
            andreLanna.ficarOnline();
            
        } catch (MotoristaInvalidoException e) {
            System.err.println("ERRO ESPERADO: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("ERRO INESPERADO: " + e.getClass());
        }

        // TESTE 2: MOTORISTA COM CARRO VELHO
        System.out.println("\n[TESTE 2] Motorista com Carro Antigo");
        try {
            CNH cnhOk = new CNH("2222", "B", LocalDate.of(2030, 1, 1));
            Veiculo fusca = new Veiculo("OLD-9999", "Fusca", "Azul", 2010, tipoComum);
            Motorista fulano = new Motorista("fulano", "222", "email", "123", "999", cnhOk, fusca);
            
            System.out.println("Tentando ficar online...");
            fulano.ficarOnline();            
        } catch (MotoristaInvalidoException e) {
            System.err.println("ERRO ESPERADO: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("ERRO INESPERADO: " + e.getClass());
        }

        // TESTE 3: NENHUM MOTORISTA DISPONÍVEL
        System.out.println("\n[TESTE 3] Solicitar corrida sem motoristas");
        Passageiro joaozinho = new Passageiro("Joaozinho", "333", "email", "123", "888");
        central.cadastrarPassageiro(joaozinho);
        CarteiraApp carteiraJoao = new CarteiraApp(500.0);
        joaozinho.adicionarMetodoPagamento(carteiraJoao);

        try {
            System.out.println("---Solicitando corrida de LUXO (mas não tem motorista de luxo disponível)---");
            central.solicitarCorrida(joaozinho, "Casa", "Trabalho", carteiraJoao, tipoLuxo);
            
        } catch (NenhumMotoristaDisponivelException e) {
            System.err.println("ERRO ESPERADO: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("ERRO INESPERADO: " + e.getClass());
        }

        // TESTE 4: SALDO INSUFICIENTE (gera pendência)
        System.out.println("\n--- [TESTE 4] Corrida com Saldo Insuficiente ---");
        Passageiro semDinheiro = new Passageiro("Sem dinheiro da Silva", "444", "email", "123", "888");
        central.cadastrarPassageiro(semDinheiro);
        CarteiraApp carteiraVazia = new CarteiraApp(0.0);
        semDinheiro.adicionarMetodoPagamento(carteiraVazia);
        
        CNH cnhValida = new CNH("5555", "B", LocalDate.of(2029, 1, 1));
        Veiculo carroValido = new Veiculo("OKK-1234", "Onix", "Prata", 2021, tipoComum);
        Motorista motoristaComum = new Motorista("Motorista Comum", "555", "email", "123", "999", cnhValida, carroValido);
        
        try {
            motoristaComum.ficarOnline();
            central.cadastrarMotorista(motoristaComum);

            Corrida corridaCalote = central.solicitarCorrida(semDinheiro, "A", "B", carteiraVazia, tipoComum);
            corridaCalote.getMotorista().aceitarCorrida(corridaCalote);
            corridaCalote.iniciarViagem();
            corridaCalote.setDistancia(20.0); 
            motoristaComum.finalizarCorrida(); 
            
        } catch (Exception e) {
             System.out.println("(Nota: O erro de pagamento é tratado dentro do finalizar)");
        }

        // TESTE 5: BLOQUEIO POR PENDÊNCIA ANTERIOR
        System.out.println("\n[TESTE 5] Tentar nova corrida devendo a anterior");
        try {
            System.out.println("Passageiro possui pendência? " + semDinheiro.getPossuiPendencia());
            System.out.println("Sem dinheiro tenta pedir outro Uber...");
            central.solicitarCorrida(semDinheiro, "Bar", "Casa", carteiraVazia, tipoComum);
            
        } catch (PassageiroPendenteException e) {
            System.err.println("ERRO ESPERADO: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("ERRO INESPERADO: " + e.getClass());
        }
        
        // TESTE 6: ESTADO INVÁLIDO DA CORRIDA
        System.out.println("\n[TESTE 6] Tentar cancelar uma corrida que já acabou");
        try {
            // criando uma corrida generica para testar o estado inválido
            Passageiro apressado = new Passageiro("Apressado", "777", "email", "123", "000");
            CarteiraApp carteiraCheia = new CarteiraApp(1000.0);
            apressado.adicionarMetodoPagamento(carteiraCheia);
            central.cadastrarPassageiro(apressado);

            // utiliza o motoristaComum que já tinha sido criado
            motoristaComum.setStatus(entidades.StatusMotorista.DISPONIVEL); // muda o status para disponível
            
            Corrida corridaRelampago = central.solicitarCorrida(apressado, "A", "B", carteiraCheia, tipoComum);
            corridaRelampago.getMotorista().aceitarCorrida(corridaRelampago);
            corridaRelampago.iniciarViagem();
            motoristaComum.finalizarCorrida(); // corrida finalizada

            System.out.println("Tentando cancelar a corrida depois de finalizada...");
            corridaRelampago.cancelarPeloPassageiro();

        } catch (EstadoInvalidoDaCorridaException e) {
            System.err.println("ERRO ESPERADO: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("ERRO INESPERADO: " + e.getMessage());
        }
        System.out.println("\nFIM DOS TESTES");
    }
}