package com.example.mkrawcz.m8radar.network.client;

/**
 * Identification and authentication of a user.
 */
public interface RadarUserCredential {

    /**
     * Unique user identification.
     */
    String emailAddress();

    /**
     * A password / FB-issued auth-token / any other mean of confirming the ownership of the
     * {@link #emailAddress()}.
     */
    String authenticationToken();
}
