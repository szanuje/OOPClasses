import interfaces.ACLi;
import interfaces.Datagram;

public class DefaultCondition implements ACLi.Condition {

  private final Integer sourceGroupId;
  private final Integer destGroupId;
  private final Datagram.Protocol protocol;
  private final Datagram.Flag flag;
  private final KeyStoreManager keyStoreManager;

  public DefaultCondition(Integer sourceGroupId,
                          Integer destGroupId,
                          Datagram.Protocol protocol,
                          Datagram.Flag flag,
                          KeyStoreManager keyStoreManager) {
    this.sourceGroupId = sourceGroupId;
    this.destGroupId = destGroupId;
    this.protocol = protocol;
    this.flag = flag;
    this.keyStoreManager = keyStoreManager;
  }

  public Integer getSourceGroupId() {
    return sourceGroupId;
  }

  public Integer getDestGroupId() {
    return destGroupId;
  }

  public Datagram.Protocol getProtocol() {
    return protocol;
  }

  public Datagram.Flag getFlag() {
    return flag;
  }

  @Override
  public boolean match(Datagram datagram) {
    return new ConditionMatcher(keyStoreManager, this, datagram).match();
  }
}