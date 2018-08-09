package com.example.mkrawcz.m8radar.network.client;

/**
 * A geo-location and heading of a Radar member.
 */
public interface RadarPosition {

    /**
     * The latitude in a standard map format.
     */
    double latitude();

    /**
     * The longitude in a standard map format.
     */
    double longitude();

    /**
     * The heading, relative to north, in radians.
     */
    double heading();
}
