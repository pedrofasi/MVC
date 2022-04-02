/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controle;

import java.util.Collection;
import java.util.HashMap;
import java.io.*;
import mvc.modelo.Aluno;

public class ControladorAlunoSerializado {
    public static ControladorAlunoSerializado controladorAlunoSerializado = null;

    public static ControladorAlunoSerializado getControladorAlunoSerializado() {
        if (controladorAlunoSerializado == null)
            controladorAlunoSerializado = new ControladorAlunoSerializado();
        return controladorAlunoSerializado;
    }

    HashMap<String, Aluno> alunos;

    private ControladorAlunoSerializado() {
        alunos = new HashMap<String, Aluno>();
    }

    public Aluno getAluno(String dre) throws AlunoInexistenteException {
        if (alunos.get(dre) == null)
            throw new AlunoInexistenteException(dre);
        else
            return alunos.get(dre);
    }

    public void criaAluno(String dre, String nome, String endereco, String telefone, String data_nasci) throws DreDuplicadoException {
        if (alunos.get(dre) == null) {
            alunos.put(dre, new Aluno(dre, nome, endereco, telefone, data_nasci));
        } else
            throw new DreDuplicadoException();
    }

    public Collection<Aluno> getAlunos() {
        return alunos.values();
    }

    public void salvarAlunos() throws IOException {
        FileOutputStream fos = new FileOutputStream("alunos.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(alunos);
        oos.close();
    }

    public void recuperarAlunos() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("alunos.ser"));
        Object objeto = ois.readObject();
        alunos = (HashMap<String, Aluno>) objeto;
        ois.close();
    }

    public void updateAluno(String dre, String nome, String endereco, String telefone, String data_nasci) throws DreDuplicadoException {
        if (alunos.get(dre) == null) {
            throw new DreDuplicadoException();
        } else
            alunos.put(dre, new Aluno(dre, nome, endereco, telefone, data_nasci));
    }

    public void deleteAluno(String dre) throws DreDuplicadoException {
        if (alunos.get(dre) == null) {
            throw new DreDuplicadoException();
        } else
            alunos.remove(dre);
    }

    public void limparDados() {
        // destroi todos os dados da memória
        alunos = new HashMap<String, Aluno>();
    }
}
