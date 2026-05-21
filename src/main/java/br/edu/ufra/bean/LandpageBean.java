package br.edu.ufra.bean;

import br.edu.ufra.entidade.Partida;
import br.edu.ufra.entidade.Usuario;
import br.edu.ufra.rn.PartidaRN;
import br.edu.ufra.rn.UsuarioRN;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import java.util.List;

@ManagedBean
@RequestScoped
public class LandpageBean {
    private final UsuarioRN USUARIO_RN = new UsuarioRN();
    private final PartidaRN PARTIDA_RN = new PartidaRN();
    private List<Usuario> dezPrimeiros;
    private List<Partida> proximosJogos;

    public LandpageBean() {
    }

    @PostConstruct
    public void init() {
        List<Usuario> ranking = USUARIO_RN.obterOrdenadoPorPontuacao();
        if (ranking != null && ranking.size() > 10){
            dezPrimeiros = ranking.subList(0, 10);
        } else {
            dezPrimeiros = ranking;
        }
        proximosJogos = PARTIDA_RN.listarProximasPartidas();
        }

    public List<Usuario> getDezPrimeiros() {
        return dezPrimeiros;
    }

    public List<Partida> getProximosJogos() {
        return proximosJogos;
    }
}
