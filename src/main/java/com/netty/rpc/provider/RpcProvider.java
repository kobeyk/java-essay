package com.netty.rpc.provider;

import com.netty.rpc.server.RpcServer;

public class RpcProvider {

    private String host;
    private int port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public RpcProvider(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start(){
        RpcServer.startServer(host,port);
    }

    public static void main(String[] args) {
        new RpcProvider("localhost",6666).start();
    }
}
