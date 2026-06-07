package br.edu.ufra.bean;

import br.edu.ufra.entidade.Campus;
import br.edu.ufra.rn.CampusRN;
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

    private List<Campus> campi;

    private PieChartModel palpiteirosPorTipo;
    private PieChartModel palpiteirosPorCampus;

    public GraficosBean() {
        System.out.println("Criando GraficosBean");
    }

    @PostConstruct
    public void init() {
        System.out.println("Inicializando GraficosBean");
        campi = CAMPUS_RN.listarTodos();
        palpiteirosPorTipo = criarGraficoPalpiteirosPorTipo();
        palpiteirosPorCampus = criarGraficoPalpiteirosPorCampus();
    }

    public List<Campus> getCampi() {
        return campi;
    }

    public PieChartModel criarGraficoPalpiteirosPorCurso(Campus campus) {
        PieChartModel resposta = new PieChartModel();
        Map<String, Integer> palpiteiros = ESTATISTICA_USUARIO_RN.obterPalpiteirosPorCurso(campus);
        Integer quantidade = null;
        for (String chave : palpiteiros.keySet()) {
            quantidade = palpiteiros.get(chave);
            resposta.set(chave, quantidade);
        }
        resposta.setTitle("Palpiteiros por Curso - " + campus.getNome());
        resposta.setLegendPosition("w");
        resposta.setShowDataLabels(true);
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
