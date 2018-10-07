package net.glowstone;

public interface IGlowServer {

    void run();

    void start();

    //void shutdown();

    void shutdown(String message);

}
