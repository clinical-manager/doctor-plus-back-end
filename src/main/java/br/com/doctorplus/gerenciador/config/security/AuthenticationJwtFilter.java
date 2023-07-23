package br.com.doctorplus.gerenciador.config.security;

import br.com.doctorplus.gerenciador.model.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationJwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String jwtStr = getTokenHeader(httpServletRequest);
        if (jwtProvider.isValidateJwt(jwtStr)) {
            String usuario = jwtProvider.getUserName(jwtStr);
            UsernamePasswordAuthenticationToken authentication = Optional.of(userDetailsService.loadUserByUsername(usuario))
                    .map(this::authorizationUserDetails)
                    .map(auth -> authorizationSetDetails(auth, httpServletRequest))
                    .orElseThrow(
                            UnauthorizedException::new
                    );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private UsernamePasswordAuthenticationToken authorizationUserDetails(UserDetails userDetails) {
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    private UsernamePasswordAuthenticationToken authorizationSetDetails(UsernamePasswordAuthenticationToken authorization, HttpServletRequest httpServletRequest) {
        authorization.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
        return authorization;
    }

    private String getTokenHeader(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length());
        }
        return Strings.EMPTY;
    }
}

