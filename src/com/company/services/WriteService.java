package com.company.services;

import com.company.entities.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteService {
    private static WriteService instance = null;
    private BufferedWriter buff;

    public static synchronized WriteService getInstance() {
        if(instance == null)
            instance = new WriteService();
        return instance;
    }
    public <T> void writeDetails(Object obj, Class<T> classOf) {
        String path = "Files/output.csv";
        try {
            buff = new BufferedWriter(new FileWriter(path, true));
            //delete the previous content
            new FileWriter(path, false).close();
            if(classOf.toString().equals("class com.company.entities.User")){
                buff.write(((User)obj).toString() + "\n");
            }
            else if(classOf.toString().equals("class com.company.entities.Restaurant")) {
                buff.write(((Restaurant)obj).toString() + "\n");
            }
            else if(classOf.toString().equals("class com.company.entities.Drink")) {
                buff.write(((Drink)obj).toString() + "\n");
            }
            else if(classOf.toString().equals("class com.company.entities.Pizza")) {
                buff.write(((Pizza)obj).toString() + "\n");
            }
            else if(classOf.toString().equals("class com.company.entities.Sweet")) {
                buff.write(((Sweet)obj).toString() + "\n");
            }

            buff.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
