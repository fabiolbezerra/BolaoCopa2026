package br.edu.ufra.rn;

import br.edu.ufra.dao.UsuarioDAO;
import br.edu.ufra.entidade.Usuario;
import br.edu.ufra.util.UtilTexto;

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

    public List<Usuario> listar() {
        return DAO.obterTodos(Usuario.class);
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
}
