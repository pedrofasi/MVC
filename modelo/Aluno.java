/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo;

import java.util.Date;

public class Aluno implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String dre;
    private String nome;
    private String endereco;
    private String telefone;
    private String data_nasci;

    public Aluno(String dre, String nome, String endereco, String telefone, String data_nasci) {
        super();
        this.dre = dre;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.data_nasci = data_nasci;
    }

    public String getDre() {
        return dre;
    }

    public void setDre(String dre) {
        this.dre = dre;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco(){
        return endereco;
    }

    public void setEndereco(String endereco){
        this.endereco = endereco;
    }

    public String getTelefone(){
        return telefone;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    public String getData_nasci(){
        return data_nasci;
    }

    public void setData_nasci(String data_nasci){
        this.data_nasci = data_nasci;
    }

}
