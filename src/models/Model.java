package models;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;


public class Model<T extends Model> implements Serializable{
    
    LinkedList<T> model;
    public String modelName;
    public int id;
    
    public Model() {
        this.model = new LinkedList<>();
    }
    
    public void add(T data) {
        
        data.id = generateId();
        
        if (getById(data.id) != null) {
            data.id = generateId();            
        }
        model.add(data);
        save();
    }
    
    public T getById(int id) {
         for (T m : model) {
            if (m.id == id) {
                return m;
            }
        }
         
        return null;
    }
    
    public LinkedList<T> getAll() {
        return this.model;
    }
    
    public int generateId() {
        return (int) (Math.random() * Integer.MAX_VALUE) & 3872;
    }
    
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
            e.printStackTrace();
        }
    }

    private String getPath() {
        return System.getProperty("user.dir") + "/src/models/data/" + modelName + ".ser";
    }
}
