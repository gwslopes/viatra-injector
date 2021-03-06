package br.unicamp.ic.laser.viatrainjector;

import br.unicamp.ic.laser.viatrainjector.MfcnodeSpecification;
import br.unicamp.ic.laser.viatrainjector.MifsdecisionSpecification;
import br.unicamp.ic.laser.viatrainjector.MlpaforkSpecification;
import br.unicamp.ic.laser.viatrainjector.MlpapinSpecification;
import br.unicamp.ic.laser.viatrainjector.MvivSpecification;
import br.unicamp.ic.laser.viatrainjector.MvivobjectSpecification;
import br.unicamp.ic.laser.viatrainjector.Patterns;
import br.unicamp.ic.laser.viatrainjector.WaldanodeSpecification;
import br.unicamp.ic.laser.viatrainjector.Wbc1controlSpecification;
import br.unicamp.ic.laser.viatrainjector.Wbc1ctrlflowSpecification;
import br.unicamp.ic.laser.viatrainjector.Wbc1objectSpecification;
import br.unicamp.ic.laser.viatrainjector.Wbc1objflowSpecification;
import br.unicamp.ic.laser.viatrainjector.WsutnodeSpecification;
import br.unicamp.ic.laser.viatrainjector.WsutpinSpecification;
import br.unicamp.ic.laser.viatrainjector.WvavcguardSpecification;
import br.unicamp.ic.laser.viatrainjector.WvavoguardSpecification;
import com.google.common.base.Objects;
import java.util.Collection;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.ControlFlow;
import org.eclipse.uml2.uml.DecisionNode;
import org.eclipse.uml2.uml.InputPin;
import org.eclipse.uml2.uml.LiteralBoolean;
import org.eclipse.uml2.uml.LiteralInteger;
import org.eclipse.uml2.uml.LiteralReal;
import org.eclipse.uml2.uml.LiteralString;
import org.eclipse.uml2.uml.LiteralUnlimitedNatural;
import org.eclipse.uml2.uml.ObjectFlow;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValueSpecification;
import org.eclipse.uml2.uml.ValueSpecificationAction;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.emf.EMFScope;
import org.eclipse.viatra.transformation.runtime.emf.modelmanipulation.IModelManipulations;
import org.eclipse.viatra.transformation.runtime.emf.modelmanipulation.SimpleModelManipulations;
import org.eclipse.viatra.transformation.runtime.emf.rules.batch.BatchTransformationRuleFactory;
import org.eclipse.viatra.transformation.runtime.emf.transformation.batch.BatchTransformation;
import org.eclipse.viatra.transformation.runtime.emf.transformation.batch.BatchTransformationStatements;
import org.eclipse.xtext.xbase.lib.CollectionExtensions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class InjectorTransformation {
  /**
   * VIATRA Query Pattern group
   */
  @Extension
  private final Patterns faultSpecifications = Patterns.instance();
  
  /**
   * EMF metamodels
   */
  @Extension
  private final UMLPackage umlPackage = UMLPackage.eINSTANCE;
  
  @Extension
  private final UMLFactory umlFactory = this.umlPackage.getUMLFactory();
  
  /**
   * Transformation-related extensions
   */
  @Extension
  private BatchTransformation transformation;
  
  @Extension
  private BatchTransformationStatements statements;
  
  /**
   * Transformation rule-related extensions
   */
  @Extension
  private BatchTransformationRuleFactory _batchTransformationRuleFactory = new BatchTransformationRuleFactory();
  
  @Extension
  private IModelManipulations manipulation;
  
  protected ViatraQueryEngine engine;
  
  protected Resource resource;
  
  public InjectorTransformation(final Resource resource) {
    this.resource = resource;
    final EMFScope scope = new EMFScope(resource);
    this.engine = ViatraQueryEngine.on(scope);
    this.createTransformation();
  }
  
  /**
   * Aqui ?? onde a transforma????o ?? de fato implementada
   * Nada impede voc?? criar mais m??todos com diferentes transforma????es,
   * adicionar par??metros ou chamar otras classes
   */
  public void wbc1objflowExecution() {
    final Collection<Wbc1objflowSpecification.Match> matched = this.engine.<Wbc1objflowSpecification.Matcher>getMatcher(this.faultSpecifications.getWbc1objflowSpecification()).getAllMatches();
    for (final Wbc1objflowSpecification.Match el : matched) {
      {
        String text = el.getC().getBody();
        System.out.println(el.getC().getBody());
        Comment _c = el.getC();
        _c.setBody("INJECTED!");
        final Comment comment = this.umlFactory.createComment();
        comment.getAnnotatedElements().addAll(el.getC().getAnnotatedElements());
        comment.getAnnotatedElements().remove(el.getObjflow());
        comment.getAnnotatedElements().remove(el.getAct());
        EcoreUtil.delete(el.getObjflow());
        final String target = this.getTarget(text);
        final String source = this.getSource(text);
        if ((source == null)) {
          final ControlFlow ctrlFlow = this.umlFactory.createControlFlow();
          ctrlFlow.setName("Injected-control-flow");
          ctrlFlow.setGuard(el.getObjflow().getGuard());
          ctrlFlow.setWeight(el.getObjflow().getWeight());
          ctrlFlow.setSource(el.getObjflow().getSource());
          ctrlFlow.setTarget(el.getObjflow().getTarget());
          el.getAct().getEdges().add(ctrlFlow);
        } else {
          final ControlFlow ctrlFlow_1 = this.umlFactory.createControlFlow();
          ctrlFlow_1.setName("Injected-control-flow");
          ctrlFlow_1.setGuard(el.getObjflow().getGuard());
          ctrlFlow_1.setWeight(el.getObjflow().getWeight());
          final Function1<ActivityNode, Boolean> _function = (ActivityNode it) -> {
            return Boolean.valueOf(it.getName().equals(source));
          };
          ctrlFlow_1.setSource(IterableExtensions.<ActivityNode>findFirst(el.getAct().getOwnedNodes(), _function));
          final Function1<ActivityNode, Boolean> _function_1 = (ActivityNode it) -> {
            return Boolean.valueOf(it.getName().equals(target));
          };
          ctrlFlow_1.setTarget(IterableExtensions.<ActivityNode>findFirst(el.getAct().getOwnedNodes(), _function_1));
        }
      }
    }
  }
  
  public void wbc1ctrlflowExecution() {
    final Collection<Wbc1ctrlflowSpecification.Match> matched = this.engine.<Wbc1ctrlflowSpecification.Matcher>getMatcher(this.faultSpecifications.getWbc1ctrlflowSpecification()).getAllMatches();
    for (final Wbc1ctrlflowSpecification.Match el : matched) {
      {
        String text = el.getC().getBody();
        System.out.println(el.getC().getBody());
        Comment _c = el.getC();
        _c.setBody("INJECTED!");
        final Comment comment = this.umlFactory.createComment();
        comment.getAnnotatedElements().addAll(el.getC().getAnnotatedElements());
        comment.getAnnotatedElements().remove(el.getCtrlflow());
        comment.getAnnotatedElements().remove(el.getAct());
        final String target = this.getTarget(text);
        final String source = this.getSource(text);
        EcoreUtil.delete(el.getCtrlflow());
        if ((source == null)) {
          final ObjectFlow objFlow = this.umlFactory.createObjectFlow();
          objFlow.setName("Injected-control-flow");
          objFlow.setGuard(el.getCtrlflow().getGuard());
          objFlow.setWeight(el.getCtrlflow().getWeight());
          objFlow.setSource(el.getCtrlflow().getSource());
          objFlow.setTarget(el.getCtrlflow().getTarget());
          el.getAct().getEdges().add(objFlow);
        } else {
          final ObjectFlow objFlow_1 = this.umlFactory.createObjectFlow();
          objFlow_1.setName("Injected-control-flow");
          objFlow_1.setGuard(el.getCtrlflow().getGuard());
          objFlow_1.setWeight(el.getCtrlflow().getWeight());
          objFlow_1.setSource(el.getCtrlflow().getSource());
          objFlow_1.setTarget(el.getCtrlflow().getTarget());
          el.getAct().getEdges().add(objFlow_1);
        }
      }
    }
  }
  
  public void wbc1controlExecution() {
    final Collection<Wbc1controlSpecification.Match> matched = this.engine.<Wbc1controlSpecification.Matcher>getMatcher(this.faultSpecifications.getWbc1controlSpecification()).getAllMatches();
    for (final Wbc1controlSpecification.Match el : matched) {
      {
        Comment _c = el.getC();
        _c.setBody("INJECTED!");
        final ControlFlow ctrlFlow = this.umlFactory.createControlFlow();
        ctrlFlow.setName("ControlFlowInject");
        ctrlFlow.setSource(el.getCtrflow().getTarget());
        ctrlFlow.setTarget(el.getCtrflow().getSource());
        ctrlFlow.setGuard(el.getCtrflow().getGuard());
        ctrlFlow.setWeight(el.getCtrflow().getWeight());
        EcoreUtil.delete(el.getCtrflow());
        el.getAct().getEdges().add(ctrlFlow);
      }
    }
  }
  
  public void wbc1ObjectExecution() {
    final Collection<Wbc1objectSpecification.Match> matched = this.engine.<Wbc1objectSpecification.Matcher>getMatcher(this.faultSpecifications.getWbc1objectSpecification()).getAllMatches();
    for (final Wbc1objectSpecification.Match el : matched) {
      {
        Comment _c = el.getC();
        _c.setBody("INJECTED!");
        final ControlFlow ctrlFlow = this.umlFactory.createControlFlow();
        ctrlFlow.setName("ObjectFlowInject");
        ctrlFlow.setSource(el.getObjflow().getTarget());
        ctrlFlow.setTarget(el.getObjflow().getSource());
        ctrlFlow.setGuard(el.getObjflow().getGuard());
        ctrlFlow.setWeight(el.getObjflow().getWeight());
        EcoreUtil.delete(el.getObjflow());
        el.getAct().getEdges().add(ctrlFlow);
      }
    }
  }
  
  public void mifsdecisionExecution() {
    Collection<MifsdecisionSpecification.Match> matched = this.engine.<MifsdecisionSpecification.Matcher>getMatcher(this.faultSpecifications.getMifsdecisionSpecification()).getAllMatches();
    for (final MifsdecisionSpecification.Match el : matched) {
      {
        Comment _c = el.getC();
        _c.setBody("INJECTED!");
        final DecisionNode decision = this.umlFactory.createDecisionNode();
        decision.getOutgoings().addAll(el.getObjdecision().getOutgoings());
        final Function1<ActivityEdge, Boolean> _function = (ActivityEdge it) -> {
          return Boolean.valueOf(true);
        };
        ActivityEdge _findFirst = IterableExtensions.<ActivityEdge>findFirst(el.getObjdecision().getOutgoings(), _function);
        final Function1<ActivityEdge, Boolean> _function_1 = (ActivityEdge it) -> {
          return Boolean.valueOf(true);
        };
        _findFirst.setTarget(IterableExtensions.<ActivityEdge>findLast(el.getObjdecision().getOutgoings(), _function_1).getTarget());
        final Function1<ActivityEdge, Boolean> _function_2 = (ActivityEdge it) -> {
          return Boolean.valueOf(true);
        };
        ActivityEdge _findLast = IterableExtensions.<ActivityEdge>findLast(el.getObjdecision().getOutgoings(), _function_2);
        final Function1<ActivityEdge, Boolean> _function_3 = (ActivityEdge it) -> {
          return Boolean.valueOf(true);
        };
        _findLast.setTarget(IterableExtensions.<ActivityEdge>findFirst(decision.getOutgoings(), _function_3).getTarget());
      }
    }
  }
  
  public void mlpapinExecution() {
    final Collection<MlpapinSpecification.Match> matched = this.engine.<MlpapinSpecification.Matcher>getMatcher(this.faultSpecifications.getMlpapinSpecification()).getAllMatches();
    for (final MlpapinSpecification.Match el : matched) {
      {
        Comment _c = el.getC();
        _c.setBody("INJECTED!");
        EObject _eContainer = el.getPin().eContainer();
        final Action action = ((Action) _eContainer);
        EList<ActivityEdge> _incomings = el.getPin().getIncomings();
        boolean _tripleNotEquals = (_incomings != null);
        if (_tripleNotEquals) {
          EList<ActivityEdge> _incomings_1 = el.getPin().getIncomings();
          for (final ActivityEdge i : _incomings_1) {
          }
        }
        EList<ActivityEdge> _outgoings = el.getPin().getOutgoings();
        boolean _tripleNotEquals_1 = (_outgoings != null);
        if (_tripleNotEquals_1) {
          EList<ActivityEdge> _outgoings_1 = el.getPin().getOutgoings();
          for (final ActivityEdge i_1 : _outgoings_1) {
          }
        }
        EcoreUtil.delete(el.getPin());
      }
    }
  }
  
  /**
   * def mifsdecisionExecution() {
   * 
   * 
   * val matched = engine.getMatcher(mifsdecisionSpecification).allMatches;
   * 
   * // Iterando em todas as inst??ncias do pattern que foram encontradas...
   * 
   * for(el : matched) {
   * 
   * el.c.body = "INJECTED!"
   * val decision = umlFactory.createDecisionNode
   * var out = decision.outgoings
   * decision.outgoings.clear
   * decision.outgoings.addAll(out.reverse)
   * //el.act.
   * }
   * }
   */
  public void waldanodeExecution() {
    final Collection<WaldanodeSpecification.Match> matched = this.engine.<WaldanodeSpecification.Matcher>getMatcher(this.faultSpecifications.getWaldanodeSpecification()).getAllMatches();
    for (final WaldanodeSpecification.Match el : matched) {
      {
        Comment _c = el.getC();
        _c.setBody("INJECTED!");
        String text = el.getC().getBody();
        final String target = this.getTarget(text);
        final String source = this.getSource(text);
        final CallBehaviorAction behavior = this.umlFactory.createCallBehaviorAction();
        behavior.setName("InjectBehavior");
        behavior.setActivity(el.getAnode().getActivity());
        System.out.println(el.getAnode().getInputs());
        behavior.getInputs().addAll(el.getAnode().getInputs());
        CollectionExtensions.<InputPin>removeAll(el.getAnode().getInputs());
        final Function1<InputPin, Boolean> _function = (InputPin it) -> {
          return Boolean.valueOf(true);
        };
        el.getAnode().getInputs().add(IterableExtensions.<InputPin>findLast(behavior.getInputs(), _function));
        final Function1<InputPin, Boolean> _function_1 = (InputPin it) -> {
          return Boolean.valueOf(true);
        };
        el.getAnode().getInputs().add(IterableExtensions.<InputPin>findFirst(behavior.getInputs(), _function_1));
      }
    }
  }
  
  public void mvivvaluesaExecution() {
    final Collection<MvivSpecification.Match> matched = this.engine.<MvivSpecification.Matcher>getMatcher(this.faultSpecifications.getMvivSpecification()).getAllMatches();
    for (final MvivSpecification.Match el : matched) {
      {
        System.out.println(el.getValuesa().getValue());
        Comment _c = el.getC();
        _c.setBody("INJECTED!");
        EcoreUtil.delete(el.getValuesa());
        final ValueSpecificationAction valuesa = this.umlFactory.createValueSpecificationAction();
        LiteralInteger testes = this.umlFactory.createLiteralInteger();
        testes.setValue(10);
        valuesa.setValue(testes);
        valuesa.setName("NEWVALUESA");
        valuesa.getIncomings().addAll(el.getValuesa().getIncomings());
        valuesa.getOutgoings().addAll(el.getValuesa().getOutgoings());
        valuesa.setActivity(el.getValuesa().getActivity());
        System.out.println(valuesa.getValue());
        el.getAct().getOwnedNodes().addAll(el.getValuesa().getOutputs());
        el.getAct().getOwnedNodes().add(valuesa);
      }
    }
  }
  
  public void mfcnodeExecution() {
    final Collection<MfcnodeSpecification.Match> matched = this.engine.<MfcnodeSpecification.Matcher>getMatcher(this.faultSpecifications.getMfcnodeSpecification()).getAllMatches();
    for (final MfcnodeSpecification.Match el : matched) {
      {
        Comment _c = el.getC();
        _c.setBody("INJECTED!");
        EcoreUtil.delete(el.getNodes(), true);
      }
    }
  }
  
  public void wvavoguardExecution() {
    Collection<WvavoguardSpecification.Match> matched = this.engine.<WvavoguardSpecification.Matcher>getMatcher(this.faultSpecifications.getWvavoguardSpecification()).getAllMatches();
    for (final WvavoguardSpecification.Match el : matched) {
      {
        Comment _c = el.getC();
        _c.setBody("INJECTED!");
        LiteralInteger novovalor = this.umlFactory.createLiteralInteger();
        novovalor.setValue(1534);
        ObjectFlow _guard = el.getGuard();
        _guard.setGuard(novovalor);
        System.out.println(el.getGuard().getGuard().eContainer());
        System.out.println(el.getGuard().getGuard().getOwnedElements());
      }
    }
    final Collection<WvavcguardSpecification.Match> newmatched = this.engine.<WvavcguardSpecification.Matcher>getMatcher(this.faultSpecifications.getWvavcguardSpecification()).getAllMatches();
    for (final WvavcguardSpecification.Match el_1 : newmatched) {
      {
        Comment _c = el_1.getC();
        _c.setBody("INJECTED!");
        LiteralInteger novovalor = this.umlFactory.createLiteralInteger();
        novovalor.setValue(1534);
        ControlFlow _guard = el_1.getGuard();
        _guard.setGuard(novovalor);
        ControlFlow _guard_1 = el_1.getGuard();
        _guard_1.setGuard(el_1.getGuard().getGuard());
        System.out.println(el_1.getGuard().getGuard().eContainer());
        System.out.println(el_1.getGuard().getGuard().getType());
      }
    }
  }
  
  public void mlpaforkExecution() {
    Collection<MlpaforkSpecification.Match> matched = this.engine.<MlpaforkSpecification.Matcher>getMatcher(this.faultSpecifications.getMlpaforkSpecification()).getAllMatches();
    for (final MlpaforkSpecification.Match el : matched) {
      {
        Comment _c = el.getC();
        _c.setBody("INJECTED!");
        EList<ActivityEdge> _outgoings = el.getFork().getOutgoings();
        for (final ActivityEdge fo : _outgoings) {
          {
            final ObjectFlow objFlow = this.umlFactory.createObjectFlow();
            objFlow.setName("Injected-control-flow");
            objFlow.setGuard(fo.getGuard());
            objFlow.setWeight(fo.getWeight());
            final Function1<ActivityEdge, Boolean> _function = (ActivityEdge it) -> {
              return Boolean.valueOf(true);
            };
            objFlow.setSource(IterableExtensions.<ActivityEdge>findFirst(el.getFork().getIncomings(), _function).getSource());
            System.out.println(objFlow.getSource());
            objFlow.setTarget(fo.getTarget());
            el.getAct().getEdges().add(objFlow);
            final Function1<ActivityEdge, Boolean> _function_1 = (ActivityEdge it) -> {
              return Boolean.valueOf(true);
            };
            System.out.println(IterableExtensions.<ActivityEdge>findFirst(el.getFork().getIncomings(), _function_1).getSource());
          }
        }
        final Function1<ActivityEdge, Boolean> _function = (ActivityEdge it) -> {
          return Boolean.valueOf(true);
        };
        EcoreUtil.delete(IterableExtensions.<ActivityEdge>findFirst(el.getFork().getIncomings(), _function));
        EcoreUtil.delete(el.getFork());
      }
    }
  }
  
  public void mvivobjectExecution() {
    Collection<MvivobjectSpecification.Match> matched = this.engine.<MvivobjectSpecification.Matcher>getMatcher(this.faultSpecifications.getMvivobjectSpecification()).getAllMatches();
    for (final MvivobjectSpecification.Match el : matched) {
      final Function1<InputPin, Boolean> _function = (InputPin it) -> {
        return Boolean.valueOf(true);
      };
      EcoreUtil.delete(IterableExtensions.<InputPin>findFirst(el.getAsfva().getInputs(), _function));
    }
  }
  
  public void wsutnodeExecution() {
    Collection<WsutnodeSpecification.Match> matched = this.engine.<WsutnodeSpecification.Matcher>getMatcher(this.faultSpecifications.getWsutnodeSpecification()).getAllMatches();
    for (final WsutnodeSpecification.Match el : matched) {
      {
        LiteralInteger newint = this.umlFactory.createLiteralInteger();
        LiteralReal newreal = this.umlFactory.createLiteralReal();
        LiteralString newstring = this.umlFactory.createLiteralString();
        LiteralBoolean newbool = this.umlFactory.createLiteralBoolean();
        LiteralUnlimitedNatural newunnatu = this.umlFactory.createLiteralUnlimitedNatural();
        ValueSpecification _value = el.getNodes().getValue();
        Type _type = newint.getType();
        boolean _equals = Objects.equal(_value, _type);
        if (_equals) {
          ValueSpecification _value_1 = el.getNodes().getValue();
          _value_1.setType(newreal.getType());
        }
        ValueSpecification _value_2 = el.getNodes().getValue();
        Type _type_1 = newreal.getType();
        boolean _equals_1 = Objects.equal(_value_2, _type_1);
        if (_equals_1) {
          ValueSpecification _value_3 = el.getNodes().getValue();
          _value_3.setType(newstring.getType());
        }
        ValueSpecification _value_4 = el.getNodes().getValue();
        Type _type_2 = newstring.getType();
        boolean _equals_2 = Objects.equal(_value_4, _type_2);
        if (_equals_2) {
          ValueSpecification _value_5 = el.getNodes().getValue();
          _value_5.setType(newbool.getType());
        }
        ValueSpecification _value_6 = el.getNodes().getValue();
        Type _type_3 = newbool.getType();
        boolean _equals_3 = Objects.equal(_value_6, _type_3);
        if (_equals_3) {
          ValueSpecification _value_7 = el.getNodes().getValue();
          _value_7.setType(newunnatu.getType());
        }
        ValueSpecification _value_8 = el.getNodes().getValue();
        Type _type_4 = newunnatu.getType();
        boolean _equals_4 = Objects.equal(_value_8, _type_4);
        if (_equals_4) {
          ValueSpecification _value_9 = el.getNodes().getValue();
          _value_9.setType(newint.getType());
        }
      }
    }
  }
  
  public void wsutpinExecution() {
    Collection<WsutpinSpecification.Match> matched = this.engine.<WsutpinSpecification.Matcher>getMatcher(this.faultSpecifications.getWsutpinSpecification()).getAllMatches();
    for (final WsutpinSpecification.Match el : matched) {
      {
        LiteralInteger newint = this.umlFactory.createLiteralInteger();
        LiteralReal newreal = this.umlFactory.createLiteralReal();
        LiteralString newstring = this.umlFactory.createLiteralString();
        LiteralBoolean newbool = this.umlFactory.createLiteralBoolean();
        LiteralUnlimitedNatural newunnatu = this.umlFactory.createLiteralUnlimitedNatural();
        Type _type = el.getPin().getType();
        Type _type_1 = newint.getType();
        boolean _equals = Objects.equal(_type, _type_1);
        if (_equals) {
          Pin _pin = el.getPin();
          _pin.setType(newreal.getType());
        }
        Type _type_2 = el.getPin().getType();
        Type _type_3 = newreal.getType();
        boolean _equals_1 = Objects.equal(_type_2, _type_3);
        if (_equals_1) {
          Pin _pin_1 = el.getPin();
          _pin_1.setType(newstring.getType());
        }
        Type _type_4 = el.getPin().getType();
        Type _type_5 = newstring.getType();
        boolean _equals_2 = Objects.equal(_type_4, _type_5);
        if (_equals_2) {
          Pin _pin_2 = el.getPin();
          _pin_2.setType(newbool.getType());
        }
        Type _type_6 = el.getPin().getType();
        Type _type_7 = newbool.getType();
        boolean _equals_3 = Objects.equal(_type_6, _type_7);
        if (_equals_3) {
          Pin _pin_3 = el.getPin();
          _pin_3.setType(newunnatu.getType());
        }
        Type _type_8 = el.getPin().getType();
        Type _type_9 = newunnatu.getType();
        boolean _equals_4 = Objects.equal(_type_8, _type_9);
        if (_equals_4) {
          Pin _pin_4 = el.getPin();
          _pin_4.setType(newint.getType());
        }
      }
    }
  }
  
  public void executeall(final InjectorTransformation transformation) {
    transformation.wbc1objflowExecution();
  }
  
  private String getSource(final String text) {
    String source = null;
    int _indexOf = text.indexOf("source:");
    boolean _notEquals = (_indexOf != (-1));
    if (_notEquals) {
      int _indexOf_1 = text.indexOf("[");
      boolean _notEquals_1 = (_indexOf_1 != (-1));
      if (_notEquals_1) {
        int _indexOf_2 = text.indexOf("source:");
        int _plus = (_indexOf_2 + 7);
        int _indexOf_3 = text.indexOf("source:");
        int _plus_1 = (_indexOf_3 + 7);
        source = text.substring(_plus, text.indexOf(",", _plus_1)).trim();
        source = source.replace("[", "").replace("]", "");
        return source;
      }
      int _indexOf_4 = text.indexOf("source:");
      int _plus_2 = (_indexOf_4 + 7);
      int _indexOf_5 = text.indexOf("source:");
      int _plus_3 = (_indexOf_5 + 7);
      source = text.substring(_plus_2, text.indexOf(",", _plus_3)).trim();
      return source;
    } else {
      return null;
    }
  }
  
  private String getTarget(final String text) {
    String target = null;
    int _indexOf = text.indexOf("target:");
    boolean _notEquals = (_indexOf != (-1));
    if (_notEquals) {
      int _indexOf_1 = text.indexOf("[");
      boolean _notEquals_1 = (_indexOf_1 != (-1));
      if (_notEquals_1) {
        int _indexOf_2 = text.indexOf("[");
        int _plus = (_indexOf_2 + 1);
        int _indexOf_3 = text.indexOf("[");
        int _plus_1 = (_indexOf_3 + 1);
        target = text.substring(_plus, text.indexOf(".", _plus_1)).trim();
        target = target.replace("[", "").replace("]", "");
        return target;
      }
      int _indexOf_4 = text.indexOf("target:");
      int _plus_2 = (_indexOf_4 + 7);
      int _indexOf_5 = text.indexOf("target:");
      int _plus_3 = (_indexOf_5 + 7);
      target = text.substring(_plus_2, text.indexOf(".", _plus_3)).trim();
      return target;
    } else {
      return null;
    }
  }
  
  private BatchTransformationStatements createTransformation() {
    BatchTransformationStatements _xblockexpression = null;
    {
      SimpleModelManipulations _simpleModelManipulations = new SimpleModelManipulations(this.engine);
      this.manipulation = _simpleModelManipulations;
      this.transformation = BatchTransformation.forEngine(this.engine).build();
      _xblockexpression = this.statements = this.transformation.getTransformationStatements();
    }
    return _xblockexpression;
  }
  
  /**
   * def protected static PrimitiveType importPrimitiveType(org.eclipse.uml2.uml.Package package_, String name) {
   * 
   * org.eclipse.uml2.uml.Package umlLibrary = (org.eclipse.uml2.uml.Package) load(URI.createURI(UMLResource.UML_PRIMITIVE_TYPES_LIBRARY_URI));
   * PrimitiveType primitiveType = (PrimitiveType) umlLibrary.getOwnedType(name);
   * package_.createElementImport(primitiveType);
   * out("Primitive type '%s' imported.", primitiveType.getQualifiedName());
   * return primitiveType;
   * }
   */
  public void dispose() {
    boolean _notEquals = (!Objects.equal(this.transformation, null));
    if (_notEquals) {
      this.transformation.getRuleEngine().dispose();
    }
    this.transformation = null;
    return;
  }
}
