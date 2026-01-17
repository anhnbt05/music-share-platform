
package com.example.musicshareserver.security;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JwtRoleTest {

    @Test
    public void testRoleExtractionLogic() {
        // Case 1: Token has "ROLE_ADMIN" (Standard case if DB has "ADMIN")
        String tokenRole1 = "ROLE_ADMIN";
        String authority1 = ensureRolePrefix(tokenRole1);
        assertEquals("ROLE_ADMIN", authority1);

        // Case 2: Token has "ADMIN" (If Provider didn't add prefix)
        String tokenRole2 = "ADMIN";
        String authority2 = ensureRolePrefix(tokenRole2);
        assertEquals("ROLE_ADMIN", authority2);

        // Case 3: Token has "ROLE_ROLE_ADMIN" (Double prefix edge case)
        String tokenRole3 = "ROLE_ROLE_ADMIN";
        String authority3 = ensureRolePrefix(tokenRole3);
        // We probably want to fix this to be ROLE_ADMIN if we think double prefix is the bug
        // But if the "Role" IS "ROLE_ADMIN", then authority should be "ROLE_ROLE_ADMIN"?
        // Usually, the bug is accidental double prefix.
        // Let's assume we want to sanitize it to single prefix.
        assertEquals("ROLE_ADMIN", authority3); 
    }
    
    private String ensureRolePrefix(String role) {
        if (role == null) return "";
        // Remove all starting ROLE_
        String rawRole = role.replaceAll("^(ROLE_)+", "");
        return "ROLE_" + rawRole;
    }
}
