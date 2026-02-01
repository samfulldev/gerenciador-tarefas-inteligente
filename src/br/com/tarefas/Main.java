package br.com.tarefas;

import br.com.tarefas.model.StatusTarefa;
import br.com.tarefas.model.Tarefa;
import br.com.tarefas.service.GerenciadorTarefas;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    // Método auxiliar para pausar
    private static void pausa(Scanner sc) {
        System.out.println("\nPressione ENTER para continuar...");
        sc.nextLine();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GerenciadorTarefas gerenciador = new GerenciadorTarefas();

        int opcao;
        do {
            System.out.println("\n=== Gerenciador de Tarefas Inteligente ===");
            System.out.println("1 - Cadastrar tarefa");
            System.out.println("2 - Listar todas as tarefas");
            System.out.println("3 - Filtrar por status");
            System.out.println("4 - Listar ordenadas por data limite");
            System.out.println("5 - Listar tarefas atrasadas");
            System.out.println("6 - Notificações inteligentes");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1:
                    try {
                        System.out.print("Título: ");
                        String titulo = sc.nextLine();

                        System.out.print("Descrição: ");
                        String descricao = sc.nextLine();

                        System.out.print("Data limite (AAAA-MM-DD): ");
                        LocalDate data = LocalDate.parse(sc.nextLine());

                        // Status fixo como CONCLUIDO (requisito do desafio)
                        Tarefa tarefa = new Tarefa(titulo, descricao, data, StatusTarefa.CONCLUIDO);
                        gerenciador.cadastrarTarefa(tarefa);
                        System.out.println("✅ Tarefa cadastrada com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Erro ao cadastrar tarefa: " + e.getMessage());
                    }
                    pausa(sc);
                    break;

                case 2:
                    gerenciador.listarTarefas();
                    pausa(sc);
                    break;

                case 3:
                    gerenciador.filtrarPorStatus(StatusTarefa.CONCLUIDO);
                    pausa(sc);
                    break;

                case 4:
                    gerenciador.listarTarefasOrdenadasPorData();
                    pausa(sc);
                    break;

                case 5:
                    gerenciador.listarTarefasAtrasadas();
                    pausa(sc);
                    break;

                case 6:
                    gerenciador.alertarTarefasProximasDoPrazo();
                    pausa(sc);
                    break;

                case 0:
                    System.out.println("Encerrando programa...");
                    break;

                default:
                    System.out.println("Opção inválida!");
                    pausa(sc);
            }
        } while (opcao != 0);

        sc.close();
    }
}