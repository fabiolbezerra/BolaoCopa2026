package br.edu.ufra.entidade.comparador;

import br.edu.ufra.entidade.Usuario;

import java.util.Comparator;

public class ComparadorUsuarioPontuacao implements Comparator<Usuario> {

    @Override
    public int compare(Usuario joao, Usuario maria) {
        //Ordem inversa da pontuação, por isso multiplica por -1
        return -1 * joao.getPontuacao().compareTo(maria.getPontuacao());
    }
}
