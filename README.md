# warehouse-microservice
1. This microservice is responsible for doing all the CRUD operation in the MySQL database. This is where all the parts objects are stored.
2. When the price of a part is updated a record is send to kafka into a topic. A listener in central-unit microservice uses this service to 
   calculate the new price of the part by calling also and external api for currency rates. After the price is calculated the new part object
   with the new price is send to another topic to kafka as a record where it will be read by a listener in the validation-unit microservice.
   With this record the validation-unit will update the price of the part in the PostgreSQL database.
3. To access this microservice from central-unit microservice you need a value for the header "X-API-Authorization-Key". If the values of the
   key is not the same as the one in the warehouse-microservice, the service will not grant access.
   
   
![Untitled Diagram drawio(5)](https://user-images.githubusercontent.com/58910040/168304410-6ab98b08-ebc0-4e6c-a21c-0d70b9375420.png)
