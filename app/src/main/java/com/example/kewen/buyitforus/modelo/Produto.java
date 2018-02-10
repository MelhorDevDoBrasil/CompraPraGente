package com.example.kewen.buyitforus.modelo;

/**
 * Created by kewen on 10/02/18.
 */

public class Produto {
    private long id;
    private String nome;
    private String descricao;

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Produto() {
    }

    @Override
    public String toString() {
        return getNome();
    }
}
