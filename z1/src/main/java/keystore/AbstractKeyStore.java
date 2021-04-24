package keystore;

import java.util.HashMap;
import java.util.Map;

public class AbstractKeyStore<K, V> implements KeyStore<K, V> {

    private final Map<K, V> map;

    public AbstractKeyStore() {
        map = new HashMap<>();
    }

    @Override
    public V get(K key) {
        return map.get(key);
    }

    @Override
    public void insert(K key, V val) {
        map.putIfAbsent(key, val);
    }

    public Map<K, V> getAll() {
        return map;
    }
}