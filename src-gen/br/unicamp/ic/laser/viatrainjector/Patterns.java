/**
 * Generated from platform:/resource/viatra-injector/src/br/unicamp/ic/laser/viatrainjector/Patterns.vql
 */
package br.unicamp.ic.laser.viatrainjector;

import br.unicamp.ic.laser.viatrainjector.McacontrolSpecification;
import br.unicamp.ic.laser.viatrainjector.McaobjflowSpecification;
import br.unicamp.ic.laser.viatrainjector.MfcnodeSpecification;
import br.unicamp.ic.laser.viatrainjector.MifsdecisionSpecification;
import br.unicamp.ic.laser.viatrainjector.MlpaforkSpecification;
import br.unicamp.ic.laser.viatrainjector.MlpanodeSpecification;
import br.unicamp.ic.laser.viatrainjector.MlpapinSpecification;
import br.unicamp.ic.laser.viatrainjector.MvivSpecification;
import br.unicamp.ic.laser.viatrainjector.MvivobjectSpecification;
import br.unicamp.ic.laser.viatrainjector.WaldanodeSpecification;
import br.unicamp.ic.laser.viatrainjector.WsutnodeSpecification;
import br.unicamp.ic.laser.viatrainjector.WsutpinSpecification;
import br.unicamp.ic.laser.viatrainjector.WvavcguardSpecification;
import br.unicamp.ic.laser.viatrainjector.WvavoguardSpecification;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;

/**
 * A pattern group formed of all public patterns defined in Patterns.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file Patterns.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package br.unicamp.ic.laser.viatrainjector, the group contains the definition of the following patterns: <ul>
 * <li>mcaobjflowSpecification</li>
 * <li>mcacontrolSpecification</li>
 * <li>mifsdecisionSpecification</li>
 * <li>mlpapinSpecification</li>
 * <li>waldanodeSpecification</li>
 * <li>mvivSpecification</li>
 * <li>mfcnodeSpecification</li>
 * <li>wvavoguardSpecification</li>
 * <li>wvavcguardSpecification</li>
 * <li>mlpaforkSpecification</li>
 * <li>wsutnodeSpecification</li>
 * <li>mlpanodeSpecification</li>
 * <li>mvivobjectSpecification</li>
 * <li>wsutpinSpecification</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class Patterns extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static Patterns instance() {
    if (INSTANCE == null) {
        INSTANCE = new Patterns();
    }
    return INSTANCE;
  }
  
  private static Patterns INSTANCE;
  
  private Patterns() {
    querySpecifications.add(McaobjflowSpecification.instance());
    querySpecifications.add(McacontrolSpecification.instance());
    querySpecifications.add(MifsdecisionSpecification.instance());
    querySpecifications.add(MlpapinSpecification.instance());
    querySpecifications.add(WaldanodeSpecification.instance());
    querySpecifications.add(MvivSpecification.instance());
    querySpecifications.add(MfcnodeSpecification.instance());
    querySpecifications.add(WvavoguardSpecification.instance());
    querySpecifications.add(WvavcguardSpecification.instance());
    querySpecifications.add(MlpaforkSpecification.instance());
    querySpecifications.add(WsutnodeSpecification.instance());
    querySpecifications.add(MlpanodeSpecification.instance());
    querySpecifications.add(MvivobjectSpecification.instance());
    querySpecifications.add(WsutpinSpecification.instance());
  }
  
  public McaobjflowSpecification getMcaobjflowSpecification() {
    return McaobjflowSpecification.instance();
  }
  
  public McaobjflowSpecification.Matcher getMcaobjflowSpecification(final ViatraQueryEngine engine) {
    return McaobjflowSpecification.Matcher.on(engine);
  }
  
  public McacontrolSpecification getMcacontrolSpecification() {
    return McacontrolSpecification.instance();
  }
  
  public McacontrolSpecification.Matcher getMcacontrolSpecification(final ViatraQueryEngine engine) {
    return McacontrolSpecification.Matcher.on(engine);
  }
  
  public MifsdecisionSpecification getMifsdecisionSpecification() {
    return MifsdecisionSpecification.instance();
  }
  
  public MifsdecisionSpecification.Matcher getMifsdecisionSpecification(final ViatraQueryEngine engine) {
    return MifsdecisionSpecification.Matcher.on(engine);
  }
  
  public MlpapinSpecification getMlpapinSpecification() {
    return MlpapinSpecification.instance();
  }
  
  public MlpapinSpecification.Matcher getMlpapinSpecification(final ViatraQueryEngine engine) {
    return MlpapinSpecification.Matcher.on(engine);
  }
  
  public WaldanodeSpecification getWaldanodeSpecification() {
    return WaldanodeSpecification.instance();
  }
  
  public WaldanodeSpecification.Matcher getWaldanodeSpecification(final ViatraQueryEngine engine) {
    return WaldanodeSpecification.Matcher.on(engine);
  }
  
  public MvivSpecification getMvivSpecification() {
    return MvivSpecification.instance();
  }
  
  public MvivSpecification.Matcher getMvivSpecification(final ViatraQueryEngine engine) {
    return MvivSpecification.Matcher.on(engine);
  }
  
  public MfcnodeSpecification getMfcnodeSpecification() {
    return MfcnodeSpecification.instance();
  }
  
  public MfcnodeSpecification.Matcher getMfcnodeSpecification(final ViatraQueryEngine engine) {
    return MfcnodeSpecification.Matcher.on(engine);
  }
  
  public WvavoguardSpecification getWvavoguardSpecification() {
    return WvavoguardSpecification.instance();
  }
  
  public WvavoguardSpecification.Matcher getWvavoguardSpecification(final ViatraQueryEngine engine) {
    return WvavoguardSpecification.Matcher.on(engine);
  }
  
  public WvavcguardSpecification getWvavcguardSpecification() {
    return WvavcguardSpecification.instance();
  }
  
  public WvavcguardSpecification.Matcher getWvavcguardSpecification(final ViatraQueryEngine engine) {
    return WvavcguardSpecification.Matcher.on(engine);
  }
  
  public MlpaforkSpecification getMlpaforkSpecification() {
    return MlpaforkSpecification.instance();
  }
  
  public MlpaforkSpecification.Matcher getMlpaforkSpecification(final ViatraQueryEngine engine) {
    return MlpaforkSpecification.Matcher.on(engine);
  }
  
  public WsutnodeSpecification getWsutnodeSpecification() {
    return WsutnodeSpecification.instance();
  }
  
  public WsutnodeSpecification.Matcher getWsutnodeSpecification(final ViatraQueryEngine engine) {
    return WsutnodeSpecification.Matcher.on(engine);
  }
  
  public MlpanodeSpecification getMlpanodeSpecification() {
    return MlpanodeSpecification.instance();
  }
  
  public MlpanodeSpecification.Matcher getMlpanodeSpecification(final ViatraQueryEngine engine) {
    return MlpanodeSpecification.Matcher.on(engine);
  }
  
  public MvivobjectSpecification getMvivobjectSpecification() {
    return MvivobjectSpecification.instance();
  }
  
  public MvivobjectSpecification.Matcher getMvivobjectSpecification(final ViatraQueryEngine engine) {
    return MvivobjectSpecification.Matcher.on(engine);
  }
  
  public WsutpinSpecification getWsutpinSpecification() {
    return WsutpinSpecification.instance();
  }
  
  public WsutpinSpecification.Matcher getWsutpinSpecification(final ViatraQueryEngine engine) {
    return WsutpinSpecification.Matcher.on(engine);
  }
}
