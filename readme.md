### Tiny Weather Bulletin

#### Pre-Requesities

-> Java 8+

-> Spring Boot

-> Project lombok plugin

-> PostMan

#### About 

The Tiny weather bulletin that will be used by employees to check the weather conditions in the cities where other teammates work. This service must use the OpenWeather API (​ https://openweathermap.org/api​ ) to retrieve the weather forecast.
Given a city with specific work hour that respond next three day average temperature and humidity on specific working hour and non-working hour speratily.

The application contains following Rest API

**1. Fetch Data given city name and working hours.**

URL to get data of average temperature and humidity:
    **[Get]http://localhost:8080/TinyWeather/avgTH/{cityName}/{startTime}/{endTime}**
    
    
It takes as an input city name and will return the weather forecast that is average temperature and humidity for inside and outside working hours for next three days by using the city name and start time and end time for working hours provided.

**How to run**

To run the application, get a free API key from openweathermap.com and put it to file src/main/resources/default.properties file You can run in following three ways:-


1.Clone the application from github and then run the application and use above API to get data.


2.Run the package task and the jar will be created in target folder. You can also run the application using the jar file and use the API to get data.


-> Command to run jar file: java -jar jarName

3.You can use Docker File located in the main folder.
First create the jar file using above command. 
Then cd into the folder and run command "sudo docker build -t nameOfImage .". 
Image will be created in the docker and you can use the API's to get data.
