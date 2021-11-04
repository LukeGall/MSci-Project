

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

**Week 4**

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

_Subtyping_

Two main ways forward as I can see

* Can subtype following the rules found in the “Async” paper 
    * They give rules for when (v1, S1) &lt;: (v2, S2) so could use this to subtype (v1, S1) which we can infer from the class usage. Delays would be involved to infer clock changes, for now we can assume maybe only a single clock but I think the extension to more than one clock should be fine from the papers seen. 
    * They also include a S which is the subtyping rules of two clock conditions, this would be helpful if we were inferring a clock condition on the usage of a class but this seems like a difficult path to go down and it might be easier just to infer the time usage instead. 
        * If you were to infer clock constraints, it would likely have to the most strict constraint i.e. delay(5), which may infer a reset after the action, would infer the constraint (x = 5), which would be a subtype of say (x &lt; 6). 
* Infer time from the usage of the class and can ‘subtype’ (v,S) &lt;: (v’,S’) if the S is a subtype of S’ and v holds under the conditions of S’. This is current subtyping method I have chosen. Going through some examples if I were to infer conditions like above a delay would need to infer a time that would need to continue on to the next method, this could lead to over complicating the type inference system and have lots of case. This way however just updates all values in v which can then be carried on more naturally. 
    * I.e. (x->3, [ true, {} ] t m (t’) : S) &lt;: (0, [x &lt; 5, {}] t m (t’) : S’) would hold if S &lt;: S’ and as v holds under the x &lt; 5. 
    * This way seems simpler and feels like it follows on from the work down in the other time paper for ensuring that timings workout but more research will need to be down on if this could be added to Mungo. 

Be careful, need to reread some of the rules I made to make sure they take into account the fact that the time constraints are optional 

Good work on rdy, join and more ideas about how subtyping will work.

**Week 5**

This week is focusing on subtyping and the type inference rules that are going to get edited to include the notion of time. 

Should the typing of time values be optional for a class? Yes, for the stack example we don’t want to change the type it gets inferred too, this would not only involve rewriting rules that are sound but it would add irrelevant information to a class that would only increase its typing complexity. 

Should also have a look at join this week 

When adding a delay primitive it should update the time for every class path r. This includes paths where the class doesn't have a 'v' yet. This will cover the case where it is the first delay for a class that cares about time. One case that needs to be investigated is for a class that doesn't need time i.e. the stack class, time will be added to the inferred path for r but we would need correct subtyping rules such that a stack with clock values can be a subtype of a stack that has no clock conditions. 

Need to be careful, delay will need to add time information to every class path even if that typestate won't need it at the end. 

So I think I've figured out the right idea to add the notion of time. So a delay expression will take an input context pi' and output pi such that every r: C[S] is updated to be r: C[(n, S)] in union with r: c[(v + t, S)] for every r: C[(v, S)]. This allows us to add the notion of time when going from bottom to top adding each branch for typestates etc. Then for the subtyping, when we have the notion of time it is important this gets pushed 'down' when subtyping the next thing. 

Good work today in terms of getting my head around where to put the notion of inferring types for time. Feels quite simple in the end which is good, so will update the rules tomorrow and work through how the subtyping should work for the inferred types.

Work today should focus on getting the correct definitions down for the delay rule, seeing if any new rules need added to the class ones and editing the existing rules so they can deal with (v,S). Once those rules have been edited some initial subtyping rules can be drawn up. 

*runtime syntax*
Maybe need some slight clearing up on why the runtime syntax is needed and where I will need to update it, delay has been added so far which seems fine but I suspect more will need to be added later.

*things that are still a tad confusing*
* The need for s =sbt s'
    * Says that its so the caller (this) doesn't get its type changed but use the s=sbt s' so that it isn't viewable within the method?

The simple addition of the delay rule where the value of a clock gets added to a class definition x : c[(4, S)] works for a simple delay but then would cause issues for rules further along, such as call which expects x : c[s]. One possible avenue to fix this would be to introduce a new type Si (meaning typestate inferred) which would be very similar to a standard typestate but could have (v,Si) as the continuation. This would allow nested (v,S) within the inferred typestate which can then by used by the subtyping system. 

Another possible avenue of approach which may or may not be entirely feasible would be the notion of checking if it holds in the untimed scenario and then typechecking again following a more standard approach compared with the timed literature where we go through each class and we know which typestate each field is associated with a typecheck in a more "linear" fashion going through the functions and seeing if it matches the typestate it is applied to. However this idea may not be the simplest notion and may be unsuitable as it goes against the main ideas of inferred types in the Mungo paper. It will be mentioned here as a possible solution that could be explored later if the first avenue doesn't work.


**typestate-inferred**
A new type of typestate SI has been added which represents an inferred typestate from the expressions, classes etc. This typestate will be very similar to the typestate found in the original Mungo paper but with the addition of (v, SI) added to the definition of SI. This allows delays time values to be added during inference of a typestate. New subtyping rules are being added to reflect the new typestate. 

V will be a similar time function. When inferring this will just add a single clock value to the default global clock 'x'. When subtyping we will need to be careful to use correct properties to create and remove clocks from v when required.

We need to figure what clock values we have when determining subtypes. From some of the other papers it seems that they type a process against (v,T) where they have the initial assignments of the clocks for that Local timed type. So when checking if SI is a subtype of S we need to know which clocks are going to get checked in S i.e if it has a constraint (x < 4, y = 4) etc. 
* Can therefore find all clocks in declared typestate S by using a helper function OR
* We can keep track of a global clock G and whenever we find a new condition say pi we check the clocks in that condition and make copy of them that gets added to that v. 

A clocks function has been made that can get the sets of all clocks that have been given a condition in a declared typestate S. This helper function recursively goes through every typestate and updates a set holding all the clocks.

Good work for today, think I have all the subtyping needed. Tomorrow, should work on updating the inference rules such that they push up the notion of time. Then go through some examples. Join needs done as well. As do subtypes for inferred and inferred.

A subtyping relation for the clock value functions v has been defined which states v <: v' if all clocks in V are in V' and have the same value. V' can have more clocks in it (notion of superset) but the values must be the same for the clocks also in v.

Had a look at subtyping for declared <: declared, fairly straightforward in most cases. When subtyping the individual clock constraints it would just be that all valuations possible under phi must also be true under phi'. Reset predicates are more complicated, one avenue is to just have the set of reset predicate be the same if they contain the same clocks however this is a simplification and isn't accurate. A second avenue would be to have the clock constraints of the declared types be updates with the idea of a "virtual clock" where the clock conditions get updated based on the last time that clock was seen. This avenue can be explored further if it is more important.

Inferred subtyping has been well defined. Declared <: declared subtyping was slightly harder and proved to have some issues in it as mentioned above. Join for declared has been declared typestates with no major issues. Join for inferred types is slightly harder when it comes to joining SI with (v,SI) and another one as if the times are different it should return a set but this isn't defined for x: [(v,SI), (v',SI')] as say two different paths after a switch with different delay values. It makes sense that these should be a set tho, so will likely just update the grammar to allow c[SI, SI',..] etc but this may have more issues. 

For the start of the day tomorrow, have another look at how to fix join, if not too easy then best working on the next parts of the paper. 

**Week 6** 

First thing to do is update the existing notes so that an inferred type can have a set of Inferred subtypes which can show branches of actions with different timings. Rules will need to be updated for the subtyping to also reflect this. As well as the Join. 

Maybe ask for helping looking at how the enum attaches labels to the branches it could go down with the join. From the inference rules it is clear it doesn't attach labels to the typestate that a Enum method would progress to. This is because it uses joins.

Also maybe ask for help where the Fig 7 rules are used, I understand them but struggle to see when they would be used. 

In the Timed literature, they check the typing by adding the clocks to the local session type such as (v,S). Currently my implementation keeps the clocks on the inferred side of the subtyping rules when comparing if an inferred is a subtype of a declared. I don't think it makes much of a different but I could add simplification to the inferred type as it would only need to worry about integer timing values when inferring and then when subtyping it would add these values to the clocks held in S when moving to the right. Currently, the delay action would need to infer a global clock which could lead to a more complicated implementation of the inference system in Mungo.

Time was spent on Monday quickly editing the current typing system to match this simplification. Following the new refined version of the subtyping system, the join system is easier to define, based just on the value of t in (t,I).

Can now look at how the typestate inference rules would change. Delay rule is the first main one. All inference rules would need to translate S to I, but other than that and the change of rule New. Delay is the onl major update to the inference rules. 

When starting the subtyping of a class (in the New rule) we can add (v, S) or leave it as just S if we want to keep time constraints optional. The existing subtyping rules would need to be edited to allow this but it shouldn't be too much of an issue.

Inference rules were updated including the class ones. Good work today, even if a little sideways. Tomorrow should finish up the rules and then work on making some examples.

For the reduction relation rules (Fig 9) a new delay rules will need to be added to allow the typing context to reduce when presented with (t, I). I think that the delay rule should "sync" all the inferred typestates such that you only need to perform delay once. E.g. if we had x : Stack[pop : push : (5, pop)] such that the final pop is performed after a delay of 5, and we had y : Stack[(5, pop)] then if both of these paths are in the typing context we should first have to pop, push on x before we delay by 5 which will reduce y and x. If we could just use delay for any typestate then we would have to continuously reduce by using a delay each time (t, I) came up which doesn't match the expression labels we would go through. 

Above may not be required, so from the Type preservation property, a label reduction only looks like it will apply to PHI if we move from h,e using that label, therefore we only move that way using a delay once which will update all typestates that will have had that delay which is every typestate not an end, I think.

The delay rule does not add time to C[end], this is because this class has not be created at this stage so it wouldn't make sense that it would have the notion of time as the time constraints of a class are relative to it's creation. 

Fgi 7 contains typestate inference rules for class definitions, I originally updated these to be typed against the inferred type but it is more likely that it is checking the declared typestate makes sense. These will need to be looked at again in more detail drawing on properties found in the literature. Mungo does not provide proofs for this section, so I may not need to either however it is still a good idea to update it for the new version of S. 

**Point to raise**
Currently when checking subtype of I <: (v, S) it can also just check i <: S if S contains no clocks so therefore isn't timed. Currently multiple rules have been put in place to allow this optional change but it may be simpler to just type against (v,S) always even if it doesn't contain any notion of clock constraints.

**Inventory of Theory**
A short list to take stock of the theory that has been written in my notes and what else needs to be updated 

* Top level syntax updated +
* Runtime syntax updated ++
* Labelled reduction semantics - (don't think they need updated)
* Init for clocks updated +
* Operational semantics updated +
* Syntax for types updated +
    * May need to add C[(v,S)] as well
* Subtyping relations
    * Inferred <: declared +
        * When declared has time +
        * When declared does not have v - 
    * Inferred <: inferred +
    * declared <: declared - (Likely not needed)
* Join for inferred +
* Typestate transitions for Inferred and Declared +
* Inference rules for expressions +
* Inference rules for methods, fields etc - (Need to look at how to do this)
* Inference rules for runtime +
* Reduction relation on typing contexts +

Need to have a think about how the theory works when a user makes a delay in the method of a class. It should be okay but should run through some examples. 

When is the Class subtyping rule used? It is likely I only need it for inferred <: inferred but not for the inf <: declared. 

Should write up the free clocks function so I don't abuse notion in the clocks function. 

Runtime syntax is updated with the inferred type as this is used for the runtime typing later on. 


LaTeX file stuff to continue with
* Make macro for optional stuff +
* T's can just match in one of the subtyping rules +
* Clean up positions of the figures in the file
* Rename X in the top level syntax +
* Clean up clocks definition +
* Split apart on of the inferred rules +
* Typestate transitions for inferred and Declared 
* Typestate inference for the Methods + 
* Inference rules for runtime +
* Reduction relation on typing contexts
* Clean up
* Another read through 


**Week 7**
This week, time should be spent cleaning up the LaTeX file, working on how the inference system will work for classes and methods, and then write up some examples and work through them. 

*Inference for classes*
Having a closer look at the rules for figure 7, they more seem like they are focused on if the declared typestate is consistent, in this regard they more focus on if the typestate definitions match the inference of the expressions. So figure 7 will likely change as a minimum to use I and S when appropriate, this will require subtyping of S <: I, such that the i = S can hold correctly. This would certainty work as a baseline for checking if the typestate as consistent, however it is unclear how well this will work with more confusing examples. However it doesn't seem like a huge part of the paper so it may be sufficient. 

If I was to try and add more checks to the declared typestate, there seems to be two main approaches. 
1. Following the Scribble paper, it is important that the typestate is infinitely satisfiable, we can then check for feasibility using an alg. (Likely best option)
2. The Asynch paper uses the notion of past to stop junk types during an typing example. This may also be a solution. 

For the first part of the day, it was spent understanding the problem and potential solutions. The next part should be to update the subtyping rules, and explore method 1 first. 

For my understanding so far, the reason the method-st holds is because it takes a full typestate declaration as input to the method expression and basically wants it to not majorly change it when the expression is finished. Therefore the inferred type would still have all the method declarations from the input S, when it came out the other end as S'. I was originally confused how it would infer all the correct paths for a typestate without realising that because it takes as input the declared definition it just needs to not modify it a huge amount. 

Possible Solutions to the method inference 
* At the start of a call method we convert C[S] into C[I], this allows the existing rules to apply when working out the typing inference for the expression, so we get C[I'] out the other side. We can then use I' = S and it should hold with the current rules allowing declared <: inferred. 
* We use the existing system and rules and then have time checking another way (Not ideal). 

The current system were declared is subtyped against inferred isn't perfect, more work needs done to figure out when it makes sense.

Also need to have another look at how the join system works, still confused how enum labels are computed. Have a look at the inferred type example and then try to subtype it.

The current subtyping rule for the branches is likley not correct but it is difficult to know when this rule would be used as it isn't clear how enum values even subtype. Can leave this part for now and talk to Ornela for more info.

*Tuesday* 
Focus more on getting some examples written down and then write up the logic I did yesterday, need more help with it so try and not spend all day just banging your head against it. Focus on getting the current rules up on the LaTeX and then get some examples down, this will help figure out if there is any holes that needs filling. 

An example needs worked through, but I'm not sure how delays will work within a call as it would likely change the typestate of a 'this'. Could maybe look to change the delay rule such that "this" doesn't get updated? Not sure how that would work. 

Even a simple example feels quite confusing, not sure how enum correctly get subtyped. As the inference rules for the expressions don't attach labels so I'm not sure how the subtyping works :(.

Things to try and figure out
* How does the enum subtyping work, with my current example using a switch it needs to match against an enum method but I'm not sure how the join of the switch branches allows a subtyping with the enum method 
* How would a delay in a method call affect the "this" typestate, would this stop the call premise from holding? - yes

After lunch, do a much smaller example of inference, mainly the delay one, possibly a switch. 

Can do a couple of small subtyping examples but not a huge amount, inference is more important. Current example is quite large but useful. 

Main points that have come up during the week so far 
* Inference for methods is possible if a tad clunky, not sure how the checking works and why it needs to be there. Can likely add alg to do the time checking for inf sat and feasibility. 
* Big example made for inference which should be correct with a bit more clean up.
* Subtyping of enums is not too clear but will work on looking at how it works. 


A slightly slower and more frustrating day but still good work done, tomorrow should be spent writing up rules and thinking about which way to go for the Method-st rule based on how delays in methods should work. 


*Wednesday*
Two main ways to progress with the Call and Method-St parts
* Part one based on keeping the abstraction principle (whatever this is) alive which does not allow delays 
* Part two allows delays but only delays to occur between I' and I. 
* Have a look at the branches rule
    * The branches rule would only work for a switch were each part has a different timing. The grammar has been updated to allow a set of {N, (t,I)}. The join rule was updated for this plus the changing of the S-Set subtyping rule. 
* I don't think the S-Enum rule is required, likely only needed for I <: I

Part one is the easiest route and would require the least amount of new work. 

Part two is probably the most handy. Work will be done on both. Part 2 seems easy to implement and will be both will be written up in the LaTeX.

Good work today, got most of the updated theory down and fixed a few issues with the grammar and how the joins would work with the switches etc. Updated a lot of stuff in the LaTeX file as well.

*Thursday* 
When subtyping something with no time, it may be easier to just have an empty set of clocks. *Point to bring up* 

