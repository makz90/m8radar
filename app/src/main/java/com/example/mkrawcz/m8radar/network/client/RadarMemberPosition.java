package com.example.mkrawcz.m8radar.network.client;

/**
 * A position of a single Group Member.
 */
public interface RadarMemberPosition {

    /**
     * The member's identification, unique within the group.
     */
    RadarMemberId id();

    /**
     * The user-defined, current on-screen label.
     */
    String label();

    /**
     * The current position.
     */
    RadarPosition position();
}
