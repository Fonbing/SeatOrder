package com.lxt.seatorder.bean;

/**
 * Created by Lxt Jxfen on 2019-12-12.
 * email: 1771874056@qq.com
 */
public class UserToken extends BaseBean {

    /**
     * accessToken : eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIzYjY1YzA3MS0yNjc1LTRjZWEtODljZi0yY2Q0MmQ0Mjk1YWIiLCJpYXQiOjE1NzYwNTgxMzksInN1YiI6IntcInVpZFwiOlwiMjAxODEwNjIwMzM0XCIsXCJhcHBJZFwiOlwiMmM5MmI2NGYtNDVkZi00MDJiLTlmMGMtMTcyZTE1ODllZWE5XCIsXCJzZXNzaW9uSWRcIjpcIjI0YjAxYWI0LWQxZWEtNDQ5OS1iYzdiLTIxN2ZjZWY5MWFjY1wiLFwidGltZVwiOjE1NzYwNTgxMzkwMjF9IiwiZXhwIjoxNTc2MDY1MzM5fQ.27VN-oU32R25dNdLHNTNsVeybJrMPOd5taV1JV1XHNE
     * expires : 7200
     * reflushToken : eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJleUpoYkdjaU9pSklVekkxTmlKOS5leUpxZEdraU9pSXpZalkxWXpBM01TMHlOamMxTFRSalpXRXRPRGxqWmkweVkyUTBNbVEwTWprMVlXSWlMQ0pwWVhRaU9qRTFOell3TlRneE16a3NJbk4xWWlJNkludGNJblZwWkZ3aU9sd2lNakF4T0RFd05qSXdNek0wWENJc1hDSmhjSEJKWkZ3aU9sd2lNbU01TW1JMk5HWXRORFZrWmkwME1ESmlMVGxtTUdNdE1UY3laVEUxT0RsbFpXRTVYQ0lzWENKelpYTnphVzl1U1dSY0lqcGNJakkwWWpBeFlXSTBMV1F4WldFdE5EUTVPUzFpWXpkaUxUSXhOMlpqWldZNU1XRmpZMXdpTEZ3aWRHbHRaVndpT2pFMU56WXdOVGd4TXprd01qRjlJaXdpWlhod0lqb3hOVGMyTURZMU16TTVmUS4yN1ZOLW9VMzJSMjVkTmRMSE5UTnNWZXliSnJNUE9kNXRhVjFKVjFYSE5FIiwiaWF0IjoxNTc2MDU4MTM5LCJzdWIiOiJ7XCJ1aWRcIjpcIjIwMTgxMDYyMDMzNFwiLFwiYXBwSWRcIjpcIjJjOTJiNjRmLTQ1ZGYtNDAyYi05ZjBjLTE3MmUxNTg5ZWVhOVwiLFwic2Vzc2lvbklkXCI6XCIyNGIwMWFiNC1kMWVhLTQ0OTktYmM3Yi0yMTdmY2VmOTFhY2NcIixcInRpbWVcIjoxNTc2MDU4MTM5MDIxfSIsImV4cCI6MTU3NjIzMDkzOX0.JisK4qYhKPCMM7aih900DEYJhKLHZYf07g5dj6O8L9g
     * appId : 2c92b64f-45df-402b-9f0c-172e1589eea9
     * appSecret : ab6b8467-8299-4d49-95fd-3cb2c169041f
     */

    private String accessToken;
    private int expires;
    private String reflushToken;
    private String appId;
    private String appSecret;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpires() {
        return expires;
    }

    public void setExpires(int expires) {
        this.expires = expires;
    }

    public String getReflushToken() {
        return reflushToken;
    }

    public void setReflushToken(String reflushToken) {
        this.reflushToken = reflushToken;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
