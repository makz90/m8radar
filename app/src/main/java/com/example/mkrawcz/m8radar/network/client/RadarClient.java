package com.example.mkrawcz.m8radar.network.client;

/**
 * Radar Server API.
 */
public interface RadarClient {

    /**
     * Authenticates the {@link RadarUserCredential user's identity} and starts their new session
     * within the Radar.
     */
    RadarSession logIn(RadarUserCredential credential);
}
