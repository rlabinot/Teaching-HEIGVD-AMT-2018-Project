# Teaching-HEIGVD-AMT-2018-Project
## Introduction

Welcome to our project !

We are currently building a gamification engine. The project is still on his way but we are proud to show you what you can already do.

This project contains a web application where you can register and log in as a developer and add/edit/delete your applications. The web application will give you an API Key and an API Secret.

It is possible to manage the website if you log in as an admin. An admin has the possibility to suspend users, look at their applications, reset their password or check the logs of the web application.

The project is working with a Payara application server and a MySQL database maintained with PHPMyAdmin. You will find another project folder called "webuiUserAcceptanceTests" which contains functional tests. There are also several markdown files if you want to know more about the tests done.

This project is the first part of a **gamification engine**. You can find the global description on [**this repo github**](https://github.com/SoftEng-HEIGVD/Teaching-HEIGVD-AMT-2018-Project) and more information about this first part [**here**](WP1.md).

## Deployment

You can easily deploy the project with the help of the Docker Compose technology. Simply follow these steps :
1) Be sure to have [**Docker Compose**](https://docs.docker.com/compose/install/) installed on your machine
2) Clone the current repo
3) Go the docker/topologies/deploy and type docker-compose up --build
4) Open your favorite browser and enter localhost:8080