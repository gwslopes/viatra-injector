package br.unicamp.ic.laser.viatrainjector

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine
import org.eclipse.viatra.query.runtime.emf.EMFScope
import org.eclipse.viatra.transformation.runtime.emf.modelmanipulation.IModelManipulations
import org.eclipse.viatra.transformation.runtime.emf.modelmanipulation.SimpleModelManipulations
import org.eclipse.viatra.transformation.runtime.emf.rules.batch.BatchTransformationRuleFactory
import org.eclipse.viatra.transformation.runtime.emf.transformation.batch.BatchTransformation
import org.eclipse.viatra.transformation.runtime.emf.transformation.batch.BatchTransformationStatements
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.uml2.uml.UMLPackage
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.uml2.uml.UMLFactory
import java.lang.reflect.Type
import java.util.StringTokenizer
import org.eclipse.uml2.uml.Action
import org.eclipse.uml2.uml.InputPin
import org.eclipse.uml2.uml.DecisionNode
import org.eclipse.uml2.uml.PrimitiveType
import org.eclipse.uml2.uml.resource.UMLResource
import org.eclipse.emf.common.util.URI
import org.eclipse.uml2.uml.LiteralInteger
import org.eclipse.uml2.uml.LiteralReal
import org.eclipse.uml2.uml.LiteralString
import org.eclipse.uml2.uml.LiteralBoolean
import org.eclipse.uml2.uml.LiteralUnlimitedNatural

class InjectorTransformation {

	/** VIATRA Query Pattern group **/
	val extension Patterns faultSpecifications = Patterns.instance

	/** EMF metamodels **/
	val extension UMLPackage umlPackage = UMLPackage.eINSTANCE
	val extension UMLFactory umlFactory = umlPackage.UMLFactory

    /* Transformation-related extensions */
    extension BatchTransformation transformation
    extension BatchTransformationStatements statements
    
    /* Transformation rule-related extensions */
    extension BatchTransformationRuleFactory = new BatchTransformationRuleFactory
    extension IModelManipulations manipulation

    protected ViatraQueryEngine engine
    protected Resource resource
    //protected org.eclipse.uml2.uml.Package package_
    //protected BatchTransformationRule<?,?> exampleRule
    
    new(Resource resource) {
        this.resource = resource
        // Create EMF scope and EMF IncQuery engine based on the resource
        val scope = new EMFScope(resource)
        engine = ViatraQueryEngine.on(scope);
        
        createTransformation

    }

	/** 
	 * Aqui é onde a transformação é de fato implementada
	 * Nada impede você criar mais métodos com diferentes transformações,
	 * adicionar parâmetros ou chamar otras classes 
	 * * */
    //Transforma Object Flow em Control Flow
    def mcaobjflowExecution() {

		// executo o pattern "mcaSpecification" (mesmo nome em Patterns.vql)
		// e guardo o resultado na variável "matched"
		val matched = engine.getMatcher(mcaobjflowSpecification).allMatches;

		// Iterando em todas as instâncias do pattern que foram encontradas...
		for(el : matched) {
			
			// Cada elemento "el" é uma tupla, tendo como propriedade as variáveis
			// listadas na especificação do pattern! Assim pode acessar os elementos diretamente
			
			// altera o "body" do Comment
			//
			
			var String text = el.c.body
			System.out.println(el.c.body)
			el.c.body = "INJECTED!" 
			
			val String target = getTarget(text)
			val String source = getSource(text)
			System.out.println(source)
			System.out.println(target)
			
			// Apaga o ObjectFlow
			// Para eliminar elementos de forma consistente tem que usar essa função
			EcoreUtil.delete(el.objflow)
			
			// Criando um novo ControlFlow.
			// Para criar elementos (objetos) de forma consistente tem que usar a Factory
			// Existe um método createXXX para cada metaclasse UML
			val ctrlFlow = umlFactory.createControlFlow
			ctrlFlow.name = "Injected-control-flow"
			ctrlFlow.guard = el.objflow.guard
			ctrlFlow.weight = el.objflow.weight
			ctrlFlow.source = el.objflow.source
			ctrlFlow.target = el.objflow.target
			
			// Adiciona o novo ControlFlow entre os elementos na propriedade "edge" de Activity
			// Para ver onde cada elemnto fica pode se ajudar com o MoDisco Model Browser" ou o metamodelo UML
			el.act.edges.add(ctrlFlow)
		}
    }

	//Altera um Control Flow invertendo o target para source
	def mcacontrolExecution() {


		val matched = engine.getMatcher(mcacontrolSpecification).allMatches;

		// Iterando em todas as instâncias do pattern que foram encontradas...
		for(el : matched) {
			
			el.c.body = "INJECTED!"
			
			//EcoreUtil.delete(el.objflow)

			val ctrlFlow = umlFactory.createControlFlow
			ctrlFlow.name = "NewControlFlow"
			ctrlFlow.source = el.ctrflow.target
			ctrlFlow.target = el.ctrflow.source
			ctrlFlow.guard = el.ctrflow.guard
			ctrlFlow.weight = el.ctrflow.weight
			//ctrlFlow.source = el.objflow.source
			//ctrlFlow.target = el.objflow.target
			
			EcoreUtil.delete(el.ctrflow)
			
			el.act.edges.add(ctrlFlow)
		}
    }

	//Alterar os Alvos da Decisão
	def mifsdecisionExecution() {


		var matched = engine.getMatcher(mifsdecisionSpecification).allMatches;

		// Iterando em todas as instâncias do pattern que foram encontradas...
		for(el : matched) {
			
			el.c.body = "INJECTED!"
			
			//EcoreUtil.delete(el.guard, true)
			el.objdecision.outgoings.findFirst[true].target = el.objdecision.outgoings.findLast[true].target
			//el.objdecision.outgoings.
			//el.act.edges.add(ctrlFlow)
			//System.out.println(el.guard.guard.eContainer)
			//System.out.println(el.guard.guard.ownedElements)
		}
		
    }  
	
	//Alterar os Pins
	def mlpapinExecution() {

		
		val matched = engine.getMatcher(mlpapinSpecification).allMatches;
		for(el : matched) {
			
			
			/*Identificar o Pin que será retirado
			Identificar o Objeto que guarda o Pin
			Identificar se há objetos que tenham como target o pin de entrada ou como source o pin 
			de saida
			(caso tenha - pin Entrada)
			Identificar outro pin no Objeto
			Alterar o flow que tinha como target o objeto que apontava para o pin que será retirado 
			para o outro pin
			(caso tenha - Pin Saida)
			Identificar outro pin no Objeto
			Alterar o flow que tinha como source o objeto que apontava para o pin que será retirado 
			para o outro pin
			Retirar o Pin*/
			
			el.c.body = "INJECTED!"
			val Action action = el.pin.eContainer as Action
			
			if (el.pin.incomings !== null){
				for (i : el.pin.incomings){
					
				}
			}
			if (el.pin.outgoings !== null){
				for (i : el.pin.outgoings){
					
				}
			}
			
			EcoreUtil.delete(el.pin)
			//objFlow.source = el.fork.incomings.findFirst[true].source
			
			/*val out = el.pin.outgoings 
			val in = el.pin.incomings
			val Action action = el.pin.eContainer as Action
			for (i : in){
				
				var boolean found = false 
				while (found == false){
					action.inputs.
					if (j != el.pin){
						j.incomings.addAll(el.pin.incomings)
					}
					found = true
				}
				el.pin.eContainer
			}
			var InputPin myPin;
			for( p : action.inputs) {
    			if( p != el.pin) {
    	    		myPin = p
    			}
			}

			if(myPin != null) {
    			// achei o pin diferente do outro!
			}*/
		
		}
    }
	
	// Inutilizado
	/*def mifsdecisionExecution() {


		val matched = engine.getMatcher(mifsdecisionSpecification).allMatches;

		// Iterando em todas as instâncias do pattern que foram encontradas...
		
		for(el : matched) {
			
			el.c.body = "INJECTED!"
			val decision = umlFactory.createDecisionNode
			var out = decision.outgoings
			decision.outgoings.clear
			decision.outgoings.addAll(out.reverse)
			//el.act.
		}
    }*/

	//Behavior WALD
	def waldanodeExecution() {


		val matched = engine.getMatcher(waldanodeSpecification).allMatches;

		// Iterando em todas as instâncias do pattern que foram encontradas...

		
		for(el : matched) {
			
			el.c.body = "INJECTED!"
			val behavior = umlFactory.createCallBehaviorAction
			//var in = behavior.incomings
			//behavior.incomings.clear
			//behavior.incomings.addAll(in.reverse)
			behavior.name = "NewBehavior"
			behavior.activity = el.anode.activity
			behavior.ownedElements.addAll(el.anode.ownedElements)
			//behavior.ownedElements.findFirst[inputPin ] //[](0).add(el.anode.ownedElements.indexOf(1))
			
			EcoreUtil.delete(el.anode)
			el.act.ownedNodes.add(behavior)
		}
    }

	//Transforma Value Specification Action
	def mvivvaluesaExecution() {


		val matched = engine.getMatcher(mvivSpecification).allMatches;
		for(el : matched) {
			
 			System.out.println(el.valuesa.value)
			el.c.body = "INJECTED!"
			EcoreUtil.delete(el.valuesa)	
			
			val valuesa = umlFactory.createValueSpecificationAction
			var LiteralInteger testes = umlFactory.createLiteralInteger
			testes.value = 10
			//val valuesas = umlFactory.createValue
			//var org.eclipse.uml2.uml.LiteralString valueLS = UMLFactory.eINSTANCE.createLiteralString();
			//valuesa.value.type = el.valuesa.ownedElements.lastIndexOf()
			//el.valuesa.value.name 
			valuesa.value = testes
			valuesa.name = "NEWVALUESA"
			valuesa.incomings.addAll(el.valuesa.incomings)
			valuesa.outgoings.addAll(el.valuesa.outgoings)
			valuesa.activity = el.valuesa.activity
			//valuesa.outputs.contains(outputPin output -> "result")
			//System.out.println(el.valuesa.value)
			System.out.println(valuesa.value)
			//valuesa.ownedElements.addAll(el.valuesa.outputs)
					
			//var nvalue = valuesa.value
			//var IsType = value
			//nvalue = IsType
			//("Primitive type '%s' imported.", primitiveType.getQualifiedName());
			//el.act.getNodes().add(valuesa)
			el.act.ownedNodes.addAll(el.valuesa.outputs)
			el.act.ownedNodes.add(valuesa)
			
		}
    }

 	//Deleta um Node
	def mfcnodeExecution() {


		val matched = engine.getMatcher(mfcnodeSpecification).allMatches;

		// Iterando em todas as instâncias do pattern que foram encontradas...
		for(el : matched) {
			
			el.c.body = "INJECTED!"
			
			//EcoreUtil.delete(el.objflow)
			//if (el.nodes.ownedElements !== null){
			//	el.act.ownedNodes.addAll(el.nodes.ownedElements)	
			//}
			
			//val ctrlFlow = umlFactory.createControlFlow
			//ctrlFlow.name = "This-is-the-new-control-flow"
			
			EcoreUtil.delete(el.nodes, true)
			
			//el.act.edges.add(ctrlFlow)
		}
    }   

	//Altera valor de guard
	def wvavoguardExecution() {


		var matched = engine.getMatcher(wvavoguardSpecification).allMatches;

		// Iterando em todas as instâncias do pattern que foram encontradas...
		for(el : matched) {
			
			el.c.body = "INJECTED!"
			//val valuesa = umlFactory.createValueSpecificationAction
			var LiteralInteger novovalor = umlFactory.createLiteralInteger
			novovalor.value = 1534
			//EcoreUtil.delete(el.guard, true)
			el.guard.guard = novovalor
			//el.act.edges.add(ctrlFlow)
			System.out.println(el.guard.guard.eContainer)
			System.out.println(el.guard.guard.ownedElements)
		}
		
		val newmatched = engine.getMatcher(wvavcguardSpecification).allMatches;
		// Iterando em todas as instâncias do pattern que foram encontradas...
		for(el : newmatched) {
			
			el.c.body = "INJECTED!"
			var LiteralInteger novovalor = umlFactory.createLiteralInteger
			novovalor.value = 1534
			//EcoreUtil.delete(el.guard, true)
			el.guard.guard = novovalor
			//EcoreUtil.delete(el.guard, true)
			//el.guard.guard = el.guard.guard
			el.guard.guard = el.guard.guard
			System.out.println(el.guard.guard.eContainer)
			System.out.println(el.guard.guard.type)
			//el.act.edges.add(ctrlFlow)
		}
		
    }  

	//Retirar Fork
	def mlpaforkExecution() {


		var matched = engine.getMatcher(mlpaforkSpecification).allMatches;

		// Iterando em todas as instâncias do pattern que foram encontradas...
		for(el : matched) {
			
			el.c.body = "INJECTED!"
			/*	Identificar o fork que será retirado
				identificar o objeto flow que tenha como target o fork
				Identificar os objeto que tenha como source um flow do fork
				Alterar de cada um dos objetos que tenham como source o flow do fork para o source 
				do objeto flow 
				Apagar o flow do objeto para o fork
				Apagar o fork
			 */
			//EcoreUtil.delete(el.guard, true)
			
			for(fo : el.fork.outgoings){
				val objFlow = umlFactory.createObjectFlow
				objFlow.name = "Injected-control-flow"
				objFlow.guard = fo.guard//el.objflow.guard
				objFlow.weight = fo.weight
				objFlow.source = el.fork.incomings.findFirst[true].source
				System.out.println(objFlow.source)
				objFlow.target = fo.target
				el.act.edges.add(objFlow)
				System.out.println(el.fork.incomings.findFirst[true].source)
			}
			
			EcoreUtil.delete(el.fork.incomings.findFirst[true])	
			EcoreUtil.delete(el.fork)
			//el.act.edges.add(ctrlFlow)
			//System.out.println(el.guard.guard.eContainer)
			//System.out.println(el.guard.guard.ownedElements)
		}
		
    }  
	
	//Retirar referencia ao objeto
	def mvivobjectExecution(){
		
		var matched = engine.getMatcher(mvivobjectSpecification).allMatches;

		// Iterando em todas as instâncias do pattern que foram encontradas...
		for(el : matched) {
			//el.asfva.input.findFirst[true].source
			EcoreUtil.delete(el.asfva.inputs.findFirst[true])
		}
	}
	
	//Alterar tipo do Nó 
	def wsutnodeExecution(){
		var matched = engine.getMatcher(wsutnodeSpecification).allMatches;

		for(el : matched) {
			//el.asfva.input.findFirst[true].source
			//EcoreUtil.delete(el.asfva.inputs.findFirst[true])
			var LiteralInteger newint = umlFactory.createLiteralInteger
			var LiteralReal newreal = umlFactory.createLiteralReal
			var LiteralString newstring = umlFactory.createLiteralString
			var LiteralBoolean newbool = umlFactory.createLiteralBoolean
			var LiteralUnlimitedNatural newunnatu = umlFactory.createLiteralUnlimitedNatural
			
			/*val types = newArrayList(newint, newreal, newstring, newbool, newunnatu)
			
			for (i : types){
				if (el.pin.type == i.type){
					el.pin.type = types.filter[el.pin.type != types.type]
				}
			}*/
			
			if (el.nodes.value == newint.type){
				el.nodes.value.type = newreal.type
			}
			if (el.nodes.value == newreal.type){
				el.nodes.value.type = newstring.type
			}
			if (el.nodes.value == newstring.type){
				el.nodes.value.type = newbool.type
			}
			if (el.nodes.value == newbool.type){
				el.nodes.value.type = newunnatu.type
			}
			if (el.nodes.value == newunnatu.type){
				el.nodes.value.type = newint.type
			}
		}
	}
	
	//Alterar tipo do Pin
	def wsutpinExecution(){
		var matched = engine.getMatcher(wsutpinSpecification).allMatches;

		// Iterando em todas as instâncias do pattern que foram encontradas...
		for(el : matched) {
			var LiteralInteger newint = umlFactory.createLiteralInteger
			var LiteralReal newreal = umlFactory.createLiteralReal
			var LiteralString newstring = umlFactory.createLiteralString
			var LiteralBoolean newbool = umlFactory.createLiteralBoolean
			var LiteralUnlimitedNatural newunnatu = umlFactory.createLiteralUnlimitedNatural
			
			/*val types = newArrayList(newint, newreal, newstring, newbool, newunnatu)
			
			for (i : types){
				if (el.pin.type == i.type){
					el.pin.type = types.filter[el.pin.type != types.type]
				}
			}*/
			
			if (el.pin.type == newint.type){
				el.pin.type = newreal.type
			}
			if (el.pin.type == newreal.type){
				el.pin.type = newstring.type
			}
			if (el.pin.type == newstring.type){
				el.pin.type = newbool.type
			}
			if (el.pin.type == newbool.type){
				el.pin.type = newunnatu.type
			}
			if (el.pin.type == newunnatu.type){
				el.pin.type = newint.type
			}
			
		}
	}
	
	def executeall(InjectorTransformation transformation){
		transformation.mcaobjflowExecution //Done Object Flow MCA
		//transformation.mlpapinExecution //Pin - Alterar Pins -- Refazer
		//transformation.execute3 // Inutilizado -- Refiz no 9
		//transformation.waldanodeExecution //Behavior
		//transformation.mvivvaluesaExecution //Done - Value Specification Action
		//transformation.mcacontrolExecution //Done Control Flow MCA
		//transformation.mfcnodeExecution //Done Node MFC
		//transformation.wvavoguardExecution //done Guard WVAV -- Alterar valor de propriedade
		//transformation.mifsdecisionExecution //done Decision Node MIFS
		//transformation.mlpaforkExecution //done Fork
		//transformation.wsutnodeExecution //Done Node - Alterar tipo atribuido
		//transformation.mvivobjectExecution//Done Node - retirar referencia a objetos
		//transformation.wsutpinExecution //Done Pin - Alterar tipo do pin
	}
	
	private def String getSource(String text){
		
		var String source
		if (text.indexOf("source:") != -1) {
				source = text.substring(text.indexOf("source:")+7, text.indexOf(",", text.indexOf("source:")+7)).trim
				//System.out.println(source)
				return source
		}
		else{
			return null
		}
		
	}
	
	private def getTarget(String text){
		
		var String source
		if (text.indexOf("target:") != -1) {
				source = text.substring(text.indexOf("target:")+7, text.indexOf(".", text.indexOf("target:")+7)).trim
				//System.out.println(source)
				return source
		}
		else{
			return null
		}
		
	}
	
    private def createTransformation() {
        //Create VIATRA model manipulations
        this.manipulation = new SimpleModelManipulations(engine)
        //Create VIATRA Batch transformation
        transformation = BatchTransformation.forEngine(engine)
        .build
        //Initialize batch transformation statements
        statements = transformation.transformationStatements
    }
    
    /*def protected static PrimitiveType importPrimitiveType(org.eclipse.uml2.uml.Package package_, String name) {
		
		org.eclipse.uml2.uml.Package umlLibrary = (org.eclipse.uml2.uml.Package) load(URI.createURI(UMLResource.UML_PRIMITIVE_TYPES_LIBRARY_URI));
		PrimitiveType primitiveType = (PrimitiveType) umlLibrary.getOwnedType(name);
		package_.createElementImport(primitiveType);
		out("Primitive type '%s' imported.", primitiveType.getQualifiedName());
		return primitiveType;
	}*/

    

    def dispose() {
        if (transformation != null) {
            transformation.ruleEngine.dispose
        }
        transformation = null
        return
    }
}
