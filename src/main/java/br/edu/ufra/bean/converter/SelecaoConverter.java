package br.edu.ufra.bean.converter;

import br.edu.ufra.entidade.Selecao;
import br.edu.ufra.rn.SelecaoRN;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "selecaoConverter")
public class SelecaoConverter implements Converter {
    private final SelecaoRN RN = new SelecaoRN();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().isEmpty() || "null".equalsIgnoreCase(value)) {
            return null;
        }
        try {
            return RN.obter(Integer.valueOf(value));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null
                || ! (value instanceof Selecao)) {
            return null;
        }
        Selecao selecao = (Selecao) value;
        return selecao.getId().toString();
    }
}
