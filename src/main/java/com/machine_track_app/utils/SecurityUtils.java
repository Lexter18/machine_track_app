package com.machine_track_app.utils;

import com.machine_track_app.auth.CustomAuthenticationToken;
import io.jsonwebtoken.Jwts;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.crypto.SecretKey;

import static com.machine_track_app.utils.ConstantsUtils.ZERO_INT;
import static com.machine_track_app.utils.ConstantsUtils.ZERO_LONG;

@UtilityClass
public class SecurityUtils {

    public final static SecretKey SECRET_KEY = Jwts.SIG.HS512.key().build();
    public final static String PREFIX_TOKEN = "Bearer ";
    public final static String HEADER_AUTHORIZATION = "Authorization";
    public final static String HEADER_APPLICATION_JSON = "application/json";

    public static Long getCurrentOwnerId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof CustomAuthenticationToken) {
            return ((CustomAuthenticationToken) authentication).getIdOwner();
        }
        return ZERO_LONG;
    }

    public static Integer getCurrentRol() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof CustomAuthenticationToken) {
            return ((CustomAuthenticationToken) authentication).getIdRol();
        }
        return ZERO_INT;
    }
}
