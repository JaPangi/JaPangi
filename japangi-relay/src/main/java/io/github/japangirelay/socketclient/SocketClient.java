package io.github.japangirelay.socketclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {

    private final Socket socket;
    private final BufferedReader reader;
    private final PrintWriter writer;

    public SocketClient(Socket socket, BufferedReader reader, PrintWriter writer) {
        this.socket = socket;
        this.reader = reader;
        this.writer = writer;
    }

    public static SocketClient of(String host, int port) {
        try {
            Socket socket = new Socket(host, port);
            return new SocketClient(
                    socket,
                    new BufferedReader(new InputStreamReader(socket.getInputStream())),
                    new PrintWriter(new OutputStreamWriter(socket.getOutputStream()))
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String relayRequest(String message) {
        writeMassage(message);
        return readMassage();
    }

    public String readMassage() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Cannot read message");
        }
    }

    public void writeMassage(String message) {
        writer.println(message);
        writer.flush();
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException("Cannot close socket session");
        }
    }
}
