package com.example.goodjoob.model;

import java.util.List;
import java.util.Stack;

public class Perfil {
    private static String Id_usuario;
    private static String Nome ;
    private static String Login ;
    private static String Email ;
    private static String Telefone ;
    private static String Cpf ;
    private static String Tipo ;


    public Perfil (String id_usuario, String nome, String login, String email, String telefone, String cpf, String tipo){
        setId_usuario(id_usuario);
        setNome(nome);
        setLogin(login);
        setEmail(email);
        setTelefone(telefone);
        setCpf(cpf);
        setTipo(tipo);
    }

    public static String getId_usuario() {
        return Id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        Id_usuario = id_usuario;
    }

    public static String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public static String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        Telefone = telefone;
    }

    public static String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public static String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public static String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public static String getCpf() { return Cpf; }

    public void setCpf(String cpf) {
        Cpf = cpf;
    }



}
