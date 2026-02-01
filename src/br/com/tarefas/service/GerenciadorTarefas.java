package br.com.tarefas.service;

import br.com.tarefas.model.Tarefa;
import br.com.tarefas.model.StatusTarefa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GerenciadorTarefas {

    private final List<Tarefa> tarefas = new ArrayList<>();

    // Cadastro de tarefas
    public void cadastrarTarefa(Tarefa tarefa) {
        tarefas.add(tarefa);
    }

    // Listar todas as tarefas
    public void listarTarefas() {
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada.");
        } else {
            tarefas.forEach(System.out::println);
        }
    }

    // Filtrar por status
    public void filtrarPorStatus(StatusTarefa status) {
        boolean encontrou = tarefas.stream()
                .filter(t -> t.getStatus() == status)
                .peek(System.out::println)
                .count() > 0;

        if (!encontrou) {
            System.out.println("Nenhuma tarefa encontrada com status: " + status);
        }
    }

    // Filtrar por data limite
    public void filtrarPorData(LocalDate data) {
        boolean encontrou = tarefas.stream()
                .filter(t -> t.getDataLimite().equals(data))
                .peek(System.out::println)
                .count() > 0;

        if (!encontrou) {
            System.out.println("Nenhuma tarefa encontrada com data limite: " + data);
        }
    }

    // Listar tarefas atrasadas
    public void listarTarefasAtrasadas() {
        LocalDate hoje = LocalDate.now();
        boolean encontrou = tarefas.stream()
                .filter(t -> t.getDataLimite().isBefore(hoje) && t.getStatus() != StatusTarefa.CONCLUIDO)
                .peek(System.out::println)
                .count() > 0;

        if (!encontrou) {
            System.out.println("Nenhuma tarefa atrasada encontrada.");
        }
    }

    // Listar tarefas ordenadas por data limite
    public void listarTarefasOrdenadasPorData() {
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada.");
        } else {
            tarefas.stream()
                    .sorted(Comparator.comparing(Tarefa::getDataLimite))
                    .forEach(System.out::println);
        }
    }

    // Notificações inteligentes: alertar tarefas próximas do prazo
    public void alertarTarefasProximasDoPrazo() {
        LocalDate hoje = LocalDate.now();
        boolean encontrou = tarefas.stream()
                .filter(t -> {
                    long diasRestantes = java.time.temporal.ChronoUnit.DAYS.between(hoje, t.getDataLimite());
                    return diasRestantes >= 0 && diasRestantes <= 2 && t.getStatus() != StatusTarefa.CONCLUIDO;
                })
                .peek(t -> System.out.println("⚠️ Atenção! Tarefa próxima do prazo: " + t))
                .count() > 0;

        if (!encontrou) {
            System.out.println("Nenhuma tarefa próxima do prazo.");
        }
    }
}