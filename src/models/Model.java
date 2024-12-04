package models;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;


abstract class Model<T extends Model> implements Serializable{
    
    LinkedList<T> model;
    public String modelName;
    public int id;
    public LocalDateTime date;
    
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
    
    public boolean deleteById(int id) {
        T current = getById(id);
        if (current == null) {
            return false;
        }
        model.remove(current);
        save();
        return true;
    }
    
    public boolean updateById(int id, T update) {
        T current = getById(id);
        if (current == null) {
            return false;
        }
        model.set(model.indexOf(current), update);
        save();
        return true;
    }
    
    public LinkedList<T> getAll() {
        return this.model;
    }
    
    public int generateId() {
        return (int) (Math.random() * Integer.MAX_VALUE) % 3872;
    }
    
    public void clean() {
        load();
        LocalDateTime now = LocalDateTime.now();

        for (T p : model) {
            if (!p.date.isAfter(now.minusDays(2))) {
                model.remove(p);
                System.out.println("lol");
            }
        }
        save();
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
            System.err.println("Note: Serialized File Not Found For Model: " + modelName);
        }
    }

    private String getPath() {
        return System.getProperty("user.dir") + "/src/models/data/" + modelName + ".ser";
    }
    
}
