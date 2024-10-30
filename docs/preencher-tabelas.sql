
INSERT INTO categoria (nome_categoria) VALUES
('Ficção'),
('Não Ficção'),
('Fantasia'),
('História'),
('Biografia'),
('Tecnologia');

INSERT INTO livro (titulo_livro, id_categoria) VALUES
('O Senhor dos Anéis', 3),
('1984', 1),
('Sapiens: Uma Breve História da Humanidade', 2),
('O Código Da Vinci', 1),
('A História do Tempo', 2),
('Steve Jobs', 5),
('Dom Casmurro', 1),
('O Pequeno Príncipe', 3);

INSERT INTO emprestimo (id_livro, data_emprestimo, data_devolucao) VALUES
(1, '2024-10-01', '2024-10-15'),
(2, '2024-10-05', '2024-10-20'),
(3, '2024-10-10', '2024-10-25'),
(4, '2024-10-12', '2024-10-26'),
(5, '2024-10-15', '2024-10-30'),
(6, '2024-10-20', '2024-11-03');
