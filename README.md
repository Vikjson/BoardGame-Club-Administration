# Board Game Club Administration

## About

This project is a school assignment on backend programming using Spring Framework and JPA/Hibernate for database
interactions.

The application models a web service for a boardgame club, intended for administrative use for keeping records of club
members, the game collection, statistics for previous game sessions, etc.

It is a fullstack project, wherein we have used Spring MVC through Tomcat providing a REST API as backend, and Vue.js as
framework for the frontend application.
The client application sends HTTP requests to the API to perform basic CRUD operations on the data. 
We have included some mock data to work with. Both the database and mock data is initialized in the installation script.

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
