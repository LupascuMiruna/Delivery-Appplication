package com.company.services;
import java.io.*;
import java.sql.Timestamp;
import java.time.Instant;

public class AuditService {
    private static AuditService instance = null;
    private BufferedWriter  bufferWriter;

    private AuditService() {
        try {
            String path = "Files/audit.csv";
            new FileWriter(path, false).close();

            bufferWriter = new BufferedWriter(new FileWriter(path, true));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized AuditService getInstance() {//just one thread can access
        if (instance == null) {
            instance = new AuditService();
        }
        return instance;
    }

    public void writeTime(String message) {
        try {
            Timestamp ts = Timestamp.from(Instant.now());

            bufferWriter.write(message + ", " + ts + "\n");
            bufferWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
