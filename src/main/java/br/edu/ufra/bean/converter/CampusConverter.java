package br.edu.ufra.bean.converter;

import br.edu.ufra.entidade.Campus;
import br.edu.ufra.rn.CampusRN;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "campusConverter")
public class CampusConverter implements Converter {
    private final CampusRN RN = new CampusRN();

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
                || ! (value instanceof Campus)) {
            return null;
        }
        Campus campus = (Campus) value;
        return campus.getId().toString();
    }
}
