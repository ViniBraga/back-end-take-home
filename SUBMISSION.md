# INSTRUCTIONS

### Maven
If you have Maven installed, it is advisable to generate the application package by executing the following command 
on the root of the project:

`mvn clean package`

### Starting server

To start the server, simply run the following command on the root of the project:

`java -jar ./target/guestlogix-backend-0.0.1-SNAPSHOT.jar`

PS: Remembering that to run the application, you need java 8 installed

### Heroku

if there is any other reason preventing the application from running in the local environment, 
it is possible to execute it through the following URL:

`https://back-end-take-home.herokuapp.com`

The application is hosted on a heroku server linked to this repository. 
Therefore, whenever the repository is updated, a deploy on the server runs automatically

### Executing

The path to research for the shortest route is the following:

`/ guestlogix / {IATA3_ORIGIN_AIRPORT} / {IATA3_DESTINATION_AIRPORT}`

Example:

http://localhost:8080/guestlogix/route/IST/BRU

or

https://back-end-take-home.herokuapp.com/guestlogix/route/IST/BRU

### Return

The return is going to be a JSON containing the list of as few as possible connections between Origin and Destination.
Example:

[  
   {  
      "id":2421,
      "airlineCode":"TK",
      "airline":{  
         "id":4,
         "digitCode2":"TK",
         "digitCode3":"THY",
         "name":"Turkish Airlines",
         "country":"Turkey"
      },
      "originIata3":"IST",
      "originAirport":{  
         "id":1656,
         "iata3":"IST",
         "name":"Atatürk International Airport",
         "city":"Istanbul",
         "country":"Turkey",
         "latitude":40.97689819,
         "longitude":28.81459999
      },
      "destinationIata3":"BRU",
      "destinationAirport":{  
         "id":300,
         "iata3":"BRU",
         "name":"Brussels Airport",
         "city":"Brussels",
         "country":"Belgium",
         "latitude":50.90140152,
         "longitude":4.48443985
      }
   }
]


