-- Inserir categorias
INSERT INTO categoria (nome_categoria) VALUES
('Ficção'),
('Não Ficção'),
('Fantasia'),
('História'),
('Biografia'),
('Tecnologia');

-- Inserir livros
INSERT INTO livro (titulo_livro, id_categoria) VALUES
('O Senhor dos Anéis', 3), -- Fantasia
('1984', 1),               -- Ficção
('Sapiens: Uma Breve História da Humanidade', 2), -- Não Ficção
('O Código Da Vinci', 1),  -- Ficção
('A História do Tempo', 2), -- Não Ficção
('Steve Jobs', 5),         -- Biografia
('Dom Casmurro', 1),       -- Ficção
('O Pequeno Príncipe', 3); -- Fantasia

-- Inserir empréstimos
INSERT INTO emprestimo (id_livro, data_emprestimo, data_devolucao) VALUES
(1, '2024-10-01', '2024-10-15'), -- O Senhor dos Anéis
(2, '2024-10-05', '2024-10-20'), -- 1984
(3, '2024-10-10', '2024-10-25'), -- Sapiens: Uma Breve História da Humanidade
(4, '2024-10-12', '2024-10-26'), -- O Código Da Vinci
(5, '2024-10-15', '2024-10-30'), -- A História do Tempo
(6, '2024-10-20', '2024-11-03'); -- Steve Jobs
