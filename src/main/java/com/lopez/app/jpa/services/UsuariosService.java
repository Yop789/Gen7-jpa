package com.lopez.app.jpa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.lopez.app.jpa.dao.IUsuarioDao;
import com.lopez.app.jpa.models.Usuario;

@Component
public class UsuariosService implements UserDetailsService {

    @Autowired
    private IUsuarioDao usuarioDao;

    private Usuario usuarioDetail;

    // Getters and Setters
    public Usuario getUsuarioDetail() {
        return usuarioDetail;
    }


    public void setUsuarioDetail(Usuario usuarioDetail) {
        this.usuarioDetail = usuarioDetail;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadUserByUsername'");
    }


    

}