

# Notes

This will be a general notes page to get ideas down on paper which will help in the long run. The structure isn’t that important and is more to write while going through ideas. Earlier notes at the start of the project may have ideas that are wrong, they will likely not be corrected at the end. 

**Week 1**

So it seems standard that for CTAs that the clock conditions and reset protocols are used as the main features of time. This is likely from the timed automata which I should investigate and read. 

It will be likely that the main point of the project is to extend the current Mungo type automata / system with the notion of time. It will then need to be proved with certain properties, likely safety etc or maybe not, depending on how hard that is tbh.

I’m still struggling with the general understanding of the papers so far so I assume this will improve over time but I may need to be careful as I may be missing valuable points in the first papers I read. 

The more I read papers the more understanding I am drawing from them, which was too be expected and helps reduce overall stress from them. When extending Mungo with time two main points I need to worry about 



* Which method shall I take inspiration from.
* Which properties do I want to have and then prove (maybe) for Mungo.

Papers are getting easier to read overall but still struggling a bit with how long a paper takes to read

**Week 2**

Week is going well so far, the _Glossary _was a good idea to list different terms and ideas to focus on building my understanding. 

In terms of the project, I need to research and find out how to add the notation of time to the current Mungo system so I need to take a closer look at how time could be captured by a type system and then see how that could be implemented with Mungo and it’s type system. 

Mungo is based on the idea of multiparty session types so the time system will need to respect this notation. Some constraints will apply based on the current system of Mungo as well.

For the Timed Multiparty Session types paper they have the notation of p~>q where p has sent a message to q but it hasn’t been received yet. They have a theorem that shows this is equivalent to message queues but it doesn’t use urgent receive. This is different to Async Timed session types which uses queues and has _urgent receive _semantics where if a message can receive a message it must do so before another reduction. Both methods have pros and cons so will need to be explored which one is more suitable for typestates. 

For this week, it is important to flesh out my original summaries that I completed for these papers. As the intern report requires a lit review it is very important that I make sure these summaries are useful and well made before moving onto newer papers. My knowledge level was a bit low the first pass of these papers so it isn’t an issue giving them a quick re-read as it will save time in the future. The ‘new’ summaries are much better than the original and help consolidate the knowledge around the 4 main papers I need to read and understand.

In order to think about how time should be represented by Mungo extended with time think about where it would be used so have a look at the case study and see where restrictions would need to be contained. 

CTAs feel very similar to the notation of a type state (they follow closely to Session types) and they contain guards so the state transition between states in a typestate could be related to time guards. Now the time is slightly more difficult when communicating members are involved but a global protocol can still be built from lots of smaller protocols following the _meeting deadlines together _paper. It’s cool tho!

**Week 3**

For Monday the longer paper should have a new summary added to it. After that either more papers should be read or ideas can start to be generated for how to add time to Mungo. 

Best trying to come up with a general plan of action for the rest of the semester. Still a tad difficult as I’m not sure how the project will go so far. 

So maybe need it refined a bit but the general look for this paper is to 



* Extend the existing Core Calculus for Mungo
* Extend the runtime syntax for Mungo
* Provide an LTS (and the operational semantics)
* Extend the typing rules of Mungo (including the subtyping relation)
    * Including the typing of the runtime syntax
* Preserve Properties found in Mungo and maybe other time papers
    * Progress, Type Preservation, and subject reduction
    * Will need to look at the properties of time that we need to preserve 	
* Extend Mungo
* Perform Case Study

For next week I shall decide how I will add the notion of time to Mungo. This is best done by looking at how Mungo currently implements it and what parts would need to be added. 



* Typestate for a class is the <span style="text-decoration:underline;">local session protocol </span>(compared with MPS) for a class.
* The capture of time should likely be optional and will be captured by methods. (I,e. the stack example shouldn’t need to change)
* This will likely be an optional time constraint to the method construct 
* Look at the encoding in the local MPST and figure out where this would go for the notion of typestates. 
* The timed version of MPST gets rid of base send and receive and has them with just label selections and branches, doesn’t reduce the semantics tho.
* The typestate Mungo paper doesn’t deal with the notion of communication queues, and would likely not require this notion - adding it would change typestate quite a lot and its unlikely the queues are 100% needed for the notion of time, communication could use the queues but the current implementation of typesstate doesn’t seem to need it. 
* The only major change from MPST to the timed version is the notion of time constraints between the send and receive labels.
* The timed local protocols for Scribble again look fairly simple when updated with time, the send and receive actions have clock constraints and a reset predicate.
* Comparing a timed scribble local protocol to the scribble protocols StMungo uses, it has the clock and reset information before the action, this should just be converted into the typestate definition also.
* Can likely add a _delay_ expression to the core calculus which can be used to model how long some methods may take, better reasons to use delay are given in the most recent paper

**Week 4 **

Typestates deal with the local endpoints of a MPST, so only care about the order of methods for class that follows its local type. When adding time it is more important that the programmer works within the typestate specified rather than focusing on if the typestate is well defined as that is the global protocols job (think scribble).

For Tuesday the first part of the day shall be spent exploring the possibility of adding timeout for methods (if required) and what that would look like for the general syntax. 

As far as I’m aware the assumption is that I don’t need to verify that a typestate (with time) is well formed, and that it would be Scribbles role to do that. 

_Timeouts _



* ‘Timed Multiparty Session Types” only adds the notion of delays and doesn’t add timeouts. With only delays they successfully modelled the example process (worker, adder). 
* ‘Timed runtime monitoring...’ (The updated scribble) one which would be the basis for an updated StMungo checks that global protocols have wait-freedom, this could mean that delays were all that were required. They use a _timeout_ primitive that will interrupt an ongoing computation to meet an approaching deadline. Can look to update the core calculus with the idea of timeout but not sure how easy it would be to add the notion of it to the Java that Mungo can use. 

Overall I don’t believe that a timeout primitive is needed to be added for the use of static typechecking a typestate for a class. The delay primitive should be enough to model the passing of time for computations and as we assume the protocols are wait free due to the latest scribble paper when a class is facing an external choice it shouldn’t need to have a timeout for that process. Timeouts are used in the Scribble paper for runtime monitoring to ensure some computations don’t take too long but for static type checking. Blocking receives are used in the Asynch paper to help model dual non-wait free processes which is not the avenue I will be going down as this only works for binary session types which would restrict Mungo too much. 

If i was to add timeouts as a primitive it would require updating of the operational semantics plus may lead to different inference of typestate for an object. For example if timeout(method, 5) this would inferrer that method must complete (xm &lt; 5) so would likely say the previous clock constraint must have had a reset as well. 

For recursive protocols may need to think about Infinitely satisfiable to ensure they are regulate across different instances of a recursion. This allows only the first recursive loop to need to be checked, but can also see how Mungo does it and extend it. 

Inference of resets may be a slight challenge. The notion of virtual time constraints from ‘Timed runtime monitoring…’ may pose a solution. 

Subtyping clock conditions may be quite difficult, but what are we inferring? We may only be inferring the values of the clocks for a class, do we need to infer clock conditions themselves? Could leave the inferring of the clock conditions but not the values and then subtypes the value with a subtype with clock conditions?

Putting the subtyping on hold for a bit, confused how I would subtype the notion of clock constraints and resets. Asynch paper subtypes it but based on a relation that is about how time can progress on it, may be worth a look but changes how subtyping works with Mungo atm. 

_Subtyping _

Two main ways forward as I can see



* Can subtype following the rules found in the “Async” paper 
    * They give rules for when (v1, S1) &lt;: (v2, S2) so could use this to subtype (v1, S1) which we can infer from the class usage. Delays would be involved to infer clock changes, for now we can assume maybe only a single clock but I think the extension to more than one clock should be fine from the papers seen. 
    * They also include a S which is the subtyping rules of two clock conditions, this would be helpful if we were inferring a clock condition on the usage of a class but this seems like a difficult path to go down and it might be easier just to infer the time usage instead. 
        * If you were to infer clock constraints, it would likely have to the most strict constraint i.e. delay(5), which may infer a reset after the action, would infer the constraint (x = 5), which would be a subtype of say (x &lt; 6). 
* Infer time from the usage of the class and can ‘subtype’ (v,S) &lt;: (v’,S’) if the S is a subtype of S’ and v holds under the conditions of S’.
    * I.e. (x->3, [ true, {} ] t m (t’) : S) &lt;: (0, [x &lt; 5, {}] t m (t’) : S’) would hold if S &lt;: S’ and as v holds under the x &lt; 5. 
    * This way seems simpler and feels like it follows on from the work down in the other time paper for ensuring that timings workout but more research will need to be down on if this could be added to Mungo. 

Be careful, need to reread some of the rules I made to make sure they take into account the fact that the time constraints are optional 

Good work on rdy, join and more ideas about how subtyping will work.
