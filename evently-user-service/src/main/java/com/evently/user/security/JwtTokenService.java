package com.evently.user.security;

import com.evently.user.entity.persistent.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
@Slf4j
@RequiredArgsConstructor
public class JwtTokenService {

    private final JwtTokenConfiguration jwtTokenConfiguration;

    /**
     * Extracts id of the user, for which the token was issued.
     * @param  token JWT token string
     * @return subject claim of the JWT token as {@link Integer}
     */
    public String extractSubject(final String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extracts list of authorities of the user, for which the token was issued.
     * @param  token JWT token string
     * @return list of authorities
     */
    public List<? extends GrantedAuthority> extractAuthorities(final String token) {
        final String authoritiesString = extractClaim(token, claims ->
                claims.get("aut", String.class));

        if (authoritiesString == null) {
            return List.of();
        }

        return Arrays
                .stream(authoritiesString.split(" "))
                .map(String::trim)
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    /**
     * Extracts a concrete claim from JWT token.
     * @param  token          JWT token string
     * @param  claimsResolver function, that extracts the claim
     * @return claim value
     * @param  <T> type of the claim value
     */
    private <T> T extractClaim(final String token,
                               final Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);
    }

    /**
     * Generates JWT token for the given user.
     * @param  user user entity
     * @return JWT token string
     */
    private JwtTokenDto generateToken(final User user) {
        log.info("Generating JWT token for user `{}`", user.getId());

        final Map<String, Object> extraClaims = new HashMap<>();

        final Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        extraClaims.put("aut", String.join(" ", authorities
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList()));

        final Date issuedAt = new Date(System.currentTimeMillis());
        final Date tokenExpiration = new Date(issuedAt.getTime()
                + jwtTokenConfiguration.getTtl() * 1000);

        final String token = Jwts
                .builder()
                .audience()
                .add(jwtTokenConfiguration.getIssuer())
                .and()
                .issuer(jwtTokenConfiguration.getIssuer())
                .subject(String.valueOf(user.getId()))
                .claims(extraClaims)
                .issuedAt(issuedAt)
                .notBefore(issuedAt)
                .expiration(tokenExpiration)
                .signWith(getSignInKey())
                .compact();

        log.info("Successfully generated JWT token `{}` for user `{}`",
                token, user.getId());

        return JwtTokenDto
                .builder()
                .accessToken(token)
                .accessTokenExpiry(tokenExpiration)
                .build();
    }

    /**
     * Extracts all claims from JWT token.
     * @param  token JWT token string
     * @return all claims as {@link Claims} object
     */
    private Claims extractAllClaims(final String token) {
        return Jwts
                .parser()
                .verifyWith((SecretKey) getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * @return {@link SecretKey} object, used to sign and verify JWT tokens
     */
    private Key getSignInKey() {
        return new SecretKey() {

            @Override
            public String getAlgorithm() {
                return "HmacSHA512";
            }

            @Override
            public String getFormat() {
                return "RAW";
            }

            @Override
            public byte[] getEncoded() {
                return jwtTokenConfiguration.getSecret().getBytes();
            }
        };
    }
}