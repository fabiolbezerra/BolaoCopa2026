/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufra.bean.validator;

import br.edu.ufra.entidade.Usuario;
import br.edu.ufra.rn.UsuarioRN;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("emailUnicoValidator")
public class EmailUnicoValidator implements Validator {

    private final UsuarioRN RN = new UsuarioRN();

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (!(value == null || value.toString().trim().isEmpty())) {
            String email = value.toString();
            Usuario usuario = RN.obter(email);
            if (usuario != null) {
                FacesMessage msg = new FacesMessage(
                        "Email inválido",
                        "Já existe um usuário com mesmo e-mail");
                msg.setSeverity(FacesMessage.SEVERITY_WARN);
                throw new ValidatorException(msg);
            }
        }
    }
}