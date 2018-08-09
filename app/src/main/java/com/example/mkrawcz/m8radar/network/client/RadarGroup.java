package com.example.mkrawcz.m8radar.network.client;

/**
 * A reference to a joined group.
 */
public interface RadarGroup extends AutoCloseable {

    /**
     * Leaves the group.
     */
    @Override
    void close();
}
