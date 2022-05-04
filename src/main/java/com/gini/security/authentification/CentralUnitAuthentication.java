package com.gini.security.authentification;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CentralUnitAuthentication extends UsernamePasswordAuthenticationToken {
    public CentralUnitAuthentication(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public CentralUnitAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
