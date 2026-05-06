/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufra.seguranca;

import br.edu.ufra.entidade.Usuario;
import br.edu.ufra.rn.UsuarioRN;
import br.edu.ufra.util.UtilTexto;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fabio
 */
public class AuthenticationHandler implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication a) throws AuthenticationException {
        //Recuperando o email e senha digitados pelo usuário
        String login = a.getName();
        String senha = a.getCredentials().toString();

        //Criptografar a senha digitada
        String senhaCripto = UtilTexto.criptografarSHA256(senha);

        //Autenticando o usuário
        UsuarioRN RN = new UsuarioRN();
        Usuario usuarioAutenticado = RN.login(login, senhaCripto);
        if (usuarioAutenticado == null) {
            //E-mail ou senha inválidos
            throw new BadCredentialsException("Email ou senha inválidos");
        } else {
            //Usuário foi autenticado, então concedendo autorização
            GrantedAuthorityImpl ga = new GrantedAuthorityImpl("ROLE_" + usuarioAutenticado.getPerfil());
            List<GrantedAuthority> auts = new ArrayList<GrantedAuthority>();
            auts.add(ga);
            return new UsernamePasswordAuthenticationToken(login, senha, auts);
        }
    }

    @Override
    public boolean supports(Class<? extends Object> type) {
        return true;
    }

}
