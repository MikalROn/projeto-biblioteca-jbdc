CREATE DATABASE IF NOT EXISTS `biblioteca`;

USE `biblioteca`;

CREATE TABLE categoria (
  id_categoria INT UNSIGNED NOT NULL AUTO_INCREMENT,
  nome_categoria VARCHAR(45),
  PRIMARY KEY(id_categoria)
);

CREATE TABLE livro (
  id_livro INT UNSIGNED NOT NULL AUTO_INCREMENT,
  titulo_livro VARCHAR(200),
  id_categoria INT UNSIGNED,
  PRIMARY KEY(id_livro),
  FOREIGN KEY(id_categoria) REFERENCES categoria(id_categoria)
);

CREATE TABLE usuario (
  id_usuario INT UNSIGNED NOT NULL AUTO_INCREMENT,
  nome_usuario VARCHAR(100),
  email_usuario VARCHAR(100),
  PRIMARY KEY(id_usuario)
);

INSERT INTO usuario (nome_usuario, email_usuario) VALUES
('João Silva', 'joao.silva@example.com'),
('Maria Oliveira', 'maria.oliveira@example.com'),
('Carlos Souza', 'carlos.souza@example.com'),
('Ana Santos', 'ana.santos@example.com'),
('Fernanda Lima', 'fernanda.lima@example.com');

CREATE TABLE emprestimo (
  id_emprestimo INT UNSIGNED NOT NULL AUTO_INCREMENT,
  id_livro INT UNSIGNED NOT NULL,
  id_usuario INT UNSIGNED NOT NULL,
  data_emprestimo DATE,
  data_devolucao DATE,
  PRIMARY KEY(id_emprestimo),
  FOREIGN KEY(id_livro) REFERENCES livro(id_livro),
  FOREIGN KEY(id_usuario) REFERENCES usuario(id_usuario)
);
