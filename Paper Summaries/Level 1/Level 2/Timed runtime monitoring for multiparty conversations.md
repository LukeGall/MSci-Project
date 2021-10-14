# Timed runtime monitoring for multiparty conversations


## Rumyana Neykova, Laura Bocchi, and Nobuko Yoshida

**Summary **

_Abstract_

We propose a _dynamic verification_ framework for protocols in real-time distributed systems. The framework is based on Scribble, a tool-chain for design and verification of choreographies based on multiparty session types, which we have developed with our industrial partners. Drawing from recent work on multiparty session types for real-time interactions, we extend Scribble with clocks, resets, and clock predicates in order to constrain the times in which interactions occur. We present a timed API for Python to program distributed implementations of Scribble specifications. A dynamic verification framework ensures the safe execution of applications written with our timed API: we have implemented dedicated runtime monitors that check that each interaction occurs at a correct timing with respect to the corresponding Scribble specification. To demonstrate the practicality of the proposed framework, we express and verify four categories of widely used temporal patterns from use cases in literature. We analyse the performance of our implementation via benchmarking and show negligible overhead.

_Specifying timed protocols with scribble_

SCribble models asynchronous actions between pairs of roles, sending an action, adds a message to a queue, and the receiving action which collects a message from the queue. Participants send messages with labels, and can own multiple clocks. The rest is standard from the other literature I have read. The syntax for global protocols is given which is similar to normal GT but time constraints are given with interactions [@A: phi, reset(phi)][@B: pi’, reset(pi’)] a(T) from A to B; G.

The Formal semantics of global protocols characterises the correct behaviour of the roles in a multiparty protocol. The semantics are based on the LTS from the MPST paper. Three types of transition labels, send, receive, and time. Subject of an action is the role that has the responsibility of performing the action i.e. who is sending / receiving. _Runtime global protocols _are introduced that model the intermediary state where a message has been sent but not yet received. The LTS is given for the paper and follows similar rules found in other papers and is easy to grasp from the syntax alone. The async role is important to allow non-causally related actions to be sent in different orders. _Ready actions _are actions that have no causal relationship with actions that appear earlier, syntactically so can be performed instantly. rdy(G) gets the set (of set) of clock constraints from the ready actions. The extra ‘set of’ is so choice options can be put into its own set. The time rules ensures that the semantics of time passing allows each participant to be able to execute one of the possible next ready actions of which the participant is subject. If a participant has choices then time can only eclipse if it ensures at least one ready action can later be performed. 

_Feasibility _and _wait-freedom _are two properties introduced by the MPST paper. These conditions yield progress for statically validated time programs, which ensures that the next available action will be executed in the specified time-range. 

Scribble timed local protocols, which describe a session from the perspective of a single participant, match the syntax found previously but add the time constraints on actions with brackets similar to the global protocols. External or internal choice is given by if the local protocol is given the choice or another. _Progress _is when every process waiting for a message eventually gets one, and every message sent is eventually received. Several restrictions on the shape of the global protocol are required for progress. A role must be involved in all branches of a choice and each projection must be meragable. Two local protocols are mergeable if they are identical or if they are both external choices, where the branches may only be different if they have different labels. The merging of two local protocols only works for receiving, but not sending from the definition. The formal semantics of configurations puts all the local roles in a configuration with a set of channels between the roles. The LTS for the configuration is standard with the last condition on time ensuring that if we have an external choice and the sender has already made the choice then time can’t pass such that the receiver can no longer receive that specific label. Theorem 2.4 states that a global protocol is trace equivalent to its projections in a configuration. 

_Checking feasibility and wait-freedom_

This section of the paper presents a syntactic checker for feasibility and wait-freedom. Wait-freedom rules out busy waiting but isn’t very realistic for some online internet protocols such as SMTP. It first builds a time dependency graph of causal dependencies between the actions in a protocol. For each n it then uses DFS to find the set of paths from the initial node to n. All the times in the constraints are converted to _virtual time _based on when that clock was last reset in the node. Logical formula is then used to check that the modified constraints of a node n is satisfiable given the restrictions from previous nodes. To ensure the graph always terminates, the alg only considers protocols that are _infinitely satisfiable. _This condition ensures that only one recursive unfold is needed to be checked. For the dependency graph of G = (n,E). Causal dependencies are not necessarily given syntactically, for example two receives by the same participant. Syntactic order of actions in a protocol corresponds to an actual causal dependency when actions are performed by the same role. Recursive dependencies can also be captured because of one time unfolding of infinitely satisfiable processes. In order to reason about virtual time constraints, they talk about _clock states _which uniquely identifies the value of the clock of p in a node n. Think of it as each node has a unique clock and depends on its time constraint but adding the time of the clocks where it was actually reset. I.e. xa2 > 5  + xa1. Where both nodes use xa, but the second one adds the value of clock xa1 as this is where xa was reset. For a global protocol to be feasible, every node in its time dependency graph must be satisfiable; its constraint must be satisfiable given all restrictions posed by constraints of preceding nodes. 

_Implementing timed protocols with python_

They update their python API with a _delay _primitive, and a _timeout _primitive (on function task not receive). This framework they present is a way for Python functions to talk to each other to model distributed communications.

_Runtime verification and enforcement of time properties_

The paper presents the work for creating _monitors _that check that communication is within its type. Global scribble to local scribble to local type to CTA to Python Monitor. This monitor checks runtime exceptions first based on the messages passed, then based on the time. The monitor can prevent some timing errors by adding an extra delay if an action is performed too early. 

_Benchmarks on transparency of timed monitors_

They benchmark the speed between monitored and unmonitored processes. Not important for me.

_Temporal patterns in global protocols _

This section of the paper gives a study of timed patterns that they have collected from literature and applying them to their Scribble global protocol. They first describe the ‘request-response timeout’ which is used by SMTP and show how it can be implemented by scribble, this could maybe get around the issue of needing it be a non-wait-free global protocol if we transform them into this style? They get around this by putting a constraint on either the client or server for sending / receiving.They also show the pattern which allows setting a limit to the number of messages that can be sent in a given time frame i.e. “a user can only send three redirect messages to a server with interval…”. They also show the Action duration pattern, and repeated constraints pattern. 

**Conclusion **

This work presents the design and implementation of a real-time verification framework via timed Scribble. With feasibility and wait-freedom done at the earlier stage for the Global types. 

**Context**

Multiparty Session Types have been extended with time and previous work has shown how to enable modular static type checking of distributed implementations (in a session pi calc). 

**Contributions**



* Define timed protocols with Scribble
* Automatically verify the consistency of these timed protocols
* Automatically project timed protocols onto local timed protocols 
* Automatically derive runtime monitors from each local timed protocol

**Personal View**

Mungo focuses on the ability to transform a locally projected protocol into a typestate. This paper shows how scribble (and to an extent MPST) can be extended with time, so it is likely that this would be the area to extend Mungo in. Most of the paper was useful to me, but as it focuses on the notion of runtime verification the later part could be skimmed. The most important part was the extension of Scribble with timings which could be useful for Mungo. 

**Critical View**

Remember wait-free is not a perfect condition and is not realistic when comparing to real life protocols i.e. SMTP. However, it may be that a wait-free protocol can still capture the requirements if rewritten another way.

**Problems**

Most type systems for MPST are targeted at implementations written in calculi with first class communication primitives and communication-oriented control flow. This is not standard in most mainstream languages and would require extensions. In some scenarios, such as Web Programming, it is common to use dynamically typed or untyped languages (JS). Static verification can only guarantee safe communications in the overall distributed system assuming that all its parts have been independently and locally type-checked. 

**Related Work**



* **Untimed runtime monitoring - **Other work building apon runtime verification for Python
* **Specification languages -** Different specification models and verification models have been developed
* **Verification tools and frameworks - **Not many support time properties (from 2017)
* **Time Properties - **Several works study properties of timed formalisms, mainly in the contexts of automata. Problems such as reachability are known to be undecidable in the general case. Correspondence between MPSTs and CTA are given in [BYY14a]. 
* **Tractable verification of distributed systems - **The shape of interaction enforced by MPSTs and inherited by Scribble, is critical to guarantee tractability of verification based on MPSTs