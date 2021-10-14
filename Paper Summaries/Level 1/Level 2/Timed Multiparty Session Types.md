

# Timed Multiparty Session Types


## Laura Bocchi, Weizhen Yang, and Nobuko Yoshida

**Summary **

_Abstract_

We propose a typing theory, based on multiparty session types, for modular verification of real-time choreographic interactions. To model real-time implementations, we introduce a simple calculus with delays and a decidable static proof system. The proof system with time constraints ensures type safety and time-error freedom, namely processes respect the prescribed timing and causalities between interactions. A decidable condition, enforceable on timed global types, guarantees global time-progress for validated processes with delays, and gives a sound and complete characterisation of a new class of CTAs with general topologies that enjoys global progress and liveness.

_Timed Multiparty Session Types_

Global types are specifications of the interactions between processes in a communication system. This TG can be projected onto a set of local types for each participation and used for local verification. This paper extends Global and local types with constraints on clocks. The syntax for their extension involves S (sort) which is bool, nat, G (Global type) or T, pi (local type with clock constraints. A global type G uses the notation of p -> q: {li, &lt;Si>{Ai}.Gi} etc. Whereas a local type is either send, receive, recursive etc. With the time conditions as well. A session can delegate  the remaining behaviour T. For the example above, it models an _interaction_, this i where p chooses a branch so sends that branch label to q, with message (sort) Si and the clock constraints. Clocks are disjoint and can only be reset by the participant that owns the clock. For each clock constraint in G, when projecting down onto the local types, the constraints are split so only the relevant messages are sent to the local projection. Resets are important to help model the same notation of time supported by CTAs, this helps give a more direct comparison between CTAs and types. The Labelled Transition States (LTS) for a TG include label actions of send and receive. They also extend TG with the notion of states in which a message has been sent but yet to be received using p ~> q. The LTS is designed to ensure that no action invalidates the constraint of any _ready action._ A _ready action _is an action that has no causal relationship with other actions that occur earlier. Def. 3.2 is the satisfiability of ready actions. Configurations are defined as a set of T (local types) with a vector of unidirectional channels. G is trace equivalent to the set of its projections. 

_Multiparty session processes with delays_

They model processes using a timed extension of the asynch session calculus [6]. ubar[n](y).P sends, along u, a request to start a new session (related to a session type i.e. a STMP protocol which is as G)) with participants 1,...,n where it participates as 1, and continues as P. If a process delays then all other parallel processes must delay as well (so time is global). A process will act and follow a local timed session type. The ‘programs’ (set of processes) are validated against specifications based on their TLs. The type environment maps shared variables/names to sorts, as well as processes variables to their types (TLs). A _session envo _holds information on the ongoing sessions.  _Infinitely satisfiable _guarantees a regularity across different instances of a recursion. When validating a process P recursion is unfolded so every instance is checked to be satisfiable (is normal [13]). The proof rules ensures that you can't send a session type to another participant if it doesn’t pass that session's clock constraint. Type preservation is given in theorem 4.3. This paper introduces an error state in which a violation occurs. 

_Time-progress of timed processes and CTAs_

A timed global type is feasible if every partial execution can be extended to a terminated session. Wait freedom is introduced such that a receiver checking a queue at any prescribed time never has to wait for a message. It is important that a process is wait-free as they show that a well-typed and feasible process can’t correctly execute because it is not wait-free. Non-wait-free specification allows participants to have incompatible views of the timings of action. They present an algorithm that decides if G is infinitely satisfiable for checking that it is feasible and wait-free. They give an assumption that conforms to session delay where a delay can not occur before all sessions are initiated. They give the definition for simple sessions basically only one session occurs in a global protocol. 

They give a description of the subset of CTAs that corresponds to their class of TGs. In order to establish a correspondence between TGs and CTAs they introduce an additional condition on the semantics of C, similar to the constraint on ready actions in the LTS. They add a similar time constraint to a label t, such that it can only make a time constraint if it stays within the clock constraints for the transitions. Their encoding adds clock constraints and reset predicates to the corresponding edges. They adapt two conditions from [11], timed automata have the same shape as TLs and that timed automata perform the same actions as a set of project TG. They then give their theorem on the soundness of their encoding. 

**Context**

Communicating timed automata extends the theory of timed automata to enable a precise specification and versification of real-time distributed protocols. CTAs consist of a finite number of timed automata, synchronising over the elapsing of time and exchanging messages over unbound channels. The model is known as computationally hard, and is difficult to directly link its idealised semantics to implementations of PL and distributed systems.

Multiparty Session types are used to describe communication protocols among two or more participants from a global viewpoint. The global types are then projected to local types, against which programs can be type-checked and verified to have no deadlocks. 

**Methods**

They apply the idaelised mathemeatical semantics of CTAs to the design of MPSTs with clocks, clock constraints and resets, in order to fill the abstraction gap that CTA leaves.

**Problems**

CTA model is known to be computationally hard, and it is difficult to directly link its idealised semantics to implementations of programming languages.

**Outcome**

They add time annotations to global types but still show that time-error freedom is guaranteed without additional time analysis of the types. They also give the semantics of TGs and prove the soundness and completeness of the projection onto timed local types. 

They then investigate the conditions for an advanced property, time progress and show that if deadlock free for untimed processes also yields processes for timed processes. They give conditions on the TGs to stop this happening. So if you have feasible and wait-free TGs, then the proof system guarantees time-progress for processes. They also give details of a new class of CTAs which enjoy progress and liveness. 

**Critical View**

Once again seems like a good paper overall, certainly not perfect. The last part felt a little rushed and out of place with the focus on the global types but I am likely just starting to get a bit confused. As seen by the end of the “Meeting deadlines together” paper a lot of the issues covered in this paper are solved by the newer paper. Including sufficient conditions for CTAs to belong to the class of safe CTAs with progress. It also includes better conditions that don’t rule out certain types of correct CTAs that this paper rules out. The Asych Timed ST paper says that this work rules out non-wait-free protocols, but SMTP and a lot of existing protocols use non wait free. Not allowing sessions to be created after delay is not required in the newer ‘Asynch Timed Session types...’

**Personal View**

A good paper that works as a good base for how timed multiparty session types can be used for progress and type safety. Some elements of the paper are covered better in Meeting deadlines together, and more information would need to be gathered on why some issues occur with CTAs, but it is likely CTAs could relate to typestates and that the global process could be constructed with it. Good paper overall tho, my knowledge is increasing. 

If I could work out how Mungo uses the notation of session types to implement Typestates then I could implement typestates based on the timed multiparty session types in this paper.

Typestates will be like the local session timed types, and then the Mungo implementation of methods and classes would be like a process. It is likely that we could include a delay function to Mungo to simulate the passing of time. 

**Related Work**



* Timed Automata [3]
* Communicating automata [8]
* Asynchronous session calculus [6]