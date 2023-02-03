# ConsumerContractTesting-Student-service
1. Run the StudentServiceApplication to start the service

In Student service: (provider service)
--------------------------------------
Operation a. run searchStudentWithID to search the student already existed
Operation b. run addNewStudent to add new student to student service

In Enroll Service: (Consumer service)
-------------------------------------
To start the consumer side test we need to make ready the provider service 
to do so we need to run the mvn clean install on the provider side so that it generates jar in m2 floder to run
to use in consumer side
