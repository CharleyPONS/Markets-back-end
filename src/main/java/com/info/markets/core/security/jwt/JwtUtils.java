package com.info.markets.core.security.jwt;

import com.info.markets.model.user.UserDetailsInfo;
import com.info.markets.sevice.markets.MarketConfigurationService;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@Component
public class JwtUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(MarketConfigurationService.class);
    @Value("${market.jwt.secret}")
    private String jwtSecret;
    @Value("${market.jwt.expiratios.ms}")
    private String expirationJwtMs;

    public String generateJwtToken(Authentication authentication) throws ParseException {
        UserDetailsInfo userDetailsInfo = (UserDetailsInfo) authentication.getPrincipal();
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MILLISECOND, Integer.parseInt(expirationJwtMs));
            Date addMilliSeconds = calendar.getTime();


        return Jwts.builder().setSubject((userDetailsInfo.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(addMilliSeconds)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }


    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            LOGGER.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            LOGGER.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            LOGGER.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            LOGGER.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            LOGGER.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
}
