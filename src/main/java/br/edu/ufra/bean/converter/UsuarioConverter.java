package br.edu.ufra.bean.converter;

import br.edu.ufra.entidade.Usuario;
import br.edu.ufra.rn.UsuarioRN;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "usuarioConverter")
public class UsuarioConverter implements Converter {
    private final UsuarioRN RN = new UsuarioRN();

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
                || ! (value instanceof Usuario)) {
            return null;
        }
        Usuario usuario = (Usuario) value;
        return usuario.getId().toString();
    }
}
