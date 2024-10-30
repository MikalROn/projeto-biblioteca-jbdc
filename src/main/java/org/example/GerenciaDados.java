package org.example;

import java.sql.*;

public class GerenciaDados {
    private final String URL = "jdbc:mysql://localhost:3306/biblioteca?useUnicode=true&characterEncoding=utf8";
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
        } catch (Exception e) {
            e.printStackTrace();
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

    // CRUD usuario

    // Adiciona um novo usuário
    public void addUsuario(String nome, String email) throws SQLException {
        String sql = "INSERT INTO usuario (nome_usuario, email_usuario) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.executeUpdate();
        }
    }

    // Lista todos os usuários
    public ResultSet getUsuarios() throws SQLException {
        String sql = "SELECT * FROM usuario";
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        return stmt.executeQuery();
    }

    // Atualiza um usuário
    public void updateUsuario(int id, String novoNome, String novoEmail) throws SQLException {
        String sql = "UPDATE usuario SET nome_usuario = ?, email_usuario = ? WHERE id_usuario = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, novoNome);
            stmt.setString(2, novoEmail);
            stmt.setInt(3, id);
            stmt.executeUpdate();
        }
    }

    // Deleta um usuário
    public void deleteUsuario(int id) throws SQLException {
        String sql = "DELETE FROM usuario WHERE id_usuario = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Adiciona um novo empréstimo
    public void addEmprestimo(int idLivro, int idUsuario, Date dataEmprestimo, Date dataDevolucao) throws SQLException {
        String sql = "INSERT INTO emprestimo (id_livro, id_usuario, data_emprestimo, data_devolucao) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idLivro);
            stmt.setInt(2, idUsuario);
            stmt.setDate(3, dataEmprestimo);
            stmt.setDate(4, dataDevolucao);
            stmt.executeUpdate();
        }
    }

    // Lista todos os empréstimos
    public ResultSet getEmprestimos() throws SQLException {
        String sql = "SELECT e.id_emprestimo, l.titulo_livro, u.nome_usuario, e.data_emprestimo, e.data_devolucao " +
                "FROM emprestimo e " +
                "JOIN livro l ON e.id_livro = l.id_livro " +
                "JOIN usuario u ON e.id_usuario = u.id_usuario";
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        return stmt.executeQuery();
    }

    // Atualiza um empréstimo
    public void updateEmprestimo(int id, int idLivro, int idUsuario, Date novaDataEmprestimo, Date novaDataDevolucao) throws SQLException {
        String sql = "UPDATE emprestimo SET id_livro = ?, id_usuario = ?, data_emprestimo = ?, data_devolucao = ? WHERE id_emprestimo = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idLivro);
            stmt.setInt(2, idUsuario); // Atualiza o ID do usuário
            stmt.setDate(3, novaDataEmprestimo);
            stmt.setDate(4, novaDataDevolucao);
            stmt.setInt(5, id);
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
