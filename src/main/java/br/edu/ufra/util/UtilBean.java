package br.edu.ufra.util;

import br.edu.ufra.entidade.Usuario;
import br.edu.ufra.rn.UsuarioRN;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class UtilBean {

    /**
     * Recupera, do escopo da requisição, o valor do parâmetro com nome
     * informado.
     *
     * @param parametro nome do parâmetro que guarda o valor que se deseja
     * recuperar
     * @return
     */
    public static String obterValor(String parametro) {
        if (parametro == null) {
            return null;
        } else {
            FacesContext currentInstance = FacesContext.getCurrentInstance();
            return currentInstance.getExternalContext().getRequestParameterMap().get(parametro);
        }
    }

    public static void adicionarValor(String parametro, String valor) {
        if (parametro != null && valor != null) {
            FacesContext currentInstance = FacesContext.getCurrentInstance();
            currentInstance.getExternalContext().getRequestParameterMap().put(parametro, valor);
        }
    }

    public static void criarMensagemDeInformacao(String resumo, String detalhe) {
        criarMensagem(FacesMessage.SEVERITY_INFO, resumo, detalhe);
    }

    public static void criarMensagemDeInformacao(String detalhe) {
        criarMensagem(FacesMessage.SEVERITY_INFO, "Sucesso", detalhe);
    }

    public static void criarMensagemDeAviso(String resumo, String detalhe) {
        criarMensagem(FacesMessage.SEVERITY_WARN, resumo, detalhe);
    }

    public static void criarMensagemDeAviso(String detalhe) {
        criarMensagem(FacesMessage.SEVERITY_WARN, "Alerta", detalhe);
    }

    public static void criarMensagemDeErro(String resumo, String detalhe) {
        criarMensagem(FacesMessage.SEVERITY_ERROR, resumo, detalhe);
    }

    public static void criarMensagemDeErro(String detalhe) {
        criarMensagem(FacesMessage.SEVERITY_ERROR, "Erro", detalhe);
    }

    private static void criarMensagem(FacesMessage.Severity tipo, String resumo, String detalhe) {
        FacesContext currentInstance = FacesContext.getCurrentInstance();
        FacesMessage fm = new FacesMessage(resumo, detalhe);
        fm.setSeverity(tipo);
        currentInstance.addMessage(null, fm);
    }

    public static void encerrarSessao() {
        FacesContext currentInstance = FacesContext.getCurrentInstance();
        if (currentInstance != null) {
            ExternalContext externalContext = currentInstance.getExternalContext();
            if (externalContext != null) {
                Object session = externalContext.getSession(false);
                if (session != null
                        && session instanceof HttpSession) {
                    ((HttpSession) session).invalidate();
                }
            }
        }
    }

    public static Object daSessao(String chave) {
        if (chave == null) {
            return null;
        } else {
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            Map<String, Object> sessao = externalContext.getSessionMap();
            return sessao.get(chave);
        }
    }

    public static void naSessao(String chave, Object valor) {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessao = externalContext.getSessionMap();
        //Preciosismo: remover antes de inserir
        sessao.remove(chave);
        sessao.put(chave, valor);
    }

    public static String obterLoginDaSessao() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext e = facesContext.getExternalContext();
        if (e != null) {
            return e.getRemoteUser();
        } else {
            return null;
        }
    }

    public static Usuario obterUsuarioLogado() {
        final String CHAVE_USUARIO = "usuario";
        Object objUsuario = UtilBean.daSessao(CHAVE_USUARIO);
        if (objUsuario == null) {
            String email = UtilBean.obterLoginDaSessao();
            UsuarioRN usuarioRN = new UsuarioRN();
            Usuario usuario = usuarioRN.obter(email);
            Usuario usuarioTemp = null;
            //Clonando o usuário logado (sem a senha)
            if (usuario != null) {
                usuarioTemp = new Usuario();
                usuarioTemp.setId(usuario.getId());
                usuarioTemp.setNome(usuario.getNome());
                usuarioTemp.setSobrenome(usuario.getSobrenome());
                usuarioTemp.setEmail(usuario.getEmail());
                usuarioTemp.setPerfil(usuario.getPerfil());
            }
            UtilBean.naSessao(CHAVE_USUARIO, usuarioTemp);

            return usuarioTemp;
        } else {
            return (Usuario) objUsuario;
        }
    }

    public static void atualizarUsuarioLogado(Usuario usuario) {
        final String CHAVE_PESSOA = "usuario";
        if (usuario != null) {
            naSessao(CHAVE_PESSOA, usuario);
        }
    }

//    public static String irParaPaginaPrincipal(Pessoa usuario ){
//        if (usuario != null){
//            if (possuiPapel(usuario, 'A')) return "/administrador/principal.xhtml?faces-redirect=true";
//            if (possuiPapel(usuario, 'C')) return "/coordenador/principal.xhtml?faces-redirect=true";
//            if (possuiPapel(usuario, 'G')) return "/gerente/principal.xhtml?faces-redirect=true";
//            if (possuiPapel(usuario, 'P')) return "/pesquisador/principal.xhtml?faces-redirect=true";
//            if (possuiPapel(usuario, 'B')) return "/bolsista/principal.xhtml?faces-redirect=true";
//        }
//        return "/login.xhtml?faces-redirect=true";
//    }
//

}
