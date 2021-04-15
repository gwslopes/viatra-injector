package br.unicamp.ic.laser.viatrainjector

import java.util.concurrent.ExecutionException
import org.eclipse.uml2.uml.UMLPackage
import org.eclipse.emf.ecore.EPackage
import org.eclipse.uml2.uml.profile.standard.StandardPackage
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl
import java.io.File
import java.nio.file.Files
import java.io.IOException
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.emf.common.util.URI
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine
import org.eclipse.viatra.query.runtime.emf.EMFScope
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException
import org.eclipse.uml2.uml.internal.resource.UMLResourceFactoryImpl
import org.eclipse.uml2.uml.resource.UMLResource

class Runner {
	
	/***
	 * Esse é o método main de onde a transformação é executada.
	 * Sugiro separar em submétodos, parametereizar para receber como entrada
	 * (no "args") o nome no modelo, etc.
	 ***/
	def static void main(String[] args) throws ExecutionException {
		
		// Do standard registration for UML and StandardProfile
		val p = UMLPackage.eINSTANCE;
		val factory = p.getUMLFactory();
		EPackage.Registry.INSTANCE.put(p.getNsURI(), p);

		val pStandard = StandardPackage.eINSTANCE;
		EPackage.Registry.INSTANCE.put(pStandard.getNsURI(), pStandard);
		
		// Register .uml extension with UML metamodel
		val rs = new ResourceSetImpl();
		rs.getResourceFactoryRegistry()
			.getExtensionToFactoryMap()
			.put("uml", UMLResource.Factory.INSTANCE);
		
		// Open source file and copy to a new one
		//val fOriginal = new File("C:/Users/gwlop/eclipse-workspace-2019-09/model2Inject-main/ModelosTeste/ModelosTeste.uml");		
		val fOriginal = new File("C:/Users/gwlop/eclipse-workspace-Viatra/testegeral/testegeral.uml");
		//val fOriginal = new File("C:/Users/gwlop/eclipse-workspace/Langmuir-Injected/Langmuir/moka.langmuir.uml");
		val fCopy = new File("C:/Users/gwlop/eclipse-workspace-2019-09/model2Inject-main/ModelosTeste/ModelosTeste-new-viatra.uml");
		try {
			Files.deleteIfExists(fCopy.toPath());
			Files.copy(fOriginal.toPath(), fCopy.toPath());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// Open copied file as resource
		val res = rs.getResource(URI.createFileURI(fCopy.getAbsolutePath()), true);

		// Initialize Viatra engine and transformation module
		var ViatraQueryEngine engine;
		// Transformation module. Possibly, more than one...
		var InjectorTransformation transformation;
		//var OtherTransformation otherTransformation;
		if (engine === null) {
			try {
				engine = ViatraQueryEngine.on(new EMFScope(rs));
				// Initialize the transformation module. Possibly, more than one...
				transformation = new InjectorTransformation(res)
				//otherTransformation = new OtherTransformation(res)

			} catch (ViatraQueryException e) {
				throw new ExecutionException(e.getMessage(), e);
			}
		}
		//transformation.execute6
		//transformation.execute7
		//otherTransformation.execute
		transformation.executeall(transformation)
		
		// Save the resource (that is, save the file from which it was opened)
		try {
			EcoreUtil.resolveAll(res);
			res.save(null);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		};
	}
}