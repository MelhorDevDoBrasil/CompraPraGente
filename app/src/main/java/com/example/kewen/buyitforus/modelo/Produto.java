package com.example.kewen.buyitforus.modelo;

import java.io.Serializable;

/**
 * Created by kewen on 10/02/18.
 */

public class Produto implements Serializable{


    private Long id;
    private String nome;
    private String descricao;

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
