# placesapi

## About

A simple api on top of Google's PlacesApi.

## How-Tos

Use your Google Maps api key as an environment variable to run the api successfully.

### Steps

1. After cloning, execute `mvn package`
2. When the jar file is ready
```
export placesApi=yourApiKey
java -jar target/api-0.0.1-SNAPSHOT.jar
```
3. Go to http://localhost:8080/swagger-ui.html
