package com.exercicios.devweb;

import java.util.UUID;
import java.util.Date;

public class Usuario {
    private String id;
    private String nome;
    private String email;
    private Date dataCadastro;

    public Usuario(String id, String nome, String email, Date dataCadastro) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dataCadastro = dataCadastro;
    }

    public static Usuario create(String nome, String email) {
        String uuid = UUID.randomUUID().toString();
        return new Usuario(uuid, nome, email, new Date());
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }
}