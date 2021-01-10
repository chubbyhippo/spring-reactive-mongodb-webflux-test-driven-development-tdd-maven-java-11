import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "/reservations"

    request {
	url "/reservations"
	method 'GET'
    }

    response {
	status 200
	headers {
	    contentType(applicationJson())
	}
	body ([
	    [id: 1, name: "Jane"],
	    [id: 2, name: "Joe"]
	])
    }
}