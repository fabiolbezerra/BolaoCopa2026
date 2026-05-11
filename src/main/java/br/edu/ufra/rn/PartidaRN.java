package br.edu.ufra.rn;

import br.edu.ufra.dao.GenericDAO;
import br.edu.ufra.entidade.Partida;
import br.edu.ufra.entidade.Fase;
import org.springframework.context.annotation.Import;

import java.time.Instant;
import java.util.List;

public class PartidaRN {
    private final GenericDAO<Partida> PARTIDA_DAO = new GenericDAO();

    public PartidaRN() {
    }

    public boolean salvar(Partida partida) {
        if (partida == null) {
            return false;
        } else {
            return partida.getId() != null && partida.getId() != 0 ? this.PARTIDA_DAO.alterar(partida) : this.PARTIDA_DAO.criar(partida);
        }
    }

    private boolean definirVencedor(Integer partidaId, Integer golsA, Integer golsB){
        Partida partida = obter(partidaId);
        if(Instant.now().isBefore(partida.getInicioDaPartida())){
            return false;
        }
        partida.setGolsA(golsA);
        partida.setGolsB(golsB);
        partida.setAtualizacaoDoResultado(Instant.now());

        determinarVencedor(partida,golsA,golsB);

        return PARTIDA_DAO.alterar(partida);


    }
    private void determinarVencedor(Partida partida, Integer golsA, Integer golsB){
        if(golsA>golsB){
            partida.setTipoDeVitoria("NORMAL");
            partida.setVencedor(partida.getSelecaoA());
        }
        else if(golsB>golsA){
            partida.setTipoDeVitoria("NORMAL");
            partida.setVencedor(partida.getSelecaoB());
        }
        else{
            partida.setVencedor(null);
            if(partida.getFase().getEliminatoria() != 0){
                partida.setTipoDeVitoria("PÊNALTIS");
            }else{
                partida.setTipoDeVitoria("EMPATE");
            }
        }
    }

    public List<Partida> listar() {
        return this.PARTIDA_DAO.obterTodos(Partida.class);
    }

    public Partida obter(Integer id) {
        return (Partida)this.PARTIDA_DAO.obter(Partida.class, id);
    }

    public boolean excluir(Partida partida) {
        return partida == null ? false : this.PARTIDA_DAO.excluir(partida);
    }
}
