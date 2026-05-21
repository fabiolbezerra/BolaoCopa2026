package br.edu.ufra.bean;

import br.edu.ufra.entidade.Usuario;
import br.edu.ufra.entidade.tipo.EnumPerfilUsuario;
import br.edu.ufra.rn.UsuarioRN;
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

    private final static UsuarioRN USUARIO_RN = new UsuarioRN();

    private String email;
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
                && codigoGerado.equals(codigoInformado)
                && horarioDoCodigo.plusSeconds(241).isAfter(Instant.now());
    }

    public void enviarEmail() {
        codigoGerado = USUARIO_RN.enviarEmailDeValidacao(email);
        UtilBean.criarMensagemDeInformacao(codigoGerado);
        panelEmail = false;
        panelCodigo = true;
        panelFormulario = false;
        horarioDoCodigo = Instant.now();

    }

    public void validarEmail() {
        if (!isCodigoValido()) {
            UtilBean.criarMensagemDeErro("Erro ao validar email", "O código informado está incorreto ou expirou. Um novo código foi gerado.");
            horarioDoCodigo = Instant.now();
            codigoGerado = USUARIO_RN.enviarEmailDeValidacao(email);
            this.codigoInformado = null;
        } else {
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
            enviarSaudacao(usuario.getEmail());
            return "login.xhtml?faces-redirect=true";
        } else {
            return null;
        }
    }
    public void enviarSaudacao(String email){
        if (usuario.getEmail() != null) {
            USUARIO_RN.enviarEmailDeSaudacao(email);
        }
    }
}