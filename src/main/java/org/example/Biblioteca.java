package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Biblioteca extends JFrame {

    private JTabbedPane abas;
    private JTable tabelaCategoria;
    private JTable tabelaLivro;
    private JTable tabelaEmprestimo;
    private DefaultTableModel modeloTabelaCategoria;
    private DefaultTableModel modeloTabelaLivro;
    private DefaultTableModel modeloTabelaEmprestimo;

    private JTextField campoNomeCategoria;
    private JTextField campoTituloLivro;
    private JComboBox<String> comboCategoria;
    private JComboBox<String> comboLivro;
    private JTextField campoDataEmprestimo;
    private JTextField campoDataDevolucao;

    public Biblioteca() {
        setTitle("Biblioteca");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Criando as abas
        abas = new JTabbedPane();

        // Aba de Categorias
        JPanel painelCategoria = criarPainelCategoria();
        abas.addTab("Categorias", painelCategoria);

        // Aba de Livros
        JPanel painelLivro = criarPainelLivro();
        abas.addTab("Livros", painelLivro);

        // Aba de Empréstimos
        JPanel painelEmprestimo = criarPainelEmprestimo();
        abas.addTab("Empréstimos", painelEmprestimo);

        // Adicionando o JTabbedPane à janela
        add(abas, BorderLayout.CENTER);
    }

    // Método para criar o painel de categorias
    private JPanel criarPainelCategoria() {
        JPanel painel = new JPanel(new BorderLayout());

        // Modelo e Tabela
        modeloTabelaCategoria = new DefaultTableModel();
        tabelaCategoria = new JTable(modeloTabelaCategoria);
        modeloTabelaCategoria.addColumn("ID");
        modeloTabelaCategoria.addColumn("Nome");

        painel.add(new JScrollPane(tabelaCategoria), BorderLayout.CENTER);

        // Formulário para adicionar categorias
        JPanel formulario = new JPanel(new GridLayout(0, 2));
        formulario.add(new JLabel("Nome da Categoria:"));
        campoNomeCategoria = new JTextField();
        formulario.add(campoNomeCategoria);

        JButton btnAdicionarCategoria = new JButton("Adicionar");
        JButton btnEditarCategoria = new JButton("Editar");
        JButton btnExcluirCategoria = new JButton("Excluir");

        // Ação para Adicionar Categoria
        btnAdicionarCategoria.addActionListener(e -> {
            // Lógica para adicionar categoria
        });

        // Ação para Editar Categoria
        btnEditarCategoria.addActionListener(e -> {
            int linhaSelecionada = tabelaCategoria.getSelectedRow();
            if (linhaSelecionada != -1) {
                // Lógica para editar a categoria selecionada
                String nome = campoNomeCategoria.getText();
                modeloTabelaCategoria.setValueAt(nome, linhaSelecionada, 1);
                campoNomeCategoria.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Selecione uma categoria para editar.");
            }
        });

        // Ação para Excluir Categoria
        btnExcluirCategoria.addActionListener(e -> {
            int linhaSelecionada = tabelaCategoria.getSelectedRow();
            if (linhaSelecionada != -1) {
                // Lógica para excluir a categoria selecionada
                modeloTabelaCategoria.removeRow(linhaSelecionada);
                campoNomeCategoria.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Selecione uma categoria para excluir.");
            }
        });

        formulario.add(btnAdicionarCategoria);
        formulario.add(btnEditarCategoria);
        formulario.add(btnExcluirCategoria);
        painel.add(formulario, BorderLayout.SOUTH);
        return painel;
    }

    // Método para criar o painel de livros
    private JPanel criarPainelLivro() {
        JPanel painel = new JPanel(new BorderLayout());

        // Modelo e Tabela
        modeloTabelaLivro = new DefaultTableModel();
        tabelaLivro = new JTable(modeloTabelaLivro);
        modeloTabelaLivro.addColumn("ID");
        modeloTabelaLivro.addColumn("Título");
        modeloTabelaLivro.addColumn("Categoria");

        painel.add(new JScrollPane(tabelaLivro), BorderLayout.CENTER);

        // Formulário para adicionar livros
        JPanel formulario = new JPanel(new GridLayout(0, 2));
        formulario.add(new JLabel("Título do Livro:"));
        campoTituloLivro = new JTextField();
        formulario.add(campoTituloLivro);

        formulario.add(new JLabel("Categoria:"));
        comboCategoria = new JComboBox<>(); // Preencher com categorias do banco
        formulario.add(comboCategoria);

        JButton btnAdicionarLivro = new JButton("Adicionar");
        JButton btnEditarLivro = new JButton("Editar");
        JButton btnExcluirLivro = new JButton("Excluir");

        // Ação para Adicionar Livro
        btnAdicionarLivro.addActionListener(e -> {
            // Lógica para adicionar livro
        });

        // Ação para Editar Livro
        btnEditarLivro.addActionListener(e -> {
            int linhaSelecionada = tabelaLivro.getSelectedRow();
            if (linhaSelecionada != -1) {
                // Lógica para editar o livro selecionado
                String titulo = campoTituloLivro.getText();
                modeloTabelaLivro.setValueAt(titulo, linhaSelecionada, 1);
                campoTituloLivro.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um livro para editar.");
            }
        });

        // Ação para Excluir Livro
        btnExcluirLivro.addActionListener(e -> {
            int linhaSelecionada = tabelaLivro.getSelectedRow();
            if (linhaSelecionada != -1) {
                // Lógica para excluir o livro selecionado
                modeloTabelaLivro.removeRow(linhaSelecionada);
                campoTituloLivro.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um livro para excluir.");
            }
        });

        formulario.add(btnAdicionarLivro);
        formulario.add(btnEditarLivro);
        formulario.add(btnExcluirLivro);
        painel.add(formulario, BorderLayout.SOUTH);
        return painel;
    }

    // Método para criar o painel de empréstimos
    private JPanel criarPainelEmprestimo() {
        JPanel painel = new JPanel(new BorderLayout());

        // Modelo e Tabela
        modeloTabelaEmprestimo = new DefaultTableModel();
        tabelaEmprestimo = new JTable(modeloTabelaEmprestimo);
        modeloTabelaEmprestimo.addColumn("ID");
        modeloTabelaEmprestimo.addColumn("Livro");
        modeloTabelaEmprestimo.addColumn("Data de Empréstimo");
        modeloTabelaEmprestimo.addColumn("Data de Devolução");

        painel.add(new JScrollPane(tabelaEmprestimo), BorderLayout.CENTER);

        // Formulário para adicionar empréstimos
        JPanel formulario = new JPanel(new GridLayout(0, 2));
        formulario.add(new JLabel("Livro:"));
        comboLivro = new JComboBox<>(); // Preencher com livros do banco
        formulario.add(comboLivro);

        formulario.add(new JLabel("Data de Empréstimo:"));
        campoDataEmprestimo = new JTextField();
        formulario.add(campoDataEmprestimo);

        formulario.add(new JLabel("Data de Devolução:"));
        campoDataDevolucao = new JTextField();
        formulario.add(campoDataDevolucao);

        JButton btnAdicionarEmprestimo = new JButton("Adicionar");
        JButton btnEditarEmprestimo = new JButton("Editar");
        JButton btnExcluirEmprestimo = new JButton("Excluir");

        // Ação para Adicionar Empréstimo
        btnAdicionarEmprestimo.addActionListener(e -> {
            // Lógica para adicionar empréstimo
        });

        // Ação para Editar Empréstimo
        btnEditarEmprestimo.addActionListener(e -> {
            int linhaSelecionada = tabelaEmprestimo.getSelectedRow();
            if (linhaSelecionada != -1) {
                // Lógica para editar o empréstimo selecionado
                String livro = (String) comboLivro.getSelectedItem();
                modeloTabelaEmprestimo.setValueAt(livro, linhaSelecionada, 1);
                modeloTabelaEmprestimo.setValueAt(campoDataEmprestimo.getText(), linhaSelecionada, 2);
                modeloTabelaEmprestimo.setValueAt(campoDataDevolucao.getText(), linhaSelecionada, 3);
                campoDataEmprestimo.setText("");
                campoDataDevolucao.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um empréstimo para editar.");
            }
        });

        // Ação para Excluir Empréstimo
        btnExcluirEmprestimo.addActionListener(e -> {
            int linhaSelecionada = tabelaEmprestimo.getSelectedRow();
            if (linhaSelecionada != -1) {
                // Lógica para excluir o empréstimo selecionado
                modeloTabelaEmprestimo.removeRow(linhaSelecionada);
                campoDataEmprestimo.setText("");
                campoDataDevolucao.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um empréstimo para excluir.");
            }
        });

        formulario.add(btnAdicionarEmprestimo);
        formulario.add(btnEditarEmprestimo);
        formulario.add(btnExcluirEmprestimo);

        painel.add(formulario, BorderLayout.SOUTH);
        return painel;
    }


    public void atualizarTabelaCategoria(Object[][] dados, String[] colunas) {
        modeloTabelaCategoria.setDataVector(dados, colunas);
    }

    public void atualizarTabelaLivro(Object[][] dados, String[] colunas) {
        modeloTabelaLivro.setDataVector(dados, colunas);
    }

    public void atualizarTabelaEmprestimo(Object[][] dados, String[] colunas) {
        modeloTabelaEmprestimo.setDataVector(dados, colunas);
    }
}
