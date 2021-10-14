# Typechecking Java Protocols with [St]Mungo


## A. Laura Voinea, Ornela Dardha, and Simon J. Gay

**Summary**

_Abstract_

This is a tutorial paper on [St]Mungo, a toolchain based on multiparty session types and their connection to typestates for safe distributed programming in Java language. The StMungo (Scribble-to-Mungo) tool is a bridge between multiparty session types and typestates. StMungo translates a communication protocol, namely a sequence of sends and receives of messages, given as a multiparty session type in the Scribble language, into a typestate specification and a Java API skeleton. The generated API skeleton is then further extended with the necessary logic, and finally typechecked by Mungo. The Mungo tool extends Java with (optional) typestate specifications. A typestate is a state machine specifying a Java object protocol, namely the permitted sequence of method calls of that object. Mungo statically typechecks that method calls follow the object’s protocol, as defined by its typestate specification. Finally, if no errors are reported, the code is compiled with javac and run as standard Java code. In this tutorial paper we give an overview of the stages of the [St]Mungo toolchain, starting from Scribble communication protocols, translating to Java classes with typestates, and finally to typechecking method calls with Mungo. We illustrate the [St]Mungo toolchain via a real-world case study, the HTTP client-server request-response protocol over TCP. During the tutorial session, we will apply [St]Mungo to a range of examples having increasing complexity, with HTTP being one of them.

_StMungo_

The StMungo tool is a java-based transpiler implemented using ANTLR. It acts as a bridge between MPST and typestate specifications. Scribble specification language is an implementation of multiparty session types. You start with a global protocol which is verified and then projected onto local protocols. The rest of this section gives an example of the HTTP protocol as written in Scribble and a local client protocol. StMungo then translates the local protocol under the principle that each role in the session following its local protocol, can be abstracted as a Java class following its typestate specification. Typestate is a state machine defining the object’s protocol based on its permitted sequence of method calls.

_Mungo_

The Mungo tool is implemented in Java using the ExtendJ framework, a meta-compiler based on reference attribute grammars. The typestate file is saved in a separate file and is attached to a Java class using annotations. It is important that the typestate definition can capture the external choice presented by S in the client. This is captured by the enum definition which is the return type of the function. Every choice needs an enum type. 

**Context**

This paper is based around using multiparty session types and their connection to typestate for safe distributed programming in Java. APIs and the boom of the API economy due to internal functions being visible to more programmers. APIs capture constraints on the parameter types and the return types but not the sequence of method calls themselves. Communication protocols persistent in APIs have certain operations that must be performed in order but the type system is not expressive enough to capture this. Can think of _behavioural APIs _that specify sequence-related properties involving multiple method calls. The transfer of session type concepts into object-oriented languages required embedding them in a more general setting that support typestates. This is due to it being natural to define methods that each perform several communication steps, and then the session type gives rise to different, though related, sequencing constraints on the methods. 

**Related Work**
* BETTY project 
    * Foundations of behavioural types [33]
    * Behavioural types and security [13]
    * Behavioral types in programing languages [5]
* Sing# [20] creates a OS based on message passing, incorporating typestate-like contracts
* Plaid [3,43] proposes a new paradigm where classes consist of states instead of methods.
* Aliasing is an issue with Mungo and different techniques have been used in other languages to address it [36,16,40]. Papaya is probably the best one to look at if I needed to.

**Personal View**

A short paper that was more a tutorial paper on Mungo, not the most useful but good for background information that will be added to my paper. 
