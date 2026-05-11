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
       (8, 'Grupo H'),
       (9, 'Grupo I'),
       (10, 'Grupo J'),
       (11, 'Grupo K'),
       (12, 'Grupo L');

-- =========================
-- SELEÇÕES (exemplo consistente)
-- =========================
INSERT INTO selecao (id, nome, sigla, simbolo, grupo)
VALUES  -- Grupo A (ID: 1)
        (1, 'Mexico', 'MEX', NULL, 1),
        (2, 'South Africa', 'RSA', NULL, 1),
        (3, 'Korea Republic', 'KOR', NULL, 1),
        (4, 'Czechia', 'CZE', NULL, 1),

        -- Grupo B (ID: 2)
        (5, 'Canada', 'CAN', NULL, 2),
        (6, 'Switzerland', 'SUI', NULL, 2),
        (7, 'Qatar', 'QAT', NULL, 2),
        (8, 'Bosnia and Herzegovina', 'BIH', NULL, 2),

        -- Grupo C (ID: 3)
        (9, 'Brazil', 'BRA', NULL, 3),
        (10, 'Morocco', 'MAR', NULL, 3),
        (11, 'Scotland', 'SCO', NULL, 3),
        (12, 'Haiti', 'HAI', NULL, 3),

        -- Grupo D (ID: 4)
        (13, 'USA', 'USA', NULL, 4),
        (14, 'Paraguay', 'PAR', NULL, 4),
        (15, 'Australia', 'AUS', NULL, 4),
        (16, 'Türkiye', 'TUR', NULL, 4),

        -- Grupo E (ID: 5)
        (17, 'Germany', 'GER', NULL, 5),
        (18, 'Ecuador', 'ECU', NULL, 5),
        (19, 'Ivory Coast', 'CIV', NULL, 5),
        (20, 'Curacao', 'CUW', NULL, 5),

        -- Grupo F (ID: 6)
        (21, 'Netherlands', 'NED', NULL, 6),
        (22, 'Japan', 'JPN', NULL, 6),
        (23, 'Tunisia', 'TUN', NULL, 6),
        (24, 'Sweden', 'SWE', NULL, 6),

        -- Grupo G (ID: 7)
        (25, 'Belgium', 'BEL', NULL, 7),
        (26, 'Iran', 'IRN', NULL, 7),
        (27, 'Egypt', 'EGY', NULL, 7),
        (28, 'New Zealand', 'NZL', NULL, 7),

        -- Grupo H (ID: 8)
        (29, 'Spain', 'ESP', NULL, 8),
        (30, 'Uruguay', 'URU', NULL, 8),
        (31, 'Saudi Arabia', 'KSA', NULL, 8),
        (32, 'Cape Verde', 'CPV', NULL, 8),

        -- Grupo I (ID: 9)
        (33, 'France', 'FRA', NULL, 9),
        (34, 'Senegal', 'SEN', NULL, 9),
        (35, 'Norway', 'NOR', NULL, 9),
        (36, 'Iraq', 'IRQ', NULL, 9),

        -- Grupo J (ID: 10)
        (37, 'Argentina', 'ARG', NULL, 10),
        (38, 'Austria', 'AUT', NULL, 10),
        (39, 'Algeria', 'ALG', NULL, 10),
        (40, 'Jordan', 'JOR', NULL, 10),

        -- Grupo K (ID: 11)
        (41, 'Portugal', 'POR', NULL, 11),
        (42, 'Colombia', 'COL', NULL, 11),
        (43, 'Uzbekistan', 'UZB', NULL, 11),
        (44, 'DR Congo', 'COD', NULL, 11),

        -- Grupo L (ID: 12)
        (45, 'England', 'ENG', NULL, 12),
        (46, 'Croatia', 'CRO', NULL, 12),
        (47, 'Panama', 'PAN', NULL, 12),
        (48, 'Ghana', 'GHA', NULL, 12);

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
VALUES  -- Rodada 1 e partidas inaugurais baseadas no mapeamento oficial e de estádios
        (1, '2026-06-11 16:00:00', NULL, 'Mexico City', NULL, NULL, NULL, 1, 1, 2, NULL),
        (2, '2026-06-11 16:00:00', NULL, 'Guadalajara', NULL, NULL, NULL, 1, 3, 4, NULL),
        (3, '2026-06-12 16:00:00', NULL, 'Toronto', NULL, NULL, NULL, 1, 5, 8, NULL),
        (4, '2026-06-12 16:00:00', NULL, 'Los Angeles', NULL, NULL, NULL, 1, 13, 14, NULL),
        (5, '2026-06-13 18:00:00', NULL, 'New York/New Jersey', NULL, NULL, NULL, 1, 9, 10, NULL),
        (6, '2026-06-15 16:00:00', NULL, 'TBD', NULL, NULL, NULL, 1, 29, 32, NULL),
        (7, '2026-06-15 16:00:00', NULL, 'TBD', NULL, NULL, NULL, 1, 25, 27, NULL),
        (8, '2026-06-15 16:00:00', NULL, 'TBD', NULL, NULL, NULL, 1, 31, 30, NULL),
        (9, '2026-06-15 16:00:00', NULL, 'TBD', NULL, NULL, NULL, 1, 26, 28, NULL),
        (10, '2026-06-16 15:00:00', NULL, 'New York/New Jersey', NULL, NULL, NULL, 1, 33, 34, NULL),
        (11, '2026-06-16 16:00:00', NULL, 'TBD', NULL, NULL, NULL, 1, 36, 35, NULL),

        -- Rodadas intermediárias exigindo processamento intenso no agendamento e fusos
        (12, '2026-06-18 12:00:00', NULL, 'Atlanta', NULL, NULL, NULL, 1, 4, 2, NULL),
        (13, '2026-06-18 15:00:00', NULL, 'Los Angeles', NULL, NULL, NULL, 1, 6, 8, NULL),
        (14, '2026-06-18 18:00:00', NULL, 'Vancouver', NULL, NULL, NULL, 1, 5, 7, NULL),
        (15, '2026-06-18 21:00:00', NULL, 'Guadalajara', NULL, NULL, NULL, 1, 1, 3, NULL),
        (16, '2026-06-19 16:00:00', NULL, 'TBD', NULL, NULL, NULL, 1, 9, 12, NULL),
        (17, '2026-06-19 16:00:00', NULL, 'TBD', NULL, NULL, NULL, 1, 11, 10, NULL),
        (18, '2026-06-19 16:00:00', NULL, 'TBD', NULL, NULL, NULL, 1, 16, 14, NULL),
        (19, '2026-06-22 20:00:00', NULL, 'New York/New Jersey', NULL, NULL, NULL, 1, 35, 34, NULL),
        (20, '2026-06-23 16:00:00', NULL, 'San Francisco Bay Area', NULL, NULL, NULL, 1, 41, 43, NULL),
        (21, '2026-06-23 16:00:00', NULL, 'Houston', NULL, NULL, NULL, 1, 45, 48, NULL),
        (22, '2026-06-23 16:00:00', NULL, 'Boston', NULL, NULL, NULL, 1, 47, 46, NULL),

        -- Super rodada definidora do dia 24 de junho e desfecho da matriz de classificação
        (23, '2026-06-24 15:00:00', NULL, 'Vancouver', NULL, NULL, NULL, 1, 6, 5, NULL),
        (24, '2026-06-24 15:00:00', NULL, 'Seattle', NULL, NULL, NULL, 1, 8, 7, NULL),
        (25, '2026-06-24 18:00:00', NULL, 'Miami', NULL, NULL, NULL, 1, 11, 9, NULL),
        (26, '2026-06-24 18:00:00', NULL, 'Atlanta', NULL, NULL, NULL, 1, 10, 12, NULL),
        (27, '2026-06-24 21:00:00', NULL, 'Mexico City', NULL, NULL, NULL, 1, 4, 1, NULL),
        (28, '2026-06-24 21:00:00', NULL, 'Monterrey', NULL, NULL, NULL, 1, 2, 3, NULL),
        (29, '2026-06-24 16:00:00', NULL, 'Toronto', NULL, NULL, NULL, 1, 42, 44, NULL),
        (30, '2026-06-25 16:00:00', NULL, 'New York/New Jersey', NULL, NULL, NULL, 1, 18, 17, NULL),
        (31, '2026-06-27 17:00:00', NULL, 'New York/New Jersey', NULL, NULL, NULL, 1, 47, 45, NULL);

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
VALUES  -- Campus Belém (ID: 1)
        (1, 'Agronomia', 'AGRO', 1),
        (2, 'Ciências Biológicas', 'CB', 1),
        (3, 'Ciências e Tecnologia de Alimentos', 'CTA', 1),
        (4, 'Computação (Licenciatura)', 'COMP', 1),
        (5, 'Engenharia Ambiental e Energias Renováveis', 'EAER', 1),
        (6, 'Engenharia Cartográfica e Agrimensura', 'ECA', 1),
        (7, 'Engenharia de Pesca', 'EP', 1),
        (8, 'Engenharia Florestal', 'EF', 1),
        (9, 'Letras/Libras', 'LL', 1),
        (10, 'Letras/Português', 'LP', 1),
        (11, 'Pedagogia', 'PED', 1),
        (12, 'Sistemas de Informação', 'BSI', 1),
        (13, 'Medicina Veterinária', 'MV', 1),
        (14, 'Zootecnia', 'ZOO', 1),

        -- Campus Capanema (ID: 2)
        (15, 'Administração', 'ADM', 2),
        (16, 'Agronomia', 'AGRO', 2),
        (17, 'Ciências Biológicas (Bacharelado)', 'CBB', 2),
        (18, 'Ciências Biológicas (Licenciatura)', 'CBL', 2),
        (19, 'Ciências Contábeis', 'CC', 2),
        (20, 'Engenharia Ambiental e Sanitária', 'EAS', 2),

        -- Campus Capitão Poço (ID: 3)
        (21, 'Agronomia', 'AGRO', 3),
        (22, 'Ciências Biológicas', 'CB', 3),
        (23, 'Engenharia Florestal', 'EF', 3),
        (24, 'Sistemas de Informação', 'BSI', 3),
        (25, 'Computação (Licenciatura)', 'COMP', 3),

        -- Campus Paragominas (ID: 4)
        (26, 'Administração', 'ADM', 4),
        (27, 'Agronomia', 'AGRO', 4),
        (28, 'Ciências Contábeis', 'CC', 4),
        (29, 'Engenharia Florestal', 'EF', 4),
        (30, 'Sistemas de Informação', 'BSI', 4),
        (31, 'Zootecnia', 'ZOO', 4),

        -- Campus Parauapebas (ID: 5)
        (32, 'Administração', 'ADM', 5),
        (33, 'Agronomia', 'AGRO', 5),
        (34, 'Enfermagem', 'ENF', 5),
        (35, 'Engenharia Florestal', 'EF', 5),
        (36, 'Engenharia de Produção', 'EPROD', 5),
        (37, 'Zootecnia', 'ZOO', 5),

        -- Campus Tomé-Açu (ID: 6)
        (38, 'Administração', 'ADM', 6),
        (39, 'Ciências Biológicas', 'CB', 6),
        (40, 'Ciências Contábeis', 'CC', 6),
        (41, 'Engenharia Agrícola', 'EA', 6),
        (42, 'Letras/Língua Portuguesa', 'LP', 6);

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