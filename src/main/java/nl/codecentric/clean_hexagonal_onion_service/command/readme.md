###Command Adapter
The command adapter handles the incoming connections via REST that have the intention to change the state of the 
domain in the microservice. The command adapter adapts a REST request with a JSON body to a method call on a 
Domain Service or an Aggregate in the domain.