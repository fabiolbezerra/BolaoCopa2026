/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufra.bean;

import br.edu.ufra.entidade.Usuario;
import br.edu.ufra.rn.UsuarioRN;
import br.edu.ufra.util.UtilBean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

/**
 *
 * @author Fabio
 */
@ManagedBean
@RequestScoped
public class UsuarioBean {

    private final UsuarioRN USUARIO_RN = new UsuarioRN();

    private Usuario usuario = new Usuario();

    private List<Usuario> usuarios;

    /**
     * Creates a new instance of UsuarioBean
     */
    public UsuarioBean() {
    }

    @PostConstruct
    public void init() {
        usuarios = USUARIO_RN.listarTodos();
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario animal) {
        this.usuario = animal;
    }

    public Usuario getUsuarioLogado() {
        return UtilBean.obterUsuarioLogado();
    }

    public boolean isNovoUsuario() {
        return this.usuario != null
                && (this.usuario.getId() == null || this.usuario.getId() == 0);
    }

    public boolean isAdministrador() {
        Usuario u = getUsuarioLogado();
        if (u != null) {
            return u.getPerfil().isAdministrador();
        } else {
            return false;
        }
    }

    public boolean isPalpiteiro() {
        Usuario u = getUsuarioLogado();
        if (u != null) {
            return u.getPerfil().isPalpiteiro();
        } else {
            return false;
        }
    }

    public List<Usuario> autoCompletePalpiteiros(String query) {
        return USUARIO_RN.obterPalpiteiros(query);
    }

    public void excluir() {
        if (USUARIO_RN.excluir(usuario)) {
            UtilBean.criarMensagemDeInformacao("Usuario excluído");
            usuarios = USUARIO_RN.listarTodos();
        } else {
            UtilBean.criarMensagemDeErro("Não foi possível excluir o usuário");
        }
    }

    public void salvar() {
        if (USUARIO_RN.salvar(usuario)) {
            UtilBean.criarMensagemDeInformacao("Usuario salvo");
        } else {
            UtilBean.criarMensagemDeErro("Não foi possível salvar o usuário");
        }
    }

}
