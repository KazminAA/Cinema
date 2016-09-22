package helpers;


import dao.api.Dao;
import models.Entity;

/**
 * Created by Alex on 16.08.2016.
 */
public interface GenericStorage<K extends Number, V extends Entity> extends Dao<V> {
    K add(V value);

    V get(K key);

    void deleteC(K key);

    void update(K key, V value);

    class Node<K, V> {
        private K key;
        private V value;

        public Node(K key, V value) {
            setKey(key);
            setValue(value);
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
}
