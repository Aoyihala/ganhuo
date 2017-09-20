package com.p2p.dsad.ganhuo.utlis.okhttp;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * 去除验证
 * Created by dsad on 2017/9/18.
 */

public class NoVerifiedCheck implements HostnameVerifier
{

    @Override
    public boolean verify(String s, SSLSession sslSession) {
        return true;
    }
}
