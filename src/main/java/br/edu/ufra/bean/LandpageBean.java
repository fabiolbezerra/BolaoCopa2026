package br.edu.ufra.bean;

import br.edu.ufra.entidade.Partida;
import br.edu.ufra.entidade.Usuario;
import br.edu.ufra.rn.PartidaRN;
import br.edu.ufra.rn.UsuarioRN;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
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
        dezPrimeiros = USUARIO_RN.obterOrdenadosPorPontuacao(10);
        proximosJogos = PARTIDA_RN.obterProximasOitoPartidas();
    }

    public List<Usuario> getDezPrimeiros() {
        return dezPrimeiros;
    }

    public List<Partida> getProximosJogos() {
        return proximosJogos;
    }
}
