package br.edu.ufra.bean;

import br.edu.ufra.entidade.Usuario;
import br.edu.ufra.entidade.tipo.EnumPerfilUsuario;
import br.edu.ufra.rn.UsuarioRN;
import br.edu.ufra.util.UtilBean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.time.Instant;

@ManagedBean
@ViewScoped
public class RedefinirSenhaBean implements Serializable {

    private final static UsuarioRN USUARIO_RN = new UsuarioRN();

    private String email;
    private String senha;
    private String codigoInformado; //Código secreto
    private String codigoGerado; //Código secreto
    private Instant horarioDoCodigo; //Horário de inicialização do código

    private boolean panelEmail = true;
    private boolean panelCodigo = false;
    private boolean panelFormulario = false;

    private Usuario usuario = new Usuario();

    public RedefinirSenhaBean() {
        System.out.println("Criando RedefinirSenhaBean ...");
    }

    @PostConstruct
    public void init() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCodigoInformado() {
        return codigoInformado;
    }

    public void setCodigoInformado(String codigoInformado) {
        this.codigoInformado = codigoInformado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isPanelEmail() {
        return panelEmail;
    }

    public boolean isPanelCodigo() {
        return panelCodigo;
    }

    public boolean isPanelFormulario() {
        return panelFormulario;
    }

    public boolean isCodigoValido() {
        return codigoGerado != null
                && !codigoGerado.isEmpty()
                && codigoGerado.equals(codigoInformado) //Códigos iguais
                && horarioDoCodigo.plusSeconds(241).isAfter(Instant.now()); //Validação dentro do limite de tempo
    }

    public void enviarEmail() {
        usuario = USUARIO_RN.obter(email);
        if (usuario == null) {
            //Não existe palpiteiro com o e-mail informado
            UtilBean.criarMensagemDeErro("Palpiteiro não cadastrado.");
            UtilBean.criarMensagemDeAviso("Informar e-mail de palpiteiro existente.");
        } else {
            codigoGerado = USUARIO_RN.enviarEmailDeValidacao(usuario);
            panelEmail = false;
            panelCodigo = true;
            panelFormulario = false;
            horarioDoCodigo = Instant.now();
            UtilBean.criarMensagemDeInformacao("Código", "Email enviado com informação para validação!");
            UtilBean.criarMensagemDeAviso("Atenção", "Mensagem pode estar no spam");
        }
    }

    public void validarEmail() {
        if (!isCodigoValido()) {
            UtilBean.criarMensagemDeErro("O código informado está incorreto ou expirou.");
            UtilBean.criarMensagemDeErro("Um novo código foi gerado.");
            horarioDoCodigo = Instant.now();
            codigoGerado = USUARIO_RN.enviarEmailDeValidacao(email);
            this.codigoInformado = null;
        } else {
            UtilBean.criarMensagemDeInformacao("Validação", "Código informado foi validado");
            panelEmail = false;
            panelCodigo = false;
            panelFormulario = true;
        }
    }

    public String redefinirSenha() {
        boolean sucesso = USUARIO_RN.alterarSenha(usuario, senha);
        if (sucesso) {
            USUARIO_RN.enviarEmailDeRedefinicaoDaSenha(usuario);
            UtilBean.criarMensagemDeAviso("Sucesso", "Conta gerada com sucesso!");
            UtilBean.criarMensagemDeAviso("E-mail", "Verifique sua caixa de entrada no e-mail.");
            return "login.xhtml?faces-redirect=true";
        } else {
            return null;
        }
    }

}