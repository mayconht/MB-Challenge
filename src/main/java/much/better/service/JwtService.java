package much.better.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.inject.Singleton;

import java.util.Date;

@Singleton
public class JwtService {
    private final long DEFAULT_EXPIRE_IN_SECONDS = 2400;
    private final String SECRET = System.getenv("JWT_SECRET");

    private final Algorithm algorithm = Algorithm.HMAC256(this.SECRET);

    public String generateJWTToken(final String id) {
        final Date expireDate = new Date(new Date().getTime() + (this.DEFAULT_EXPIRE_IN_SECONDS * 1000));

        final String jwtToken = JWT.create()
                .withIssuer(System.getenv("JWT_ISSUER"))
                .withClaim("UUID", id)
                .withExpiresAt(expireDate)
                .sign(this.algorithm);

        return jwtToken;
    }

    public boolean verifyJWTToken(final String token) {
        try {
            final JWTVerifier verifier = JWT.require(this.algorithm)
                    .withIssuer(System.getenv("JWT_ISSUER"))
                    .acceptExpiresAt(this.DEFAULT_EXPIRE_IN_SECONDS)
                    .build();
            verifier.verify(token);
            return true;
        } catch (final JWTVerificationException ex) {
            return false;
        }
    }

    public String getClaimFromToken(final String token, final String claimKey) {
        final DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getClaims().get(claimKey).toString().replace("\"", "");
    }
}
