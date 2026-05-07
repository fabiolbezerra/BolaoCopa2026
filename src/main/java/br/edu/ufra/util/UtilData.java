package br.edu.ufra.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.GregorianCalendar;

public class UtilData {
    public static String formatarData(Date data) {
        return formatarData(data, "ddMMyyyy");
    }

    public static String formatarData(Date data, String padrao) {
        if (data == null) {
            return null;
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(padrao);
            String resposta = sdf.format(data);
            return resposta;
        }
    }

    public static Date criarData(String data, String padrao) {
        if (data == null || padrao == null) {
            return null;
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(padrao);
            sdf.setLenient(false);
            Date resposta = null;
            try {
                resposta = sdf.parse(data);
            } catch (ParseException e) {
                System.err.println(e);
            }
            return resposta;
        }
    }

    public static Date adicionarDias(Date data, int dias) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(data);
        gc.add(GregorianCalendar.DATE, dias);
        return gc.getTime();
    }

    public static Date criarData(String data) {
        String[] padroes = {
                "dd/MM/yy",
                "dd/MM/yyyy",
                "yyyy-MM-dd",
                "yyyy-MM-dd HH:mm:ss"
        };

        Date resposta = null;
        for (int i = 0; i < padroes.length; i++) {
            resposta = criarData(data, padroes[i]);
            if (resposta != null) {
                return resposta;
            }
        }
        return resposta;
    }

    public static int getAnoAtual() {
        return LocalDate.now().getYear();
    }

    /**
     * Extrai como um array de strings os valores de dia, mês e ano, de uma data
     * que pode estar no formato MM/AAAA ou DD/MM/AAAA
     *
     * @param strData data em formato MM/AAAA ou DD/MM/AAAA
     * @return [DD, MM, AAAA]
     */
    public static String[] extrairDiaMesAno(String strData) {
        if (strData == null || strData.length() < 7 || strData.length() > 10) {
            return null;
        }
        String[] resposta = new String[3];
        resposta[0] = strData.length() > 7 ? strData.substring(0, 2) : "";
        resposta[1] = strData.length() > 7 ? strData.substring(3, 5) : strData.substring(0, 2);
        resposta[2] = strData.length() > 7 ? strData.substring(6, 10) : strData.substring(3, 7);
        return resposta;
    }

    /**
     * Uma data é válida quando possui dia, mes e ano válidos. Mas também é válida quando possui apenas mês e ano
     * válidos. Sempre no formato DD e MM e AAAA.
     *
     * @param dia            Dígitos do dia
     * @param mes            Dígitos do mês
     * @param ano            Dígitos do ano
     * @param podeDataFutura Indica se uma data do futuro é válida
     * @return
     */
    public static boolean isDataValida(String dia, String mes, String ano, boolean podeDataFutura) {
        if (ano == null || ano.isEmpty()
                || mes == null || mes.isEmpty()) {
            return false;
        } else {
            if (dia == null || dia.isEmpty()) {
                dia = "01";
            }
            String strData = dia + "/" + mes + "/" + ano;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                sdf.setLenient(false);
                Date data = sdf.parse(strData);
                if (!podeDataFutura) {
                    return data.before(new Date());
                } else {
                    return true;
                }
            } catch (Exception e) {
                return false;
            }
        }
    }

    /**
     * Compara duas datas, a partir das partes (dia, mês e ano) das mesmas.
     * As partes são fornecidas como strings. DD, MM e AAAA de início devem
     * formar uma data menor que DD, MM e AAAA de fim.
     *
     * @param diaInicio string com os dígitos do dia DD
     * @param mesInicio string com os dígitos do mês MM
     * @param anoInicio string com os dígitos do ano AAAA
     * @param diaFim string com os dígitos do dia DD
     * @param mesFim string com os dígitos do mês MM
     * @param anoFim string com os dígitos do ano AAAA
     * @return um boolean indicando início < fim
     */
    public static boolean isDataFinalMaiorQueDataInicial(String diaInicio, String mesInicio, String anoInicio, String diaFim, String mesFim, String anoFim) {
        if (!isDataValida(diaInicio, mesInicio, anoInicio, false)
                || !isDataValida(diaFim, mesFim, anoFim, false)) {
            return false;
        } else {
            String strInicial = (diaInicio == null || diaInicio.trim().isEmpty()) ? ("01/" + mesInicio + "/" + anoInicio) : (diaInicio + "/" + mesInicio + "/" + anoInicio);
            String strFinal = (diaFim == null || diaFim.trim().isEmpty()) ? ("01/" + mesFim + "/" + anoFim) : (diaFim + "/" + mesFim + "/" + anoFim);
            Date dataInicio = criarData(strInicial); //padrão dd/MM/yyyy
            Date dataFim = criarData(strFinal); //padrão dd/MM/yyyy
            return dataFim.after(dataInicio);
        }
    }

    /**
     * fjkfdjfdnsfdlknfdkjnfdkjnfdn
     *
     * @param dia dsdsd
     * @param mes dsdsd
     * @param ano dsdsdsd
     * @param podeDataFutura dsdsd
     * @return
     */
    public static String montarData(String dia, String mes, String ano, boolean podeDataFutura) {
        if (!isDataValida(dia, mes, ano, podeDataFutura)) {
            return null;
        }
        String resposta;
        if (dia == null || dia.isEmpty()) {
            resposta = mes + "/" + ano;
        } else {
            resposta = dia + "/" + mes + "/" + ano;
        }
        return resposta;
    }

}
