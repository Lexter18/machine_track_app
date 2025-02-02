package com.machine_track_app.auth;

import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private final Long idOwner;
    private final Integer idRol;

    public CustomAuthenticationToken(Object principal, Object credentials, Long idOwner, Integer idRol) {
        super(principal, credentials);
        this.idOwner = idOwner;
        this.idRol = idRol;
    }

    public CustomAuthenticationToken(Object principal, Object credentials,
                                     Collection<? extends GrantedAuthority> authorities, Long idOwner, Integer idRol) {
        super(principal, credentials, authorities);
        this.idOwner = idOwner;
        this.idRol = idRol;
    }

}
