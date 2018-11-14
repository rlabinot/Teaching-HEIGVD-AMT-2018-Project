# Teaching-HEIGVD-AMT-2018-Testing-Functional
## Introduction

Automated tests are very useful for big projects with many developers. It alarms to the developers if their small code modification will effect or not the current user experience. 

In this project, we chose Selenium to test the fonctional aspect of user experience, specially the fluent version. The tests will be using a web navigator. In our case we use chrome and his chromedriver. 

Currently, we made many simple tests, here they are :

- The user can register himself
- The user logs in with good credentials
- The user can register many apps and navigate in the differents pages
- The user can't have a weak password
- The user can't register with an already registered email
- The user can't access to user content
- The user can't log in with bad credentials

##Implementation

Before implementing the tests, we need to modelize our webapp and our different pages. Every pages must have an Java class like this example below :

![1542137357652](img/classPages.png)

The folder "pages" contains all the page and also the AbstractWebuiFluentPage wich contains the menus. It is abstract to make pages have an implementation of the menus (nearly all these pages have the menus). Here is an example of a page :

![1542137357652](img/loginPage.png)

BE CAREFUL. It is important to have the same IDs with the JSPs pages. IDs are the only way to link JSPs pages and tests classes. For this example, look a the JSP page below : 

![1542137357652](img/loginJSP.png)

We can see the id for the email field wich is also in the loginPage.java for the tests.

Now check the WebuiFluentTest. It contains all the tests that will be launched. BE CAREFUL, the tests are launched in disorder.

![1542137357652](img/webuiTest.png)

This is an typical example of test, because we use many methods for field component and click component. To make your own tests, you just need to add theses tags and code an similar methods.

## Deployment

To enjoy theses tests, you just have to do a simple manipulation. You need first to launch the containers with the payara app server, the database and phpmyadmin in one step. This step is to go to the "docker/topology/deploy" folder and make this command with a docker terminal :

- `docker-compose up --build`

Wait until all the containers are launched and then go to your favorite Java IDE and open the webuiUserAcceptanceTests. In my case, it's Intellij.

![1542137357652](img/intellij.png)

Build up the project to have a target and juste click on run to start the fluent tests. This will open instantly your chrome navigator and navigate in it automatically !

![1542137357652](img/robot.png)

Notice in this screenshot that the url will be 192.168.99.100 and not localhost. This is because you will launch this from a container and using docker machine. BE CAREFUL, if you are using another OS than Windows, change the chromedriver path "./chromedriver.exe" and delete the ".exe" part in the WebFluentTest class.

When all the tests are finished, you will have a feedback with all the tests status :

![1542137357652](img/testsPassed.png)

Everything went well, now you are ready to make some new tests !