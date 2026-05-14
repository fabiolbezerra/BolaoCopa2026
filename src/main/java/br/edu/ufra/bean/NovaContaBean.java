package br.edu.ufra.bean;

import br.edu.ufra.entidade.Usuario;
import br.edu.ufra.rn.UsuarioRN;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.time.LocalDate;

@ManagedBean
@ViewScoped
public class NovaContaBean {
    private final static UsuarioRN USUARIO_RN = new UsuarioRN();

    private String email;
    private String codigoInformado; //Código secreto
    private String codigoGerado; //Código secreto
    private LocalDate horarioDoCodigo; //Horário de inicialização do código
    private Usuario usuario = new Usuario();

    public NovaContaBean() {
        System.out.println("Criando NovaContaBean ...");
    }

    @PostConstruct
    public void init() {

    }

    public boolean isCodigoValido() {
        return codigoGerado != null
                && !codigoGerado.isEmpty()
                && codigoGerado.equals(codigoInformado);
    }

    public void enviarEmail() {
        //TODO: Criar código com 6 números
        //TODO: enviar e-mail com código
        //TODO: Inicializar data
    }

    public void validarEmail() {
        if (!isCodigoValido()) {
            //TODO: Código expirado?
            //TODO: Criar código com 6 números
            //TODO: Inicializar codigoInformado
        }
    }

    public String criarConta() {
        boolean sucesso = false;
        //TODO: tentar criar novo usuário
        if (sucesso) {
            return ""; //TODO: Encaminhar para página de login
        } else {
            return null;
        }
    }
}
