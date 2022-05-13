# warehouse-microservice
1. This microservice is responsible for doing all the CRUD operation in the MySQL database. This is where all the parts objects are stored.
2. When the price of a part is updated a record is send to kafka into a topic. A listener in central-unit microservice uses this service to 
   calculate the new price of the part by calling also and external api for currency rates. After the price is calculated the new part object
   with the new part is send to another topic to cafka as a record where it will be read by a listener in the validation-unit microservice.
   With this record the validation-unit will update the price of the part in the PostgresSQL database.
