

# Meeting Deadlines Together


## Laura Bocchi , Julien Lange, and Nobuko Yoshida

**Abstract**

This paper studies safety, progress, and non-zeno properties of Communicating Timed Automata (CTAs), which are timed automata (TA) extended with unbounded communication channels, and presents a procedure to build timed global specifications from systems of CTAs. We define safety and progress properties for CTAs by extending properties studied in communicating finitestate machines to the timed setting. We then study non-zenoness for CTAs; our aim is to prevent scenarios in which the participants have to execute an infinite number of actions in a finite amount of time. We propose sound and decidable conditions for these properties, and demonstrate the practicality of our approach with an implementation and experimental evaluations of our theory

**Summary**

_Communicating Timed Automata_

The paper introduces the notation of Communicating Timed Automata based on definitions from [14, 17]. Given a finite set of participants P, we can also have a finite alphabet A. Channels are given as { pq | p, q from P and p != q}. These unbounded channels send messages are FIFO. The message also have channel guards based on clocks and clock reset specifics as well.a CTA is a tuple of states, and actions that transition between the states. (Kinda like a state machine). Deterministic CTAs are defined as standard deterministic state machines. A _Timed communicating system (TCS) _consists of a finite set of machines and a set of queues for the channels. So can think of a CTA as the transitions and clocks of a single session whereas a TCS is a set of these CTAs where they can communicate to each other. For a TCS system S = (_q, w, v) _with q being a vector of all the states of the machines. W being a vector of the queues, and v being a clock validation. Each machine has the set of states for a participant and the clock validations for that state as well. Configurations of a system S can progress by the sending and receiving of messages as well as the passing of time.

_Safety in CTAs_

This section defines the notion of **safe** CTAs and gives a condition for safety called multiparty compatibility [14]. They present a new approach based on _synchronous transition systems, _this is just a different notation of writing the channels and events that can progress over a **TCS,** it models the synchronous executions of the TCS. I.e. (s -> r: a, Gs, Ls, Gr, Lr) which is when s sends r, ‘a’ under the constraints Gs, Ls, and r receives under its constraints. More info can be found in [19]. A STS system S contains a tuple (N,n0, ->, E) such that -> is the relation defining the events that change the states within N. So n ->e n’ for the e given above if n = q, and n’ = q’ where q is the initial states of qs, qr and q’ is them changed after sending / receiving. An event can project down onto the local CTA conditions (sr!(...)). **Multiparty compatibility** states that for every sending state, all messages that can be sent can also be received, while for every receiving state, at least one transition will eventually be fireable. MC guarantees that any asynch execution can be mapped to a path in STS. They prove safety for S if it is multiparty compatible. Can project a system STS onto each machine, they also have a bisimulation relation (T. 7). 

_Progress with Time Constraints_

This section introduces a progress property for CTAs. Two progress errors. One based on if a participant can’t receive a message as there is an unexpected message on its queue or all the time constraints are off. Another based on if it can’t send a message due to its time constraints being false. Progress defined if for all configurations of a TCS none are deadlock, orphan, unsuccessful etc. They update the definition of CTAs specifically the transition definition such that prevents time transitions if machines are waiting to send, and also ensures that time transitions can’t occur for receives if it would ruin its message queue and disrupt the time constraints. They give a sound condition that guarantees progress in the presence of time constraints. _Interaction-enabling_ is the main part and basically checks if future actions are possible. By analysing the paths of events in  a STS(S), they can construct a graph of the dependencies of the actions of a path. The graph of a sequence of actions has nodes whose edges model causal dependencies between actions. Different functions are then given based on the dependency graph which works out all the conditions that must be met for that path to continue based on the clock conditions relative to when they were last reset. Given a node and event in a STS, a participant p, they give a definition that ensures that for all possible past clock valuations, there exists a future time where p can execute the event. Progress follows from MC and IE.

_Non-Zenoness and Eventual Reception in CTAs_

Zeno behaviour is when a participant’s only possible way forward is to fire actions at increasingly short intervals of time. The paper adds a special clock x to a systems that is never reset. While true for some other systems of synchronous communications, non-zeno needs to be present in all machines. A system S is cycle enabling if each x is reset in the cycle or that it is possible to let some time elapse at each iteration. It also ensures that the ‘escape’ route can always be taken. If a system is MC and CE, then S is non-zeno.

_Applications and Implementation_

The paper gives an algorithm to construct sound timed global specifications such as multiparty session types (important). This can be built from CTAs. The algorithm can generate a timed global type equivalent to the original system S. Therefore if S is IE, then the constructed timed global type will also enjoy progress

_Conclusions_

MC gives an equivalence between an MC system and a system consisting of the projections of its STS. MC and interaction enabling form a sufficient condition for progress, while MC and cycle enabling form a sufficient condition for non-zenoness. Together MC, IE, and CE ensure safety progress, NZ, and eventual reception.

**Related Work**



* Timed Multiparty Session Types [6] (READ)

        Typing system for a timed pi-calculus using timed global types. Focuses on the projection of well formed timed global types onto timed local types. Some conditions in the well-formedness of these types can be more restrictive for the CTAs. 

* Communicating Finite State Machines [9]
* Communicating Timed Automata [14, 17]

**Context**

To type checking for distributed software systems that have real-time constraints. Seeks to help model the problem of multiple communicating systems that each have individual deadlines. CFSMs have standard safety and progress properties studied in the literature so the paper aims to bridge these gaps in CTAs by extending it with properties from CFSMs. 

**Problem**

CTAs have been introduced to address this problem however unbounded channels and time make reachability undecidable in general. Hasn’t been any work to build to build global specifications from (local) CTAs, this isn’t useful for modern Agile development where global systems are rarely known from the start.

**Methods**

A lot of the definitions are formed by beginning with properties found in CFSM and then bringing the properties over into CTAs. These properties and errors are then used to provide a definition for safety met by MC. They then propose a bisimulation relation between a system of CTAs and STS and then derivative progress, non-zeno and eventual reception from STS onto this system. 

**Outcome**

Studies safety and progress properties for CTAs from the context of CFSMs. Also studies time-divergence and non-zenoness for CTAs as well. The paper gives several conditions that guarantee that no participant (CTA) misses its deadlines, that every message sent is eventually received on time, and that no participant is forced to perform actions infinitely fast.

**Critical View**

A really good paper overall, good to read and was well explained. The results from this paper would be very beneficial to a new type system if an easy correspondence could be found between this system of CTAs and a new type system. Not too sure what elements could be improved with this paper. Maybe a clearer description of how this CTA system could be converted to session types but my knowledge of surrounding papers is not perfect yet to be able to have a critical eye over this paper. Session types and pi calc have a lot of existing literature surrounding it so maybe could have shown a way that the concepts of CTAs could be linked to it? More information surrounding typing rules of CTAs could maybe have been useful too

**Personal View**

Great paper, much easier to understand the second time round. CTAs, TCS, and STS all have great properties that could be utilised in the Mungo type system. If possible, could be a useful avenue to extend Mungo using the properties from this paper as it would help guarantee a lot of important properties i.e. safety, progress etc. If I could find the link between CTAs and Mungo system then a lot of properties could be taken?
