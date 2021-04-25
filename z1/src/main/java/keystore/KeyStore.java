package keystore;

import java.util.Map;

public interface KeyStore<K, V> {

    V get(K key);

    void insert(K key, V val);

    Map<K, V> getAll();
}
