package br.edu.ufra.bean.converter;
import br.edu.ufra.entidade.Fase;
import br.edu.ufra.rn.FaseRN;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "faseConverter")
public class FaseConverter implements Converter {
    private final FaseRN RN = new FaseRN();

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
                || ! (value instanceof Fase)) {
            return null;
        }
        Fase fase = (Fase) value;
        return fase.getId().toString();
    }
}

