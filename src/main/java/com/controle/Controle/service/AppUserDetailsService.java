package com.controle.Controle.service;

import com.controle.Controle.model.Usuario;
import com.controle.Controle.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.tags.form.OptionsTag;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AppUserDetailsService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Optional<Usuario> usuario  = usuarioRepository.findByEmail(s);
        return new User(s, usuario.get().getSenha(), getPermissoes(usuario.get()));
    }

    private Collection<? extends GrantedAuthority> getPermissoes(Usuario usuario){

        Set<SimpleGrantedAuthority> simpleGrantedAuthorities = new HashSet<>();

        usuario.getPermissoes().forEach(a -> {
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(a.getDescricao().toUpperCase()));
        });

        return simpleGrantedAuthorities;
    }
}
