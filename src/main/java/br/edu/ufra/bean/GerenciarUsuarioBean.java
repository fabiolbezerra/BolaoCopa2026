package br.edu.ufra.bean;

import br.edu.ufra.entidade.Usuario;
import br.edu.ufra.rn.UsuarioRN;
import br.edu.ufra.util.UtilBean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ManagedBean
@ViewScoped
public class GerenciarUsuarioBean {
    private final UsuarioRN USUARIO_RN = new UsuarioRN();

    private Usuario usuario;

    private String chave;

    private List<Usuario> usuarios;

    @PostConstruct
    public void init() {

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void selecionar() {
        if (usuario != null) {
            UtilBean.criarMensagemDeInformacao(usuario.getNome(), "Selecionado!");
        }
    }

    public void pesquisar() {
        usuarios = USUARIO_RN.pesquisar(chave);
        if (!usuarios.isEmpty()) {
            UtilBean.criarMensagemDeInformacao("Usuários encontrados com sucesso!");
        } else {
            UtilBean.criarMensagemDeAviso("Nenhum usuário encontrado.");
        }
    }
}
