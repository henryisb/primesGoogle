##Scope
Design and implement a service with JSON REST API that for a numerical input X, returns a list of all prime numbers that are less or equal to X with an option to choose the algorithm.

Design and implement a service with JSON REST API that for any given String input Q, returns the second search result from Google for query Q, excluding ads."

###Running
To run the application just pull down the repository, gradle clean build and run:

java -jar ./build/libs/prime-google-1.0-SNAPSHOT.jar

##Querying
The prime number api is a GET at localhost:8080/primes/{number}/{algorithm}

With {algorithm} as an optional field - automatically returning the for loop algorithm.

The google query api is a POST at /querygoogle/ that takes a body of

{"query": "the query you want to pass to google"} 
