package br.com.tarefas.model;

import java.time.LocalDate;

public class Tarefa {
    private String titulo;
    private String descricao;
    private LocalDate dataLimite;
    private StatusTarefa status;

    public Tarefa(String titulo, String descricao, LocalDate dataLimite, StatusTarefa status) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("Título não pode ser vazio");
        }
        if (titulo.length() < 5) {
            throw new IllegalArgumentException("Título deve ter pelo menos 5 caracteres");
        }
        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("Descrição não pode ser vazia");
        }
        if (status == null) {
            throw new IllegalArgumentException("Status não pode ser nulo");
        }
        if (status != StatusTarefa.CONCLUIDO) {
            throw new IllegalArgumentException("A tarefa só pode ser cadastrada com status CONCLUIDO");
        }
        if (dataLimite == null || dataLimite.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Data limite não pode ser no passado");
        }

        this.titulo = titulo;
        this.descricao = descricao;
        this.dataLimite = dataLimite;
        this.status = status;
    }

    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public LocalDate getDataLimite() { return dataLimite; }
    public StatusTarefa getStatus() { return status; }

    @Override
    public String toString() {
        return "Tarefa{" +
                "titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataLimite=" + dataLimite +
                ", status=" + status +
                '}';
    }
}