package com.example.proyecto01.modelo;

public class Usuario {
    private int usu_id;
    private String usu_nomb;
    private String usu_pass;
    private int per_id;

    public Usuario() {
    }

    public Usuario(String usu_nomb, String usu_pass, int per_id) {
        this.usu_nomb = usu_nomb;
        this.usu_pass = usu_pass;
        this.per_id = per_id;
    }

    public boolean isNull(){
        if (usu_nomb.equals("") && usu_pass.equals("")){
            return false;
        }else{
            return true;
        }
    }

    public String toString() {
        return "Usuario{ usu_id = " + usu_id +
                ", usu_nomb = '" + usu_nomb + '\'' +
                ", usu_cont ='" + usu_pass + '\'' +
                ", per_id =" + per_id +
                '}';
    }

    public int getUsu_id() {
        return usu_id;
    }

    public void setUsu_id(int usu_id) {
        this.usu_id = usu_id;
    }

    public String getUsu_nomb() {
        return usu_nomb;
    }

    public void setUsu_nomb(String usu_nomb) {
        this.usu_nomb = usu_nomb;
    }

    public String getUsu_pass() {
        return usu_pass;
    }

    public void setUsu_pass(String usu_pass) {
        this.usu_pass = usu_pass;
    }

    public int getPer_id() {
        return per_id;
    }

    public void setPer_id(int per_id) {
        this.per_id = per_id;
    }
}
