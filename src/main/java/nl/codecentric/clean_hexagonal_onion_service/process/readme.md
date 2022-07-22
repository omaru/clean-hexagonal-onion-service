###Process Adapter
the Process Adaper provides integration of the process engine (i.e. Camunda) with the domain. It provides process 
delegates that allow a process definition to interact with Domain Services or Aggregates in the domain. Also, it 
may implement event listeners to respond to domain events. For interaction towards the process engine it provides 
process bridges to initiate processes or feed back information to running processes.