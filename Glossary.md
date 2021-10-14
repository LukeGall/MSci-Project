

# Glossary

**Typestate** - Allows classes to be associated with state machines defining permitted sequences of method calls. Define valid sequences of pertations that can be performed upon an instance of a given type.

**Protocol** - Application-level specifications of interaction patterns (via message passing) among distributed applications. I.e. how different processes should communicate to each other.

**Endpoint / Local Protocol** - Given a global protocol specification, a local protocol is the sequence of messages related purely to that local protocol only. 

**Multiparty Session Types (MST)** - Sessions that involve more than two participants.

**Session** - A unit of conversation between participants. Session types, type this information such that participants can be checked that they perform the messages in the specified order.

**Object aliasing** - When several variables contain the address to the same object on the heap.

**Linear Objects** - An object which may only have one variable (pointer) with its address. 

**Internal Choice** - Where a session type can select a path to go down, represented by a circle with a cross in it. When used it is a left pointing triangle

**External Choice** - Where a session offers the choice to other participants, also known as a branch as it waits for the other process to choose the option. &. When used it is a right pointing triangle.

**Duality** - The relationship between the types of opposite endpoints of a session channel.

**Subtyping** - No violation of type system guarantees when using a value of type S that is a subtype of type T, if T was expected. Can think about it as S &lt;: T, where the set of values in S is a subset of the values in T.

**Operational Semantics** - Certain desired properties of a program (correctness, safety etc) are verified by constructing proofs from logical statements about its execution and procedures.

{x/y} - replace y with x

**Progress** - Guarantee that a program either reduces or is a value

**Simulation Relation** - Relation between state transition systems where they both behave in the same way, where one simulates the other. System simulates another if it matches all its moves

**Variance (subtyping)** - Refers to how subtyping works between more complex types based on the subtyping of their component types. I.e. if x &lt;: y, does [x] &lt;: [y]? If yes then covariant, if the opposite it is contravariant.

**Global types (MST)** - The specifications of the interactions of multiparty sessions.

**Timed Automata** - Models the behaviour of real-time systems over time.

**Communicating Timed Automata** - Timed automata extended with unbounded communication channels.

**Labelled Transition System (LTS)** - The semantics to allow the reductions between different states. I.e. the reduction rules

**Isomorphism** - Structure-preserving mapping between two structures of the same type that can be reserved by an inverse mapping.

**Properties**


<table>
  <tr>
   <td>Meeting Deadlines Together
   </td>
   <td>Timed Multiparty Session Types
   </td>
   <td>Asynch Timed Session Types...
   </td>
   <td>Mungo
   </td>
  </tr>
  <tr>
   <td>Communicating Timed Automata extending properties found in CFSM. A bit of Synchronous Timed System
   </td>
   <td>Typing theory based on multiparty session types
   </td>
   <td>(Binary) Timed Sessions types extended. Also introduce a calculus with delays and timeouts
   </td>
   <td>Typestate 
   </td>
  </tr>
  <tr>
   <td>Safety
   </td>
   <td>Type safety
   </td>
   <td>Duality (with urgent receive)
   </td>
   <td>Safety
   </td>
  </tr>
  <tr>
   <td>Progress
   </td>
   <td>Time-error freedom
   </td>
   <td>Subject Reduction 
   </td>
   <td>Progress
   </td>
  </tr>
  <tr>
   <td>Non-zenoness
   </td>
   <td>Global time-progress
   </td>
   <td>Time and type safety
   </td>
   <td>
   </td>
  </tr>
  <tr>
   <td>Eventual Reception
   </td>
   <td>New class of CTAs with global progress and liveness
   </td>
   <td>
   </td>
   <td>
   </td>
  </tr>
  <tr>
   <td>Not reliant on prior global knowledge of the system
   </td>
   <td>
   </td>
   <td>
   </td>
   <td>
   </td>
  </tr>
</table>


 
