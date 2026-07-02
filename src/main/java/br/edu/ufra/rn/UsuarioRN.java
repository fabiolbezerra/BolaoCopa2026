package br.edu.ufra.rn;

import br.edu.ufra.dao.UsuarioDAO;
import br.edu.ufra.entidade.Campus;
import br.edu.ufra.entidade.Curso;
import br.edu.ufra.entidade.Partida;
import br.edu.ufra.entidade.Usuario;
import br.edu.ufra.entidade.comparador.ComparadorUsuarioPontuacao;
import br.edu.ufra.entidade.tipo.EnumPerfilUsuario;
import br.edu.ufra.rn.email.MensagemDeEmailRN;
import br.edu.ufra.util.UtilTexto;

import java.util.*;

public class UsuarioRN {

    private final UsuarioDAO USUARIO_DAO = new UsuarioDAO();
    private final MensagemDeEmailRN EMAIL_RN = new MensagemDeEmailRN();

    public boolean salvar(Usuario usuario) {
        if (usuario == null) {
            return false;
        } else {
            if (usuario.getId() == null || usuario.getId() == 0) {
                String senhaCriptografada = UtilTexto.criptografarSHA256(usuario.getSenha());
                usuario.setSenha(senhaCriptografada);
                usuario.setDataCadastro(new Date());
                return USUARIO_DAO.criar(usuario);
            } else {
                return USUARIO_DAO.alterar(usuario);
            }
        }
    }

    public boolean salvar(List<Usuario> usuarios) {
        if (usuarios == null || usuarios.isEmpty()) {
            return false;
        } else {
            return USUARIO_DAO.salvar(usuarios);
        }
    }

    public boolean alterarSenha(Usuario usuario, String novaSenha) {
        if (usuario == null
                || usuario.getId() == null
                || usuario.getId() == 0
                || novaSenha == null) {
            return false;
        } else {
            String senhaCriptografada = UtilTexto.criptografarSHA256(usuario.getSenha());
            usuario.setSenha(senhaCriptografada);
            return salvar(usuario);

        }
    }

    public List<Usuario> listarTodos() {
        return USUARIO_DAO.obterTodos(Usuario.class);
    }

    public List<Usuario> pesquisar(String chave) {
        if (chave == null || chave.isEmpty()) {
            return listarTodos();
        }
        return USUARIO_DAO.pesquisar(chave);
    }

    public List<Usuario> obterPalpiteiros() {
        return USUARIO_DAO.obter(EnumPerfilUsuario.PALPITEIRO);
    }

    public List<Usuario> obterPalpiteiros(String query) {
        List<Usuario> resposta = obterPalpiteiros();

        if (query == null || query.trim().equals("")) {
            return resposta;
        }

        String nome = null;

        for (int i = 0; i < resposta.size(); i++) {
            nome = resposta.get(i).getNome() + " " + resposta.get(i).getSobrenome();
            if (!nome.toUpperCase().contains(query.toUpperCase())) {
                resposta.remove(i);
                i--;
            }
        }

        return resposta;
    }

    public List<Usuario> obter(Partida partida) {
        if (partida == null || partida.getId() == null || partida.getId() == 0) {
            return null;
        }
        return USUARIO_DAO.obter(partida);
    }

    public Usuario obter(Integer id) {
        return USUARIO_DAO.obter(Usuario.class, id);
    }

    public boolean processarOrdenacao() {
        List<Usuario> palpiteiros = obterPalpiteiros();

        //Atualizando a pontuação de cada palpiteiro
        Integer pontuacao = 0;
        for (Usuario palpiteiro : palpiteiros) {
            //Atualizando a pontuação
            pontuacao = obterPontuacao(palpiteiro);
            palpiteiro.setPontuacao(pontuacao);
        }

        //Ordenando pela pontuação
        Collections.sort(palpiteiros, new ComparadorUsuarioPontuacao());

        Integer posicao = 1;
        //Atualizando a posição de cada palpiteiro
        for (Usuario palpiteiro : palpiteiros) {
            palpiteiro.setPosicao(posicao); //Atualizando posição
            posicao++;
        }

        //Pontuação dos palpites processada, então salvar pontuação e posição dos palpiteiros
        boolean usuariosAtualizados = salvar(palpiteiros);

        return usuariosAtualizados;
    }

    public boolean excluir(Usuario usuario) {
        if (usuario == null || usuario.getId() == null || usuario.getId() == 0) {
            return false;
        } else {
            return USUARIO_DAO.excluir(usuario);
        }
    }

    public Usuario autenticar(String usuario, String senha) {
        if (usuario == null
                || usuario.trim().isEmpty()
                || senha == null
                || senha.trim().isEmpty()) {
            return null;
        }
        //Criptografar a senha digitada
        String senhaCripto = UtilTexto.criptografarSHA256(senha);
        return USUARIO_DAO.login(usuario, senhaCripto);
    }

    public Usuario obter(String email) {
        if (email == null || email.trim().isEmpty()) {
            return null;
        }

        return USUARIO_DAO.obter(email);
    }

    public Integer obterPontuacao(Usuario palpiteiro) {
        if (palpiteiro == null || palpiteiro.getId() == null || palpiteiro.getId() == 0) {
            return null;
        } else {
            return USUARIO_DAO.obterPontuacao(palpiteiro);
        }
    }

    public List<Usuario> obterOrdenadosPorPontuacao(int limite) {
        if (limite <= 0) {
            return USUARIO_DAO.obterOrdenadosPorPontuacao(0);
        }
        return USUARIO_DAO.obterOrdenadosPorPontuacao(limite);
    }

    public List<Usuario> obterOrdenadosPorPontuacao(Campus campus) {
        if (campus == null || campus.getId() == null) {
            return null;
        }
        return USUARIO_DAO.obterOrdenadosPorPontuacao(campus);
    }

    public List<Usuario> obterOrdenadosPorPontuacao(Curso curso) {
        if (curso == null || curso.getId() == null) {
            return null;
        }
        return USUARIO_DAO.obterOrdenadosPorPontuacao(curso);
    }

    public List<Usuario> obterBanidos() {
        return USUARIO_DAO.obterBanidos();
    }

    public boolean banir(Usuario usuario) {
        if (usuario == null
                || usuario.getId() == null
                || usuario.getId() == 0
                || usuario.getPerfil().isAdministrador()) {
            return false;
        } else {
            usuario.setBanido(true);
            usuario.setDataBanimento(new Date());
            return USUARIO_DAO.alterar(usuario);
        }
    }

    public List<String> notificarRanking() {
        List<String> respostaDaNotificacao = new ArrayList<>();

        List<Usuario> usuarios = obterOrdenadosPorPontuacao(10);
        //Notificar usuários do ranking geral
        respostaDaNotificacao.add(EMAIL_RN.enviarEmailTop10Geral(usuarios));

        final CampusRN CAMPUS_RN = new CampusRN();
        List<Campus> campi = CAMPUS_RN.listarTodos();
        for (Campus campus : campi) {
            usuarios = obterOrdenadosPorPontuacao(campus);
            if (usuarios != null && !usuarios.isEmpty()) {
                if (usuarios.size() > 10) {
                    usuarios = usuarios.subList(0, 10);
                }
                //Notificar usuários do campus
                respostaDaNotificacao.add(EMAIL_RN.enviarEmailTop10Campus(usuarios, campus));
            }
            for (Curso curso : campus.getCursos()) {
                usuarios = obterOrdenadosPorPontuacao(curso);
                if (usuarios != null && !usuarios.isEmpty()) {
                    if (usuarios.size() > 10) {
                        usuarios = usuarios.subList(0, 10);
                    }
                    //Notificar usuários do curso
                    respostaDaNotificacao.add(EMAIL_RN.enviarEmailTop10Curso(usuarios, curso));
                }
            }
        }

        return respostaDaNotificacao;
    }

}