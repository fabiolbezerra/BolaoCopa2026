package br.edu.ufra.rn;

import br.edu.ufra.dao.UsuarioDAO;
import br.edu.ufra.entidade.Usuario;
import br.edu.ufra.entidade.tipo.EnumPerfilUsuario;
import br.edu.ufra.util.UtilEmail;
import br.edu.ufra.util.UtilTexto;

import java.util.Date;
import java.util.List;

public class UsuarioRN {

    private final UsuarioDAO DAO = new UsuarioDAO();

    public boolean salvar(Usuario usuario) {
        if (usuario == null) {
            return false;
        } else {
            if (usuario.getId() == null || usuario.getId() == 0) {
                String senhaCriptografada = UtilTexto.criptografarSHA256(usuario.getSenha());
                usuario.setSenha(senhaCriptografada);
                return DAO.criar(usuario);
            } else {
                return DAO.alterar(usuario);
            }
        }
    }

    public List<Usuario> listarTodos() {
        return DAO.obterTodos(Usuario.class);
    }

    public List<Usuario> listarPalpiteiros(String query) {
        List<Usuario> resposta = DAO.obter(EnumPerfilUsuario.PALPITEIRO);
        if (query == null || query.trim().equals("")) {
            return resposta; //Todos
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

    public List<Usuario> listarAdminstradores() {
        return DAO.obter(EnumPerfilUsuario.ADMINISTRADOR);
    }

    public Usuario obter(Integer id) {
        return DAO.obter(Usuario.class, id);
    }

    public boolean excluir(Usuario usuario) {
        if (usuario == null) {
            return false;
        } else {
            return DAO.excluir(usuario);
        }
    }

    public Usuario login(String usuario, String senha) {
        if (usuario == null
                || usuario.trim().isEmpty()
                || senha == null
                || senha.trim().isEmpty()) {
            return null;
        }
        return DAO.login(usuario, senha);
    }

    public Usuario obter(String email) {
        if (email == null || email.trim().isEmpty()) {
            return null;
        }
        return DAO.obter(email);
    }

    public String enviarEmailDeValidacao(String email) {
        final String CODIGO_GERADO = UtilTexto.gerarNumeros(6);
        StringBuilder texto = new StringBuilder();
        texto
                .append("Olá!\n")
                .append("Digite o código abaixo no formulário de cadastro para validar seu email.")
                .append("\n")
                .append(CODIGO_GERADO);
        UtilEmail.enviarEmail(email, "Validação do email", texto.toString());
        return CODIGO_GERADO;
    }
    public void enviarEmailDeSaudacao(String email) {
        StringBuilder saudacao = new StringBuilder();
        saudacao
                .append("Olá palpiteiro(a)!\n")
                .append("Seja bem vindo(a) !\n.")
                .append("Agora você já pode:\n")
                .append(" - Criar palpites\n")
                .append(" - Acompanhar rankings\n")
                .append(" - Participar de bolões\n");
        UtilEmail.enviarEmail(email, "Seja bem vindo(a)!", saudacao.toString());
    }
}
