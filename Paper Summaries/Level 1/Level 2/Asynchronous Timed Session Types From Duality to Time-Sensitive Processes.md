

# Asynchronous Timed Session Types From Duality to Time-Sensitive Processes


## Laura Bocchi , Maurizio Murgia, Vasco Thudichum Vasconcelos, and Nobuko Yoshida

**Summary**

_Abstract_

We present a behavioural typing system for a higher-order timed calculus using session types to model timed protocols. Behavioural typing ensures that processes in the calculus perform actions in the time windows prescribed by their protocols. We introduce duality and subtyping for timed asynchronous session types. Our notion of duality allows typing a larger class of processes with respect to previous proposals. Subtyping is critical for the precision of our typing system, especially in the presence of session delegation. The composition of dual (timed asynchronous) types enjoys progress when using an urgent receive semantics, in which receive actions are executed as soon as the expected message is available. Our calculus increases the modelling power of extant calculi on timed sessions, adding a blocking receive primitive with timeout and a primitive that consumes an arbitrary amount of time in a given range

_Intro definitions_

**Asynchronous timed duality**

Duality is the unique dual of each (untimed) session type.  Duality characterises well-behaved systems: the behaviour described by the composition of dual types has no communication mismatches. This doesn’t naturally convert into the timed scenario as timed constraints and resets don’t guarantee well-behaved systems. 

**Urgent receive semantics**

Based on the semantics found in mainstream programming languages (not sure their source). This is when the receive actions are executed as soon as the expected message is available, given that the guard of that action is satisfied. This is used to make the communication of protocols follow in sync.

**Calculus for timed asynchronous processes**

They introduce two time sensitive primitives. A receive operation that has a timeout n, which can be non-blocking or block with a timeout. The second is a time consuming delay. Can use the delay to model estimation of computation time.

**Type system for timed async processes**

They give a typing system to define the relationship between types and processes in their calculus. Well typed processes are ensured to communicate at the time prescribed by their types. Their subject reduction holds under a **receive liveness **assumption, which is when timing information is not present in a process, an action waiting for a receive, the corresponding message is eventually provided by the rest of the system. It is based on the idea that if an untimed process can’t guarantee progress then the timed version may miss deadlines. 

_Asynchronous Timed Session Types_

The use the model of time that is adapted from timed automata [3]. The set of clocks and predicates are given as standard from the other papers I have read. The types again follow a similar syntax to other papers but include T and S, where T is (pred, S), Nat, Bool or another primitive type. Where S is a session which can be a send, receive, select, branch, and recursion. Each action is annotated with a guard and reset, including every branch in select and receive. The base syntax rules don’t rule out junk types that can’t progress to “end” so they introduce formation rules to rule out junk types. The judgements use the notation of ‘pasts’ where intuitively think of the “past” of a guard pi as a new guard where for whenever this guard is true, pi is also true for v plus some amount of time. Past can be inferred algorithmically, and some pi can be sub conditions of others. So if all v hold under pi means they also hold under pi’. The base ‘interact’ rule for two actions in a session type works by saying that the initial guard must hold under it’s past but then once the next action is checked, the previous judgement must hold under its past. I.e. The next action must have a guard that is a superset of the previous guard, otherwise a value that could have passed the last guard may not be valid for the next action. Choice checks for at least one viable path in the branches are viable and that they are well structured. They then give a definition for when a session S is well-formed against its clock valuation. This allows the rest of the paper to assume that the types are well-formed. 

_Asynchronous Session Types Semantics and Subtyping_

They give the types in isolation from their environment and queues before moving onto the systems (simple type configurations). Can think of the simple type configurations as the local protocol (typestate) before needing to deal with the interleaving of the typestates. The LTS for types in isolation is very similar to what has been seen before, i.e. send rules, receive rule etc. Interestingly enough, the time rule has no premise and always lets time elapse (but I suspect this will change later). THey define the subtyping as a partial relation on simple type configurations. An action l is future enabled in s if time can pass in s and then l can be performed. The grammar enforces all actions to either input or output (for a session). A Timed Type Simulation for a relation R is when two sessions ‘match’ and can follow the same sort of path, think of the relations in MRS. So when an environment can safely interact with s2, it can also interact with s1, if (s1,s2) in R, we say that s2 simulates s1. They then give a definition for a live simple type config where (v,S) is live if S = end or Et, l : (v, S) -> t {!,?}m. S1 is a subtype of s2, s1 &lt;: s2, if there exists a timed type simulation R on live simple type configurations s.t. (s1,s2) in R. As this paper only deals with Binary Session types they give queues for S as M where it is the queue of messages received from it’s coparty. The LTS for systems is given, most rules are self explanatory with the third condition in the premise of time used for the _urgent receive _semantics. 

_Timed Asynchronous Duality_

Timed duality follows on from duality in the untimed settings with clock and reset preds matching between duals. Duality requires urgent receive semantics and enjoys the following properties: progress, behaviour is preserved with subtypes. Assuming types are well formed. Def. 7 gives their definition of progress. Two more theorems are given for **safe and progressing substitution**.

_A calculus for Asynchronous Timed Processes_

This section introduces their calculus for timed processes following a similar syntax to pi calc. Recursive calls have a list of parameters, one for ground types and the second for channels. Two run time processes are given but these are not written by the programmer and are required for typing. Sessions (the type) are modelled as processes of the following form (vab)(P | ab:h | ba:h’). This means that queues are not present as continuations of processes, fixes misplaced queues. _Their calculus allows sessions to be created at any point unlike_ ‘Timed multiparty Session Types’.  Their reduction rules are given with most being the standard rules for asych communication. Interesting notes include Par not being able to reduce by time as it is handled by the delay rule which has a time passing function pit(P) which has time pass across all processes but only those not needing an urgent action. This is done by finding the intersection between waiting processes and nempty queues. 

_Typing for async Timed Processes_

For the typing rules, similar environments are used compared with other papers. Triangle is used to keep track of ongoing sessions, dom(tri) is the set of variables and channels in tri. Environment r maps variables to sorts and process variables X to pairs  (T,O) where T is a vector of sorts and O is the set of session environments. val(tri) is the disjoint union of all the clocks in tri. For the set of session environments O, they give three conditions, the final one ensuring that O is finitely representable. They give a definition of t-reading for a session environment, which is when any of the open sessions in the mapping prescribe a read action within the time-frame between v and v+t. Definition ten gives conditions for when two configurations are compatible. The typing rules are given in a figure. For receiving but using a timeout, the typing rule ensures that you can’t progress past the time delay but also that the time out satisfies the receive clock constraint. As can be seen in the send rule, the continuation of the process needs to be well typed against the continuation of the type. When delegating a session, the typing discards this session type as it is no longer required by the process P. Subtyping is used to add more power to their typing system, they give an example where the session delegation would be ill-typed if not for their subtyping relationship where a weaker time condition can be the subtype of a stronger time condition. 

_Subject Reduction and Time Safety_

The main properties of the typing system are Subject Reduction and Time Safety. Time Safety ensures that the execution of well-typed processes will only reach fail-free states. The typing system in place for this paper doesn’t check against some circularities across different interleaved sessions. Which is common in other literature. In the untimed scenario, progress for interleaved scenarios can be guaranteed by means of additional checks on processes, they then use a _receive liveness _assumption, (if an endpoint is waiting to receive a message, then the overall process can reach a state where this message is received). The process of time safety relies on the fact that live erased Processes can reduce. 

**Conclusion**

Introduced duality and subtyping for asynchronous timed session types. Compositions of asyn timed dual types enjoy progress when using an urgent receive semantics. _Considers binary sessions rather than multiparty. _They propose a behavioral typing system for a timed calculus that features non-blocking and blocking receive primitives (with and without timeout) and delays. Unlike other work, receive liveness is needed for Subject Reduction as they explicitly capture failures, which they deem as more useful for future work on static checking. 

**Related Work**



* Timed automata [3,26,34]
* Message Sequence Chart [2]
* Some work based on session types, focus on the relationship between time-sensitive protocols, modelled as timed extensions of session types, and their **implementations ** abstracted as _processes_ in some timed calculus 
* Relationship between protocols and processes [12,15]
    * In [12] they don’t need to receive_ liveness_ as processes become stuck instead of failing, will need to think about which is more useful
* Most seem to follow on from **timed automata** and then **timed communication automata** 
* [6,7,30] - run time monitoring 
* [12,15] - static behavioural typing

**Context**

A lot of common client-server interactions have time based constraints i.e. SMTP. For this paper, _protocol _is application-level specifications of interaction patterns (via message passing) among distributed applications (which is what mine is about). A lot of recent work focuses on the relationship between timed protocols, modelled as extensions of session types, and their implementations abstracted as processes in some timed calculus. The relationship is given in terms of **static behavioral typing** OR **run-time monitoring** of processes against types. _Wait-free _protocols have an empty intersection for time windows in corresponding send and receive actions. 

**Problem**

Existing work on timed session types does not capture blocking receive primitives with timeouts and time consuming actions (delay). Previous work with static behavioural typing could not check processes against not wait free protocols. 

**Methods**

Uses asynchronous timed session types focusing on using the dual of types to create type safety protocols in the timed setting using Urgent Receive semantics.

**Critical View**

The semantics from timed session types and CTA, is that only timed actions take ‘time’ while all other actions are instantaneous. Not perfect as real life actions will obviously take time. They have a foot note that says separated semantics can describe situations where actions have an associated duration however. This paper uses Binary session types which they say is one of the only restrictions they add compared to previous work. 

**Personal View**

A good paper overall that adds some very nice typing properties to session types and the calculus it shows. The best part of the paper is the introduction of the timeout and delays and the examples they give in SMTP that use these notions, definitely something to look at when comparing it to Mungo. The notion of being able to check ‘non-wait-free’ sessions is also incredibly important as this is the type also used in SMTP.
