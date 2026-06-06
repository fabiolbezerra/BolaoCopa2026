USE bolao_bd;

-- =============================
-- USUÁRIO PALPITEIRO - Senha: a
-- =============================
insert into usuario (id, nome, sobrenome, email, senha, perfil, tipo, pontuacao, posicao, curso, campus)
VALUES (5, 'Fábio', 'Bezerra', 'fabio.bezerra@ufra.edu.br',
        'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb', 'PALPITEIRO', 'DOCENTE', 0, 0, null, 1),
       (6, 'Fulano', 'de Tal', 'fulano@email.com.br',
        'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb', 'PALPITEIRO', 'DOCENTE', 0, 0, null, 1),
       (7, 'Licinius', 'Dimitri', 'licinius@ufra.edu.br',
        'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb', 'PALPITEIRO', 'DOCENTE', 0, 0, null, 1);

-- ==============================================================================
-- NOVOS USUÁRIOS PALPITEIROS (JOGADORES E JOGADORAS DA SELEÇÃO BRASILEIRA)
-- Respeitando restrições: DOCENTE/TECNICO -> curso = NULL
-- DISCENTE -> curso correspondente ao campus correto
-- ==============================================================================

INSERT INTO usuario (id, nome, sobrenome, email, senha, perfil, tipo, pontuacao, posicao, curso, campus)
VALUES
    -- Campus 1: Belém
    (8, 'Marta', 'Silva', 'marta.silva@ufra.edu.br',
     'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb', 'PALPITEIRO', 'DOCENTE', 0, 0, NULL, 1),
    (9, 'Vinicius', 'Junior', 'vini.jr@aluno.ufra.edu.br',
     'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb', 'PALPITEIRO', 'DISCENTE', 0, 0, 12,
     1), -- SI Belém (ID 12)
    (10, 'Debinha', 'Marta', 'debinha.m@ufra.edu.br',
     'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb', 'PALPITEIRO', 'TECNICO', 0, 0, NULL, 1),

    -- Campus 2: Capanema
    (11, 'Rodrygo', 'Goes', 'rodrygo.goes@ufra.edu.br',
     'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb', 'PALPITEIRO', 'DOCENTE', 0, 0, NULL, 2),
    (12, 'Tamires', 'Dias', 'tamires.dias@aluno.ufra.edu.br',
     'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb', 'PALPITEIRO', 'DISCENTE', 0, 0, 15,
     2), -- ADM Capanema (ID 15)
    (13, 'Lucas', 'Paquetá', 'lucas.paqueta@ufra.edu.br',
     'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb', 'PALPITEIRO', 'TECNICO', 0, 0, NULL, 2),

    -- Campus 3: Capitão Poço
    (14, 'Rafaelle', 'Souza', 'rafaelle.souza@ufra.edu.br',
     'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb', 'PALPITEIRO', 'DOCENTE', 0, 0, NULL, 3),
    (15, 'Endrick', 'Felipe', 'endrick.felipe@aluno.ufra.edu.br',
     'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb', 'PALPITEIRO', 'DISCENTE', 0, 0, 24,
     3), -- SI Cap. Poço (ID 24)
    (16, 'Gabi', 'Nunes', 'gabi.nunes@ufra.edu.br',
     'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb', 'PALPITEIRO', 'TECNICO', 0, 0, NULL, 3),

    -- Campus 4: Paragominas
    (17, 'Bruno', 'Guimarães', 'bruno.g@ufra.edu.br',
     'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb', 'PALPITEIRO', 'DOCENTE', 0, 0, NULL, 4),
    (18, 'Ary', 'Borges', 'ary.borges@aluno.ufra.edu.br',
     'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb', 'PALPITEIRO', 'DISCENTE', 0, 0, 30,
     4), -- SI Paragominas (ID 30)
    (19, 'Raphinha', 'Dias', 'raphinha.dias@ufra.edu.br',
     'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb', 'PALPITEIRO', 'TECNICO', 0, 0, NULL, 4),

    -- Campus 5: Parauapebas
    (20, 'Bia', 'Zaneratto', 'bia.z@ufra.edu.br',
     'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb', 'PALPITEIRO', 'DOCENTE', 0, 0, NULL, 5),
    (21, 'Gabriel', 'Magalhães', 'g.magalhaes@aluno.ufra.edu.br',
     'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb', 'PALPITEIRO', 'DISCENTE', 0, 0, 36,
     5), -- Eng. Prod Parauapebas (ID 36)
    (22, 'Antônia', 'Silva', 'antonia.silva@ufra.edu.br',
     'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb', 'PALPITEIRO', 'TECNICO', 0, 0, NULL, 5),

    -- Campus 6: Tomé-Açu
    (23, 'Alisson', 'Becker', 'alisson.becker@ufra.edu.br',
     'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb', 'PALPITEIRO', 'DOCENTE', 0, 0, NULL, 6),
    (24, 'Kerolin', 'Nicoli', 'kerolin.n@aluno.ufra.edu.br',
     'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb', 'PALPITEIRO', 'DISCENTE', 0, 0, 38,
     6), -- ADM Tomé-Açu (ID 38)
    (25, 'Éder', 'Militão', 'eder.militao@ufra.edu.br',
     'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb', 'PALPITEIRO', 'TECNICO', 0, 0, NULL, 6),

    -- Fechamento da rodada (Campi rotativos adicionais para completar 20)
    (26, 'Duda', 'Sampaio', 'duda.sampaio@ufra.edu.br',
     'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb', 'PALPITEIRO', 'DOCENTE', 0, 0, NULL, 1),
    (27, 'Marquinhos', 'Aoás', 'marquinhos@aluno.ufra.edu.br',
     'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb', 'PALPITEIRO', 'DISCENTE', 0, 0, 4,
     1) -- COMP Belém (ID 4)
;

-- Bolões
insert into bolao (id, nome, criacao, dono)
values (1, 'Amigos do BPM LAB', '2026-05-12', 5), -- Fábio é o dono
       (2, 'Amigos da Matemática', '2026-05-12', 7);
-- Licinius é o dono

-- Participantes do bolão 'Amigos do BPM LAB'
insert into participante (id, bolao, usuario)
values (1, 1, 5),
       (2, 1, 6);

-- Participantes do bolão 'Amigos da Matemática'
insert into participante (id, bolao, usuario)
values (3, 2, 5),
       (4, 2, 6),
       (5, 2, 7);

-- ========
-- PALPITES TODO: Acrescentar mais inserts de palpites para testes
-- ========
insert into palpite
(id, dataDoPalpite, ultimaAlteracao, golsA, golsB, tipoDeVitoria, pontuacao, usuario, partida)
values (1, '2026-05-26', '2026-05-26', 1, 0, 'TEMPO_NORMAL', 10, 5, 1),
       (2, '2026-05-26', '2026-05-26', 1, 0, 'TEMPO_NORMAL', 0, 6, 1),
       (3, '2026-05-26', '2026-05-26', 1, 0, 'TEMPO_NORMAL', null, 7, 1);