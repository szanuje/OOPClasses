package keystore;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AbstractKeyStore<K, V> implements KeyStore<K, V> {

  private final Map<K, V> map;

  public AbstractKeyStore() {
    map = new HashMap<>();
  }

  @Override
  public Optional<V> get(K key) {
    return Optional.ofNullable(map.get(key));
  }

  @Override
  public void insert(K key, V val) {
    map.putIfAbsent(key, val);
  }
}