/**
 * Generated from platform:/resource/viatra-injector/src/br/unicamp/ic/laser/viatrainjector/Patterns.vql
 */
package br.unicamp.ic.laser.viatrainjector;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.ObjectFlow;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.IQuerySpecification;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedEMFPQuery;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedEMFQuerySpecification;
import org.eclipse.viatra.query.runtime.api.impl.BaseMatcher;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.emf.types.EClassTransitiveInstancesKey;
import org.eclipse.viatra.query.runtime.emf.types.EDataTypeInSlotsKey;
import org.eclipse.viatra.query.runtime.emf.types.EStructuralFeatureInstancesKey;
import org.eclipse.viatra.query.runtime.matchers.backend.QueryEvaluationHint;
import org.eclipse.viatra.query.runtime.matchers.context.common.JavaTransitiveInstancesKey;
import org.eclipse.viatra.query.runtime.matchers.psystem.IExpressionEvaluator;
import org.eclipse.viatra.query.runtime.matchers.psystem.IValueProvider;
import org.eclipse.viatra.query.runtime.matchers.psystem.PBody;
import org.eclipse.viatra.query.runtime.matchers.psystem.PVariable;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.Equality;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.ExportedParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.ExpressionEvaluation;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.TypeFilterConstraint;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.TypeConstraint;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameterDirection;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PVisibility;
import org.eclipse.viatra.query.runtime.matchers.tuple.Tuple;
import org.eclipse.viatra.query.runtime.matchers.tuple.Tuples;
import org.eclipse.viatra.query.runtime.util.ViatraQueryLoggingUtil;

/**
 * A pattern-specific query specification that can instantiate Matcher in a type-safe way.
 * 
 * <p>Original source:
 *         <code><pre>
 *         // We are searching for an Activity, a Comment, and ObjectFlow, and a specific String...
 *         
 *         //Objet Flow and WBC1 - Execução mcaobjflowExecution
 *         pattern wbc1objflowSpecification(act : Activity, c : Comment, objflow : ObjectFlow, str : java String)
 *         {
 *         	// The Comment and the ObjectFlow must be connected by the property "annotatedElement" of Comment metaclass
 *         	// Visualize it as "c --[annotatedElement]--{@literal >} objflow"
 *             Comment.annotatedElement(c,objflow);
 *         
 *           	// The Comment and the String must be connected by the property "body" of Comment metaclass
 *         	// "c --[body]--{@literal >} str"
 *             Comment.body(c,str);
 *             
 *           	// The Activity and the ObjectFlow must be connected by the property "edge" of Activity metaclass
 *         	// "act --[edge]--{@literal >} objflow"  
 *         	Activity.edge(act,objflow);
 *         
 *         	// Additional constraints are that...
 *             check (
 *             	str.startsWith("InjectFault into object flow") && str.indexOf("WBC1") {@literal >} 0  
 *             );
 *         }
 * </pre></code>
 * 
 * @see Matcher
 * @see Match
 * 
 */
@SuppressWarnings("all")
public final class Wbc1objflowSpecification extends BaseGeneratedEMFQuerySpecification<Wbc1objflowSpecification.Matcher> {
  /**
   * Pattern-specific match representation of the br.unicamp.ic.laser.viatrainjector.wbc1objflowSpecification pattern,
   * to be used in conjunction with {@link Matcher}.
   * 
   * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
   * Each instance is a (possibly partial) substitution of pattern parameters,
   * usable to represent a match of the pattern in the result of a query,
   * or to specify the bound (fixed) input parameters when issuing a query.
   * 
   * @see Matcher
   * 
   */
  public static abstract class Match extends BasePatternMatch {
    private Activity fAct;
    
    private Comment fC;
    
    private ObjectFlow fObjflow;
    
    private String fStr;
    
    private static List<String> parameterNames = makeImmutableList("act", "c", "objflow", "str");
    
    private Match(final Activity pAct, final Comment pC, final ObjectFlow pObjflow, final String pStr) {
      this.fAct = pAct;
      this.fC = pC;
      this.fObjflow = pObjflow;
      this.fStr = pStr;
    }
    
    @Override
    public Object get(final String parameterName) {
      switch(parameterName) {
          case "act": return this.fAct;
          case "c": return this.fC;
          case "objflow": return this.fObjflow;
          case "str": return this.fStr;
          default: return null;
      }
    }
    
    @Override
    public Object get(final int index) {
      switch(index) {
          case 0: return this.fAct;
          case 1: return this.fC;
          case 2: return this.fObjflow;
          case 3: return this.fStr;
          default: return null;
      }
    }
    
    public Activity getAct() {
      return this.fAct;
    }
    
    public Comment getC() {
      return this.fC;
    }
    
    public ObjectFlow getObjflow() {
      return this.fObjflow;
    }
    
    public String getStr() {
      return this.fStr;
    }
    
    @Override
    public boolean set(final String parameterName, final Object newValue) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      if ("act".equals(parameterName) ) {
          this.fAct = (Activity) newValue;
          return true;
      }
      if ("c".equals(parameterName) ) {
          this.fC = (Comment) newValue;
          return true;
      }
      if ("objflow".equals(parameterName) ) {
          this.fObjflow = (ObjectFlow) newValue;
          return true;
      }
      if ("str".equals(parameterName) ) {
          this.fStr = (String) newValue;
          return true;
      }
      return false;
    }
    
    public void setAct(final Activity pAct) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      this.fAct = pAct;
    }
    
    public void setC(final Comment pC) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      this.fC = pC;
    }
    
    public void setObjflow(final ObjectFlow pObjflow) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      this.fObjflow = pObjflow;
    }
    
    public void setStr(final String pStr) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      this.fStr = pStr;
    }
    
    @Override
    public String patternName() {
      return "br.unicamp.ic.laser.viatrainjector.wbc1objflowSpecification";
    }
    
    @Override
    public List<String> parameterNames() {
      return Wbc1objflowSpecification.Match.parameterNames;
    }
    
    @Override
    public Object[] toArray() {
      return new Object[]{fAct, fC, fObjflow, fStr};
    }
    
    @Override
    public Wbc1objflowSpecification.Match toImmutable() {
      return isMutable() ? newMatch(fAct, fC, fObjflow, fStr) : this;
    }
    
    @Override
    public String prettyPrint() {
      StringBuilder result = new StringBuilder();
      result.append("\"act\"=" + prettyPrintValue(fAct) + ", ");
      result.append("\"c\"=" + prettyPrintValue(fC) + ", ");
      result.append("\"objflow\"=" + prettyPrintValue(fObjflow) + ", ");
      result.append("\"str\"=" + prettyPrintValue(fStr));
      return result.toString();
    }
    
    @Override
    public int hashCode() {
      return Objects.hash(fAct, fC, fObjflow, fStr);
    }
    
    @Override
    public boolean equals(final Object obj) {
      if (this == obj)
          return true;
      if (obj == null) {
          return false;
      }
      if ((obj instanceof Wbc1objflowSpecification.Match)) {
          Wbc1objflowSpecification.Match other = (Wbc1objflowSpecification.Match) obj;
          return Objects.equals(fAct, other.fAct) && Objects.equals(fC, other.fC) && Objects.equals(fObjflow, other.fObjflow) && Objects.equals(fStr, other.fStr);
      } else {
          // this should be infrequent
          if (!(obj instanceof IPatternMatch)) {
              return false;
          }
          IPatternMatch otherSig  = (IPatternMatch) obj;
          return Objects.equals(specification(), otherSig.specification()) && Arrays.deepEquals(toArray(), otherSig.toArray());
      }
    }
    
    @Override
    public Wbc1objflowSpecification specification() {
      return Wbc1objflowSpecification.instance();
    }
    
    /**
     * Returns an empty, mutable match.
     * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
     * 
     * @return the empty match.
     * 
     */
    public static Wbc1objflowSpecification.Match newEmptyMatch() {
      return new Mutable(null, null, null, null);
    }
    
    /**
     * Returns a mutable (partial) match.
     * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
     * 
     * @param pAct the fixed value of pattern parameter act, or null if not bound.
     * @param pC the fixed value of pattern parameter c, or null if not bound.
     * @param pObjflow the fixed value of pattern parameter objflow, or null if not bound.
     * @param pStr the fixed value of pattern parameter str, or null if not bound.
     * @return the new, mutable (partial) match object.
     * 
     */
    public static Wbc1objflowSpecification.Match newMutableMatch(final Activity pAct, final Comment pC, final ObjectFlow pObjflow, final String pStr) {
      return new Mutable(pAct, pC, pObjflow, pStr);
    }
    
    /**
     * Returns a new (partial) match.
     * This can be used e.g. to call the matcher with a partial match.
     * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
     * @param pAct the fixed value of pattern parameter act, or null if not bound.
     * @param pC the fixed value of pattern parameter c, or null if not bound.
     * @param pObjflow the fixed value of pattern parameter objflow, or null if not bound.
     * @param pStr the fixed value of pattern parameter str, or null if not bound.
     * @return the (partial) match object.
     * 
     */
    public static Wbc1objflowSpecification.Match newMatch(final Activity pAct, final Comment pC, final ObjectFlow pObjflow, final String pStr) {
      return new Immutable(pAct, pC, pObjflow, pStr);
    }
    
    private static final class Mutable extends Wbc1objflowSpecification.Match {
      Mutable(final Activity pAct, final Comment pC, final ObjectFlow pObjflow, final String pStr) {
        super(pAct, pC, pObjflow, pStr);
      }
      
      @Override
      public boolean isMutable() {
        return true;
      }
    }
    
    private static final class Immutable extends Wbc1objflowSpecification.Match {
      Immutable(final Activity pAct, final Comment pC, final ObjectFlow pObjflow, final String pStr) {
        super(pAct, pC, pObjflow, pStr);
      }
      
      @Override
      public boolean isMutable() {
        return false;
      }
    }
  }
  
  /**
   * Generated pattern matcher API of the br.unicamp.ic.laser.viatrainjector.wbc1objflowSpecification pattern,
   * providing pattern-specific query methods.
   * 
   * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
   * e.g. in conjunction with {@link ViatraQueryEngine#on(QueryScope)}.
   * 
   * <p>Matches of the pattern will be represented as {@link Match}.
   * 
   * <p>Original source:
   * <code><pre>
   * // We are searching for an Activity, a Comment, and ObjectFlow, and a specific String...
   * 
   * //Objet Flow and WBC1 - Execução mcaobjflowExecution
   * pattern wbc1objflowSpecification(act : Activity, c : Comment, objflow : ObjectFlow, str : java String)
   * {
   * 	// The Comment and the ObjectFlow must be connected by the property "annotatedElement" of Comment metaclass
   * 	// Visualize it as "c --[annotatedElement]--{@literal >} objflow"
   *     Comment.annotatedElement(c,objflow);
   * 
   *   	// The Comment and the String must be connected by the property "body" of Comment metaclass
   * 	// "c --[body]--{@literal >} str"
   *     Comment.body(c,str);
   *     
   *   	// The Activity and the ObjectFlow must be connected by the property "edge" of Activity metaclass
   * 	// "act --[edge]--{@literal >} objflow"  
   * 	Activity.edge(act,objflow);
   * 
   * 	// Additional constraints are that...
   *     check (
   *     	str.startsWith("InjectFault into object flow") && str.indexOf("WBC1") {@literal >} 0  
   *     );
   * }
   * </pre></code>
   * 
   * @see Match
   * @see Wbc1objflowSpecification
   * 
   */
  public static class Matcher extends BaseMatcher<Wbc1objflowSpecification.Match> {
    /**
     * Initializes the pattern matcher within an existing VIATRA Query engine.
     * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
     * 
     * @param engine the existing VIATRA Query engine in which this matcher will be created.
     * @throws ViatraQueryRuntimeException if an error occurs during pattern matcher creation
     * 
     */
    public static Wbc1objflowSpecification.Matcher on(final ViatraQueryEngine engine) {
      // check if matcher already exists
      Matcher matcher = engine.getExistingMatcher(querySpecification());
      if (matcher == null) {
          matcher = (Matcher)engine.getMatcher(querySpecification());
      }
      return matcher;
    }
    
    /**
     * @throws ViatraQueryRuntimeException if an error occurs during pattern matcher creation
     * @return an initialized matcher
     * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
     * 
     */
    public static Wbc1objflowSpecification.Matcher create() {
      return new Matcher();
    }
    
    private static final int POSITION_ACT = 0;
    
    private static final int POSITION_C = 1;
    
    private static final int POSITION_OBJFLOW = 2;
    
    private static final int POSITION_STR = 3;
    
    private static final Logger LOGGER = ViatraQueryLoggingUtil.getLogger(Wbc1objflowSpecification.Matcher.class);
    
    /**
     * Initializes the pattern matcher within an existing VIATRA Query engine.
     * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
     * 
     * @param engine the existing VIATRA Query engine in which this matcher will be created.
     * @throws ViatraQueryRuntimeException if an error occurs during pattern matcher creation
     * 
     */
    private Matcher() {
      super(querySpecification());
    }
    
    /**
     * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
     * @param pAct the fixed value of pattern parameter act, or null if not bound.
     * @param pC the fixed value of pattern parameter c, or null if not bound.
     * @param pObjflow the fixed value of pattern parameter objflow, or null if not bound.
     * @param pStr the fixed value of pattern parameter str, or null if not bound.
     * @return matches represented as a Match object.
     * 
     */
    public Collection<Wbc1objflowSpecification.Match> getAllMatches(final Activity pAct, final Comment pC, final ObjectFlow pObjflow, final String pStr) {
      return rawStreamAllMatches(new Object[]{pAct, pC, pObjflow, pStr}).collect(Collectors.toSet());
    }
    
    /**
     * Returns a stream of all matches of the pattern that conform to the given fixed values of some parameters.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     * @param pAct the fixed value of pattern parameter act, or null if not bound.
     * @param pC the fixed value of pattern parameter c, or null if not bound.
     * @param pObjflow the fixed value of pattern parameter objflow, or null if not bound.
     * @param pStr the fixed value of pattern parameter str, or null if not bound.
     * @return a stream of matches represented as a Match object.
     * 
     */
    public Stream<Wbc1objflowSpecification.Match> streamAllMatches(final Activity pAct, final Comment pC, final ObjectFlow pObjflow, final String pStr) {
      return rawStreamAllMatches(new Object[]{pAct, pC, pObjflow, pStr});
    }
    
    /**
     * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
     * Neither determinism nor randomness of selection is guaranteed.
     * @param pAct the fixed value of pattern parameter act, or null if not bound.
     * @param pC the fixed value of pattern parameter c, or null if not bound.
     * @param pObjflow the fixed value of pattern parameter objflow, or null if not bound.
     * @param pStr the fixed value of pattern parameter str, or null if not bound.
     * @return a match represented as a Match object, or null if no match is found.
     * 
     */
    public Optional<Wbc1objflowSpecification.Match> getOneArbitraryMatch(final Activity pAct, final Comment pC, final ObjectFlow pObjflow, final String pStr) {
      return rawGetOneArbitraryMatch(new Object[]{pAct, pC, pObjflow, pStr});
    }
    
    /**
     * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
     * under any possible substitution of the unspecified parameters (if any).
     * @param pAct the fixed value of pattern parameter act, or null if not bound.
     * @param pC the fixed value of pattern parameter c, or null if not bound.
     * @param pObjflow the fixed value of pattern parameter objflow, or null if not bound.
     * @param pStr the fixed value of pattern parameter str, or null if not bound.
     * @return true if the input is a valid (partial) match of the pattern.
     * 
     */
    public boolean hasMatch(final Activity pAct, final Comment pC, final ObjectFlow pObjflow, final String pStr) {
      return rawHasMatch(new Object[]{pAct, pC, pObjflow, pStr});
    }
    
    /**
     * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
     * @param pAct the fixed value of pattern parameter act, or null if not bound.
     * @param pC the fixed value of pattern parameter c, or null if not bound.
     * @param pObjflow the fixed value of pattern parameter objflow, or null if not bound.
     * @param pStr the fixed value of pattern parameter str, or null if not bound.
     * @return the number of pattern matches found.
     * 
     */
    public int countMatches(final Activity pAct, final Comment pC, final ObjectFlow pObjflow, final String pStr) {
      return rawCountMatches(new Object[]{pAct, pC, pObjflow, pStr});
    }
    
    /**
     * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
     * Neither determinism nor randomness of selection is guaranteed.
     * @param pAct the fixed value of pattern parameter act, or null if not bound.
     * @param pC the fixed value of pattern parameter c, or null if not bound.
     * @param pObjflow the fixed value of pattern parameter objflow, or null if not bound.
     * @param pStr the fixed value of pattern parameter str, or null if not bound.
     * @param processor the action that will process the selected match.
     * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
     * 
     */
    public boolean forOneArbitraryMatch(final Activity pAct, final Comment pC, final ObjectFlow pObjflow, final String pStr, final Consumer<? super Wbc1objflowSpecification.Match> processor) {
      return rawForOneArbitraryMatch(new Object[]{pAct, pC, pObjflow, pStr}, processor);
    }
    
    /**
     * Returns a new (partial) match.
     * This can be used e.g. to call the matcher with a partial match.
     * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
     * @param pAct the fixed value of pattern parameter act, or null if not bound.
     * @param pC the fixed value of pattern parameter c, or null if not bound.
     * @param pObjflow the fixed value of pattern parameter objflow, or null if not bound.
     * @param pStr the fixed value of pattern parameter str, or null if not bound.
     * @return the (partial) match object.
     * 
     */
    public Wbc1objflowSpecification.Match newMatch(final Activity pAct, final Comment pC, final ObjectFlow pObjflow, final String pStr) {
      return Wbc1objflowSpecification.Match.newMatch(pAct, pC, pObjflow, pStr);
    }
    
    /**
     * Retrieve the set of values that occur in matches for act.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    protected Stream<Activity> rawStreamAllValuesOfact(final Object[] parameters) {
      return rawStreamAllValues(POSITION_ACT, parameters).map(Activity.class::cast);
    }
    
    /**
     * Retrieve the set of values that occur in matches for act.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<Activity> getAllValuesOfact() {
      return rawStreamAllValuesOfact(emptyArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for act.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Stream<Activity> streamAllValuesOfact() {
      return rawStreamAllValuesOfact(emptyArray());
    }
    
    /**
     * Retrieve the set of values that occur in matches for act.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     *      
     * @return the Stream of all values or empty set if there are no matches
     * 
     */
    public Stream<Activity> streamAllValuesOfact(final Wbc1objflowSpecification.Match partialMatch) {
      return rawStreamAllValuesOfact(partialMatch.toArray());
    }
    
    /**
     * Retrieve the set of values that occur in matches for act.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     *      
     * @return the Stream of all values or empty set if there are no matches
     * 
     */
    public Stream<Activity> streamAllValuesOfact(final Comment pC, final ObjectFlow pObjflow, final String pStr) {
      return rawStreamAllValuesOfact(new Object[]{null, pC, pObjflow, pStr});
    }
    
    /**
     * Retrieve the set of values that occur in matches for act.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<Activity> getAllValuesOfact(final Wbc1objflowSpecification.Match partialMatch) {
      return rawStreamAllValuesOfact(partialMatch.toArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for act.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<Activity> getAllValuesOfact(final Comment pC, final ObjectFlow pObjflow, final String pStr) {
      return rawStreamAllValuesOfact(new Object[]{null, pC, pObjflow, pStr}).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for c.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    protected Stream<Comment> rawStreamAllValuesOfc(final Object[] parameters) {
      return rawStreamAllValues(POSITION_C, parameters).map(Comment.class::cast);
    }
    
    /**
     * Retrieve the set of values that occur in matches for c.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<Comment> getAllValuesOfc() {
      return rawStreamAllValuesOfc(emptyArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for c.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Stream<Comment> streamAllValuesOfc() {
      return rawStreamAllValuesOfc(emptyArray());
    }
    
    /**
     * Retrieve the set of values that occur in matches for c.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     *      
     * @return the Stream of all values or empty set if there are no matches
     * 
     */
    public Stream<Comment> streamAllValuesOfc(final Wbc1objflowSpecification.Match partialMatch) {
      return rawStreamAllValuesOfc(partialMatch.toArray());
    }
    
    /**
     * Retrieve the set of values that occur in matches for c.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     *      
     * @return the Stream of all values or empty set if there are no matches
     * 
     */
    public Stream<Comment> streamAllValuesOfc(final Activity pAct, final ObjectFlow pObjflow, final String pStr) {
      return rawStreamAllValuesOfc(new Object[]{pAct, null, pObjflow, pStr});
    }
    
    /**
     * Retrieve the set of values that occur in matches for c.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<Comment> getAllValuesOfc(final Wbc1objflowSpecification.Match partialMatch) {
      return rawStreamAllValuesOfc(partialMatch.toArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for c.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<Comment> getAllValuesOfc(final Activity pAct, final ObjectFlow pObjflow, final String pStr) {
      return rawStreamAllValuesOfc(new Object[]{pAct, null, pObjflow, pStr}).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for objflow.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    protected Stream<ObjectFlow> rawStreamAllValuesOfobjflow(final Object[] parameters) {
      return rawStreamAllValues(POSITION_OBJFLOW, parameters).map(ObjectFlow.class::cast);
    }
    
    /**
     * Retrieve the set of values that occur in matches for objflow.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<ObjectFlow> getAllValuesOfobjflow() {
      return rawStreamAllValuesOfobjflow(emptyArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for objflow.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Stream<ObjectFlow> streamAllValuesOfobjflow() {
      return rawStreamAllValuesOfobjflow(emptyArray());
    }
    
    /**
     * Retrieve the set of values that occur in matches for objflow.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     *      
     * @return the Stream of all values or empty set if there are no matches
     * 
     */
    public Stream<ObjectFlow> streamAllValuesOfobjflow(final Wbc1objflowSpecification.Match partialMatch) {
      return rawStreamAllValuesOfobjflow(partialMatch.toArray());
    }
    
    /**
     * Retrieve the set of values that occur in matches for objflow.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     *      
     * @return the Stream of all values or empty set if there are no matches
     * 
     */
    public Stream<ObjectFlow> streamAllValuesOfobjflow(final Activity pAct, final Comment pC, final String pStr) {
      return rawStreamAllValuesOfobjflow(new Object[]{pAct, pC, null, pStr});
    }
    
    /**
     * Retrieve the set of values that occur in matches for objflow.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<ObjectFlow> getAllValuesOfobjflow(final Wbc1objflowSpecification.Match partialMatch) {
      return rawStreamAllValuesOfobjflow(partialMatch.toArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for objflow.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<ObjectFlow> getAllValuesOfobjflow(final Activity pAct, final Comment pC, final String pStr) {
      return rawStreamAllValuesOfobjflow(new Object[]{pAct, pC, null, pStr}).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for str.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    protected Stream<String> rawStreamAllValuesOfstr(final Object[] parameters) {
      return rawStreamAllValues(POSITION_STR, parameters).map(String.class::cast);
    }
    
    /**
     * Retrieve the set of values that occur in matches for str.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<String> getAllValuesOfstr() {
      return rawStreamAllValuesOfstr(emptyArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for str.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Stream<String> streamAllValuesOfstr() {
      return rawStreamAllValuesOfstr(emptyArray());
    }
    
    /**
     * Retrieve the set of values that occur in matches for str.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     *      
     * @return the Stream of all values or empty set if there are no matches
     * 
     */
    public Stream<String> streamAllValuesOfstr(final Wbc1objflowSpecification.Match partialMatch) {
      return rawStreamAllValuesOfstr(partialMatch.toArray());
    }
    
    /**
     * Retrieve the set of values that occur in matches for str.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     *      
     * @return the Stream of all values or empty set if there are no matches
     * 
     */
    public Stream<String> streamAllValuesOfstr(final Activity pAct, final Comment pC, final ObjectFlow pObjflow) {
      return rawStreamAllValuesOfstr(new Object[]{pAct, pC, pObjflow, null});
    }
    
    /**
     * Retrieve the set of values that occur in matches for str.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<String> getAllValuesOfstr(final Wbc1objflowSpecification.Match partialMatch) {
      return rawStreamAllValuesOfstr(partialMatch.toArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for str.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<String> getAllValuesOfstr(final Activity pAct, final Comment pC, final ObjectFlow pObjflow) {
      return rawStreamAllValuesOfstr(new Object[]{pAct, pC, pObjflow, null}).collect(Collectors.toSet());
    }
    
    @Override
    protected Wbc1objflowSpecification.Match tupleToMatch(final Tuple t) {
      try {
          return Wbc1objflowSpecification.Match.newMatch((Activity) t.get(POSITION_ACT), (Comment) t.get(POSITION_C), (ObjectFlow) t.get(POSITION_OBJFLOW), (String) t.get(POSITION_STR));
      } catch(ClassCastException e) {
          LOGGER.error("Element(s) in tuple not properly typed!",e);
          return null;
      }
    }
    
    @Override
    protected Wbc1objflowSpecification.Match arrayToMatch(final Object[] match) {
      try {
          return Wbc1objflowSpecification.Match.newMatch((Activity) match[POSITION_ACT], (Comment) match[POSITION_C], (ObjectFlow) match[POSITION_OBJFLOW], (String) match[POSITION_STR]);
      } catch(ClassCastException e) {
          LOGGER.error("Element(s) in array not properly typed!",e);
          return null;
      }
    }
    
    @Override
    protected Wbc1objflowSpecification.Match arrayToMatchMutable(final Object[] match) {
      try {
          return Wbc1objflowSpecification.Match.newMutableMatch((Activity) match[POSITION_ACT], (Comment) match[POSITION_C], (ObjectFlow) match[POSITION_OBJFLOW], (String) match[POSITION_STR]);
      } catch(ClassCastException e) {
          LOGGER.error("Element(s) in array not properly typed!",e);
          return null;
      }
    }
    
    /**
     * @return the singleton instance of the query specification of this pattern
     * @throws ViatraQueryRuntimeException if the pattern definition could not be loaded
     * 
     */
    public static IQuerySpecification<Wbc1objflowSpecification.Matcher> querySpecification() {
      return Wbc1objflowSpecification.instance();
    }
  }
  
  private Wbc1objflowSpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryRuntimeException if the pattern definition could not be loaded
   * 
   */
  public static Wbc1objflowSpecification instance() {
    try{
        return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
        throw processInitializerError(err);
    }
  }
  
  @Override
  protected Wbc1objflowSpecification.Matcher instantiate(final ViatraQueryEngine engine) {
    return Wbc1objflowSpecification.Matcher.on(engine);
  }
  
  @Override
  public Wbc1objflowSpecification.Matcher instantiate() {
    return Wbc1objflowSpecification.Matcher.create();
  }
  
  @Override
  public Wbc1objflowSpecification.Match newEmptyMatch() {
    return Wbc1objflowSpecification.Match.newEmptyMatch();
  }
  
  @Override
  public Wbc1objflowSpecification.Match newMatch(final Object... parameters) {
    return Wbc1objflowSpecification.Match.newMatch((org.eclipse.uml2.uml.Activity) parameters[0], (org.eclipse.uml2.uml.Comment) parameters[1], (org.eclipse.uml2.uml.ObjectFlow) parameters[2], (java.lang.String) parameters[3]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link Wbc1objflowSpecification} to be created 
   *     <b>not</b> at the class load time of the outer class, 
   *     but rather at the first call to {@link Wbc1objflowSpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private static final Wbc1objflowSpecification INSTANCE = new Wbc1objflowSpecification();
    
    /**
     * Statically initializes the query specification <b>after</b> the field {@link #INSTANCE} is assigned.
     * This initialization order is required to support indirect recursion.
     * 
     * <p> The static initializer is defined using a helper field to work around limitations of the code generator.
     * 
     */
    private static final Object STATIC_INITIALIZER = ensureInitialized();
    
    public static Object ensureInitialized() {
      INSTANCE.ensureInitializedInternal();
      return null;
    }
  }
  
  private static class GeneratedPQuery extends BaseGeneratedEMFPQuery {
    private static final Wbc1objflowSpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_act = new PParameter("act", "org.eclipse.uml2.uml.Activity", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://www.eclipse.org/uml2/5.0.0/UML", "Activity")), PParameterDirection.INOUT);
    
    private final PParameter parameter_c = new PParameter("c", "org.eclipse.uml2.uml.Comment", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://www.eclipse.org/uml2/5.0.0/UML", "Comment")), PParameterDirection.INOUT);
    
    private final PParameter parameter_objflow = new PParameter("objflow", "org.eclipse.uml2.uml.ObjectFlow", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://www.eclipse.org/uml2/5.0.0/UML", "ObjectFlow")), PParameterDirection.INOUT);
    
    private final PParameter parameter_str = new PParameter("str", "java.lang.String", new JavaTransitiveInstancesKey(java.lang.String.class), PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_act, parameter_c, parameter_objflow, parameter_str);
    
    private GeneratedPQuery() {
      super(PVisibility.PUBLIC);
    }
    
    @Override
    public String getFullyQualifiedName() {
      return "br.unicamp.ic.laser.viatrainjector.wbc1objflowSpecification";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("act","c","objflow","str");
    }
    
    @Override
    public List<PParameter> getParameters() {
      return parameters;
    }
    
    @Override
    public Set<PBody> doGetContainedBodies() {
      setEvaluationHints(new QueryEvaluationHint(null, QueryEvaluationHint.BackendRequirement.UNSPECIFIED));
      Set<PBody> bodies = new LinkedHashSet<>();
      {
          PBody body = new PBody(this);
          PVariable var_act = body.getOrCreateVariableByName("act");
          PVariable var_c = body.getOrCreateVariableByName("c");
          PVariable var_objflow = body.getOrCreateVariableByName("objflow");
          PVariable var_str = body.getOrCreateVariableByName("str");
          new TypeConstraint(body, Tuples.flatTupleOf(var_act), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.eclipse.org/uml2/5.0.0/UML", "Activity")));
          new TypeConstraint(body, Tuples.flatTupleOf(var_c), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.eclipse.org/uml2/5.0.0/UML", "Comment")));
          new TypeConstraint(body, Tuples.flatTupleOf(var_objflow), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.eclipse.org/uml2/5.0.0/UML", "ObjectFlow")));
          new TypeFilterConstraint(body, Tuples.flatTupleOf(var_str), new JavaTransitiveInstancesKey(java.lang.String.class));
          body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
             new ExportedParameter(body, var_act, parameter_act),
             new ExportedParameter(body, var_c, parameter_c),
             new ExportedParameter(body, var_objflow, parameter_objflow),
             new ExportedParameter(body, var_str, parameter_str)
          ));
          // 	// The Comment and the ObjectFlow must be connected by the property "annotatedElement" of Comment metaclass	// Visualize it as "c --[annotatedElement]--> objflow"    Comment.annotatedElement(c,objflow)
          new TypeConstraint(body, Tuples.flatTupleOf(var_c), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.eclipse.org/uml2/5.0.0/UML", "Comment")));
          PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
          new TypeConstraint(body, Tuples.flatTupleOf(var_c, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.eclipse.org/uml2/5.0.0/UML", "Comment", "annotatedElement")));
          new TypeConstraint(body, Tuples.flatTupleOf(var__virtual_0_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.eclipse.org/uml2/5.0.0/UML", "Element")));
          new Equality(body, var__virtual_0_, var_objflow);
          //   	// The Comment and the String must be connected by the property "body" of Comment metaclass	// "c --[body]--> str"    Comment.body(c,str)
          new TypeConstraint(body, Tuples.flatTupleOf(var_c), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.eclipse.org/uml2/5.0.0/UML", "Comment")));
          PVariable var__virtual_1_ = body.getOrCreateVariableByName(".virtual{1}");
          new TypeConstraint(body, Tuples.flatTupleOf(var_c, var__virtual_1_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.eclipse.org/uml2/5.0.0/UML", "Comment", "body")));
          new TypeConstraint(body, Tuples.flatTupleOf(var__virtual_1_), new EDataTypeInSlotsKey((EDataType)getClassifierLiteral("http://www.eclipse.org/uml2/5.0.0/Types", "String")));
          new Equality(body, var__virtual_1_, var_str);
          //       	// The Activity and the ObjectFlow must be connected by the property "edge" of Activity metaclass	// "act --[edge]--> objflow"  	Activity.edge(act,objflow)
          new TypeConstraint(body, Tuples.flatTupleOf(var_act), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.eclipse.org/uml2/5.0.0/UML", "Activity")));
          PVariable var__virtual_2_ = body.getOrCreateVariableByName(".virtual{2}");
          new TypeConstraint(body, Tuples.flatTupleOf(var_act, var__virtual_2_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.eclipse.org/uml2/5.0.0/UML", "Activity", "edge")));
          new TypeConstraint(body, Tuples.flatTupleOf(var__virtual_2_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.eclipse.org/uml2/5.0.0/UML", "ActivityEdge")));
          new Equality(body, var__virtual_2_, var_objflow);
          // 	// Additional constraints are that...    check (    	str.startsWith("InjectFault into object flow") && str.indexOf("WBC1") > 0      )
          new ExpressionEvaluation(body, new IExpressionEvaluator() {
          
              @Override
              public String getShortDescription() {
                  return "Expression evaluation from pattern wbc1objflowSpecification";
              }
              
              @Override
              public Iterable<String> getInputParameterNames() {
                  return Arrays.asList("str");}
          
              @Override
              public Object evaluateExpression(IValueProvider provider) throws Exception {
                  String str = (String) provider.getValue("str");
                  return evaluateExpression_1_1(str);
              }
          },  null); 
          bodies.add(body);
      }
      return bodies;
    }
  }
  
  private static boolean evaluateExpression_1_1(final String str) {
    return (str.startsWith("InjectFault into object flow") && (str.indexOf("WBC1") > 0));
  }
}
