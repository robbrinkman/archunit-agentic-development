package com.jdriven.example.security;

/**
 * The authenticated user, as provided by the security package.
 * Token handling (and the JWT libraries it needs) stays inside this package.
 */
public record CurrentUser(String username) {
}
