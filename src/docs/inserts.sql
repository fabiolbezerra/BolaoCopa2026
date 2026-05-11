USE bolao_bd;

-- =========================
-- GRUPOS (A–H)
-- =========================
INSERT INTO grupo (id, nome)
VALUES (1, 'Grupo A'),
       (2, 'Grupo B'),
       (3, 'Grupo C'),
       (4, 'Grupo D'),
       (5, 'Grupo E'),
       (6, 'Grupo F'),
       (7, 'Grupo G'),
       (8, 'Grupo H');

-- =========================
-- SELEÇÕES (exemplo consistente)
-- =========================
INSERT INTO selecao (id, nome, sigla, simbolo, grupo)
VALUES (1, 'Brasil', 'BRA', NULL, 1),
       (2, 'França', 'FRA', NULL, 1),
       (3, 'Japão', 'JPN', NULL, 1),
       (4, 'Nigéria', 'NGA', NULL, 1),

       (5, 'Argentina', 'ARG', NULL, 2),
       (6, 'Alemanha', 'GER', NULL, 2),
       (7, 'Estados Unidos', 'USA', NULL, 2),
       (8, 'Marrocos', 'MAR', NULL, 2),

       (9, 'Espanha', 'ESP', NULL, 3),
       (10, 'Inglaterra', 'ENG', NULL, 3),
       (11, 'México', 'MEX', NULL, 3),
       (12, 'Coreia do Sul', 'KOR', NULL, 3),

       (13, 'Portugal', 'POR', NULL, 4),
       (14, 'Holanda', 'NED', NULL, 4),
       (15, 'Uruguai', 'URU', NULL, 4),
       (16, 'Gana', 'GHA', NULL, 4);

-- =========================
-- FASES
-- =========================
INSERT INTO fase (id, nome, eliminatoria, inicio, fim)
VALUES (1, 'Fase de Grupos', 0, '2026-06-08', '2026-06-30'),
       (2, 'Oitavas de Final', 1, '2026-07-02', '2026-07-05'),
       (3, 'Quartas de Final', 1, '2026-07-08', '2026-07-10'),
       (4, 'Semifinal', 1, '2026-07-13', '2026-07-14'),
       (5, 'Final', 1, '2026-07-19', '2026-07-19');

-- =========================
-- PARTIDAS (FASE DE GRUPOS - EXEMPLOS)
-- =========================
INSERT INTO partida
(id, inicioDaPartida, atualizacaoDoResultado, local, golsA, golsB, tipoDeVitoria, fase, selecaoA, selecaoB, vencedor)
VALUES (1, '2026-06-08 16:00:00', NULL, 'Cidade do México', NULL, NULL, NULL, 1, 1, 2, NULL),
       (2, '2026-06-08 19:00:00', NULL, 'Toronto', NULL, NULL, NULL, 1, 3, 4, NULL),

       (3, '2026-06-09 16:00:00', NULL, 'Los Angeles', NULL, NULL, NULL, 1, 5, 6, NULL),
       (4, '2026-06-09 19:00:00', NULL, 'Nova York', NULL, NULL, NULL, 1, 7, 8, NULL),

       (5, '2026-06-10 16:00:00', NULL, 'Vancouver', NULL, NULL, NULL, 1, 9, 10, NULL),
       (6, '2026-06-10 19:00:00', NULL, 'Dallas', NULL, NULL, NULL, 1, 11, 12, NULL),

       (7, '2026-06-11 16:00:00', NULL, 'Miami', NULL, NULL, NULL, 1, 13, 14, NULL),
       (8, '2026-06-11 19:00:00', NULL, 'Seattle', NULL, NULL, NULL, 1, 15, 16, NULL);

-- =========================
-- CAMPUS UFRA (simplificado)
-- =========================
INSERT INTO campus (id, nome)
VALUES (1, 'Belém'),
       (2, 'Capanema'),
       (3, 'Capitão Poço'),
       (4, 'Paragominas'),
       (5, 'Parauapebas'),
       (6, 'Tomé-Açu');

-- =========================
-- CURSOS UFRA (exemplos reais)
-- =========================
INSERT INTO curso (id, nome, sigla, campus)
VALUES (1, 'Agronomia', 'AGRO', 1),
       (2, 'Engenharia Florestal', 'ENGFLO', 1),
       (3, 'Zootecnia', 'ZOO', 1),
       (4, 'Sistemas de Informação', 'BSI', 1),

       (5, 'Engenharia Ambiental', 'ENGAMB', 2),

       (6, 'Agronomia', 'AGRO', 3),

       (7, 'Engenharia de Produção', 'ENGPROD', 4),

       (8, 'Administração', 'ADM', 5),

       (9, 'Agronomia', 'AGRO', 6);


-- ========================
-- USUÁRIO ADMIN - Senha: a
-- ========================
insert into usuario (id, nome, sobrenome, email, senha, perfil, tipo, pontuacao, curso, campus)
VALUES (1, 'Henrique', 'Costa', 'antoniocavallero935@gmail.com',
        'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb', 'ADMINISTRADOR', 'DISCENTE', 0, 4, 1),
       (2, 'Gabriel', 'Chaves', 'gabrielchavesbiel@gmail.com',
        'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb', 'ADMINISTRADOR', 'DISCENTE', 0, 4, 1),
       (3, 'Paulo', 'Glória Neto', 'pauloglorianeto@gmail.com',
        'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb', 'ADMINISTRADOR', 'DISCENTE', 0, 4, 1),
       (4, 'Murillo', 'Espírito Santo', 'murillofes@gmail.com',
        'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb', 'ADMINISTRADOR', 'DISCENTE', 0, 4, 1);

-- =============================
-- USUÁRIO PALPITEIRO - Senha: a
-- =============================
insert into usuario (id, nome, sobrenome, email, senha, perfil, tipo, pontuacao, curso, campus)
VALUES (5, 'Fábio', 'Bezerra', 'fabio.bezerra@ufra.edu.br',
        'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb', 'PALPITEIRO', 'DOCENTE', 0, null, 1);
insert into usuario (id, nome, sobrenome, email, senha, perfil, tipo, pontuacao, curso, campus)
VALUES (6, 'Fulano', 'de Tal', 'fulano@email.com.br',
        'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb', 'PALPITEIRO', 'DOCENTE', 0, null, 1);