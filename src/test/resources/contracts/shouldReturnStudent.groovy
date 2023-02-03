package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("Should return a Student")
    request {
        method GET()
        url("/student-service/student"){
            queryParameters {
                parameter("ID", "2020091701")
            }
        }
    }
    response {
        status(200)
        headers {
            contentType applicationJson()
        }
        body(
                firstName: "John",
                lastName: "Smart",
                dateOfBirth: "02/02/1997",
                registrationDate: "04/12/2009",
                address: "15 Foreshore Road, Philadelphia, PA, 19101",
                id: "2020091701"
        )
    }

}