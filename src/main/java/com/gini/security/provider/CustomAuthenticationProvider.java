package com.gini.security.provider;

import com.gini.security.authentification.CentralUnitAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;


@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Value("${authorization.keyx}") //to inject the value the class has to have a bean in spring context
    private String authorizationKey;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String xApiAuthorizationKey = authentication.getCredentials().toString();

        if (passwordEncoder.matches(xApiAuthorizationKey, passwordEncoder.encode(authorizationKey))) {
            return new CentralUnitAuthentication(null, null, null);
        }
        throw new BadCredentialsException("Invalid Central Unit microservice credentials");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CentralUnitAuthentication.class.equals(authentication);
    }
}
