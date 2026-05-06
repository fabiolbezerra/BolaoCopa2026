/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufra.seguranca;

import br.edu.ufra.entidade.Usuario;
import br.edu.ufra.rn.UsuarioRN;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Fabio
 */
public class LoginHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UsuarioRN RN = new UsuarioRN();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication a) throws IOException, ServletException {
        //Recuperando o e-mail do login
        String username = a.getName();

        //Recuperando o usuário, já autenticado, a partir do e-mail/login
        Usuario usuario = RN.obter(username);

        //Decidindo para onde encaminhar o usuário, após o login
        String pagina = "/palpiteiro/principal.xhtml";
        if (usuario != null) {
            pagina = usuario.getPerfil().getPagina();
        }
        setDefaultTargetUrl(pagina);
        super.onAuthenticationSuccess(request, response, a);
    }

}
