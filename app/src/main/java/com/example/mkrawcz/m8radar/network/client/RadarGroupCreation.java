package com.example.mkrawcz.m8radar.network.client;

/**
 * A result of {@link RadarSession#createGroup(RadarMember) group creation}.
 */
public interface RadarGroupCreation {

    /**
     * A reference that may be used to leave the group.
     */
    RadarGroup group();

    /**
     * A token that may be used to invite others to {@link RadarSession#joinGroup join} the group.
     */
    RadarGroupToken token();
}
