package com.example.mkrawcz.m8radar.network.client;

/**
 * A manager of a single Radar session.
 */
public interface RadarSession extends AutoCloseable {

    /**
     * Joins a Group specified by the {@link RadarGroupToken token}, or throws an
     * {@link IllegalArgumentException} in case it is invalid.
     * <p>
     * The given {@link RadarMember} will be used for interaction with the Group.
     * <p>
     * The returned {@link RadarGroup} can be used to leave the Group.
     */
    RadarGroup joinGroup(RadarGroupToken token, RadarMember self);

    /**
     * Creates a new Group.
     * <p>
     * The given {@link RadarMember} will be used for interaction with the Group.
     */
    RadarGroupCreation createGroup(RadarMember self);

    @Override
    void close();
}
