package core;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;

public class Writer<T extends Serializable> {
   
    public LinkedList<T> model;
    public String modelName;
    
    public void save() {
        try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(getPath()))) {
            for (T data : model) {
                writer.writeObject(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
      
    public void load() {
        model.clear();
        try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(getPath()))) {
            while (true) {
                try {
                    T data = (T) reader.readObject();
                    model.add(data);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Note: Serialized File Not Found For Model: " + modelName);
        }
    }

    protected String getPath() {
        return System.getProperty("user.dir") + "/src/storage/" + modelName + ".ser";
    }
    
}
