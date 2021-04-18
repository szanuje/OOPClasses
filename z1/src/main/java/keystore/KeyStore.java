package keystore;

public interface KeyStore<K, V> {

    V get(K key);
    void insert(K key, V val);
}