package br.edu.ufra.bean;

import br.edu.ufra.entidade.Usuario;
import br.edu.ufra.entidade.tipo.EnumPerfilUsuario;
import br.edu.ufra.rn.UsuarioRN;
import br.edu.ufra.rn.email.MensagemDeEmailRN;
import br.edu.ufra.util.UtilBean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@ManagedBean
@ViewScoped
public class NovaContaBean implements Serializable {

    @Serial
    private static final long serialVersionUID = 4108360191111001470L;

    private final UsuarioRN USUARIO_RN = new UsuarioRN();
    private final MensagemDeEmailRN EMAIL_RN = new MensagemDeEmailRN();

    private String email;
    private String senha;
    private String codigoInformado; //Código secreto
    private String codigoGerado; //Código secreto
    private Instant horarioDoCodigo; //Horário de inicialização do código

    private boolean panelEmail = true;
    private boolean panelCodigo = false;
    private boolean panelFormulario = false;

    private Usuario usuario = new Usuario();

    public NovaContaBean() {
        System.out.println("Criando NovaContaBean ...");
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
        if (USUARIO_RN.obter(email) != null) {
            //Já existe um cadastro com o e-mail
            UtilBean.criarMensagemDeErro("E-mail já está cadastrado.");
            UtilBean.criarMensagemDeAviso("Usar e-mail diferente.");
        } else {
            codigoGerado = EMAIL_RN.enviarEmailDeValidacao(email);
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
            codigoGerado = EMAIL_RN.enviarEmailDeValidacao(email);
            this.codigoInformado = null;
        } else {
            UtilBean.criarMensagemDeInformacao("Validação", "Código informado foi validado");
            panelEmail = false;
            panelCodigo = false;
            panelFormulario = true;
        }
    }

    public String criarConta() {
        this.usuario.setEmail(this.email);
        this.usuario.setPontuacao(0);
        this.usuario.setPosicao(0);
        this.usuario.setPerfil(EnumPerfilUsuario.PALPITEIRO);

        boolean sucesso = USUARIO_RN.salvar(usuario);
        if (sucesso) {
            EMAIL_RN.enviarEmailDeSaudacao(usuario);
            UtilBean.criarMensagemDeAviso("Sucesso", "Conta gerada com sucesso!");
            UtilBean.criarMensagemDeAviso("E-mail", "Verifique sua caixa de entrada no e-mail.");
            return "login.xhtml?faces-redirect=true";
        } else {
            return null;
        }
    }

}