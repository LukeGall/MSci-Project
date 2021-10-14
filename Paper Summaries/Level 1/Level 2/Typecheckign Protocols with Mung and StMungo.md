

# Typechecking Protocols with Mungo and StMungo Summary


## Dimitrios Kouzapas, Ornela Dardha, Roly Perera, Simon J. Gay

**Abstract **

Static typechecking is an important feature of many standard programming languages. However, static typing focuses on data rather than communication, and therefore does not help programmers correctly implement communication protocols in distributed systems. The theory of session types provides a basis for tackling this problem; we use it to develop two tools that support static typechecking of communication protocols in Java. The first tool, Mungo, extends Java with typestate definitions, which allow classes to be associated with state machines defining permitted sequences of method calls: for example, communication methods. The second tool, StMungo, takes a session type describing a communication protocol, and generates a typestate specification of the permitted sequences of messages in the protocol. Protocol implementations can be validated by Mungo against their typestate definitions and then compiled with a standard Java compiler. The result is a toolchain for static typechecking of communication protocols in Java. We formalise and prove soundness of the typestate inference system used by Mungo, and show that our toolchain can be used to typecheck a client for the standard Simple Mail Transfer Protocol (SMTP).

**Summary **

_StMungo: Scribble to Mungo_

The main idea behind this translation is the notation that session types capture the permitted sequence of message calls for a communication protocol which can be translated into the notation of method order in typestates. StMungo follows from the idea that each local protocol can be abstracted into a Java class each with its own typestate. Scribble uses _endpoint projection _to project the global protocol into each individual local protocol. The multiparty session types provide theory to prove its soundness for the composition of these local protocols. StMungo connects the Scribble local protocol into a typestate protocol. An API is also generated for this role, which uses this typestate and will connect to other role objects in the session. 

_Mungo_

Mungo is implemented with the JustAdd framework and extends Java with optional typestate definitions, which are provided in a separate file with Java-like syntax. The object protocol is in the form of a **state machine**. Each state offers a set of methods that transition to a new state. External choices are captured using the notation of enum classes, where each option in the Enum class has a state it will progress to. No specific typestate definitions are required for the parameters or return types when returning an object with a typestate, Mungo infers them. Mungo ensures linear usage of objects that follow a typestate protocol; aliasing could allow different method calls that might lead to an inconsistent typestate (however Papaya solves this I think). Mungo would require a typestate for the containing class that uses a typestated class. Mungo can infer the typestate of the used class within the containing class therefore if the container class follows its typestate then Mungo can infer if the field does also.

_Case study:Typechecking SMTP_

The paper carries out a case study in which they statically typecheck an SMTP client. They first provide the protocol by writing it out in Scribble, then use StMungo to translate it. For every external choice in the local protocol there is an enumerated type in the typestate. The correspondence between text-based commands in SMTP and the method calls in Mungo. In SMTP the atomic command is composed of the keyword SUBJECT and the subject text “Hello world”. Mungo uses an intermediate layer to split this command into separate method calls. The provided API is not required to be used by the programmer and can be edited or rewritten. The implementation (skelton) can also be ignored. 

_Core Calculus for Mungo _

The core object-oriented calculus is based on [23]. This paper only formalises the ability of typestate inference system. A program is a set of type declaration D, which could be a class (with typestate S) or an enum E. Mungo currently has no support for inheritance or interfaces. Typestate definition specifies a state machine that has as actions the methods of a class. Every state machine can be a list of signatures or a recursive action. For each signature it can be a method that just translates to a new state or external choice where a list of states needs to be provided for each value of the enum. Method declarations are the same in Java, while the notion of a _receiver _is the current object. For method calls, field assignments, and object creation, the target object is restricted to a path _r. _This allows the typestate of the target to be tracked by the type system. Runtime constructs are extended to the syntax to be used by the operational semantics. A _configuration _is a pair of a heap and runtime expression. The ‘heap’ is an object tree. For the expression e, all paths are replaced with the runtime path which refers to the value in the heap. Evaluation context also applies which is similar to the plugging in of values into holes like in pi-calc. The operation semantics is annotated with labels that denote actions (Could have a relation to the actions in CTAs?). A reduction relation is given for the heap access and update functions which just take a path and return the object the path points to. Labelled reduction semantics are defined for the configurations (h,e) and have standard rules for context, sequence (discards values as it only needs to build a heap), true, false. A new expression will update the values of the fields held in the heap. When assigning an object to a field, it must be used linearly so it will relocate the object from the original location to the new path, with null being left at the old location. (The type system will only allow this if the object’s typestate is fully consumed).  When a method is called, it will substitute the method call for it’s expression but changing this -> r, and the parameter to the actual value.

_Typestate Inference_

The typestate formalisation makes a simplification that the body of a method is analysed every time the method is called. This is not used in Mungo as it would be inefficient. Typestate inference system infers a typestate specification for a class, consistent with the static usage of instances of the class; then the inferred typestate is then checked against the declared typestate of the class. The subtyping on typestates constructs a set of pairs of typestates, (where each pair shows a relation). The join operator is used to compute a common typestate in typing rules that combine multiple execution paths. Transition rules are given for typestates, typestates can transition under a set of typestates and recursion. Typestate inference rules for expressions are given, linearity of typestates is enforced by these rules. The Sequence rule processes the second expression first as the output typing context is the input to the first. Typestate inference rules for methods, fields etc are also given. For switch statements, every branch has its typestate inferred. They provide a Progress and Type Preservation Theorem. Typability also guarantees that the program completely uses every object according to its typestate by following a path to end. The typestate inference rules in fig.7 are used to check that the declared typestate of each class is consistent, i.e. the typestate is consistent with the calls it makes using its field on their typestates (so containing class). In order to support recursive method calls, Mungo treats a method body in isolation by inferring a typestate for all objects used within the method body. At a method call, Mungo combines the inferred typestates from the previous analysis of the method body with current usage of the fields. This constructs cycles which resemble a state machine. A control flow structure is created for every field that is used inside a method.

**Context**

Modern software requires the correct format and sequencing of communication. This is required for correct implementation of communication protocols. Session types provide a basis for supporting communication based programming by static typechecking the operations used in communication. The communication between processes can be codified, similar to data type definitions and then type checked. 

**Contributions**

**Mungo - **Mungo typestate checker for Java. Only subset for Java.

**StMungo - **Translates Scribble local protocols into Mungo typestate specifications. Also generates Java method stubs for each endpoint.

**SMTP case study **- Presents a statically typechecked SMTP client.

**Typestate inference system **- Formalises features of Mungo as a core object-oriented calculus. Type safety is also proved. 

**Related Work**



* Other work applying session types to real-world programming languages [9, 15, 17, 16, 22, 29, 34, 37, 41]
* Capecchi et al. class defines sessions instead of methods [9]
* [35] implements a session-based actor framework that uses runtime monitoring to integrate multiparty session types
* [45] introduces typestates
* [2 ,46] proposes a new paradigm of typestate-oriented programming. Program consists of state definitions containing methods that cause transitions to other states

**Conclusions & Future Work**



* Extend Mungo to increase its power for general-purpose programming with typestate. 
    * Generalise the use of linear typing as a mechanism for the alias control required by typestate systems
    * Support generics and inheritance
        * Would require subtyping between their typestate specifications [21]

Mungo and StMungo which extend the Java development process with support for static type checking of communication protocols. Mungo extends Java with typstate definitions. They use a typestate inference system which means users wouldn’t need to declare the typestates for methods, and also provide safety and progress properties.

**Methods**

The methods used by this paper are described in the above summary but for a recap, Mungo was created using the JustAdd framework building on the notation of using multiparty session types for typestates. StMungo also converts Scribble to Mungo typestate specifications in Java, it is written in something. 

**Critical View**

Good paper, well described for all the major sections. It’s clear that the case study was large and that the section was only a small overview of what it covered so more could be found out on that if required. The typing system, (and the inference) section was rather confusing, especially adding new notation but the examples helped explain the different ideas. Not entirely sure how the base of MPST are used fo Mungo but maybe I missed something. 

**Personal View**

Good paper, enjoyed it and every re-read improves my understanding of the fairly complex typing inference section. All other sections are easy to grasp and interesting. The hardest section is the typing rules and contexts that typestates use. This is likely the area I will need to extend with the notation of time so I will need to think how and where a user would extend this notion. It would likely be in the Typestate itself as that is the “protocol” the class follows so would detect certain time constraints.

With the typestate inference system, I will need to think how it can infer the time constraints for this typestate. It will likely be included in the method declarations (in typestates) so probably isn’t that bad. 

The subtyping relations will need to be extended with the idea of time. This is likely required so the inference system can work. 

**Relevance**

This paper is very relevant to me as it will be the main system that I will be working with. It is important for me to get my head around typestates, and the typing system that Mungo uses. As I will be extending Mungo and typestates with the notion of time it is important.
