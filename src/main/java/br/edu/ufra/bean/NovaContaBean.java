package br.edu.ufra.bean;

import br.edu.ufra.entidade.Bolao;
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
    private String codigo; //Código secreto
    private LocalDate horarioDoCodigo; //Horário de inicialização do código
    private Usuario usuario = new Usuario();

    public NovaContaBean() {
        System.out.println("Criando NovaContaBean ...");
    }

    @PostConstruct
    public void init(){

    }

    public void enviarEmail(){
        //TODO: Criar código com
        //TODO: enviar e-mail com código
    }
}
