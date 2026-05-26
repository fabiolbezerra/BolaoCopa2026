package br.edu.ufra.rn;

import br.edu.ufra.dao.UsuarioDAO;
import br.edu.ufra.entidade.Partida;
import br.edu.ufra.entidade.Usuario;
import br.edu.ufra.entidade.tipo.EnumPerfilUsuario;
import br.edu.ufra.util.UtilEmail;
import br.edu.ufra.util.UtilTexto;

import java.util.List;

public class UsuarioRN {

    private final UsuarioDAO USUARIO_DAO = new UsuarioDAO();

    public boolean salvar(Usuario usuario) {
        if (usuario == null) {
            return false;
        } else {
            if (usuario.getId() == null || usuario.getId() == 0) {
                String senhaCriptografada = UtilTexto.criptografarSHA256(usuario.getSenha());
                usuario.setSenha(senhaCriptografada);
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

    public List<Usuario> listarTodos() {
        return USUARIO_DAO.obterTodos(Usuario.class);
    }

    public List<Usuario> obterPalpiteiros() {
        return USUARIO_DAO.obter(EnumPerfilUsuario.PALPITEIRO);
    }

    public List<Usuario> obterPalpiteiros(String query) {

        List<Usuario> resposta = USUARIO_DAO.obter(EnumPerfilUsuario.PALPITEIRO);

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

    public boolean excluir(Usuario usuario) {
        if (usuario == null || usuario.getId() == null || usuario.getId() == 0) {
            return false;
        } else {
            return USUARIO_DAO.excluir(usuario);
        }
    }

    public Usuario login(String usuario, String senha) {
        if (usuario == null
                || usuario.trim().isEmpty()
                || senha == null
                || senha.trim().isEmpty()) {
            return null;
        }

        return USUARIO_DAO.login(usuario, senha);
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

    public String enviarEmailDeValidacao(String email) {
        final String CODIGO_GERADO = UtilTexto.gerarNumeros(6);

        StringBuilder texto = new StringBuilder();
        texto
                .append("Olá!\n")
                .append("Digite o código abaixo no formulário de cadastro para VALIDAR seu e-mail.")
                .append("\n\n")
                .append(CODIGO_GERADO)
                .append("\n\n")
                .append("Abraços\n\n")
                .append("Equipe do Bolão da Copa do Mundo 2026")
                .append("\n\n");

        UtilEmail.enviarEmail(
                email,
                "Validação do e-mail",
                texto.toString()
        );

        return CODIGO_GERADO;
    }

    public boolean enviarEmailDeSaudacao(Usuario usuario) {
        if (usuario == null || usuario.getId() == null || usuario.getId() == 0) {
            return false;
        } else {
            StringBuilder saudacao =
                    new StringBuilder();

            saudacao
                    .append("Olá ")
                    .append(usuario.getNome())
                    .append("!\n\nSeja bem vindo(a) ao Bolão da Copa do Mundo 2026!\n\n")
                    .append("Agora você já pode:\n")
                    .append(" - Criar palpites\n")
                    .append(" - Acompanhar rankings\n")
                    .append(" - Participar de bolões\n\n")
                    .append("Abraços\n\n")
                    .append("Equipe do Bolão da Copa do Mundo 2026")
                    .append("\n\n");

            UtilEmail.enviarEmail(
                    usuario.getEmail(),
                    "Seja bem vindo(a)!",
                    saudacao.toString()
            );

            return true;
        }
    }

    public List<Usuario> obterOrdenadoPorPontuacao() {
        return USUARIO_DAO.obterOrdenadosPorPontuacao();
    }

}