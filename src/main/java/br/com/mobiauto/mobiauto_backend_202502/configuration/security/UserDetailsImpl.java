package br.com.mobiauto.mobiauto_backend_202502.configuration.security;

import br.com.mobiauto.mobiauto_backend_202502.domain.entity.UsuarioEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserDetailsImpl implements UserDetails {

    private final UsuarioEntity usuarioEntity;

    public UserDetailsImpl(UsuarioEntity usuarioEntity) {
        this.usuarioEntity = usuarioEntity;
    }

    public UsuarioEntity getUsuarioEntity() {
        return this.usuarioEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + usuarioEntity.getPerfil().name()));
    }

    @Override
    public String getPassword() {
        return usuarioEntity.getSenha();
    }

    @Override
    public String getUsername() {
        return usuarioEntity.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
