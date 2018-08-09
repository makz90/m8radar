package com.example.mkrawcz.m8radar.network.client;

import java.util.Collection;

/**
 * An entity interacting with a Radar Group.
 */
public interface RadarMember {

    /**
     * Periodically receives new positions of other group members.
     */
    void onGroupMembersUpdated(Collection<RadarMemberPosition> otherPositions);

    /**
     * Returns the current {@link RadarPosition}.
     */
    RadarPosition getCurrentPosition();
}
