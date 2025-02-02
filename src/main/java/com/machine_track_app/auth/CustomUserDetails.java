package com.machine_track_app.auth;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class CustomUserDetails extends User {

    private final Long idOwner;
    private final Integer idRol;

    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities,
                             Long idOwner, Integer idRol) {
        super(username, password, authorities);
        this.idOwner = idOwner;
        this.idRol = idRol;
    }

    public CustomUserDetails(String username, String password, boolean enabled, boolean accountNonExpired,
                             boolean credentialsNonExpired,
                             boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,
                             Long idOwner, Integer idRol) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.idOwner = idOwner;
        this.idRol = idRol;
    }

}
