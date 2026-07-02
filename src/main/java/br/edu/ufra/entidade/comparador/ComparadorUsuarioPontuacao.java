package br.edu.ufra.entidade.comparador;

import br.edu.ufra.entidade.Usuario;

import java.util.Comparator;

public class ComparadorUsuarioPontuacao implements Comparator<Usuario> {

    @Override
    public int compare(Usuario joao, Usuario maria) {
        if (joao == null || maria == null) {
            return 0;
        } else if (joao.getPontuacao() == null || maria.getPontuacao() == null) {
            return 0;
        }
        //Ordem decrescente - Maior primeiro
        //Ordem inversa da pontuação, por isso multiplica por -1
        return -1 * joao.getPontuacao().compareTo(maria.getPontuacao());
    }
}
