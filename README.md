# Teaching-HEIGVD-AMT-2018-Testing-Non-Functional-Pagination
## Introduction

We chose to compare two different way to implement a pagination system through the admin function that displays the user list with a total of 1000 users. We will call the first implementation v1 and the second v2.

In the first one we send one request to the database to retrieve all users, even if we only display the ten first ones on the web page. However when the client clicks on the next page to see more users, the information is already in memory and then there is no need for further server request.  
In the second implementation we only load the 10 first users when the client requests the user list, and every time he clicks on next page to see more users we send a requests to the database to retrieve them.

## Test with JMeter
The test consists in 10 clients sending each 50 requests to simply display the main page of the administrator, which is the user list.

Results on v1 :

![alt text](./images/t1v1u500.png "test v1")

Result on v2 :

![alt text](./images/t1v2u500.png "test v2")

As we can see a v2 request is around 4 times faster than the v1, and the gap will be even bigger the more user we have in our database.  

There is however one more point to consider, which is the page change. In the first implementation, all the data is in the client memory after the first request. This means that the speed of a page change is only limited by the speed of javascript which depends on the client machine.  
In the second implementation every page change consists on the same server request which, as we have seen previously, takes in average 16ms. Finally we could add that the tests have been done in local, in a real life implementation we would have to deal with the network speed for every request.