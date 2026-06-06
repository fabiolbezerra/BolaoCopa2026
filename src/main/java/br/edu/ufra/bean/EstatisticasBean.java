package br.edu.ufra.bean;

import br.edu.ufra.rn.estatistica.EstatisticasDePalpiteRN;
import br.edu.ufra.rn.estatistica.EstatisticasDeUsuarioRN;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class EstatisticasBean {
    private final EstatisticasDeUsuarioRN ESTATISTICA_USUARIO_RN = new EstatisticasDeUsuarioRN();
    private final EstatisticasDePalpiteRN ESTATISTICA_PALPITE_RN = new EstatisticasDePalpiteRN();

    private long numeroDeCadastros;
    private long novosCadastros24h;
    private long numeroDeUsuariosBanidos;
    private long numeroDeUsuariosBanidos24h;
    private long novosPalpites24h;
    private long alteracoesDePalpites24h;
    private long numeroDePalpites;

    @PostConstruct
    public void init() {
        //Esttísticas de usuário
        novosCadastros24h = ESTATISTICA_USUARIO_RN.obterNovosCadastros24h();
        numeroDeCadastros = ESTATISTICA_USUARIO_RN.obterNumeroDeCadastros();
        numeroDeUsuariosBanidos = ESTATISTICA_USUARIO_RN.obterNumeroDeUsuariosBanidos();
        numeroDeUsuariosBanidos24h = ESTATISTICA_USUARIO_RN.obterNumeroDeUsuariosBanidos24h();

        //Estatísticas de palpite
        novosPalpites24h = ESTATISTICA_PALPITE_RN.obterNovosPalpites24h();
        alteracoesDePalpites24h = ESTATISTICA_PALPITE_RN.obterAlteracoesDePalpites24h();
        numeroDePalpites = ESTATISTICA_PALPITE_RN.obterNumeroDePalpites();
    }

    public long getNumeroDeCadastros() {
        return numeroDeCadastros;
    }

    public long getNovosCadastros24h() {
        return novosCadastros24h;
    }

    public long getNumeroDeUsuariosBanidos() {
        return numeroDeUsuariosBanidos;
    }

    public long getNumeroDeUsuariosBanidos24h() {
        return numeroDeUsuariosBanidos24h;
    }

    public long getNovosPalpites24h() {
        return novosPalpites24h;
    }

    public long getAlteracoesDePalpites24h() {
        return alteracoesDePalpites24h;
    }

    public long getNumeroDePalpites() {
        return numeroDePalpites;
    }
}
