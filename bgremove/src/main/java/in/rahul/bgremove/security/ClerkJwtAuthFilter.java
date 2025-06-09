//package in.rahul.bgremove.security;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.security.PublicKey;
//import java.util.Base64;
//import java.util.Collections;
//
//@Component
//@RequiredArgsConstructor
//public class ClerkJwtAuthFilter extends OncePerRequestFilter {
//
//    @Value("${clerk.issuer}")
//    private String clerkIssuer;
//
//    private final ClerkJwksProvider jwksProvider;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//        String authHeader = request.getHeader("Authorization");
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Authorization header missing/invalid");
//            return;
//        }
//        if (request.getRequestURI().contains("/api/webhooks")) {
//            filterChain.doFilter(request,response);
//            return;
//
//        }
//
//        try {
//            String token = authHeader.substring(7);
//
//            // extract the kid from token header
//            String[] chunks = token.split("\\.");
//            String headerJson = new String(Base64.getUrlDecoder().decode(chunks[0]));
//            ObjectMapper mapper = new ObjectMapper();
//            JsonNode headerNode = mapper.readTree(headerJson);
//            String kid = headerNode.get("kid").asText();
//
//            // get correct public key
//            PublicKey publicKey = jwksProvider.getPublicKey(kid);
//
//            // verify token
//            Claims claims = Jwts.parserBuilder()
//                    .setSigningKey(publicKey)
//                    .setAllowedClockSkewSeconds(60) // allow 60 sec
//                    .requireIssuer(clerkIssuer)
//                    .build()
//                    .parseClaimsJws(token)
//                    .getBody();
//
//            String clerkUserId = claims.getSubject();
//
//            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
//                    clerkUserId, null, Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"))
//            );
//            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//            filterChain.doFilter(request, response);
//
//        } catch (Exception e) {
//            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid JWT token");
//        }
//    }
//}





package in.rahul.bgremove.security;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.PublicKey;
import java.util.Base64;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class ClerkJwtAuthFilter extends OncePerRequestFilter {

    @Value("${clerk.issuer}")
    private String clerkIssuer;

    private final ClerkJwksProvider jwksProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // (1) Bypass JWT check for any /api/webhooks/** endpoint
        if (request.getRequestURI().contains("/api/webhooks")) {
            filterChain.doFilter(request, response);
            return;
        }

        // (2) Otherwise, look for Authorization: Bearer <token>
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Authorization header missing/invalid");
            return;
        }

        try {
            String token = authHeader.substring(7);

            // (3) Extract "kid" from JWT header
            String[] chunks = token.split("\\.");
            if (chunks.length < 2) {
                throw new IllegalArgumentException("Invalid JWT format");
            }
            String headerJson = new String(Base64.getUrlDecoder().decode(chunks[0]));
            ObjectMapper mapper = new ObjectMapper();
            JsonNode headerNode = mapper.readTree(headerJson);
            String kid = headerNode.get("kid").asText();
//            String kid = headerNode.path("kid").asText();
            if (kid.isEmpty()) {
                throw new IllegalArgumentException("No 'kid' in JWT header");
            }

            // (4) Fetch the PublicKey by kid
            PublicKey publicKey = jwksProvider.getPublicKey(kid);

            // (5) Parse & verify claims
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(publicKey)
                    .setAllowedClockSkewSeconds(60)
                    .requireIssuer(clerkIssuer)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            // (6) Set Spring Security context
            String clerkUserId = claims.getSubject();
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    clerkUserId, null, Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"))
            );
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            filterChain.doFilter(request, response);

        } catch (Exception e) {
            // If any exception occurs (invalid JWT / parsing error), reject
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid JWT token");
        }
    }
}
