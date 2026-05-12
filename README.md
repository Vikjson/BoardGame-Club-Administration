# Board Game Club Administration

## About

This project is a school assignment on backend programming using Spring Framework and JPA/Hibernate for database
interactions.

The application models a web service for a boardgame club, intended for administrative use for keeping records of club
members, the game collection, statistics for previous game sessions, etc.

It is a fullstack project, wherein we have used Spring MVC through Tomcat providing a REST API as backend, and Vue.js as
framework for the frontend application. For database, we use Microsoft SQL Server, and we have included some mock data to work with. Both the database and mock data is initialized in the installation script.


## Workflow
On user interaction, the client application sends HTTP requests to the API to perform basic CRUD operations on the data. Any request bodies are formatted in JSON, which backend maps to domain entities using Jackson. 
The request goes to the appropriate controller endpoint based on the @RequestMapping annotation. The controller checks parameters and such with guards, and uses the appropriate service to handle the request. 
The services handle the business logic and interacts with the data layer through Dao-interfaces.

## How to run

___(Requirements: A Docker installation)___<br>
To run the application, clone this GitHub repo to a local folder. Open a UNIX terminal (such as Git Bash) in the
project's root folder.
Here you will run some scripts, which will download two Docker images, one for Microsoft SQL Server and one for Tomcat.
This may take a few minutes.
Follow these steps:

1. Make sure Docker engine is running.
2. In the terminal, enter this command to run the installation script:
   > ./install.sh
3. Check that you didn't get any errors, then run:
   > ./build_run.sh

When the script has finished, it may take a minute for the Tomcat container to start up.

4. Click the URL in the terminal (which must remain open while running the application), or paste this URL in your
   browser:
   > http://localhost:5173/
 
Now everything should be running, and you can try the application. The next time you wish to start the application you can follow the above steps, but skip the _install.sh_ script, which is only necessary to run once. 
To see logs from the application, either run this command in a terminal: 
   > docker logs -f tomcat-container

Or open the Docker Desktop app and navigate to the container named _tomcat-container_. In the 'Logs' section you will be able to see all log messages from the application. 

## Troubleshooting
If any of the scripts fail in any way, make sure you have preserved Unix line endings (LF) in the script files.
