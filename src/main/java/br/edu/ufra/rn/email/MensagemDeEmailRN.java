package br.edu.ufra.rn.email;

import br.edu.ufra.entidade.Campus;
import br.edu.ufra.entidade.Curso;
import br.edu.ufra.entidade.Usuario;
import br.edu.ufra.util.UtilEmail;
import br.edu.ufra.util.UtilTexto;

import java.util.ArrayList;
import java.util.List;

public class MensagemDeEmailRN {
    public String enviarEmailDeValidacao(String email) {
        final String CODIGO_GERADO = UtilTexto.gerarNumeros(6);

        StringBuilder texto = new StringBuilder();
        texto
                .append("Olá!\n\n")
                .append("Digite o código abaixo no formulário de cadastro para VALIDAR seu e-mail.")
                .append("\n\n")
                .append(CODIGO_GERADO)
                .append("\n\n")
                .append("Para evitar banimentos, NÃO USAR nome ou sobrenome com conteúdo:\n")
                .append(" - ofensivo;\n")
                .append(" - vulgar;\n")
                .append(" - religioso;\n")
                .append(" - ou político.\n\n")
                .append("Abraços\n\n")
                .append("Equipe do Bolão da Copa do Mundo 2026")
                .append("\n\n");

        UtilEmail.enviarEmail(
                email,
                "Validação do e-mail",
                texto.toString()
        );

        return CODIGO_GERADO;
    }

    public String enviarEmailDeValidacao(Usuario usuario) {
        if (usuario == null || usuario.getId() == null || usuario.getId() == 0) {
            return null;
        }

        final String CODIGO_GERADO = UtilTexto.gerarNumeros(6);

        StringBuilder texto = new StringBuilder();
        texto
                .append("Olá")
                .append(usuario.getNome())
                .append(" ")
                .append(usuario.getSobrenome())
                .append("!\n\n")
                .append("Digite o código abaixo no formulário de REDEFINIÇÃO da senha para VALIDAR seu e-mail.")
                .append("\n\n")
                .append(CODIGO_GERADO)
                .append("\n\n")
                .append("Abraços\n\n")
                .append("Equipe do Bolão da Copa do Mundo 2026")
                .append("\n\n");

        UtilEmail.enviarEmail(
                usuario.getEmail(),
                "Redefinição da senha",
                texto.toString()
        );

        return CODIGO_GERADO;
    }

    public boolean enviarEmailDeSaudacao(Usuario usuario) {
        if (usuario == null || usuario.getId() == null || usuario.getId() == 0) {
            return false;
        } else {
            StringBuilder saudacao = new StringBuilder();

            saudacao
                    .append("Olá ")
                    .append(usuario.getNome())
                    .append("!\n\nSeja bem vindo(a) ao Bolão da Copa do Mundo 2026!\n\n")
                    .append("Agora você já pode:\n")
                    .append(" - Criar palpites\n")
                    .append(" - Acompanhar rankings\n")
                    .append(" - Participar de bolões\n\n")
                    .append("Abraços\n\n")
                    .append("Equipe do Bolão da Copa do Mundo 2026\n");

            UtilEmail.enviarEmail(
                    usuario.getEmail(),
                    "Seja bem vindo(a)!",
                    saudacao.toString()
            );

            return true;
        }
    }

    public boolean enviarEmailDeRedefinicaoDaSenha(Usuario usuario) {
        if (usuario == null || usuario.getId() == null || usuario.getId() == 0) {
            return false;
        } else {
            StringBuilder msgNovaSenha = new StringBuilder();

            msgNovaSenha
                    .append("Olá ")
                    .append(usuario.getNome())
                    .append(" ")
                    .append(usuario.getSobrenome())
                    .append("!\n\nSua senha foi REDEFINIDA no Bolão da Copa do Mundo 2026!\n\n")
                    .append("Evite fazer os seus palpites de última hora.\n\n")
                    .append("Abraços\n\n")
                    .append("Equipe do Bolão da Copa do Mundo 2026")
                    .append("\n\n");

            UtilEmail.enviarEmail(usuario.getEmail(),
                    "Senha Redefinida com Sucesso",
                    msgNovaSenha.toString());
            return true;
        }
    }

    public String enviarEmailTop10Geral(List<Usuario> usuarios) {
        StringBuilder rankingTop10Geral = new StringBuilder();

        rankingTop10Geral
                .append("Parabéns!\n\n")
                .append("Você aparece nas melhores posições do RANKING GERAL!\n\n");

        List<String> destinatarios = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            destinatarios.add(usuarios.get(i - 1).getEmail());
            rankingTop10Geral
                    .append(i)
                    .append(". ")
                    .append(usuarios.get(i - 1).getNome())
                    .append(" ")
                    .append(usuarios.get(i - 1).getSobrenome())
                    .append("\n");
        }

        rankingTop10Geral
                .append("Evite fazer os seus palpites de última hora.\n\n")
                .append("Abraços\n\n")
                .append("Equipe do Bolão da Copa do Mundo 2026")
                .append("\n\n");

        String assunto = "Ranking Geral - Top 10";
        UtilEmail.enviarEmail(destinatarios,
                assunto,
                rankingTop10Geral.toString());

        return assunto;
    }

    public String enviarEmailTop10Campus(List<Usuario> usuarios, Campus campus) {
        StringBuilder rankingTop10Geral = new StringBuilder();

        rankingTop10Geral
                .append("Parabéns!\n\n")
                .append("Você aparece nas melhores posições do RANKING do Campus ")
                .append(campus.getNome())
                .append("!\n\n");

        List<String> destinatarios = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            destinatarios.add(usuarios.get(i - 1).getEmail());
            rankingTop10Geral
                    .append(i)
                    .append(". ")
                    .append(usuarios.get(i - 1).getNome())
                    .append(" ")
                    .append(usuarios.get(i - 1).getSobrenome())
                    .append("\n");
        }

        rankingTop10Geral
                .append("Evite fazer os seus palpites de última hora.\n\n")
                .append("Abraços\n\n")
                .append("Equipe do Bolão da Copa do Mundo 2026")
                .append("\n\n");

        String assunto = "Ranking Campus " + campus.getNome() + " - Top 10";
        UtilEmail.enviarEmail(destinatarios,
                assunto,
                rankingTop10Geral.toString());

        return assunto;
    }

    public String enviarEmailTop10Curso(List<Usuario> usuarios, Curso curso) {
        StringBuilder rankingTop10Geral = new StringBuilder();

        rankingTop10Geral
                .append("Parabéns!\n\n")
                .append("Você aparece nas melhores posições do RANKING do Curso ")
                .append(curso.getNome())
                .append("!\n\n");

        List<String> destinatarios = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            destinatarios.add(usuarios.get(i - 1).getEmail());
            rankingTop10Geral
                    .append(i)
                    .append(". ")
                    .append(usuarios.get(i - 1).getNome())
                    .append(" ")
                    .append(usuarios.get(i - 1).getSobrenome())
                    .append("\n");
        }

        rankingTop10Geral
                .append("Evite fazer os seus palpites de última hora.\n\n")
                .append("Abraços\n\n")
                .append("Equipe do Bolão da Copa do Mundo 2026")
                .append("\n\n");

        String assunto = "Ranking Curso " + curso.getNome() + " - Top 10";
        UtilEmail.enviarEmail(destinatarios,
                assunto,
                rankingTop10Geral.toString());

        return assunto;
    }
}
