package com.agenda.agendacontato;

import java.io.Serializable;

public class Contato implements Serializable {

    private Integer id;
    private String nome;
    private String email;
    private String telefone;
    private String endereco;
    private String nascimento;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() { return endereco; }

    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getNascimento() { return nascimento; }

    public void setNascimento(String nascimento) { this.nascimento = nascimento; }

    @Override
    public String toString(){
        return nome + ":  " + telefone + "\n" + email+"\n" + endereco + "\n" + nascimento;
        //return nome;
    }
}
