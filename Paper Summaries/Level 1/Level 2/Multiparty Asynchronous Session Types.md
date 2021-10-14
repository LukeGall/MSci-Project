

# Multiparty Asynchronous Session Types


## Kohei Honda, Nobuko Yoshida, Marco Carbone

**Summary **

_Abstract_

Communication is becoming one of the central elements in software development. As a potential typed foundation for structured communication-centred programming, session types have been studied over the last decade for a wide range of process calculi and programming languages, focussing on binary (two-party) sessions. This work extends the foregoing theories of binary session types to multiparty, asynchronous sessions, which often arise in practical communication-centred applications. Presented as a typed calculus for mobile processes, the theory introduces a new notion of types in which interactions involving multiple peers are directly abstracted as a global scenario. Global types retain a friendly type syntax of binary session types while capturing complex causal chains of multiparty asynchronous interactions. A global type plays the role of a shared agreement among communication peers, and is used as a basis of efficient type checking through its projection onto individual peers. The fundamental properties of the session type discipline such as communication safety, progress and session fidelity are established for general n-party asynchronous interactions.

_Multiparty Asynchronous Sessions_

A session is a series of interactions which serve as a unit of conversation. A session is established among multiple parties via a shared name; they then send fresh session channels in which they then perform a series of communication actions. New sessions are initiated through a shared interaction point, which then distributes a vector of fresh session channels to the participants. When performing session delegation, the whole channels associated with the session are passed. The number of threads created in session delegate can be different from that of session channels, giving flexibility in channel usage. Every session gets its own channel, required for asynchrony. Session channels are linear meaning that only two participants should send between them. Important for asynchrony so that messages don’t get tangled. 

_Global Types and Causal Analysis_

Global type syntax reflects a lot of the syntax seen in other papers so uses p -> p’ : k&lt;U>.G’. For when participant p sends a message of type U to p’ along channel k. U can be a vector of Sort types or a session channel.  N1 &lt; n2 in G when n1 ‘prefixes’ n2, can be thought of as n1 can cause n2 in the Global type. Sequencing is only imposed on the actions of the same participant in ordered prefixes. I.e. Two sends by A, but received by C and B. B and C can get it in any order, but A needs to send in a specific order. If two sends are different to the same participant then the channel needs to be the same otherwise conflict may arise. This prefix ordering is used to reason about when communication errors would occur in channels and which order sends and receives are ordered or asynch. When a common channel is used in two communications, their sending / receiving actions should be ordered temporally, so no race conditions occur. Linearity needs to be preserved in global type definitions, stops concurrent executions of race conditions, and can be checked algorithmically. 

_Type Discipline for Multiparty Sessions_

Syntax from local types follows from the binary session type syntax with two major differences, the identity of the session channel needs to be recorded for every action as it may have multiple session channels. Types are also inferred for each participant, so they use a notation T@p representing a local type T assigned to a participant p. Isomorphism is given to capture asynchrony in communication. When projecting the global type onto a local type, it is ensured that the local type exists on only a single thread. For local projection, all branches should generate the same local type; this is also required for coherence. The typing rules follow very closely to that of binary session types and seem reasonable enough to understand and grasp. When delegating a multiparty session, they pass the whole capability to participate in the sessions, so the whole session channels. 

_Safety and Progress_

The typing rules are extended to include those for runtime processes. Reduction over session typings is also defined. They then go on to give _subject reduction_ and _safety_.They use a local type with a hole (type context) for typing a queue. A typing judgement is given to guarantee that there is at most one queue for each channel. Type reduction is then given over session and global types. Theorems 5.4, and 5.5 show that a typed process “never goes wrong” in the sense that its interaction at a multiparty session channel is always one-to-one and that each delivered value matches the receiving prefix. Session fidelity is also gained as a corollary. Communication safety is given which states that if a process ever does a reduction, it conforms to the typing and it is linear. 

_Conclusion_

The presented theory can guarantee stronger conformance to conversation structures than binary sessions when it involves more than two parties. Global types is the structure underpinning the work. The structures are described from a global viewpoint and can then be projected into local sessions. GT also offer a basis for clean modular causal analysis systematically applicable to both synchronous and async communications. 

**Context**

Communication is a growing and varied field for software development, including web services, parallel computing, multicore etc. Session types are based on the observation that a communication-centred application often exhibits a highly structured sequence of interactions involving branching and recursion. The structure of the communication is abstracted as a type which is then used as a basis for validating programs through a type discipline. Various properties can then be guaranteed including communication safety, linearity, progress, session fidelity, and predictability. Many existing business protocols exist based around the collaboration of more than two peers in a session. Most of these applications also have an asynchronous message system that should also be represented by the system. The question of multiparty session types was also posed by the W3C working group. 

**Personal View**

A good paper that was used to get a better understanding of the groundwork that Mungo and a lot of the other papers I read is based on. It is clear that this is a seminal paper and was read more for learning rather than as a direction of study or critical view.

**Problems**

Session types' main properties of simplicity and tractability come from a notion of _duality _which can’t be easily applied to the multiparty setting. Linearity analysis of channels also becomes highly involved under a combination of asynchrony and  multiparty. 
