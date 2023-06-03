package org.rpcframework.myRPCVersion1.proxytest;

/**
 * @author Dongyu Lv
 * @create 2023-05-27 00:06
 */
public class SmsServiceImpl implements SmsService{
    @Override
    public String send(String message) {
        System.out.println("send message:" + message);
        return message;
    }
}
