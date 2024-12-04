package models;

import core.Writer;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;


abstract class Model<T extends Model> extends Writer<T> implements Serializable{
    

    public int id;
    public LocalDateTime date;
    
    public Model() {
        model = new LinkedList<>();
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
       
}
