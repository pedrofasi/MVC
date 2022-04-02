/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import mvc.vista.AlunoInexistenteException;
import mvc.controle.ControladorAlunoSerializado;
import mvc.controle.DreDuplicadoException;

public class GuiSimplesSalvando {
    private String dre, nome, endereco, telefone, data_nasci;
    private JFrame janela;
    private JPanel painelGeral, pCentro, pDisplay, pDre, pNome, pBotoes, pMensagem, pTelefone, pData_nasci, pEndereco;
    private JLabel labelDre, labelNome, labelMensagem, labelEndereco, labelTelefone, labelData;
    private JButton botCriar, botDados, botSalvar, botLimpar, botRemove, botEdit;
    private JTextField tfDre, tfNome, tfMensagem, tfEndereco,tfTelefone,tfData_nasci;
    private ControladorAlunoSerializado controlador = ControladorAlunoSerializado.getControladorAlunoSerializado();

    public GuiSimplesSalvando() {
        janela = new JFrame("GUI Simples Persistente");
        painelGeral = new JPanel(new BorderLayout());
        pCentro = new JPanel(new BorderLayout());
        pDisplay = new JPanel(new GridLayout(10, 2));
        pDre = new JPanel();
        pNome = new JPanel();
        pEndereco = new JPanel();
        pTelefone = new JPanel();
        pData_nasci = new JPanel();
        pDre.setLayout(new FlowLayout(FlowLayout.LEFT));
        pNome.setLayout(new FlowLayout(FlowLayout.CENTER));
        pEndereco.setLayout(new FlowLayout(FlowLayout.CENTER));
        pTelefone.setLayout(new FlowLayout(FlowLayout.CENTER));
        pData_nasci.setLayout(new FlowLayout(FlowLayout.CENTER));
        pDisplay.add(pDre);
        pDisplay.add(pNome);
        pDisplay.add(pEndereco);
        pDisplay.add(pTelefone);
        pDisplay.add(pData_nasci);
        labelDre = new JLabel("DRE: ");
        pDre.add(labelDre);
        tfDre = new JTextField(10);
        tfDre.addKeyListener(new OuvinteTfDre());
        pDre.add(tfDre);
        labelNome = new JLabel("Nome: ");
        pNome.add(labelNome);
        tfNome = new JTextField(25);
        pNome.add(tfNome);
        labelEndereco = new JLabel("Endereço: ");
        pEndereco.add(labelEndereco);
        tfEndereco = new JTextField(25);
        pEndereco.add(tfEndereco);
        labelData = new JLabel("Data de Nascimento: ");
        pData_nasci.add(labelData);
        tfData_nasci = new JTextField(25);
        pData_nasci.add(tfData_nasci);
        labelTelefone = new JLabel("Telefone: ");
        pTelefone.add(labelTelefone);
        tfTelefone = new JTextField(25);
        pTelefone.add(tfTelefone);
        pBotoes = new JPanel();
        botCriar = new JButton("Criar Aluno");
        botCriar.addActionListener(new OuvinteCriar());
        pBotoes.add(botCriar);
        botDados = new JButton("Obter Dados Através do MRE");
        botDados.addActionListener(new OuvinteObterDados());
        pBotoes.add(botDados);
        botSalvar = new JButton("Salvar dados");
        botSalvar.addActionListener(new OuvinteSalvar());
        pBotoes.add(botSalvar);
        botLimpar = new JButton("Limpar dados");
        botLimpar.addActionListener(new OuvinteLimpar());
        pBotoes.add(botLimpar);
        botRemove = new JButton("Remover Aluno");
        botRemove.addActionListener(new OuvinteRemove());
        pBotoes.add(botRemove);
        botEdit = new JButton("Editar Aluno");
        botEdit.addActionListener(new OuvinteUpdate());
        pBotoes.add(botEdit);
        pMensagem = new JPanel();
        labelMensagem = new JLabel("Mensagem: ");
        tfMensagem = new JTextField(70);
        tfMensagem.setEditable(false);
        pMensagem.add(labelMensagem);
        pMensagem.add(tfMensagem);
        pMensagem.setLayout(new FlowLayout(FlowLayout.LEFT));
        pCentro.add(pDisplay, BorderLayout.CENTER);
        pCentro.add(pBotoes, BorderLayout.EAST);
        painelGeral.add(pCentro, BorderLayout.CENTER);
        painelGeral.add(pMensagem, BorderLayout.PAGE_START);
        janela.add(painelGeral);
        janela.setBounds(0, 0, 1280, 720);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setVisible(true);
        try {
            controlador.recuperarAlunos();
            tfMensagem.setText("Os dados dos alunos foram recuperados do arquivo");
        } catch (IOException ioe) {
            tfMensagem.setText("Não foi possível recuperar os dados dos alunos: IOException");
        } catch (ClassNotFoundException cnf) {
            tfMensagem.setText("Não foi possível recuperar os dados dos alunos: ClassNotFoundException");
        }
    }

    class OuvinteTfDre extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent ev) {
            tfMensagem.setText("");
            tfNome.setText("");
        }
    }

    class OuvinteCriar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent aev) {
            nome = tfNome.getText();
            endereco = tfEndereco.getText();
            telefone = tfTelefone.getText();
            data_nasci = tfData_nasci.getText();
            dre = tfDre.getText();
            try {
                controlador.criaAluno(dre, nome, endereco, telefone, data_nasci);
                tfMensagem.setText("Aluno " + nome + "\n Endereço:" + endereco + "\n Telefone:" + telefone + "\n Data de Nascimento:" +data_nasci+ " criado OK, com DRE " +
                        dre);
            } catch (DreDuplicadoException ex) {
                tfMensagem.setText("Não foi possível criar o aluno. O DRE " + dre + " já foi alocado");
            }
        }
    }

    class OuvinteUpdate implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent aev) {
            nome = tfNome.getText();
            endereco = tfEndereco.getText();
            telefone = tfTelefone.getText();
            data_nasci = tfData_nasci.getText();
            dre = tfDre.getText();
            try {
                controlador.updateAluno(dre, nome, endereco, telefone, data_nasci);
                tfMensagem.setText("Aluno " +nome+" editado com sucesso.");
            } catch (DreDuplicadoException ex) {
                tfMensagem.setText("Não existe nenhum aluno com esse DRE.");
            }
        }
    }

    class OuvinteRemove implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent aev) {
            dre = tfDre.getText();
            try {
                controlador.deleteAluno(dre);
                tfMensagem.setText("Aluno "+nome+" removido do banco.");

            } catch (DreDuplicadoException ex) {
                tfMensagem.setText("Não existe nenhum aluno com esse DRE.");
            }
        }
    }

    class OuvinteObterDados implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent aev) {
            try {
                dre = tfDre.getText();
                nome = controlador.getAluno(dre).getNome();
                endereco = controlador.getAluno(dre).getEndereco();
                telefone = controlador.getAluno(dre).getTelefone();
                data_nasci = controlador.getAluno(dre).getData_nasci();
                tfMensagem.setText("Informações do aluno com DRE = " + dre + " Nome:" +
                        nome + "\n Endereço:" + endereco + "\n Telefone:" + telefone + "\n Data de Nascimento:" +data_nasci+ " criado OK, com DRE ");
            } catch (mvc.controle.AlunoInexistenteException ex) {
            }
        }
    }

    class OuvinteSalvar implements ActionListener {
        public void actionPerformed(ActionEvent aev) {
            try {
                controlador.salvarAlunos();
                tfMensagem.setText("Dados dos alunos salvos com sucesso");
            } catch (IOException ioe) {
                tfMensagem.setText("Não foi possível salvar os dados dos alunos no arquivo");
            }
        }
    }

    class OuvinteLimpar implements ActionListener {
        public void actionPerformed(ActionEvent aev) {
            controlador.limparDados();
        }
    }

    public static void main(String[] args) {
        new GuiSimplesSalvando();
    }
}
