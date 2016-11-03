package helpers;


import models.Entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alex on 16.08.2016.
 */
public class Container<V extends Entity> implements GenericStorage<Integer, V>, Iterable<V> {

    List<Node<Integer, V>> storage = new ArrayList<>();
    private Integer defKey;

    public Container(Integer defKey) {
        setDefKey(defKey);
    }

    public Integer getDefKey() {
        return defKey;
    }

    public void setDefKey(Integer defKey) {
        this.defKey = defKey;
    }

    /**
     * Add some Entity to storage if it does not exist
     *
     * @param value Entity to add
     * @return key associated with Entity in storage
     */
    @Override
    public Integer add(V value) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getValue().equals(value)) {
                return storage.get(i).getKey();
            }
        }
        Integer key = getDefKey();
        value.setId(++key);
        storage.add(new Node<>(getDefKey(), value));
        setDefKey(key);
        return key;
    }

    @Override
    public void save(V entity) {
        add(entity);
    }

    @Override
    public V get(Integer key) {
        V value = null;
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getKey().equals(key)) {
                value = storage.get(i).getValue();
            }
        }
        return value;
    }

    @Override
    public V getById(int id) {
        return get(id);
    }

    @Override
    public void deleteC(Integer key) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getKey().equals(key)) {
                storage.remove(i);
            }
        }
    }

    @Override
    public void delete(int id) {
        deleteC(id);
    }

    @Override
    public void update(Integer key, V value) {
        for (int i = 0; i < storage.size(); i++) {

        }
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getKey().equals(key)) {
                storage.get(i).setValue(value);
            }
        }
    }

    @Override
    public List<V> getAll() {
        List<V> result = new LinkedList<V>();
        for (int i = 0; i < storage.size(); i++) {
            result.add(storage.get(i).getValue());
        }
        return result;
    }

    @Override
    public void update(V entity) {
        update(entity.getId(), entity);
    }

    @Override
    public Iterator<V> iterator() {
        Iterator<V> iterator = new Iterator<V>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return storage.size() > index;
            }

            @Override
            public V next() {
                return storage.get(index++).getValue();
            }
        };
        return iterator;
    }

    @Override
    public List<V> getBy(String keyFieldName, String key) {
        return null;
    }

    @Override
    public void deleteBy(String keyFieldName, String key) {

    }

    @Override
    public void updateFieldIn(String field, String value, int key) {

    }
}
