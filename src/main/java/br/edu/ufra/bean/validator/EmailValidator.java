/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufra.bean.validator;

import br.edu.ufra.util.UtilTexto;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("emailValidator")
public class EmailValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (!(value == null || value.toString().trim().isEmpty())) {
            if (!UtilTexto.isEmail(value.toString())) {
                FacesMessage msg = new FacesMessage(
                        "Email inválido",
                        "Falha na validação do e-mail");
                msg.setSeverity(FacesMessage.SEVERITY_WARN);
                throw new ValidatorException(msg);
            }
        }
    }
}