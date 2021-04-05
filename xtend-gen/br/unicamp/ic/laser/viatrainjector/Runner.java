package br.unicamp.ic.laser.viatrainjector;

import br.unicamp.ic.laser.viatrainjector.InjectorTransformation;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.ExecutionException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.profile.standard.StandardPackage;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.emf.EMFScope;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.eclipse.xtext.xbase.lib.Exceptions;

@SuppressWarnings("all")
public class Runner {
  /**
   * Esse é o método main de onde a transformação é executada.
   * Sugiro separar em submétodos, parametereizar para receber como entrada
   * (no "args") o nome no modelo, etc.
   */
  public static void main(final String[] args) throws ExecutionException {
    final UMLPackage p = UMLPackage.eINSTANCE;
    final UMLFactory factory = p.getUMLFactory();
    EPackage.Registry.INSTANCE.put(p.getNsURI(), p);
    final StandardPackage pStandard = StandardPackage.eINSTANCE;
    EPackage.Registry.INSTANCE.put(pStandard.getNsURI(), pStandard);
    final ResourceSetImpl rs = new ResourceSetImpl();
    rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("uml", UMLResource.Factory.INSTANCE);
    final File fOriginal = new File("C:/Users/gwlop/eclipse-workspace-Viatra/testegeral/testegeral.uml");
    final File fCopy = new File("C:/Users/gwlop/eclipse-workspace-2019-09/model2Inject-main/ModelosTeste/ModelosTeste-new-viatra.uml");
    try {
      Files.deleteIfExists(fCopy.toPath());
      Files.copy(fOriginal.toPath(), fCopy.toPath());
    } catch (final Throwable _t) {
      if (_t instanceof IOException) {
        final IOException e1 = (IOException)_t;
        e1.printStackTrace();
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
    final Resource res = rs.getResource(URI.createFileURI(fCopy.getAbsolutePath()), true);
    ViatraQueryEngine engine = null;
    InjectorTransformation transformation = null;
    if ((engine == null)) {
      try {
        EMFScope _eMFScope = new EMFScope(rs);
        engine = ViatraQueryEngine.on(_eMFScope);
        InjectorTransformation _injectorTransformation = new InjectorTransformation(res);
        transformation = _injectorTransformation;
      } catch (final Throwable _t) {
        if (_t instanceof ViatraQueryException) {
          final ViatraQueryException e = (ViatraQueryException)_t;
          String _message = e.getMessage();
          throw new ExecutionException(_message, e);
        } else {
          throw Exceptions.sneakyThrow(_t);
        }
      }
    }
    transformation.executeall(transformation);
    try {
      EcoreUtil.resolveAll(res);
      res.save(null);
    } catch (final Throwable _t) {
      if (_t instanceof IOException) {
        final IOException e1 = (IOException)_t;
        e1.printStackTrace();
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
  }
}
