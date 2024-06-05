package io.github.japangiserver.socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;

@Slf4j
public abstract class AbstractSocketHandler extends Thread {

    protected final Socket connection;
    private final BufferedReader reader;
    private final PrintWriter writer;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private boolean isRunning = true;

    protected AbstractSocketHandler(Socket connection) throws IOException {
        this.connection = connection;
        this.reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        this.writer = new PrintWriter(new OutputStreamWriter(connection.getOutputStream()));
    }

    @Override
    public void run() {
        try {
            afterConnectionEstablished();
            while (isRunning) {
                String message = read();
                if (message != null) {
                    handleSocketMessage(message);
                }
            }
            afterConnectionClosed();
        } catch (IOException e) {
            throw new RuntimeException("Socket Connection Error");
        }
    }

    private String read() throws IOException {
        return reader.readLine();
    }

    protected void write(String data) throws IOException {
        writer.println(data);
        writer.flush();
    }

    protected void quit() {
        this.isRunning = false;
    }

    abstract void afterConnectionEstablished() throws IOException;
    abstract void handleSocketMessage(String message) throws IOException;
    abstract void afterConnectionClosed() throws IOException;
}
