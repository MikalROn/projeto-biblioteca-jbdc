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

CREATE TABLE emprestimo (
  id_emprestimo INT UNSIGNED NOT NULL AUTO_INCREMENT,
  id_livro INT UNSIGNED,
  data_emprestimo DATE,
  data_devolucao DATE,
  PRIMARY KEY(id_emprestimo),
  FOREIGN KEY(id_livro) REFERENCES livro(id_livro)
);