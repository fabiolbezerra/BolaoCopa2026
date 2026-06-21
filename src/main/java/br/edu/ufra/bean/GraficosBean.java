package br.edu.ufra.bean;

import br.edu.ufra.entidade.Campus;
import br.edu.ufra.entidade.Partida;
import br.edu.ufra.rn.CampusRN;
import br.edu.ufra.rn.PartidaRN;
import br.edu.ufra.rn.estatistica.EstatisticasDePalpiteRN;
import br.edu.ufra.rn.estatistica.EstatisticasDeUsuarioRN;
import org.primefaces.model.chart.*;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;
import java.util.Map;

@ManagedBean
@RequestScoped
public class GraficosBean {
    private final EstatisticasDeUsuarioRN ESTATISTICA_USUARIO_RN = new EstatisticasDeUsuarioRN();
    private final EstatisticasDePalpiteRN ESTATISTICA_PALPITE_RN = new EstatisticasDePalpiteRN();
    private final CampusRN CAMPUS_RN = new CampusRN();
    private final PartidaRN PARTIDA_RN = new PartidaRN();

    private List<Campus> campi;
    private List<Partida> partidas;

    private PieChartModel palpiteirosPorTipo;
    private PieChartModel palpiteirosPorCampus;

    public GraficosBean() {
        System.out.println("Criando GraficosBean");
    }

    @PostConstruct
    public void init() {
        System.out.println("Inicializando GraficosBean");

        campi = CAMPUS_RN.listarTodos();
        partidas = PARTIDA_RN.obterOitoPartidasAnteriores();

        palpiteirosPorTipo = criarGraficoPalpiteirosPorTipo();
        palpiteirosPorCampus = criarGraficoPalpiteirosPorCampus();
    }

    public List<Campus> getCampi() {
        return campi;
    }

    public List<Partida> getPartidas() {
        return partidas;
    }

    public PieChartModel criarGraficoPalpiteirosPorCurso(Campus campus) {
        PieChartModel resposta = new PieChartModel();
        Map<String, Integer> palpiteiros = ESTATISTICA_USUARIO_RN.obterPalpiteirosPorCurso(campus);
        Integer quantidade = null;
        if (palpiteiros != null) {
            for (String chave : palpiteiros.keySet()) {
                quantidade = palpiteiros.get(chave);
                resposta.set(chave, quantidade);
            }
        }
        resposta.setTitle("Palpiteiros por Curso - " + campus.getNome());
        resposta.setLegendPosition("w");
        resposta.setShowDataLabels(true);
        return resposta;
    }

    public BarChartModel criarGraficoPalpiteirosPorPortuacao(Partida partida) {
        BarChartModel resposta = new BarChartModel();
        ChartSeries serie = new ChartSeries();

        Map<Integer, Integer> palpiteiros = ESTATISTICA_PALPITE_RN.obterPalpitesPorPontuacao(partida);
        Integer quantidade = null;
        Integer alturaColuna = 0;
        for (Integer chave : palpiteiros.keySet()) {
            quantidade = palpiteiros.get(chave);
            serie.set(chave, quantidade);

            if (quantidade > alturaColuna) {
                alturaColuna = quantidade;
            }
        }
        serie.setLabel("Palpiteiros");

        StringBuilder titulo = new StringBuilder();
        titulo
                .append("Palpiteiros por Pontuação [")
                .append(partida.getSelecaoA().getSigla())
                .append(" x ")
                .append(partida.getSelecaoB().getSigla())
                .append("] ");

        resposta.setTitle(titulo.toString());
        resposta.setShowPointLabels(true);
        resposta.addSeries(serie);

        Axis yAxis = resposta.getAxis(AxisType.Y);
        yAxis.setLabel("Palpites");
        yAxis.setMin(0);
        yAxis.setMax(alturaColuna + 2); // Ajusta a altura conforme o maior número de vagas
        yAxis.setTickFormat("%d");

        Axis xAxis = resposta.getAxis(AxisType.X);
        xAxis.setLabel("Pontos");

        return resposta;
    }

    private PieChartModel criarGraficoPalpiteirosPorCampus() {
        PieChartModel resposta = new PieChartModel();
        Map<String, Integer> palpiteiros = ESTATISTICA_USUARIO_RN.obterPalpiteirosPorCampus();
        Integer quantidade = null;
        for (String chave : palpiteiros.keySet()) {
            quantidade = palpiteiros.get(chave);
            resposta.set(chave, quantidade);
        }
        resposta.setTitle("Palpiteiros por Campus");
        resposta.setLegendPosition("w");
        resposta.setShowDataLabels(true);
        return resposta;
    }

    private PieChartModel criarGraficoPalpiteirosPorTipo() {
        PieChartModel resposta = new PieChartModel();
        Map<String, Integer> palpiteiros = ESTATISTICA_USUARIO_RN.obterPalpiteirosPorTipo();
        Integer quantidade = null;
        for (String chave : palpiteiros.keySet()) {
            quantidade = palpiteiros.get(chave);
            resposta.set(chave, quantidade);
        }
        resposta.setTitle("Palpiteiros por Tipo");
        resposta.setLegendPosition("w");
        resposta.setShowDataLabels(true);
        return resposta;
    }

    public PieChartModel getPalpiteirosPorTipo() {
        return palpiteirosPorTipo;
    }

    public PieChartModel getPalpiteirosPorCampus() {
        return palpiteirosPorCampus;
    }

}
