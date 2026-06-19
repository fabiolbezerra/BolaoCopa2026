package br.edu.ufra.bean.converter;

import br.edu.ufra.entidade.Grupo;
import br.edu.ufra.rn.GrupoRN;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "grupoConverter")
public class GrupoConverter implements Converter {
    private final GrupoRN RN = new GrupoRN();

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
                || !(value instanceof Grupo)) {
            return null;
        }
        Grupo grupo = (Grupo) value;
        return grupo.getId().toString();
    }
}
