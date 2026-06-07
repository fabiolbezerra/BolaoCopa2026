package br.edu.ufra.bean;

import br.edu.ufra.entidade.Bolao;
import br.edu.ufra.entidade.Palpite;
import br.edu.ufra.entidade.Partida;
import br.edu.ufra.entidade.Usuario;
import br.edu.ufra.rn.BolaoRN;
import br.edu.ufra.rn.PalpiteRN;
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
    private final BolaoRN BOLAO_RN = new BolaoRN();
    private final PalpiteRN PALPITE_RN = new PalpiteRN();

    private Usuario usuario;

    private String chave;

    private List<Usuario> usuarios;
    private List<Usuario> usuariosBanidos;
    private List<Palpite> palpites;
    private List<Bolao> boloes;

    @PostConstruct
    public void init() {
        usuariosBanidos =  USUARIO_RN.obterBanidos();

        String usuarioID = UtilBean.obterValor("usuarioID");
        Usuario usuarioLogado = UtilBean.obterUsuarioLogado();
        if (usuarioID != null) {
            try {
                int id = Integer.parseInt(usuarioID);
                usuario = USUARIO_RN.obter(id);
                if (usuario == null) {
                    usuario = new Usuario();
                }
            } catch (Exception e) {
                usuario = new Usuario();
                UtilBean.criarMensagemDeErro("Não foi possível recuperar o usuário.");
            }
        }
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

    public List<Usuario> getUsuariosBanidos() {
        return usuariosBanidos;
    }

    public void setUsuariosBanidos(List<Usuario> usuariosBanidos) {
        this.usuariosBanidos = usuariosBanidos;
    }

    public List<Bolao> getBoloes() {
        return boloes;
    }

    public List<Palpite> getPalpites() {
        return palpites;
    }

    public void selecionar() {
        if (usuario != null) {
            palpites = PALPITE_RN.obter(usuario);
            boloes = BOLAO_RN.obter(usuario);
            UtilBean.criarMensagemDeInformacao(usuario.getNome(), "Selecionado!");
        }
    }

    public void pesquisar() {
        usuarios = USUARIO_RN.pesquisar(chave);
        if (usuarios != null && !usuarios.isEmpty()) {
            UtilBean.criarMensagemDeInformacao("Usuários encontrados com sucesso!");
        } else {
            UtilBean.criarMensagemDeAviso("Nenhum usuário encontrado.");
        }
    }

    public void banir() {
        if (USUARIO_RN.banir(usuario)) {
            UtilBean.criarMensagemDeInformacao("Usuário banido com sucesso!");
        } else {
            UtilBean.criarMensagemDeErro("Erro ao Banir", "Não foi possível alterar o usuário");
        }
    }
}
