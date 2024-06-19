package com.machine_track_app.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUserDetails extends User {

    private final Long idOwner;
    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, Long idOwner) {
        super(username, password, authorities);
        this.idOwner = idOwner;
    }

    public CustomUserDetails(String username, String password, boolean enabled, boolean accountNonExpired,
                             boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, Long idOwner) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.idOwner = idOwner;
    }

    public Long getIdOwner() {
        return idOwner;
    }
}
