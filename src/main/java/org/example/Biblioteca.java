package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    private JComboBox<ComboItem> comboCategoria;
    private JComboBox<String> comboLivro;
    private JTextField campoDataEmprestimo;
    private JTextField campoDataDevolucao;

    private GerenciaDados gerenciaDados;

    public Biblioteca() {
        gerenciaDados = new GerenciaDados();
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
            try {
                gerenciaDados.addCategoria(campoNomeCategoria.getText());
                atualizarTabelaCategoria();
                campoNomeCategoria.setText("");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        // Ação para Editar Categoria
        btnEditarCategoria.addActionListener(e -> {
            int linhaSelecionada = tabelaCategoria.getSelectedRow();
            if (linhaSelecionada != -1) {
                try {
                    int id = (int) modeloTabelaCategoria.getValueAt(linhaSelecionada, 0);
                    gerenciaDados.updateCategoria(id, campoNomeCategoria.getText());
                    atualizarTabelaCategoria();
                    campoNomeCategoria.setText("");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione uma categoria para editar.");
            }
        });

        // Ação para Excluir Categoria
        btnExcluirCategoria.addActionListener(e -> {
            int linhaSelecionada = tabelaCategoria.getSelectedRow();
            if (linhaSelecionada != -1) {
                try {
                    int id = (int) modeloTabelaCategoria.getValueAt(linhaSelecionada, 0);
                    gerenciaDados.deleteCategoria(id);
                    atualizarTabelaCategoria();
                    campoNomeCategoria.setText("");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione uma categoria para excluir.");
            }
        });

        formulario.add(btnAdicionarCategoria);
        formulario.add(btnEditarCategoria);
        formulario.add(btnExcluirCategoria);
        painel.add(formulario, BorderLayout.SOUTH);
        atualizarTabelaCategoria();
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
        preencherComboCategorias();
        formulario.add(comboCategoria);

        JButton btnAdicionarLivro = new JButton("Adicionar");
        JButton btnEditarLivro = new JButton("Editar");
        JButton btnExcluirLivro = new JButton("Excluir");

        // Ação para Adicionar Livro
        btnAdicionarLivro.addActionListener(e -> {
            try {
                int idCategoria = getSelectedCategoriaId();
                gerenciaDados.addLivro(campoTituloLivro.getText(), idCategoria);
                atualizarTabelaLivro();
                campoTituloLivro.setText("");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        // Ação para Editar Livro
        btnEditarLivro.addActionListener(e -> {
            int linhaSelecionada = tabelaLivro.getSelectedRow();
            if (linhaSelecionada != -1) {
                try {
                    int id = (int) modeloTabelaLivro.getValueAt(linhaSelecionada, 0);
                    int idCategoria = getSelectedCategoriaId(); // Usa o método para obter o ID correto
                    gerenciaDados.updateLivro(id, campoTituloLivro.getText(), idCategoria);
                    atualizarTabelaLivro();
                    campoTituloLivro.setText("");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um livro para editar.");
            }
        });

        // Ação para Excluir Livro
        btnExcluirLivro.addActionListener(e -> {
            int linhaSelecionada = tabelaLivro.getSelectedRow();
            if (linhaSelecionada != -1) {
                try {
                    int id = (int) modeloTabelaLivro.getValueAt(linhaSelecionada, 0);
                    gerenciaDados.deleteLivro(id);
                    atualizarTabelaLivro();
                    campoTituloLivro.setText("");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um livro para excluir.");
            }
        });

        formulario.add(btnAdicionarLivro);
        formulario.add(btnEditarLivro);
        formulario.add(btnExcluirLivro);
        painel.add(formulario, BorderLayout.SOUTH);
        atualizarTabelaLivro();
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
        preencherComboLivros();
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
            try {
                int idLivro = comboLivro.getSelectedIndex() + 1; // Ajuste conforme a lógica do banco
                Date dataEmprestimo = Date.valueOf(campoDataEmprestimo.getText());
                Date dataDevolucao = Date.valueOf(campoDataDevolucao.getText());
                gerenciaDados.addEmprestimo(idLivro, dataEmprestimo, dataDevolucao);
                atualizarTabelaEmprestimo();
                campoDataEmprestimo.setText("");
                campoDataDevolucao.setText("");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        // Ação para Editar Empréstimo
        btnEditarEmprestimo.addActionListener(e -> {
            // Implementar lógica para editar empréstimo
        });

        // Ação para Excluir Empréstimo
        btnExcluirEmprestimo.addActionListener(e -> {
            // Implementar lógica para excluir empréstimo
        });

        formulario.add(btnAdicionarEmprestimo);
        formulario.add(btnEditarEmprestimo);
        formulario.add(btnExcluirEmprestimo);
        painel.add(formulario, BorderLayout.SOUTH);
        atualizarTabelaEmprestimo();
        return painel;
    }

    // Método para preencher categorias no JComboBox
    private void preencherComboCategorias() {
        comboCategoria.removeAllItems();
        try {
            ResultSet rs = gerenciaDados.getCategorias();
            while (rs.next()) {
                int id = rs.getInt("id_categoria");
                String nome = rs.getString("nome_categoria");
                comboCategoria.addItem(new ComboItem(id, nome));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Método para preencher livros no JComboBox
    private void preencherComboLivros() {
        comboLivro.removeAllItems();
        try {
            ResultSet rs = gerenciaDados.getLivros();
            while (rs.next()) {
                int id = rs.getInt("id_livro");
                String titulo = rs.getString("titulo_livro");
                comboLivro.addItem(String.valueOf(new ComboItem(id, titulo)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Método para obter o ID da categoria selecionada
    private int getSelectedCategoriaId() {
        return ((ComboItem) comboCategoria.getSelectedItem()).getId();
    }

    // Atualiza a tabela de categorias
    private void atualizarTabelaCategoria() {
        modeloTabelaCategoria.setRowCount(0);
        try {
            ResultSet rs = gerenciaDados.getCategorias();
            while (rs.next()) {
                int id = rs.getInt("id_categoria");
                String nome = rs.getString("nome_categoria");
                modeloTabelaCategoria.addRow(new Object[]{id, nome});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Atualiza a tabela de livros
    private void atualizarTabelaLivro() {
        modeloTabelaLivro.setRowCount(0);
        try {
            ResultSet rs = gerenciaDados.getLivros();
            boolean temLivros = false;

            while (rs.next()) {
                int id = rs.getInt("id_livro");
                String titulo = rs.getString("titulo_livro");
                String categoria = rs.getString("nome_categoria");
                modeloTabelaLivro.addRow(new Object[]{id, titulo, categoria});
                temLivros = true;
            }

            if (!temLivros) {
                modeloTabelaLivro.addRow(new Object[]{"", "Nenhum livro registrado", ""});
            }

        } catch (SQLException ex) {
            // Exibe uma mensagem de erro para o usuário
            JOptionPane.showMessageDialog(this, "Erro ao atualizar tabela de livros: " + ex.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace(); // Você pode manter isso para depuração
        }
    }

    // Atualiza a tabela de empréstimos
    private void atualizarTabelaEmprestimo() {
        modeloTabelaEmprestimo.setRowCount(0);
        try {
            ResultSet rs = gerenciaDados.getEmprestimos();
            boolean temEmprestimos = false;

            while (rs.next()) {
                int id = rs.getInt("id_emprestimo");
                String livro = rs.getString("livro");
                Date dataEmprestimo = rs.getDate("data_emprestimo");
                Date dataDevolucao = rs.getDate("data_devolucao");
                modeloTabelaEmprestimo.addRow(new Object[]{id, livro, dataEmprestimo, dataDevolucao});
                temEmprestimos = true;
            }


            if (!temEmprestimos) {
                modeloTabelaEmprestimo.addRow(new Object[]{"", "Nenhum empréstimo registrado", "", ""});
            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(this, "Erro ao atualizar tabela de empréstimos: " + ex.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

}
