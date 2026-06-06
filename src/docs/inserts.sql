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
VALUES -- Grupo A (ID: 1)
       (1, 'México', 'MEX', 'mexico.png', 1),
       (2, 'África do Sul', 'AFS', 'africa-do-sul.png', 1),
       (3, 'Coreia do Sul', 'COR', 'coreia-do-sul.png', 1),
       (4, 'República Tcheca', 'RCH', 'republica-tcheca.png', 1),

       -- Grupo B (ID: 2)
       (5, 'Canadá', 'CAN', 'canada.png', 2),
       (6, 'Suíça', 'SUI', 'suica.png', 2),
       (7, 'Catar', 'CAT', 'catar.png', 2),
       (8, 'Bósnia e Herzegovina', 'BIH', 'bosnia-e-herzegovina.png', 2),

       -- Grupo C (ID: 3)
       (9, 'Brasil', 'BRA', 'brasil.png', 3),
       (10, 'Marrocos', 'MAR', 'marrocos.png', 3),
       (11, 'Escócia', 'ESC', 'escocia.png', 3),
       (12, 'Haiti', 'HAI', 'haiti.png', 3),

       -- Grupo D (ID: 4)
       (13, 'Estados Unidos', 'EUA', 'estados-unidos.png', 4),
       (14, 'Paraguai', 'PAR', 'paraguai.png', 4),
       (15, 'Austrália', 'AUS', 'australia.png', 4),
       (16, 'Turquia', 'TUR', 'turquia.png', 4),

       -- Grupo E (ID: 5)
       (17, 'Alemanha', 'ALE', 'alemanha.png', 5),
       (18, 'Equador', 'EQU', 'equador.png', 5),
       (19, 'Costa do Marfim', 'CIV', 'costa-do-marfim.png', 5),
       (20, 'Curaçao', 'CUR', 'curacao.png', 5),

       -- Grupo F (ID: 6)
       (21, 'Países Baixos', 'NLD', 'paises-baixos.png', 6),
       (22, 'Japão', 'JAP', 'japao.png', 6),
       (23, 'Tunísia', 'TUN', 'tunisia.png', 6),
       (24, 'Suécia', 'SUE', 'suecia.png', 6),

       -- Grupo G (ID: 7)
       (25, 'Bélgica', 'BEL', 'belgica.png', 7),
       (26, 'Irã', 'IRA', 'ira.png', 7),
       (27, 'Egito', 'EGI', 'egito.png', 7),
       (28, 'Nova Zelândia', 'NZL', 'nova-zelandia.png', 7),

       -- Grupo H (ID: 8)
       (29, 'Espanha', 'ESP', 'espanha.png', 8),
       (30, 'Uruguai', 'URU', 'uruguai.png', 8),
       (31, 'Arábia Saudita', 'ARA', 'arabia-saudita.png', 8),
       (32, 'Cabo Verde', 'CPV', 'cabo-verde.png', 8),

       -- Grupo I (ID: 9)
       (33, 'França', 'FRA', 'franca.png', 9),
       (34, 'Senegal', 'SEN', 'senegal.png', 9),
       (35, 'Noruega', 'NOR', 'noruega.png', 9),
       (36, 'Iraque', 'IRQ', 'iraque.png', 9),

       -- Grupo J (ID: 10)
       (37, 'Argentina', 'ARG', 'argentina.png', 10),
       (38, 'Áustria', 'AUT', 'austria.png', 10),
       (39, 'Argélia', 'ALG', 'argelia.png', 10),
       (40, 'Jordânia', 'JOR', 'jordania.png', 10),

       -- Grupo K (ID: 11)
       (41, 'Portugal', 'POR', 'portugal.png', 11),
       (42, 'Colômbia', 'COL', 'colombia.png', 11),
       (43, 'Uzbequistão', 'UZB', 'uzbequistao.png', 11),
       (44, 'República Democrática do Congo', 'RDC', 'republica-democratica-do-congo.png', 11),

       -- Grupo L (ID: 12)
       (45, 'Inglaterra', 'ING', 'inglaterra.png', 12),
       (46, 'Croácia', 'CRO', 'croacia.png', 12),
       (47, 'Panamá', 'PAN', 'panama.png', 12),
       (48, 'Gana', 'GAN', 'gana.png', 12);
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
(id, inicioDaPartida, atualizacaoDoResultado, local, publicada, golsA, golsB, tipoDeVitoria, fase, selecaoA, selecaoB,
 vencedor)
VALUES -- Primeira fase
       (1, '2026-06-11 16:00:00', NULL, 'Cidade do México', TRUE, NULL, NULL, NULL, 1, 1, 2, NULL),
       (2, '2026-06-11 23:00:00', NULL, 'Guadalajara', TRUE, NULL, NULL, NULL, 1, 3, 4, NULL),

       (3, '2026-06-12 16:00:00', NULL, 'Toronto', TRUE, NULL, NULL, NULL, 1, 5, 8, NULL),
       (4, '2026-06-12 22:00:00', NULL, 'Los Angeles', TRUE, NULL, NULL, NULL, 1, 13, 14, NULL),

       (5, '2026-06-13 16:00:00', NULL, 'Área da Baía de São Francisco', TRUE, NULL, NULL, NULL, 1, 7, 6, NULL),
       (6, '2026-06-13 19:00:00', NULL, 'Nova Jersey', TRUE, NULL, NULL, NULL, 1, 9, 10, NULL),
       (7, '2026-06-13 22:00:00', NULL, 'Boston', TRUE, NULL, NULL, NULL, 1, 12, 11, NULL),

       (8, '2026-06-14 1:00:00', NULL, 'Vancouver', TRUE, NULL, NULL, NULL, 1, 15, 16, NULL),
       (9, '2026-06-14 14:00:00', NULL, 'Houston', TRUE, NULL, NULL, NULL, 1, 17, 20, NULL),
       (10, '2026-06-14 17:00:00', NULL, 'Dallas', TRUE, NULL, NULL, NULL, 1, 21, 22, NULL),
       (11, '2026-06-14 20:00:00', NULL, 'Filadélfia', TRUE, NULL, NULL, NULL, 1, 19, 18, NULL),
       (12, '2026-06-14 23:00:00', NULL, 'Monterrey', TRUE, NULL, NULL, NULL, 1, 24, 23, NULL),

       (13, '2026-06-15 13:00:00', NULL, 'Atlanta', TRUE, NULL, NULL, NULL, 1, 29, 32, NULL),
       (14, '2026-06-15 16:00:00', NULL, 'Seattle', TRUE, NULL, NULL, NULL, 1, 25, 27, NULL),
       (15, '2026-06-15 19:00:00', NULL, 'Miami', TRUE, NULL, NULL, NULL, 1, 31, 30, NULL),
       (16, '2026-06-15 22:00:00', NULL, 'Los Angeles', TRUE, NULL, NULL, NULL, 1, 26, 28, NULL),

       (17, '2026-06-16 16:00:00', NULL, 'Nova Jersey', TRUE, NULL, NULL, NULL, 1, 33, 34, NULL),
       (18, '2026-06-16 19:00:00', NULL, 'Boston', TRUE, NULL, NULL, NULL, 1, 36, 35, NULL),
       (19, '2026-06-16 22:00:00', NULL, 'Cidade de Kansas', TRUE, NULL, NULL, NULL, 1, 37, 39, NULL),

       (20, '2026-06-17 1:00:00', NULL, 'Área da Baía de São Francisco', TRUE, NULL, NULL, NULL, 1, 38, 40, NULL),
       (21, '2026-06-17 14:00:00', NULL, 'Houston', TRUE, NULL, NULL, NULL, 1, 41, 44, NULL),
       (22, '2026-06-17 17:00:00', NULL, 'Dallas', TRUE, NULL, NULL, NULL, 1, 45, 46, NULL),
       (23, '2026-06-17 20:00:00', NULL, 'Toronto', TRUE, NULL, NULL, NULL, 1, 48, 47, NULL),
       (24, '2026-06-17 23:00:00', NULL, 'Cidade do México ', TRUE, NULL, NULL, NULL, 1, 43, 42, NULL),

       (25, '2026-06-18 13:00:00', NULL, 'Atlanta', TRUE, NULL, NULL, NULL, 1, 4, 2, NULL),
       (26, '2026-06-18 16:00:00', NULL, 'Los Angeles', TRUE, NULL, NULL, NULL, 1, 6, 8, NULL),
       (27, '2026-06-18 19:00:00', NULL, 'Vancouver', TRUE, NULL, NULL, NULL, 1, 5, 7, NULL),
       (28, '2026-06-18 22:00:00', NULL, 'Guadalajara', TRUE, NULL, NULL, NULL, 1, 1, 3, NULL),

       (29, '2026-06-19 16:00:00', NULL, 'Seattle', TRUE, NULL, NULL, NULL, 1, 13, 38, NULL),
       (30, '2026-06-19 19:00:00', NULL, 'Boston', TRUE, NULL, NULL, NULL, 1, 11, 10, NULL),
       (31, '2026-06-19 21:30:00', NULL, 'Filadélfia', TRUE, NULL, NULL, NULL, 1, 9, 12, NULL),

       (32, '2026-06-20 00:00:00', NULL, 'Área da Baía de São Francisco', TRUE, NULL, NULL, NULL, 1, 16, 14, NULL),
       (33, '2026-06-20 14:00:00', NULL, 'Houston', TRUE, NULL, NULL, NULL, 1, 21, 24, NULL),
       (34, '2026-06-20 17:00:00', NULL, 'Toronto', TRUE, NULL, NULL, NULL, 1, 17, 19, NULL),
       (35, '2026-06-20 21:00:00', NULL, 'Cidade do Kansas', TRUE, NULL, NULL, NULL, 1, 18, 20, NULL),

       (36, '2026-06-21 01:00:00', NULL, 'Monterrey', TRUE, NULL, NULL, NULL, 1, 23, 22, NULL),
       (37, '2026-06-21 13:00:00', NULL, 'Atlanta', TRUE, NULL, NULL, NULL, 1, 29, 31, NULL),
       (38, '2026-06-21 16:00:00', NULL, 'Los Angeles', TRUE, NULL, NULL, NULL, 1, 25, 26, NULL),
       (39, '2026-06-21 19:00:00', NULL, 'Miami', TRUE, NULL, NULL, NULL, 1, 30, 32, NULL),
       (40, '2026-06-21 22:00:00', NULL, 'Vancouver', TRUE, NULL, NULL, NULL, 1, 28, 27, NULL),

       (41, '2026-06-22 14:00:00', NULL, 'Dallas', TRUE, NULL, NULL, NULL, 1, 37, 38, NULL),
       (42, '2026-06-22 18:00:00', NULL, 'Filadélfia', TRUE, NULL, NULL, NULL, 1, 33, 36, NULL),
       (43, '2026-06-22 21:00:00', NULL, 'Nova Jersey', TRUE, NULL, NULL, NULL, 1, 35, 34, NULL),

       (44, '2026-06-23 00:00:00', NULL, 'Área da Baía de São Francisco', TRUE, NULL, NULL, NULL, 1, 40, 39, NULL),
       (45, '2026-06-23 14:00:00', NULL, 'Houston', TRUE, NULL, NULL, NULL, 1, 41, 43, NULL),
       (46, '2026-06-23 17:00:00', NULL, 'Boston', TRUE, NULL, NULL, NULL, 1, 45, 48, NULL),
       (47, '2026-06-23 20:00:00', NULL, 'Toronto', TRUE, NULL, NULL, NULL, 1, 47, 46, NULL),
       (48, '2026-06-23 23:00:00', NULL, 'Guadalajara', TRUE, NULL, NULL, NULL, 1, 42, 44, NULL),

       (49, '2026-06-24 16:00:00', NULL, 'Vancouver', TRUE, NULL, NULL, NULL, 1, 6, 5, NULL),
       (50, '2026-06-24 16:00:00', NULL, 'Seattle', TRUE, NULL, NULL, NULL, 1, 8, 7, NULL),
       (51, '2026-06-24 19:00:00', NULL, 'Miami', TRUE, NULL, NULL, NULL, 1, 11, 9, NULL),
       (52, '2026-06-24 19:00:00', NULL, 'Atlanta', TRUE, NULL, NULL, NULL, 1, 10, 12, NULL),
       (53, '2026-06-24 22:00:00', NULL, 'Cidade do México', TRUE, NULL, NULL, NULL, 1, 4, 1, NULL),
       (54, '2026-06-24 22:00:00', NULL, 'Monterrey', TRUE, NULL, NULL, NULL, 1, 2, 3, NULL),

       (55, '2026-06-25 17:00:00', NULL, 'Filadélfia', TRUE, NULL, NULL, NULL, 1, 20, 19, NULL),
       (56, '2026-06-25 17:00:00', NULL, 'Nova Jersey', TRUE, NULL, NULL, NULL, 1, 18, 17, NULL),
       (57, '2026-06-25 20:00:00', NULL, 'Dallas', TRUE, NULL, NULL, NULL, 1, 22, 24, NULL),
       (58, '2026-06-25 20:00:00', NULL, 'Cidade do Kansas', TRUE, NULL, NULL, NULL, 1, 23, 21, NULL),
       (59, '2026-06-25 23:00:00', NULL, 'Los Angeles', TRUE, NULL, NULL, NULL, 1, 16, 13, NULL),
       (60, '2026-06-25 23:00:00', NULL, 'Área da Baía de São Francisco', TRUE, NULL, NULL, NULL, 1, 14, 38, NULL),

       (61, '2026-06-26 16:00:00', NULL, 'Boston', TRUE, NULL, NULL, NULL, 1, 35, 33, NULL),
       (62, '2026-06-26 16:00:00', NULL, 'Toronto', TRUE, NULL, NULL, NULL, 1, 34, 36, NULL),
       (63, '2026-06-26 21:00:00', NULL, 'Houston', TRUE, NULL, NULL, NULL, 1, 32, 31, NULL),
       (64, '2026-06-26 21:00:00', NULL, 'Guadalajara', TRUE, NULL, NULL, NULL, 1, 30, 29, NULL),

       (65, '2026-06-27 00:00:00', NULL, 'Seattle', TRUE, NULL, NULL, NULL, 1, 27, 26, NULL),
       (66, '2026-06-27 00:00:00', NULL, 'Vancouver', TRUE, NULL, NULL, NULL, 1, 28, 25, NULL),
       (67, '2026-06-27 18:00:00', NULL, 'Nova Jersey', TRUE, NULL, NULL, NULL, 1, 47, 45, NULL),
       (68, '2026-06-27 18:00:00', NULL, 'Filadélfia', TRUE, NULL, NULL, NULL, 1, 46, 48, NULL),
       (69, '2026-06-27 20:30:00', NULL, 'Miami', TRUE, NULL, NULL, NULL, 1, 42, 41, NULL),
       (70, '2026-06-27 20:30:00', NULL, 'Atlanta', TRUE, NULL, NULL, NULL, 1, 44, 43, NULL),
       (71, '2026-06-27 23:00:00', NULL, 'Cidade do Kansas', TRUE, NULL, NULL, NULL, 1, 39, 38, NULL),
       (72, '2026-06-27 23:00:00', NULL, 'Dallas', TRUE, NULL, NULL, NULL, 1, 40, 37, NULL);

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
VALUES -- Campus Belém (ID: 1)
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
insert into usuario (id, nome, sobrenome, email, senha, perfil, tipo, pontuacao, posicao, curso, campus)
VALUES (1, 'Henrique', 'Costa', 'antoniocavallero935@gmail.com',
        'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb', 'ADMINISTRADOR', 'DISCENTE', 0, 0, 4, 1),
       (2, 'Gabriel', 'Chaves', 'gabrielchavesbiel@gmail.com',
        'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb', 'ADMINISTRADOR', 'DISCENTE', 0, 0, 4, 1),
       (3, 'Paulo', 'Glória Neto', 'pauloglorianeto@gmail.com',
        'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb', 'ADMINISTRADOR', 'DISCENTE', 0, 0, 4, 1),
       (4, 'Murillo', 'Espírito Santo', 'murillofes@gmail.com',
        'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb', 'ADMINISTRADOR', 'DISCENTE', 0, 0, 4, 1);
