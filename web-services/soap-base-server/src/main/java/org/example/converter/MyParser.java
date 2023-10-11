package org.example.converter;

import java.io.*;

public class MyParser {
    public static Object parseBytesToObject(byte[] bytes){
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInputStream ois;
        try {
            ois = new ObjectInputStream(bis);
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] parseObjectToBytes(Object obj){
        ByteArrayOutputStream bis = new ByteArrayOutputStream();
        ObjectOutputStream out;
        try {
            out = new ObjectOutputStream(bis);
            out.writeObject(obj);
            out.flush();
            out.close();
            return bis.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
