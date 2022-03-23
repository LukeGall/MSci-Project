

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
* Typestate transitions for inferred and Declared +
* Typestate inference for the Methods + 
* Inference rules for runtime +
* Reduction relation on typing contexts +
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

Have another wee look at how the reductions work on typing contexts, think it's all good but take another look at the delay rule. Happy with it.


**Week 8**
*Inventory of Theory*
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
* Inference rules for methods, fields etc +
* Inference rules for runtime +
* Reduction relation on typing contexts +

Got some good work done in the morning, had a look at and starting writing up the definitions for inf sat and feasibility. This is a fairly large section which can be included or not with definitions adapted from the Timed MPST paper, but it feels feasible to add some algs to check that the local typestates created created by users are feasible. The afternoon will be spent cleaning up the LaTeX to get it sent off.

More work was done in the LaTeX file to explain some of the logic to make it more readable. Great work today, will continue LaTeX file tomorrow, try and send it off then work on the examples afterwards.

*Tuesday*
LaTeX file was cleaned up in the morning, all the definitions have been written up and it looks pretty good.

More work will be done on some examples as well as seeing if more updates need to be done on the feasibility stuff. Some more work was done on how to do the inf. sat. and feasibility checker but easier to do once I start working on Mungo itself. Inf. Sat. definition could definitely be included in the theory but the feasibility checker it is a bit longer winded but would continue to follow the ideas from the MPST paper as this is what the scribble paper is based on. 

Not any major progress with more examples, need to have a better think of what example we would want to use for Scribble, Mungo inference, and then subtyping. A combination of examples of a local type that connects to other classes may be helpful, otherwise we can just focus on typestates for single classes.

*Wednesday* 
Write up some meeting notes and have the meeting plus the reading club thing. Focus on getting some good examples down on paper, looking at the examples used in other papers. Then focus on maybe writing an alg down to do the inf. sat check as this can certainty be written up for the report due this december.

My rdy function looks like it is slightly off. We want it that for a set of methods H we can progress time as long as one of the H is possible to progress. If H is an enum type then, all time constraints of the labels must be valid. We can't progress such that one of the constraints of kl is invalid as we don't know which one we would get in.

Good work today, got more written up in the LaTeX doc that I should be able to send off tomorrow and then I can start the Lit Review.

*Thursday* 
Final changes to initial definitions doc sent off. 

# Lit Review 

Taking advice from Ornela, i'm going to try and split the lit review to follow a kind of 'Related Work' section that you would find in research paper. To this end the review should be split into the main sections of research that covers the project. This includes the sections of 

* Session types (primarily multiparty session types)
* Typestates 
* Time
    * Including time in a multiparty envo but focusing first on CTAs and timed automata

*Session Type section* 

Refs taken from Papaya 
16,17,18,10,35,14

Refs taken from Mungo 
28,15,14,17,38,26,9

The session type section of the lit review will focus on covering what a session type is with points to the relevant literature and focusing on how session types can be used and the use they have in context. 

*Time Section*
Papers have been gathered for the time section. This section will first introduce the notion of time from Timed automata then discuss some of the research areas that have used time, mainly communicating timed automata, a few other areas where monitoring is useful and how it could have provided a base to use and then the mulitparty stuff with scribble 

*Typestates section*

Refs from papaya
32 - original one - yes
3 - Mungo one - maybe
13 - covered already
21 - Mungo paper I use
24 - Yes
25 - maybe
1 - yes
33 - maybe
8 - yes
9 - maybe
5 - yes
28 - yes

Just write up the paragraphs and then move them around afterwards.

*Notes for reading club* 
Focus on points from the multiparty session types first. Going over how they add time and stuff like that. The general bullet points below can be the rough slides that will be covered and then can have some speaker notes so I don't forget what I'm talking about 

Timed Multiparty session types 
* This paper follows research from multiparty session types which describe communication protocols among two or more participants from a global viewpoint. 
* They design MPSTs with clocks, clock constraints, and resets. 
* Show off the example they have with the ocean one and example how it works 
    * But not too much as not the important thing to touch on
* Give the definitions of clocks etc and time, including clock assignments etc. Go over the clock constraints too. 
* The syntax for the timed global types can then also be given
    Focusing on showing that actions are annotated with these time actions
* Clocks are unique for the participants 
* As we have local types make sure to touch on the fact that local types are projected onto them. Can give an example of the session type projection with the keys of the time constraints. 
* Can then briefly touch on the semantics and show the labelled transitions. Focusing on what 'rdy', ensures that ready actions aren't made unsatisfiable.  
* They then show an update of session processes with delays, won't cover too much about this as this paper is more the preamble to the main paper but I will show the syntax which is fairly understandably for the get go.
    * Can likely touch on what the delay primitive is however
* Can touch lightly on that they go on to show time error freedom
By discussing feasibility and wait-freedom, (also maybe inf sat too)

Timed runtime monitoring for multiparty conversations (updates scribble)
* Start again by showing the example they use, better notation this time. 
* Go through the steps the paper presents to use their tool 
* Show the syntax of timed global scribble protocols. 
* Show give an example of a larger protocol to make sure it is understandable 
* The formal semantics of the scribble stuff is adapted from the previous theory 
    * Can discuss a few steps however, i.e. how they make sure it is async
    * Might want to discuss how multiple actions may be able to fire in this paper which is why rdy is needed 
    * Rdy for choice actions only needs one to be available (like my rdy)
    * Continue the slight discussion of the protocols and wait it requires
    * Prevents violations from the protocols itself 
* Can then touch on the local protocols and show how they aren't too complicated and follow the global ones quite nicely 
* Don't think I'll touch on the merge stuff
* Projectable stuff takes a couple of page so may be an important one to touch apon just to show that a good amount of theory has been thought of
* Formal semantics of local types are again v similar to global types so not much needs to be covered of it
* Configurations are given which is just the joining of multiple local protocols and the async message queues
* Touch lightly on correspondence 
* Provide an alg to check wait-freedom and feasibility
    * Include inf sat
    * Includes virtual time checks and stuff
* The implementation of the stuff in python 
* Including showing off some of the code and stuff like that 
* Runtime verification stuff
* beanchmarks results 
* Temporal patterns is a cool section






*Have a look at rdy* 
Can have another look but if I understand it correctly, mine still works as it says that the ready actions are just the branches that typestate could check so it would need to satisfy one of them or like a whole enum one. Can have a look at it on Monday, I don't need to worry about it on Sunday. 

My ready function does work as expected. An interesting point of note is that the implementation in Timed MPST is that any of the constraints on a receive branch can be valid. This is unlikely to make sense for our implementation where we require all branches of a enum to be valid. 

Subtyping rules were incomplete for the enum case, this has now been added. The current system uses joins to make the inference system easier to understand. I think my rule would be okay to capture the requirements but can look at some more complicated examples in the afternoon to check this

Also need to try and change the notion for the constraints

Updated some of the project definition stuff


*To do - Examples*
* Write up the last of the protocols for the Scribble example
* Write out updated stack user / accumulator example
* Write out derivations, checking that my rules work as expected
* Write these rules up in LaTeX - after proofs

SMTP commands that involve time
* An SMTP client must provide a timeout mechanism, must use per-command timeouts
* Initial 220 Message - 5 mins
    * Client should wait 5 mins before it decides if it would get a failed TCP connection. 
* MAIL Command - 5 mins
    * Sends a mail request to the server, server replies with response. 
* RCPT Command - 5 mins
    * Server replies with an 250 OK or 550 reply
* DATA Initiation - 2 mins
    * Server returns a 354 intermediate reply
* Data block - 3 mins
    * Awaiting the completion of each TCP send call transmitting a chunk of data
* DATA Termination - 10 mins
    * Final period terminating the message data
* Server Timeout - 5 mins
    * SMTP server SHOULD have a timeout of at least 5 mins while it is awaiting the next command from the sender
* sender must delay retrying a particular destination after one attempt has failed. The retry interval should be at least 30 mins


Aside, the VBra rule in Timed Multiparty Session Types, states that a branch for the sessions, the time must be valid under every receive option. Similar to my Enum methods.

As all branches need to be valid for an Enum method for Mungo and compared with the VBra rule. It makes sense that we change it to a single time constraints. This would act as the "meet" of the constraints that we could have in each branch as we would need all these constraints to be valid in this translation as we not "receiving" a message per say but calling an Enum method so it doesn't make sense to have a time constraint on the branch label which we can't know ahead of time. This is good reasoning however, the time constraints, while forming a meet, have unique reset constraints based on the branch that has been taken as drawn up by one of the examples so it is important that the constraints are present on each branch. This will still need to meet of the time constraints but allows resets on each branch which is required (this follows time and Mungo). One issue with this is if Join makes sense in the typing context. As if we join branches at the top which look similar for the first few contexts we may miss a reset which is needed for one branch. For this case, join will be simplified but this may affect the proofs further down the line so more info will be required about what Join does and why it is needed. 

Need to have another look but if we are assuming wait-freedom, (which we are) then the timeout reply means we have a timout to check if the message is there but there would be some time constraints on the server side to make sure it has sent ok. There is an example of a timeout in the Timed Runtime Paper which has the timeout on the client side, which I would need to do to, just not too easy to figure out how this would work or not. As it's the wrong kind of constraint, a timeout means the Client is waiting 5 mins to get a response, but this isn't wait-free. 

It is important to note that this system needs to be synchronous and therefore wait-free as a result, as if client calls a message to get the method then it syncs with the class it is connecting to (i think).

As methods are synchronous in design the idea of a timeout doesn't naturally correspond to the design we have of Mungo, I don't think. 

As this system will be synchronous we can't really represent timeout in the same sense as the SMTP client. But we can still put the timeout on the client side such that it reads and checks the message sent by the server. We also assume messages are wait-free as that is the protocol constraints we are expecting from T-MPST so timeout in terms of waiting for the message doesn't make sense for this either. So it would work but more put a constraint on the user calling the check to see what the message is. This check would be a choice but both messages would need the same timeout as we are just checking for a response. 

Good idea to discuss this at the next meeting (which will be in Jan) but no major issues with changing the system to simplify the enum method call due to the existing Mungo system. However the notion of a timeout in SMTP won't be directly captured by this timing system, however the benefits of the timed system as still present. 

One slightly odd way to do it, would be to have the message polling method (receive_fromServer) be a choice of available or not and then this could only be called up until the timeout, again this isn't perfect but would work as a possible hack. I think the issue with timeout is more around the fact we are dealing with method calls in a synchronous setting, can't really get away from it. 

Simplification of typing constraints on ENUM will not occur and the join will be simplified to ensure correct typing of possible resets depending on the response from a message etc. Labels are not needed to be added, as far as I'm aware as the subtyping relationship will find the correct paths for them to take (I think). So only change is to make join more simple and not to join similar typestates at any stage based on method calls. This might cause issues as we could have a separate class that has the same typestate and is unchanged in the input for join and is then duplicated for the join. Or not depending on the way sets work. If we assume that sets work as expected then it should be okay as we won't end up dealing with duplicates however this isn't ideal.

So two possible paths 
1. Can go for the simplification that Ornela discussed as we may not need to bother with specific resets for branches but this is quite a large simplification and not brilliant as we may lose some expressivity that is present in the descriptions of the time stuff. 
    So the question is, do we need separate branch conditions for a external choice? Yes I'm fairly sure we do, it would be important to have on an enum the ability to switch to either end or recurse back to the start. 
2. Join is "simplified" such that we keep the current typestate definitions but join doesn't join all the method branches as we would need them to be individual for the reset of each branch. This could maybe work if we changed the definition to only join the entire typestate definition instead of having it such that parts are joined i.e. (push:pop) and (push:push) are just union-ed instead of being (push: (push,pop)). 

Neither of these paths are entirely clear and may cause some difficulties. May be best asking Ornela? 

Current path changes the join to just a union unless the typestates are identical (i.e. unchanged) in which case they are simplified to ensure we don't have duplicates. Maybe try to call Ornela tomorrow. Good work today, shame it was another slight hiccup with the theory. 

So to double check the theory I've done and make sure it makes sense to change the join can just double check exactly what Join does and if it'll be okay to make the slight simplification.

The main thing Join is doing is joining the inferred typestates that have been found for the different classes. Including the set of method calls that a typestate may send to. It computes an upper bound with respect to sbt. It makes a common typestate for multiple execution paths. 

It is used in Switch and If, to compute the common typestate of the different branches. For switch (the main one we care about) it then attaches this joined typestate to the method call (ENUM) so it has multiple branches it then goes down. This can also change all class types. So even if we are joining the type for say 'X' it would also change 'this' if we added time for example which would mean we had a different branch if the other branch didn't have this time change. Need to be careful that we add new branches as a union of typestates but make sure identical typestates don't get unioned. We just don't want to join on the continuation typestates as we need this for resets and constraints on enum branches. Happy with new definition of join, continue with examples and then go through every subtyping rule and think when it would occur.

Join won't need the optional time constraints as we only join inferred types

Main examples written up. Subtyping example can follow from the inference stuff and does seem to work well. 

Not 100% sure how the NEW rule works for fields of a class. Does it call the constructor of the class which in turn gets the subtypes of the field uses? Not 100% clear how this works. From the example provided about StackUser2 the state machine drive the checking of a field if it has a typestate for the containing class. This may be rules in Fig. 7, more work can look at it in the future but I think the type stuff here checks that the class is consistent and that the methods here wouldn't mess up the field types. *CHECK IN SEM2*


Notes about the proofs 

*Mungo paper*
* Typability of heap update
    Ensures that the heap update rule can be typed, fairly straightforward
* Replacement Lemma for expressions is given
* Substitution lemma 
    1. States that substituting two elements of the same type doesn't change the typing of the expression
    2. Substitution can occur via recursion and the types would be updated by the continuation 
* Subtyping and Join
    The join of typing types / typing contexts is a supertype of the joined elements. Makes sense and states that the proof just follows from the definition of join and subtyping. Can maybe get away with it for mine but need to be careful with new Join and will
    *MIGHT NEED I<=I' FOR THIS*
        Currently have the system with an equivalence relation in place but may need to change it back to the subtyping version (not a big deal). Currently both are in place but can remove one later on once it is more clear what proofs I will need but I'm fairly confident I will need to add the subtyping system
* Typeability of subterms
    "By inversion" is used a lot, as far as I'm aware it means to go "up" from the typing rule which is what I use a lot already
    The proof of this follows by using the typing rules present to drive the base Expression (E a hole) and then showing it is okay via the inductive hypo. It makes sense and is easy enough to understand


Progress and subject reduction
This is theorem 1. Basically a progress property which states that if we have a label such that an expression can change to a new expression, including its typing then U' must be a subtype of the old U. This can then be used for a corollary
Things that need checked in this proof
* Label l
* New heap h'
* e'
* New U' value and delta'
* U' also needs to be a subtype of U

* The proof uses induction on the structure of the expression e. 
The inductive case is the Expression context (E). They use the Lemmas given above to prove this. They give the base context which is E[e1], use config rule to get it with a value. Then use Lemma 5 to pull out the e1, use inductive hypo to get e2, and h' etc. Then can then type e2, this allows the premises of R-Ctx to work given them a translation from context to context. 

They then give the BASE cases for this proof. They started with the inductive case and show the base cases which shows how it would work for the different contexts of e. 

* e = v;e'
    Use a reduction rule to get just e' on its own
    Config rule to get the typing, then Seq rule can be used to get the typing of v and e'. As v is a value you can then show that the typing output of e' is just delta which then they use config to show is all good
* e = (r.f = new C)
    Use the new reduction rule, this updates the heap to have the path pointing at a class. Use the transition rule for typing contexts to get it to the typing that the NEW rule expects. 
    * Transition h,e using a label l (r.f new C)
    * New typing context for r.f = new C using CONFIG rule
    * Above is used to get typing using rule NEW, they then show the same transition between the two typing contexts using the same label l
    * Prove that h' holds under the new typing context
    
    l = r.f new C
    h' = updated with new path
    U = void as it's a new
    e' = * (void)
    delta = r.f: C[end]
    delta' = r.f: C[S]
    U' = * as it's void
* e = (r.f = c) 
    l = tau (silent action)
    U = void
    Delta'' = Delta', r.f : U'
    Delta = Delta', r.f: U'
    h' = updated with new path
    e' = *
    U' = *
* e = (r.f = r')
    l = r.f=r'
    h' = h{r' -> null} for linearity 
    U= void
    Delta'' = Delta1, r.f : C[S]
    Delta' = r.f:C[S], r:C[end]
* e = r.m(v)
    The most complex one to understand (which makes sense). 
    l = r.T m T'
    h' = h
    e' = e{v/x}{r/this}@r

    Use a label reduction to get h', e'. Then you can use config to get the e'. Then use the config rule to e on its own which you can then type to get delta and delta''. Can get Delta' using a transition with the typing context, then need to show that h' and e' hold under the typing transitions (hardest bit).

Probably best to leave the rest of them there. I understand the general idea of the proof, every case follows a similar pattern but can be a bit difficult to see after looking at it for so long so best reading other paper and then writing down how it would work for mine. If I have correctly extended the current system, the existing proof style could likely be used with an update to the cases and proofs. 

Would be interesting to see how I could show that I <= S was tho as this is only quickly touched upon in this paper. 

Plan for proofs is to follow the approach by the Mungo paper as this is the one I've extended so it follows to use the same proof system. Care will need to be taken on how the subtyping works with I <= (v,S)

Good work this Semester!

# Semester 2 
Proofs will follow the (rough) plan I have discussed above. Similar with the rest of the paper as this is an extension of the Mungo system the proof style they use will also be closely followed. While Papaya writes the proofs slightly differently to Mungo, the main ideas are still present but it is easy to focus on the Mungo system as the type system is close to mine. 


A note was made in SEM 1 about the new rule so some quick work will be performed making sure I understand what goes on there. Easier seeing how the Mungo implementation works out this part. I think it would work it out at the constructor part of it when it creates the fields using New. Fig 7 checks consistency stuff as well but easier to see during implementation. 

U ::= C[i], taken out the C[S] part, don't think we want that typed there? Maybe, but not too sure tbh. U is to be an inferred type so we don't need it to type C[S].

Working through the proof and so far there is no major issues. Still think the main proof I would need is to show how the subtyping works tbh but not sure how that would be approached. But so far the main proof works nicely! 

When getting to the call case of the proof I noticed a slight issue near the end where it uses S = S' however I don't have this premise. After some initial work that didn't go anywhere I noticed that my call rule was mistyped in my definitions and that it the last premise should read 

Delta - v : U' - Delta2, r: C[{T m(T') : I'}]

instead of I, this has been updated and fixed the issue I had with my proof.

The proof contained in the Mungo paper seems to work for my system! 

Work will continue today working through more of the examples shown in the extended Mungo proof paper and having a look at what additions need, which seems to be the subtyping proof and the delay case so far. 

Not sure how the subtyping proof would work. It may be possible to not have one (as Mungo doesn't) and leave this as to be seen (future work?) but the delay case seems to be done now. A little bit more difficult to see at the end part but think it all works okay. The start is aokay, Not sure if I would need to prove that Delta'' = Delta' as it seems obvious? As it's just the same rule but in reverse, last case when proving the heap' holds was a little harder but I think the idea was to show that it works using Object for a random R, we then know it works for every R so can use heap again for the updated Delta' and then config. 

    
Will spend the next day writing up the proofs on paper plus some of the examples to show off. They will be re-written in different wording but will mention if they follow work completed by Mungo previously. 

Good work so far, a wee bit slow but after lunch can write up the rest of the delay case, clean it up and then send off to Ornela, best explaining what I did then can move onto writing up the example stuff in LaTeX too. Will likely need to re-write the proofs later but will keep them as close to Mungo stuff as possible right now as tbh not huge amounts of changes. 

Need to have a quick check to see how the end state is reached in the examples they use to just make sure my example is written correct. It works but need to be sure I write it properly. 
    

With the typestates that users can create we are assuming they are infinitely satisfiable and that they have wait-freedom. If I have time it is *important* to include some algs that can check this when constructing the typestates but it is mainly an assumption that would be present in this typestate protocol as we would convert it from the new scribble. From some of the notes in my book (~page 60) it is possible to look at the work from the MPST-T paper and include the checks to see if a typestate is well formed however, as this isn't the main point of Mungo it can be left as future work later down the line was the main important parts have been completed. Inf sat should likely be checked and added to the Mungo system itself, it isn't something that can be added in the theory to check this property. 

*Update to the proofs*
In order to update the progress proof to discuss time, two extra (fairly simple) properties are mentioned which is more coverted by the subtyping system but can be mentioned here. 
1. For any typestate in Delta that is updated in Delta', the time contraints still hold. 
2. Global Time ("held" in the typestates subtyping reltion) only progresses forward or stays the same. 

Update to the update is to change the language to being more formal, work should be spent writing this formal change up and ensuring it still makes sense for each case. So far it is fairly clear that each thing holds just by the notion of the subtyping relation holding so it isn't a big worry, just make it clearer. 

Rewrite for proofs was completed, they read differently to how they were first presented in Mungo and the time proofs have again been updated to match the new formal definition. 

*Implementation notes* 
Currently having issues getting Mungo to run on existing example files, it doesn't seem to be able to "see" the files around it. It can't find types unless in the same file and can't find the protocols in the same dir. 

If all files are given to mungo one after the other it seems to find them. Not sure if this is a temp fix however. May be something that should be fixed later.

It's important that once I begin to edit and create different stuff for Mungo that I ensure that correct documentation is taken so when it is updated to the site people can use it.

# JastAdd Notes
* Based on attribute grammars and object-orientation
* Props of abstract syntax tree nodes to be programmed declaratively - attributes
* AST nodes are objects, so with attributes it is like a oo graph model
* As it is more focused on declarative programming, the OO becomes extensible, can just add new attributes, equations, and syntax rules. 
* The user can organise rules arbitrarily to form modules (may mean mine can just fit in some where)
* The abstract grammar is written in classes so slightly differently to EBNF
* From this abstract grammar, an API with constructors is generated for building AST nodes and methods for traversing the AST
* A good example is shown in the tutorial sheet, looks like it would be easy to get different parts of information. 
* Can add some ordinary java methods in a separate module called an *aspect*
* Aspects use *inter-type declarations* to add methods to existing classes
* I suspect Mungo uses a parser for the main method itself but this is something I will need to explore more of
* Two different attribution mechanisms - synthesized and inherited (not OO related) attributes
* Synth attributes propagate info upwards, inherited downwards 
* An attribute is *a = e(b1,...,bn)* where is the attribute, e is an expression with bk attributes in the AST
* The attributes and equations are declared in AST classes, so can think of each AST node having a set of declared attributes and equations
* Each attribute is declared synth or inherited 
    * synth attribute is defined by an equation in the node itself, if we have all the info in the current node or its children
    * inherited attribute is defined by an equation in an ancestor node, if we need info from further up the AST tree
    * eq defines the actual equation for an attribute 
*  E.i defines an inherited attribute, meaning *child.i = expression* but doesn't need to be the immediate child and can be further down the tree.
* An attribute is allowed to be a refernce to an AST node
    * Can therefore connect different nodes to each other and form a graph 
    * Can use referenced objects in equations etc, so we can have non-local dependencies 
    * this, meaning a reference to the node itself
    * k.v, accessing the v attribute of the node referred by k
    * i.G.t, accessing the t attribute of the G child of the node referred to by i
* Attributes may have parameters
* Think declaratively, think of the properties we would like to have computed
    * A bit harder to think of how to do it in practice but easier once I get a grasp of how Mungo works
* An attribute should be synthesized if the subtree contains all the info it will need or it can be inherited if there is more information outside that subtree that is required. 
    * Can also make a mix of both if we need some info outside the subtree and some inside
* We can view attributes as (abstract) methods of AST nodes. 
* questions are like method implementations
* When accessing attributes, java method call syntax is used.
* For equations the right hand side is written as a java expression
    * Should be a side effect free change
    * Equations represent definitions of values, not effects of execution
    * Careful about this because equations aren't performed in the order that may be expected so can lead to subtle bugs 
* From the example we can see that due to the declarative nature "functions" can be declared after they are called or needed and are added to different nodes as required. 
* syn, inh, eq. Where syn, inh are what we expect and eq defines the value of an attribute 
* Only local vars can be modified  
* equations for inherited attributes look like method chaining 
    * Allows attributes for sub nodes to be defined 
* For composite attribute values like sets, lists, maps etc. These values are often sets of node references. 
    * When accessing composite attributes only uses mutable operations during construction of the value i.e. Can construct a set by adding elements to a freshly created set object
* Collection attribute has a compositive value that is defined a combination of contributions, they can be located anywhere in the AST.
    * Each contributing nodes declares that it is contributing to the attribute 
    * Not sure if this is the type of attribute that I would need to use
* Circular Attributes
    * When we have a property depending on itself 
    * Need to use fixed-point iteration
    * Vars are initialised to some val, and assigned new values in an iteraive process until a soltion to the system is found
    * Might need this for recursion but best looking at how Mungo uses it
* Rewrites
    * Allow sub ASTs to be replaced conditionally, depending on attribute vals
* Refines
    * Equations in existing aspects can be refined in new aspects, similar to overriding without needing new subclasses. 

*Syn attributes* 
If we have a syn attribute x of A then there must be eqs in the subclasses of A defining x. so 'syn T A.x()' declares an attr for A that all subclasses must provide, i.e. it's like an abstract method

'eq A.x() = Java-expr' defines the equation for the attribute and it's children
'syn t A.x() = Java-expr' declares a syn attr but also the default equation

*Inh attributes* 
'inh T A.y()' y is an inherited attribute in class A and of Type T. Must be classes defining y in all classes that have children of type A, so like the opposite of syn. The 'eq' must be located in the parent node, not A itself. 
'eq C.getA().y() = Java-expr' - defines the value of the ingerited attribute y() of the A child of a C node. This executes in the context of C

*Col Attributes*
Have composite values defined by <contributions>. They are similar to synthesized attributes which add their val to the coll attr value. Contributions may be located in any node in the subtree of the colletion root node. 

'coll T A.c() [fresh] with m root R' - cool att with type T on A with name c(). Fresh tells us how the intermediate collection result is initialised, so an empty instance of the result type in this case. With m, specifies the name of a method to be used for updating intermediate collection objt. The root R specifies the collection root type. Coll mech starts by finding the nearrest ancestor of type R, then the subtree from R is search for contributions, so doesn't need to be the subchild of A. 

'N1 contributes value-exp when cond-exp to N2.a() for n2-ref-exp;' - contribution decl. N1 is the type of AST nodes that provide a contribution, value-exp evals to an object to be added to the collection. Can be conditional. N2 is the node type where the collection is declared, a() is the name of the att. N2-ref-exp which evals to a ref to the AST node that owns the collection attr. Use this when the collcetion root is different from the target.  


*JADD and JRAG Files*
- jrag for declarative aspects, when attrs, eqs are added to the AST classes
- jadd for imperative aspects, where you adds fields and method to the AST classes
This is just best practice but best to be followed this way

*Rewrites*
rewrite A { to B {... return expr;}}
An A node will be replaced by the node specified in the Java expression expr. It will happen when the node is accessed (by a get() method -> might help explain the large getGraph file?)

*Circular attrs*
Attrs can be circularly defined, meaning that the value of the attribute can depend on itself. They are evaluated iteratively, starting value given in the declaration of the attribute
'syn T A.x(int a) circular [bv];
eq A.x(int a) = rv;'

starting value is bv, an expr. Rv may depend on rv.

*AST Notes*
The syntax constructs discuss a generated API for each construct, would need some more info on what this is. Seems to generate class code for it

ASTNode is the parent of the AST graph

* abstract A; - makes an abstract class of A
* B: A ::= ...; - class B extends A, B corresponds to a production rule of A
* C: A ::= A B C; - C has three children of types A, B, C. The api has getA(), getB() methods
* D: A; - D has no children, it's an empty production of A
* E1 ::= A; - E1 has a child of A, so 'class E1 {A getA();}'
* E2 ::= [B]; - E2 has an optional component of type B
* E3 ::= C*; - List of zero or more C nodes, the java class uses a List
* E4 ::= <D>; - Has a token component of type D. So has a string of D;
* A ::= /D/; - Nonterminal attribute D. D is computed on demand
* F ::= Foo:A Bar:B - names children of type A and B
* G ::= Thing:B*; - can name lists too
* Can also type  tokens too

* classes are inherited by subtypes, so have the children of classes too
* Can add more children but not remove them

* JastAdd generates the get methods to get the children of a class, so need to use the (a.hasB()) etc if using optional attributes 



################################################################################
<Notes on Mungo system>

If we give the -pi flag mungo spits out the inferred type of a class. One of the first things we can try to do is make this spit out the inferred typestate with delays.
# src
## jastadd

*frontend*
### Annotations.jrag
* provides an Annotation aspect that checks for typestate annotations on the 'Modifiers'
* Provides two getTypestateAnnotation attrs for the Modifiers node
* Has a rewrite for 'ClassDecl' to rewrite ClassDecl into TypestateClassDecl

### Complication.jrag
Hard to see use atm but likely easier later on
* provides a 'Compliation' aspect
* refines 'NameCheck' to a new attribute on this compilation node which is a collection of 'problems' 
* Provides some public methods that return some error info
* Provides a protected Program.compile() attr
    * compiles the different files on the path 
* Program.collect()
    * Collects semantic and then typestate errors
* Program.compile(String[] args)
    Adds the args given to the compile paths and then collects them

### CreateTypestate.jrag
* TypestateDecl.createTypestate()
    * creates a initNode and then adds new typestate
* State.CreateTypestate()
    * Something about LoopNode
    * Add next typestate
* TypestateMethodList.creteTypestate()
    * New loopNode then adds all the typestatemethods to this loopnode
* EndTypestate are just EndNode
* TypestateMethod.createTypestate()
    * returns a newMethodNode with different TType, access etc
* TypestateSwitch.creteTypestate()
    * New switchnode
    * get creates the typestate for each switch case and adds this to the switchnode
* TypestateLabel.createTypestate()
    * gets the statenode if it has one or creates a typestate whatever the target is
* TTypestate.createTypestate()
    * new loopnode and addNext of getTypestate (maybe the current node?)

### Debug.jrag
* Program.debug(PrintStream printStream)
    * Rewite (not sure what this function is) but I assume it prints out whatever the printstream is from the program declaration (which I assume is the top level node)
* TypestateDecl.printState()
    * Gets the id of the initial state then prints out all the paths of the node
    * Gets all the states of the typestate and prints out those ids plus the paths
    * Assuming this is the declared typestate not the inferred one but not quite sure

### Enums.jrag
* A collection of enum labels which is a hashset of strigns, looks to be defined at root but would need some more info on how colls work 
* An inherited attribute for body definitions in the subtree of *EnumDecl* such that the bodyDecl has Enum ancestor as this

### Errors.jadd
Not sure what the difference is between jadd and jrag but will check the docs later. Seems to be the file that collects semantics and maybe the typestate info and reports the errors

* Provides some protected methods (maybe attrs) for typestateCheck and semanticCheck that returns void
* So collects all the semantics and typestates of the ASTnode (not sure where this is, maybe root?)
* Provides a TypestateError abstract class which has fairly standard error properties that it can then print out
* Provides info, warning, semantic, syntax, lexical, and symbol errors for typestates
* Declares a collection on the compilationunit that will hold all the typestateerrors as an arraylist
* The compilationunit and program are given a numWarnings property
* Program.errors is set as an array of typestateErrors also
* Program is given a hasErrors and hasWarnings attributes
* ASTNode class is given an addInfo, warning, and semanticError methods 
    * Where addError gets the compliation unit ancestor, sets the filepath and adds a new error to it
* A slightly difficult to understand file atm but should be easier to understand later down the line 

### GetGraph.jrag
This is a very large file and some have TODO comments so it's difficult to see what is and isn't to be used. It's likely that most of this file can be kept as is, maybe adding a bit for time but we shall see later on

*eq WhileStmt.getStmt().getTargetStmt() = this;* 
For any child nodes in getStmt for this while, it is given a new attr "getTargetStmt" which can point to the whilestmt. Above is an equation, meaning we give a value for the attribute.

*inh Stmt ContinueStmt.getTargetStmt(String label)*
As we don't have enough info in the ContinueStmt node for this attribute, set it to inh and make sure it's parent node (Program) gives the definition for this attr based on its information

* Presents attributes to get continues, breaks, and label statements
* Inherited attributes for continue, break to get the targetStmt
* For TryStmt give the giveBlock statement access to itself
* A lot of nodes are given a null value when trying to fetch the typestateVar of the node
    * boolean, unarys etc. Par and AssignExpre get the expr and destingation attr to give it's typestate var
* conditional expr gets the typestateVar of the true or false branch
* MethodAccess gets the type, finds if its a typeclassDeclType and then returns it?
* A lot of other nodes don't have typestate vars so define it as null. I suspect getTypestateVar is defined as a syn node in the parent class which means we have a lot of repeated null eqs 
* for a VarAccess node it checks if its a qualified type and gets the typestateVar
* qualifiedTypestateVar gets qualified access, gets the left access and then gets the typestate var
* A lo of other nodes are given null for their getTypestateVar
*lazy* attributes are cached by justAdd and best for attributes that are accessed often as it will reduce the amount of repeated computation
* Declarator, ParameterDeclaration, MethodDecl are given typestate fields
* for a ClassDecl node the getTypestateFields attr creates a new set of Declarators, gets all the body declaration nodes, If it's a field declaration it gets the type, looks up if it has a TS and has a typestateClassDeclType, if so will collect the Declarator nodes from this field.
    * So it checks if a field has a typestate and then adds all the declarations from that field
* For a ClassDecl to getGraph()
    * If this class has a typestate then it sets it as a class declaration node
    * Gets the typestaetDecl, creates the typestate and normalises it
    * Adds a field graphNode r which is the TypestateDecl
    * Gets the constructor graph
    * Then if it has an empty constructor gets each typestateField and checks if it has an error
        * Maybe this is what checks the fields of a class to ensure it matches the typestate?
    * Sets addTypestate to true
    * Then gets the graph for every methoddecl
    not entirely sure what 'getGraph()' does, maybe it is the attribute that starts the checking process?
* ClassDecl.getFieldGraph(GraphNode r (which I assume is the field))
    * Gets the typestateFields and enssures they aren't null
    * getsTheFieldGraph uses it's other attribute by supplying more paramters
    * For every typestate var it adds it to our env set and the returns this typestate field
* ClassDecl.getFieldGraph(...) seems to be the attr that is used to check for recursion
    * First part will set the current typestate to point to the graph if we have recursion and know where it points too
    * Otherwise it adds this this to our visited map
        * if an end node we add it to the current state and return
        * if not we get all the typestate fields, and put them into our visited map
        * We then create a tmp GraphNode and add all the current typestates of the fields to it
        * It then adds all the current typestate fields to our visited map, assuming this is for recursion
    Not massively sure exactly what this does but it looks to be the recursive stuff so can hopefully ignore it
* Different nodes are given access to the classdeclancestors
* MethodDecl.getGraph()
    * Creates a new env that stores typestateVars
    * getTypestateFields (in the method or maybe the class)
    * resets the connection and adds it to the env
    * Gets the parameters of the method, checks if they are null an then creates them if they are
    * Then resets the connection graph for this typestate method par and then adds it to the method
    * Does some further stuff if its an abstract method
* Creates a new collection for ConstructorSuccessors after adding the ConstructorDecl as the root of this tree
    * The ConstructorAccess contributes getTarget to this colection for getConstructorDeclAncestor
* ConstructorDecl.getGraph()
    * Gets the graph on constructor successorts
    * For every typestateField, it sets them to new ones if not created and then resets the connection
    * For every parameter in this constructorDecl, it gets any of them that are typestate and makes them a typestate Par
        * Resets the connectionGraph for this typestate Par
    * Gets the block (?), then getGraph of the env
    * Gets all the typestate declarations again and adds them to the env 
    * It then connects every typestate to this graph 
* ConstructorDecl can get the parameter typestates 
* defiens a syn getGraph(Set<TypestateVar> env) for statements, I wonder if the env is our Delta typing env
* Block.getGraph (assuming block is a subclass of STMT) then it
    * gets var decls (which I assume is a syn attr somewhere) Adds any typestate decls to the env
    * Gets the statements of this block from the end to the start by getting every graph
    * Then checks every typestate for this statement and then removes it from the env (not quite sure why)
* Different stmt types need to have a getGraph method declared so it looks like
*If we have a syn attr then the subclasses need to define how it work?*
* For a trystmt it checks each catch clause 
* LabeledStmt.getGraph
    * For every typestate we have in the env we put a new loopnode as its start and then add the end to be where it currently is (makes sense from the rules)
    * gets the stmt graph which I think updates the env variable, 
    * For every v in env it removes this Loop from the start, then adds the v.current as the new current of v (likely falls into how the rule works)
    These may relate the typing rules for this individual stmts 
* *Might need to add a getGraph attr for the delayStmt*
* IfStmt.getGraph(env)
    * Adds a new loopNode to the TypestateVar v
    * Easier having a look after getting a better idea how start and end work but it does seem to look at the inferrence rules maybe
* WhileStmt is similar looking to if 
* Most of these attrs are a tad difficult to understand what they do at the current moment, it's getting easier to understand them the more I read but I'm hopefull it'll become easier to understand with more info later on 
* They continue to give these getGraph attrs for other java statements however I would only need to add a delay one I think
* SwitchStmt.getGraph(env)
    * Adds some new switchNode or loopnode to the start of the typestate vars
    * Again likely not something I need to worry too much about as the logic is a tad confusing given that it will be accessing and modifying different nodes based on the switch
* ReturnStmt info is given which seems to check an error from the result 
    * Maybe one of the places that it trys to see if the typestate is used incorrectly 
* Declarator.getGraph(env)
    * Removes itself from the env if it's a typestate
    * Gets the typestate var for the declarator, if it is null, then it adds a new init node to this typestae
    * if not null then it exists for this delcarator (so I assume we have found inferred one) so in this case we check for its errors
    * If the initalise node (don't know the method decl yet) is an instance of a classinstanceexpr and it's first arg is also a classinstance (not sure why) then 
    * If the first arg has a TSType then it adds the current node to the typestae for this node
    * else it checks error
*One diffucltiy will be determing which rules are the decalared typestates and which are the inferred ones*
* AssignExpr.getGraph(env)
    * get destination typestate and gets the source expr
    * If soruce expr is condionalt then it checks the false and true versions
    * Checks if source is classinstance, if so, makes d a new InitNode
    * The source also may not have a typestateVar 
* LogicalExpr in a slightly different order of getting grpahs but unsure why 
* ParExpr, I assume this means parent expression but unsure
* Would need to work out why they use v.end.put(this, v.current) a lot, they also use a LoopNode too but not sure why 
* ConstructorAccess.getGraph(env)
    * Gets the target (?) and then gets the typestate fields for this and gets the connection graph
* MethodAccess.getGraph(env)
    * 'Plugs' the fields, which gets the plug graphs but unsure what a plug graph is
    * includes a section if the method is a typestate method but unsure what it is doing here

A long and rather unintuitive file, that I suspect builds parts of the AST graph but unsure how it does this atm and what its use is for. May be something to ask about as it is a large field so would be best if I didn't need to touch or deal with it 

### GraphNode.jrag
GraphNode class represents a node in the graph structure that represents a typestate type, it is abstract and inherited by other classes:
MethodNode -  A state where a method call should be done
LoopNode - Node representing a state where you can choose a set of continuations
SwitchNode - Procedes based on the return of a method call
InitNode - It is the inital node
EndNode - Used as the final state

Class can traver a graph
check equality between raphs
normalise a grpah by removing all empty nodes
check if a graph has empty/loop nodes

*abstract GraphNode class*
Fields:
Access type
String id
TypeDecl parameters
ASTNode expr
ArrayList<GraphNode> next
String signature

Methods:
toString
getters
addNext(GraphNode n)
hasNext()

Contains an quivalence relation to store doubles of GraphNode elements, in order to check that two nodes were revisted at the same time by the "includes" alg

Provides a protected TupleSet class which includes a private TupleEntry class which has two GraphNodes
    It also checks the hasCode of these two graphNodes and if they are equal
A reltion is then a set of TupleEntries which checks if it has a tuple of nodes, can insert and then remove them

GraphNode errorNode
GraphNode expectedNode

boolean includes(GraphNode n, TupleSet ts)
Seems to follow subset reltion perhaps

So we have G1<=G2 if Start holds
Have the end reltion too for R
R holds g1<=g2 when (G1, G2) in R
{G1}<={G2} if G1 not empty and for all G1 in {G1} then G1 <= G2 Set relation 

Function well explained for some of the rules so should look at it for when I come round to needing to add mine

Includes a starting includes function and then gives a nextToString function also

*Normalise graph part* 

isNodeVisited, give the object a set of visited nodes, checks if it is in it, and adds itself if it isn't

removeVisited(visited)

__hasLoopNode (polymorphic on LoopNode) Checks that for an unvisited graphNode it's next graphnode has a loopnode. Not sure what the base case is, maybe something to do with the polymorphic on LoopNode

hasLoopNode, same as above but gives it a new empty visited set

nextArrayList - returns an arralist with itself as the first ele

computeNextArrayList - Normalises the GraphNode at pos I on the 'next' arrayList

normalise(Set<GraphNode> visited) - If visited just return the nextArrayList otherwise it goes through all the node in 'next' and normalises tem before adding them to our new next list

normalise() - Does the same as above, creating new visited set and lists but this time will return this or normalise it before returning 

The main idea seems to be about removing LoopNode from a graph where 'next' I assume is the children or siblings of a node. Not something I will likely need to worry too much about tbh

*Count EndNodes*

isPlugged checks that a non visited node has a next node that has been added to the visited set

isReturnedPlugged does the same thing above

isReturned does the same but they all call either isPlugged/isReturned etc. 

*Print* 

getState returns the interger in the stateMap relating to 'this'

isState, if this node only has one expression next and it is either a switch or endnode then return false

toState will return stateMap of the node itself or the next node if it's a switch or end

printState, for a non visited stream it will println "State" + its state number plus the number of the nodes on it's next before adding those nodes as well

stringState - the starting function that sets up the visited and setStates to begin the printing stuff

*debug*
printPaths - prints the next nodes and what the path is like for this node

*graph nodes*
Some comments here which give some syntax for the graphs
Looks like the graphs are for (declared or inferred, haven't worked out which one yet) typestates, talks about method nodes, the states it can lead to and then some switch nodes

*MethodNode*
Instantiates a class that represents a method call

equalnode(n) - checks if the iven node is the same by checking the id of the node, then the number of parameters it has are all the same type as this node.

*SwitchNode*
A class where the continuing state depends on the result of the method call. Comments say it inherits from MethodNode but it seems to just extend the GraphNode

Just uses the super version of includes

*SwitchCase*
Just the case for the switches, checks if they are equal by plus the super call

*LoopNode*
Aux node for creating a typestate graph, represents a state where there is a possible recursion

__HasLoopNode -> True 

onlyEndNodes - For a non visited node it checks how many of it's next nodes have onlyEndNodes, returning -1 if any don't

*EndNode*
Represents the final state
Fairly simple, returns 1 for onlyEndNodes and has standard includes

*InitNode*
Represents the inital state

*ReturnNode*
Extends the EndNode used just for isReturned -> true and then the toString

*PlugNode*
Extends loopNode returns true for isPLugged 

*ReturnPlug*
Extends plugNode returns true for isReturnPlugged

So as a summary it seems that this class represents a graph structure for a typestate type (declared or maybe inferred) which has MethodNodes, LoopNodes etc and can check if they are equal. This may be on of the files I need to extend or modify so that method graphs store clocks info as well?

### javaASTFunctionality
A lot of attrs here, only perceived major ones will have notes taken
* CompilationUnit ASTNode.getCompilationUnitAncestor() 
    - Gets the program and then finds the compilationUnit assoisated with this ASTNode
* TypeDecl has falase attributes for it's base type unless impleneted by its subclasses (makes sense)
* Provides some syn "getters" that must be implemented by the subtypes as well
* The subclasses of TypeDecl then give the implementation of these syn attrs
    - TypestateDecl
    - EnumDecl
    - ClassDecl
    - TypestateClassDecl
        - Provides an attr that returns the typesateDecl of this ClassDecl, finds the elementValuePair (?) then gets the expr of what this is, finds the CompilationUnit and checks if it has a typestate Decl
    - InterfaceDecl
    - MethodDecl
        - Lets block ancestor access itself
        - Col node for MethodDecl.getUsedTypestateFields 
            - add is the method to add to this HashSet with root as itself
        - getTypestateMethod
            - Gets a classdecl (guess) then checks if it has a typestate decl to this class
            - If so finds the typestate method relating to this MethodDecl node and returns it
        - selfSuccessors coll
        - selfMethodAccess coll
        - Contributes to these coll by providing getTarget to the ancestor of this MethodDecl
        - contributes itself to selfMethodAccess as well
* The parents of a ComplicationUnit must provide getClassDeclAncestor
* MethodAcces.getTarget() uses lookupTSMethod which we shall see later
* MethodAccess.signature() - returns a string with the method sig
* MethodAcess.isTypestateMethod() - checks if this MethodAccess is a typestate method
* Access I assume is a subclass of Expr
* Access.getQualifiedAccess()
    - Gets the parent of the dot, maybe what is left and right of "r.m"?
* Access getLastAccess, not sure what access is yet so difficult to see what this will do
* More stuff on Access and Dot, not hard to grasp but difficult to see use when I am unsure what access and dot are
* VarAcess contributes getDeclarator to MethodDecl.getUsedTypestateFields()
    - Contributes either lookupTSVar and the variableDecl or the qualifiedType
* SwitchStmt has case labels collection

### JavaSemanticCheck
Provides the semantic check function for different AST nodes.

* EnumDecl has an empty check
* TypestateClassDecl
    - If it can't find a typestate for a specific class
    - Also checks if a method signature definied in a typestate is not defined either
* Declarator
    - Checks if a typestate field is declared in a non-typestate class
    - Ensures typestate fields are private
    - Ensures they must be static as well
    - Checks that a field with a typestate can't be used in an array
* MethodDecl
    - Return object of a method with a typestate shouldn't be an array
    - Methods not defined in the typestate and use typestate fields can't be private
* ParameterDeclaration
    - Ensures parameter with typestate isn't an array
* MethodAccess
    - Method call should have a target (this is the object we call it on)
    - Method call should have target the method is declared wion
    - Can't use a method call defined in a typestate within another method not defined by a typestate
* SwitchStmt
    - Ensure the enumeration type should be a switch case

### LookupConstructor
* ConstructorAccess.getParameterTypes()
    - Gets the types of the arguements for the constructor
* ConstructorAccess.getTarget
    - Provides the ConstructorDecl 
* SuperConstructorAccess.getTarget()
    - Finds the construcor given the parameters
* Collection that holds all the constructor details

Overall the aspect always the constructor to find its decl (likely won't need to worry about this file)

### LookupTSMethod
* MethodAccess.getParameterTypes()
    - Gets the parameters of this method
* TypestateMethod.getParameterTypes()
    - Same as above, slightly more complex call chain however
* MethodAccess.lookupTSMethod(TypeDecl t)
    - Returns the typestate MethodDecl that matches this MethodAccess
* TypestateMethod.lookupTSMethod(TypeDecl t)
    - Given a t, looks up the TSMethod given this TypestateMethods id and parameters - md for a TS
    - GetTS type of this
    - Then checks if md is equal to td (this)
    I think this returns the TSMethod given a typedecl (which I guess can be a class or something)
* syn TypeDecl.lookupTSMethod(string method, TypeDecl[] parameters)
* ClassDecl.lookupTSMethod
    - Searches it's method for the TSMethod that matches
* coll Set<MethodDecl> ClassDecl.getMethodDecls()
    - Collect MethodDecl for a class
* MethodDecl.isTarget(String method, TypeDecl[] parameters)
    - Checks if a MethodDecl is the target with method name and the same parameters
* syn TypeDecl.lookupTSMethodTargets(String method, TypeDecl[] parameters)
* ClassDecl.lookupTSMethodTargets
    - Adds all the methods that are a target (I assume for this TS)
* MethodAccess.lookupTSMethodTargets(TypeDecl t)
    - For the typeDecl t looksup TSMethod targets using the MethodAccess id and parameters

### LookupType
* syn boolean CompilationUnit.localLookupTSType(string typename)
    - Checks if the name of the first TypeDecl is equal to the typename
    So I assume the compilationunit has a list of types (likely methods) and checks the first one
* Program.getChild().getCompUnit(String packagenName, String typeName)
    - Provides null for the inherited attr getCompUnit name
* inh CompUnit CompilationUnit.getCompUnit
* inh TypeDecl TypestateMethod.lookupTSType
* Program.getCompilationUnit(int j).getCompilationUnit()
    - If the program file suffix exists, then compile it
    - If the program protocol suffix exists then compile it
    - Return the CompilationUnit 
* CompUnit.getPackageString()
* CompUnit.primitiveOrDefault
    - Gets the typename
* TypeDecl CompUNit.lookupTSType(String typename)
    - Gets the packagename, checks if the typeName is a primitive or a default type, f not
    - If typeName is in the localLookup and a singleton we just return that
        Not sure what this refers to but I'm sure it's fine
    - If the compUnit doesn't equal null we just return the first TypeDecl
    - Else other stuff
    Probably makes more sense when I know what a CompilationUnit is but for now this returns the TSType of a typeName
* CompUnit.lookupTSType(packagename, typeName)
    - Does the same as above basically
* Access can also lookupTSType but again unsure why it would need to

### LookupTSVariable
unlikely to be the sort of thing I will need to care too much about but it might be useful for when I need to work out the clock names for a typestate declaration

* For a NameDecl it seems to just return null in most occasions 
* coll Set<Declarator> Block.getVariablesDecls()
    - Every Declaractor adds itself to its block ancestor
* inh Declarator.getClassDeclAncestor()
* inh Block Declarator.getBlockAncestor()
* eq Block.getStmt(int j).lookupTSVariable(String var)
    - For all the vars decl in this block, find the one that equals the var then return it
* MethodDecl.getBlock().lookupTSVariable(String var)
    - checks the paramters in this Method decl and returns that NameDecl
* ConstructorDecl.lookupTSVariable
    - Checks all it's parameters and finds the ones that equals the parameters
* TypeDecl returns null
* coll Set<Declarator> ClassDecl.getFieldDecls()
    - Gets the fields of a class
* syn NameDecl ClassDecl.lookupTSVariable(String var)
    - Gets all the declarators from the Fields and returns the name if it matches the var string
* abstract NameDecl class
    - VarDecl
    - ParDecl

### Rewrite 
Focuses on the printing of Java Typestate programs

* public class TypestateStream
Used to create an output File Writer, and prints the AST
    - String path
    - PrintStream printStream 
    - StringBuffer sb (like a mutable string)
    - PrintStream out

    - int indent
    - String indentString
    - boolean changeLine

    - Provides a list of printing methods

* Different (looks like most) expressions are given a Rewrite(TypestateStream stream) method that writes the node to the typestate stream
* Will need to include my new statements here (delay, and clock constraints)
* Includes re-writing typestateDecl and the dfferent methods
* Might actual work as a list of all the nodes they have?
* Has methods for an arbitary ASTNode

### RewriteAccess
The rewrite operations in the file above iterate on a Dot until its left node is not a package reference or the node itself is not a Dot anymore
(Still don't know what a dot is)

* rewrite Dot
    - Rewrites it to an Access node where it sets the package name to the left of the dot then returns the right of the Dot
    - Or it returns the packagename

* syn lazy boolean Dot.isDotPackage()
* syn boolean Expr.isPackage() 
* syn lazy string ParseName.getName() = name()
* Dot.getRightName()
    - Checks if something is an instance of parseName, if so gets it
    - ParseName is another AST node
* syn boolean CompUnit.isPackage(String Package)
    - Checks packageDecl and checks if it starts with Package
    - Or if the import decl have Package as a starting one
* syn ImportDecl.startsWith(String Package)
    - Checks the specific import declaration and checks if they have package
    - This may be broken as it doesnt' seem to work when I try it on my end

### Type
Alg for getting the type of an Expression / Access (think access is an object name)

Each non-qualified expression has a type of
- ParseName: which looks up the code hierarch for block vars, method/constructor parameters, fields etc
- Method Access looks up in the hierach for matching class method signatures

Qualified types are evaluated access by access on the AbstractDot class (again still not too sure what this is yet). I assume this class is literally the dot we see in method calls etc. Method getQualifiedType gets recursively the parent type of each access of the abstract dot so it can pass it as an argument to method getType(TypeDecl t) to evaluate its own type. 
- ParseName searches the class of its qualified type.
- MethodAccess searches the qualifiedType for mraching method signatures

* Dot.__getQualifiedType()
    - If it hasParentDot then looks up to see if that has a qualifiedtype and if so returns that

### TypestateCheck
* ClassDecl.typestateCheck()
    - getGraph() (for classDecl)
    - Checks thats if a class is a subtype of another class then the typestates must also 
* Declarator.typestateCheck()
    - Checks if the object is used but not initialised then calls checkTypestate further down
* ParameterDeclaration.typestateCheck()
    - Same as above but for a parameters declaration 

### typestateFunctionality
Provides some functionality to the typestates. Main ones being stateMaps, duplicate names for states, if it's reachable and circular path finding.

* inh State TypestateLabel.getTarget(String name)
    - covered by TypestateDecl.getState and getInitState which uses the next attr
* Map<String, State> TypestateDecl.getStateMap()
    - Creates a map of state names to their state number
* isDuplicate is provided for State
* TypestateDecl.isDuplicate(String name)
    - Checks if a given typestate name is duplicate
* syn Set<State> State.reachable() circular [new HashSet<State>()];
    - eq State.reachable()
    - Adds all the successors, and their reachable states to a Set
* coll Set<State> State.successors() [new HashSet<State()>]
    - TypestateLabel contributes getTarget to above
    - getTarget has been defined above and is the state name to its number
* State.getCircularPath()
    - finds the circular path to this state
* coll Set<String> getLabels
    - TypestateSwitchCase contributes getLabels to above
    *This may be the style I should take in order to get clock names*
* coll Set<TypestateMethod> TypestateDecl.getMethods()
    - TypestateMethod gives itself to above, specifically it's parent

### typestateSemanticCheck
For a 'State' which I assume is a typestate state (likely declared) it checks that it doesn't have circular path, duplicate states, or that the state isn't reachable

for TypestateLabel it checks that a definition for the state has been provided.

Think it checks that a type within a typestate is also a typestate class

TType ensures that a type within a typestate (I think) is a java class

For switch it ensures no duplicate case label

- Might need to make a semantic check to ensure no other typestate protocol has the same clock names?

TypestateMethod checks if the method should return an enumerate label and if the labels are all defined in a switch label

Also ensures there is no duplicate method signatures for the typestate method signatures 

### TypestateTypingEnvironment
Is the the type enviroment structure

* class TypestateVar
    - GraphNode current
    - Map<ASTNode, GraphNode> end
    - Map<ASTNode, GraphNode> start

    - has a list of startGraph, Expr, errorGraph, and errorExpr
    - A lot of methods are left out, likely as this class will be extended
    - but can still add errors and typestate to the graph
    - The class will then report these errors
    - checkTypestate(GraphNode t, String errorMessage)
        - Checks if one typestate includes the other, if not tehn it prints out the object mismatch
    - '-pi' is here if so adds info abou the stringState and prints that out

* class TypestatePar
- Is a parameter version so returns parameterNodes

* class TypestateField 
- class for a typestate field 

*grammar*
### Typestate.ast
* TypestateClassDecl is just a subtype of the ClassDecl node
    - Makes sense, no other class info here tho
* TypestateDecl is a subtype of TypeDecl and contains a single InitState and then a set of States after it. Nothing special about InitState, it's just a state
* State contains an ID token and then a Typestate child
* Abstract typestate subtyped by MethodList, EndTypestate etc. Looks like the equiavalent of S from the grammar 
* MethodList is just a list of Methods
* EndTypestate is just a typestate
* A TypestateMethod has a TType, ID token, TTypeList, and Typestate child
* So TType looks like the return type of a method described in the typestate
    - This has an Access node (which I'm not too sure of yet)
* TTypeState is of TType and extends it with a Typestate child
* TTypeList is the args of a method, which are again TTypes.
* A switch case is Label token and a typestate child


### TypestateTypes.ast
* UnknownTypeDecl, NoNameType, ClassType, and ObjectType are all empty TypeDecl subclasses.
* PackageType has some NTA modifers, an ID token, and a list of BodyDecl NTA
* PackageParseName is a subclass of ParseName and has Package and Id tokens

So Mungo uses ExtendJ which is the JastAdd compiler so some nodes are already defined as shown by the ExtendJ API documentation which is okay... but can use it to look up any classes we are unsure of, i.e. Dot.

*parser*
This file is written in beaver syntax, which is the parser the compiler uses.
After a quick look at the file, it's clear it is adding more specific parsing details to the typestate classes that were defined in the grammar. 

The main thing we care about are "action routines" which are called when the symbols on the right hand side were matched and are about to be reduced to a signle nonterminal symbol.

Can assign names to the symbols on the right to access these symbols in the action routine.
i.e.
'expr = expr.a MULT expr.b {: return new Expr(a.value * b.value); :}'

JastAdd parser allows (optional) hava class names at the start of the production, which it uses to make a %typeof option. This just decalares java types for grammar symbols. 

This might be the file to gather the clocks of a typestate? - unlikely, clocks would need to be a collection on the parent node of a TypestateDecl (or similar) where each method contributes it's clockname to a set.

This file will almost definitely need changed to match the new declared typestate. Might want to work on that first?

This more specfically captures the things we'll see in a protocol file and how to parse it, so it includes more info on seeing LPAREN, RPAREN etc

* TypeDecl type_declaration = just returns the typestate_declaration
* TypestateDecl typestate_declaration
    - First option is the declaration of a typestate from the top of a file, so has the name of the typestate, {, then the inital typestate, then a list of other typestates
        - Creates the init typestate of this declaration
        -  Sets the start and end from the identifier (not quite sure what this is)
        - returns this TypestateDecl with the inital state
    - Second option doesn't have a list of typesates
        - does the same as above but creates but creates an empty list for the typestates
* List state_name_list
    - Gets a list of all the state names
* State state_name 
    - Using the identifier and typesate it sets up a State with the mapping between them 
* List typestate_list
    - Returns a list of the methods that can be called for a typestate
* Typestate typesate
    - For a typestate it returns the typestate method list
    - Or EndTypestate for {} or end
    - Or the label
* TypestateMethod method
    - returns a new TypestateMethod with it's ttype (return type), name, the parameter types, and the typestate it goes to
    - Or If it has no params, it makes a new TTypeArgs 
    - Or it does the same as the first option but it matches when it has a t_switch instead of just a normal typestate
    - Same as above but with no params
* TypestateSwitch t_switch 
    - Returns the TypestateSwitch
* List switch_case_list
    - Adds all the switch cases to a list
* TypestateSwithCase switch_case 
    - Returns a new TypestateSwitchCase with the id and stuff
* TypestateLabel t_label
    - Returns a new Typestate label
* TType ttype
    - Can be object with a typestate
    - Normal type 
    - or void
* List ttype_list
    - Returns a new list of all the ttypes, so used for the parameter list

*scanner*
### Keywords.flex
- Returns symbols for keywords, I suspect we will need to add delay to this

## Java

*mungo*
### Boolean.java
- Makes Boolean an enum

### Typestate.java
- Provides an interface for the Typestate which is just a string value

Will likely need to place a class here for delay. Not quite sure how we would make a new java expression but we'll figure it out. 

*org*
### TypestateChecker.java
The file that has the file usages and how to use mungo.jar

Think that's the main files covered for Mungo - good work

frontEnd folder which contains the aspects is the main place work will be carried out.

So next steps
1. Get a better idea where the typestate checking occurs
2. Update the typestate grammar to include clock constraints
3. See where the inferred typestates are
4. Update the inferrences algorthim and then find the subtyping reltion
5. Update subtyping relation 

There is a syntax folder which contains the (declared) typestate syntax and java syntax, this will need to be updated.

The getting started guide has a way of updating the syntax, which we will need to use for the new delay statement. 

# Notes on java AST in extendJ
- Program is the top node and contains a list of CompilationUnits
- CompUnit has a list of ImportDecl and TypeDecl
- TypeDecl is an abstract class that has Modifiers, ID, and a list of BodyDecls
    - TypeDecl can be class, interface, enum etc
    - bodydecl can be methods, fields etc
- Statements
    - *This is what delay will be*
        *Have likely said delay expression by mistake in a lot of places*
    - Has blocks, ifs, fors, etc
- Expressions
    - Has Assign, EQ, NE etc
- Accesses
    - i.e. 'a.b.c()'
    - It's the chaning we do to access variables and methods, or arrays etc
    - Contains 'Dots' which have a left child and right child i.e. 'a.c()' has Dot with children 'VarAccess "a"' and "MethodAccess "c"'
- Error Collection
    - problem() - collection of warnings & errors
    - Everything that can generate an error uses a contributes statement
- can use type() on any Expr to find its type
- subtyping rules are encoded with attrs *using double disptach pattern)
    - not our type of subtyping (I don't think)

# Notes on specific aspects I need to figure out

1. Typestate inferrence
To find out where this occurs I will run one of the examples and place an error in it to see where the error is written out from.

With an incorrect typestate we get an error like "Typestate mismatch. Found.... Expected:..."

This error is found in *TypestateTypingEnvironment.jrag*
- This function is on 'TypestateVar' node
- uses the 'checkTypestate(GraphNode t, String errorMessage)' function
    - errorMessage is not used here
    - Is called by typestateCheck()
        - Which is called by ASTNode.collectTypestate()
            - Which is called by Program.collect()
    - t is the declared typestate we are checking
        - we get this typestate by looking up the TSType of node we are working on (seems to be a Declarator)
    - Uncommented the debugging statement they have for the typestate checking, is helpful as it shows us the paths that are getting checked by includes
    *Will be able to use this later*
    - Adding print statements for the specific typestateCheck, it's definitely Declarator
    - Need to work out how 'typestate' comes about, as this is the inferred typestate that is checked against
    - So I think typestate comes from the GetGraph file which I will now search


- A Declarator node is an abstract node for variables and fields. Think it's used for Stack s = new Stack() but not 100% sure yet

*use JavaDumpTree* on code if we want to know what nodes are what. Can see that ClassDecl is just the class def we are used to. So it looks like a VariableDeclarator has 'ClassInstanceExpr' under it that seems to be 'new'.

*ClassInstanceExpr* is mentioned a lot and as I'm aware it is used when objects are created which would follow the rules we know from Mungo. 
- Lots of mention in the GetGraph file
- Used a lot in Declarator.getGraph(Set<TypestateVar> env)
- Used again in AssignExpr.getGraph(env)
    - Seems to follow the asign expression stuff from mungo

getGraph is likely the typestate inferrence stuff, now I just need to work out how it works

## More notes on Declaracor.getGraph(Set<TypestateVar> env)
- boolean isTypestate = getTypeAccess : Access.lookupTSType.isTypestateClassDeclType()
    - not quite sure why it is titled isTypestate instead of hasTypestate (as this would maybe make more sense) unless the class itself is a typestate? Having a look at when else we find this typestate, this is definitely the declared typestate
    - lookupTSType gets the TSType for an Access
- If this class has a typestate
    - then we check if it is declaring expression (ClassInstanceExpr) and that the init typestate is null
    - if so creates a new InitNode and adds the current typestate.current to the next and adds an init node to the start of it
    - This might be the start of the inferrence typestate stuff
- If it doesn't then 
    - It checks if the init node (of the typestate I think) is a ClassInstanceExpr
    - If it is then it gets the first Arg and checks if that is a ClassInstanceExpr too
    - then does the same as the above method but for the arg?

- From the print statement that I added, this getGraph is called a single time and adds the current typestate
- From the new print statements that have been added, this get graph for the declarator is called at the end, as it's typestate has already been filled and it adds an init node to the start.
    - So just need to work out when this typestate is updated, which I'm going to guess is in MethodDecl
    - Don't think it is MethodDecl, it is likely MethodAccess instead
    - MethodAccess is the method call and print statements have been added at the part where the typestate call is added
    - MethodAccess has a part where it edits the typestate of the parameters by making new nodes so should be able to do that for delay 

2. Going to start trying to update the inferrence system
- New, Call, and Delay are the only major rule changes from the previous system. 

start by adding delay to the system or we can just have it as a method call?
- Having issues adding delay as an expression so will leave this for now. 

Will move onto updating the existing grammar with time constraints and seeing how that works

3. Updating the existing typestate grammar for time constraints, should only be for the declared typestates
- Only update is on the signature with an optional time constraint
- Will need to have a think what type the TimeConstraint would take
- Have basic parsing, having an issue with optional values so will need to figure out how to convert those
    - So issue with optional parts of a production rule 
    - The literals 

All previously mentioned issues have now been sorted out and on the surface it all seems to work!

Now going to go through the theory and see if everything has been added correctly 

### Syntax
- Syntax has been added correctly and is parsed as well
- Inferred and declared typestate has not been split up in the implementation to avoid unnessary renaming of existing code that wouldn't lead to any major improvement
- Time is represented by doubles to allow real values

### Helper functions
- Init has been declared (in createTypestate)
- Clocks created via attributes
- Join correctly created via exiting Mungo code
- removeDelays doesn't seem to be needed
    - Therefore noOfDelays and convert haven't be made either
    - More research into why this is the case

### Subtyping relation (I <: S)
- Exists in GraphNode through the includes functions
- Terminate has been updated so it includes the clocks 
- Methods that don't have timing constraints don't check the clock constraints
- Recursion works for previous work and updated type theory 
- Works for switch statement contraints
    - And individual resets
- Branches work
- Time progression works as expected 
- All works as expected

## Subtyping (I <: I')
- Don't think this is ever used in the system but will have a check
- It is never used

## Subtyping (S <: S')
- This is used so will need updated
- Added so the current system works, will need to have a think if it should be updated to care about timing constraints and what this would look like in the theory
- The subtyping presented in other research is a bit more complex
- So can have a very simple system where for the method to match it must have the same resetPred and clockConstraints?
    - Not the most important thing to work on as it hasn't really been covered in my paper so I don't think I'll focus on it
    - Will just add that constraints and resets need to be the same, future work can be to explore how these subtypes work
    - Completed, not perfect equality but it shall do until future work

## Inference rules are good
- New has been added
- Delay has been added too
- Need to have a better look at if 'Call' works as expected
    - This indeed works as expected 

- Error messages have been improved such that if it's a time error it will only send that out. 
- Should also make my example more realistic and actually work.
    - Currently it doesn't actualy achieve anything realisitc.
    - Now actually works as a class however it doesn't do anything particularly cool or anything
- Can then have a look to moving on towards StMungo.

## StMungo
The grammar is written in ANTLR, so can look at notes for that but shouldn't be too difficult to do

Scribble only deals with local protocols so don't need to worry about some of the more confusing stuff.

local send and receive is what I care about 

Have an example in my notes about a ordering book example that may be a good example of StMungo? Can leave it for now however.

## Dissertation Notes

The SMTP example has been updated with some time constraints however it isn't awesome and more highlights the issue with the system (the fact it is synchronous) so therefore in order to make sure it is wait-free we need long times to wait. Good for evaluation but rather annoying overall. Can discuss this with Ornela tomorrow, it has been completed but it isn't ideal given my implementation. StMungo is completed however which is great.

All the work from Project Definitions has now been added to the paper template. Today will be spent fixing up this template and making a rough plan for what I will be talking about in the paper. The planning should only take one day max and be used to reduce the amount of stress at starting the proper work tomorrow but luckily most of the difficult to write stuff (the typing rules) are already up and it should be a good paper to write overall.

Need to have a look later on at which rules can stay and which can go, a lot of the rules follow on from Mungo, so the more stuff I write, the more some of these can go into the appendix 

Rough section plan

Can have a quick look at the sections that are present in Mungo, Papaya, the suggested sections from the example and then give some more detail about what to include

# Mungo paper
## Abstract
Self explanatory, do this part at the end

## Introduction
Gives an overview of the paper, what it contains and some breif intro to some background work.

## Mungo section
Could definitely be added for my work and can be used to discuss and then show the final implementation of Mungo and how the time constraints work, can also give some slight background of JastAdd works

## StMungo
A small section but can discuss how it has been updated to convert time constraints written in Scribble to the Mungo protocol files. Can give an example of a local protocol tranlated into it's time mungo protocol. 

## Case study: typechecking SMTP 
    could be done but it would more highlight work that has already been accomplished and wouldn't discuss too much as the time stuff isn't perfect but good for evaluation

## Calculus 
    Big section, should be a decent one to write, can look to follow how the Mungo paper writes this

### Typestate inference
Again a fairly big chapter that shows how the new inferrence rules work, may need to rewrite it slightly so it doesn't include a huge amount of production rules, but a few here and there would be helpful. 

### Properties
Discuss the proofs that have been done and what this system can entail, including a more up to date collerie definition 

## Related work section
Easy just put the stuff I did for ymy project proposal here

## Conclusion and future work
Again, not a difficult one to do and should be nice to write up

# Papaya Paper
Will only note the main differences that are found here

- Provides an overview example (this may be a nice way to introduce the running example that is used throughout). However the Mungo section in the Mungo paper does this already so maybe best to stick to it that way.
- Similar to Mungo

# Example paper from LaTeX template
- Decent outline but likely only best for papers that follow that outline, still best to follow the Mungo example

Current Mungo section is good but will likely split it up. The first part is good and can show off some of the cool parts of the paper, tho a slightly more complex example might be better. The implementation with JastAdd is a good idea but much better after the calculus has been presented, as it will allow more lower level details to be discussed. 

Fisrt 5 pages are pretty good, some of the code blocks can certaintly be made smaller but happy with current progress given it has only been a few days. Can look to update the SMTP client a bit more and give a (small) section on it given it certaintly isn't perfect, however it can be useful for the future work and conclusion section of the paper which will discuss the issue with "tsDelay" and "wait-free" which are baked into this project. The DATA BLOCK timeout is left out as it is unclear where this would be placed in and I don't think there is any advantage to adding this to the SMTP server impementation. This will mainly be used as part of the evaluation stage and future work, with possible fixes.

May be wise to make the example have a few more complex things in it to show how a user could use it. Main example I can see would be individual methods in separate classes that can model time that a class can choose to use or not but not too many or it would cause issues. This can be seen as an extension to the existing example and help was part of the eval.

Might need to include a subtyping reltion for the declared subtypes? Can stick this in the appendix and maybe mention that Mungo checks that subclasses have subtype typestates?

May change the wording of (v,S) to "tuple" instead of pair?