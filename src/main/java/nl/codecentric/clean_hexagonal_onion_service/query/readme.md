###Query Adapter
The Query Adapter handles the incoming connections via REST that request data from the microservice's so-called 
"read models". These requests are read-only actions, as they will never result into a state change.
The Query Adapter adapts a REST request to request data from one or more aggregates or a Domain Service within the 
domain. What is important to understand is that the data returned from the domain is transformed into a read model 
so that the aggregates themselves are NOT being queried. This data queried from the read model will be marshaled to 
JSON and then returned to the consumer of the microservice.