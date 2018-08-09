package com.example.mkrawcz.m8radar.mocks

import com.example.mkrawcz.m8radar.network.client.RadarClient
import com.example.mkrawcz.m8radar.network.client.RadarSession
import com.example.mkrawcz.m8radar.network.client.RadarUserCredential

class MockRadarClient: RadarClient {

    override fun logIn(credential: RadarUserCredential?): RadarSession {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
