package io.github.japangiserver.socket;

import lombok.RequiredArgsConstructor;

import java.io.*;
import java.net.Socket;
import java.util.UUID;

@RequiredArgsConstructor
public class SocketSession {

    private static final String SHORT_UUID_DELIMITER = "-";
    private static final int SHORT_UUID_INDEX = 0;
    private static final String PING_SIGNAL = "PING";

    private final String sessionId;
    private final Socket socket;
    private final BufferedReader reader;
    private final PrintWriter writer;

    public static SocketSession of(Socket socket) {
        try {
            return new SocketSession(
                    provideUniqueId(),
                    socket,
                    new BufferedReader(new InputStreamReader(socket.getInputStream())),
                    new PrintWriter(new OutputStreamWriter(socket.getOutputStream()))
            );
        } catch (IOException e) {
            throw new RuntimeException("cannot initialize socket session");
        }
    }

    private static String provideUniqueId() {
        return UUID.randomUUID().toString().split(SHORT_UUID_DELIMITER)[SHORT_UUID_INDEX];
    }

    public String readMessage() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("cannot read message");
        }
    }

    public void writeMessage(String message) {
        writer.println(message);
        writer.flush();
    }

    public boolean sendPing() {
        writeMessage(PING_SIGNAL);
        return writer.checkError();
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException("cannot close socket session");
        }
    }

    public String getSessionId() {
        return sessionId;
    }

    public int getLocalPort() {
        return socket.getLocalPort();
    }
}
