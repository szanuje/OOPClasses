package keystore;

import java.util.Optional;

public interface KeyStore<K, V> {

  Optional<V> get(K key);

  void insert(K key, V val);
}