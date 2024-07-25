package com.machine_track_app.auth;

import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private final Long idOwner;

    public CustomAuthenticationToken(Object principal, Object credentials, Long idOwner) {
        super(principal, credentials);
        this.idOwner = idOwner;
    }

    public CustomAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, Long idOwner) {
        super(principal, credentials, authorities);
        this.idOwner = idOwner;
    }

}
