Performance Evaluation of Distributed Systems

To analyze real world systems like communications systems
or production lines a model is needed most of the time.
Such systems are very often distributed systems and therefore
are built from communicating components. The distribution of
the system results in a complex process flow.

Because of this high complexity, mistakes are easily made during 
the design of the systems. That is why the performance of such
systems should be evaluated before they are implemented. If a system
is implemented without the evalution it is possible to utilize too
much or too few hardware to make the system perform optimally.

For simple systems this evaluation can be done by using analytical
models. Real world systems are too complex for analytical models to 
be suitable. In this case a model of the system is built and a 
simulation is used to evaluate the system.

In this project four systems where simulated. In all of those systems 
there are service units, which can handle work requests. Each service 
unit can only handle one work request at a time. If all service units
are currently handling a work request, new work requests enter a queue
and have to wait. In the case, that the queue is full, new work requests
are blocked and thrown away. For all the systems the number of service
units, the size of the queue and the arrival- and service-rates can be
configured. 

The simulation of the systems tries to answer the following questions:
- How high is the probability for a work request to be blocked?
- How high is the probability that a work request has to wait?
- How high is the probability that the waiting queue overflows?
- What is the average amount of work requests in the systems?
- What is the average amount of work requests that are handled by a service unit?
- What is the average amount of work requests that are waiting?
- What is the average time of a work request inside the system?
- What is the average time of a work request inside a serivce unity?
- What is the average time of a work request inside the waiting queue?

The first two systems, the Blocking-System and the Waiting-System, are
simple engouh to be evaluated analytically. The first system has a waiting queue
size of 0, which means that the work requests are blocked only if there is
no free service unit. The second system has an infinite waiting queue, so that no
work request is ever blocked. The results of their simulations can be
compared to the analytical results. The other two systems are hybrids of the
first two. The Overflow-Hybrid-System has a fixed waiting queue size but does
not throw away any work request. If the waiting queue is full the new work
requests are still held in the system and the probability for the overflow
of the waiting queue is calculated. The Blocking-Hybrid-System has a fixed
waiting queue size as well but it blocks new work requests once the queue
is filled.