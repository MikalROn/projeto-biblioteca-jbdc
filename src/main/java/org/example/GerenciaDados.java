package org.example;

import java.sql.*;

public class GerenciaDados {
    private final String URL = "jdbc:postgres://localhost:5433/biblioteca";
    private final String USER = "user";
    private final String SENHA = "password";

    public Connection getConnection() throws SQLException {
        try
        {
            return DriverManager.getConnection(URL, USER, SENHA);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    // CRUD categoria

    public void addCategoria(String nome) throws SQLException {
        // Adiciona nova categoria
        String sql = "INSERT INTO categoria (nome_categoria) VALUES (?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.executeUpdate();
        }
    }

    public ResultSet getCategorias() throws SQLException {
        // lista todas as categorias
        String sql = "SELECT * FROM categoria";
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        return stmt.executeQuery();
    }


    public void updateCategoria(int id, String novoNome) throws SQLException {
        // Atualiza uma categoria
        String sql = "UPDATE categoria SET nome_categoria = ? WHERE id_categoria = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, novoNome);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }


    public void deleteCategoria(int id) throws SQLException {
        // Deleta uma categoria
        String sql = "DELETE FROM categoria WHERE id_categoria = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // CRUD livro

    // Adiciona um novo livro
    public void addLivro(String titulo, int idCategoria) throws SQLException {
        String sql = "INSERT INTO livro (titulo_livro, id_categoria) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, titulo);
            stmt.setInt(2, idCategoria);
            stmt.executeUpdate();
        }
    }

    // Lista todos os livros
    public ResultSet getLivros() throws SQLException {
        String sql = "SELECT l.id_livro, l.titulo_livro, c.nome_categoria FROM livro l JOIN categoria c ON l.id_categoria = c.id_categoria";
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        return stmt.executeQuery();
    }



    public void deleteLivro(int id) throws SQLException {
        // Atualiza um livro
        String sql = "DELETE FROM livro WHERE id_livro = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }


    public void updateLivro(int id, String novoTitulo, int idCategoria) throws SQLException {
        String sql = "UPDATE livro SET titulo_livro = ?, id_categoria = ? WHERE id_livro = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, novoTitulo);
            stmt.setInt(2, idCategoria);
            stmt.setInt(3, id);
            stmt.executeUpdate();
        }
    }

    // Adiciona um novo empréstimo
    public void addEmprestimo(int idLivro, Date dataEmprestimo, Date dataDevolucao) throws SQLException {
        String sql = "INSERT INTO emprestimo (id_livro, data_emprestimo, data_devolucao) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idLivro);
            stmt.setDate(2, dataEmprestimo);
            stmt.setDate(3, dataDevolucao);
            stmt.executeUpdate();
        }
    }

    // Lista todos os empréstimos
    public ResultSet getEmprestimos() throws SQLException {
        String sql = "SELECT e.id_emprestimo, l.titulo_livro, e.data_emprestimo, e.data_devolucao " +
                "FROM emprestimo e JOIN livro l ON e.id_livro = l.id_livro";
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        return stmt.executeQuery();
    }

    // Atualiza um empréstimo
    public void updateEmprestimo(int id, int idLivro, Date novaDataEmprestimo, Date novaDataDevolucao) throws SQLException {
        String sql = "UPDATE emprestimo SET id_livro = ?, data_emprestimo = ?, data_devolucao = ? WHERE id_emprestimo = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idLivro);
            stmt.setDate(2, novaDataEmprestimo);
            stmt.setDate(3, novaDataDevolucao);
            stmt.setInt(4, id);
            stmt.executeUpdate();
        }
    }

    // Deleta um empréstimo
    public void deleteEmprestimo(int id) throws SQLException {
        String sql = "DELETE FROM emprestimo WHERE id_emprestimo = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }


}
