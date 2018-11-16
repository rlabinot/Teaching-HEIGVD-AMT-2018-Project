# Teaching-HEIGVD-AMT-2018-Testing-Non-Functional-Pagination

## The scope of the test 
In our test, we want to delete an user, to do so, we also need to delete his applications and all of his old passwords.
To do so, we will look a the different type of transaction and see how they will react to our needs.
In our assesment, we delete the applications, the old passwords and the user, during the deletion of the user we need to throw an exception. At this moment there should be a rollback and the user should be back whit his applications and his old passwords.

We will see that depending of the type of the transaction the behaviour of the transaction will drastically change.
We delete the applications, then the old passwords, and finally the user.

## Observation about the different attribute types 

### MANDATORY
The option *MANDATORY* is interesting only when we want to force the use of a transaction during the request, if you're not working in a transaction it will throw an exception.
This method can help us to get the perfect case where the rollback give us to the initial state.

### REQUIRED
The default option, if we already have a transaction it will be use if we don't, a new one will be created.
This option is really interesting because it will guarantee that our problem will be resolved with one single transaction and the rollback will be clean.

### REQUIRES_NEW
The option *REQUIRES_NEW* will always create a new transaction and close it just after the request. In some case this is really usefull, but not for our issue.
I've tried to create a transaction with REQUIRES_NEW and use it with REQUIRED but this doesn't work at all, the first transaction is finished and a new one is started and when the exception is returned we can see that we've lost some datas.

But in other scopes this option can be really helpfull.

### SUPPORTS
The option *SUPPORTS* must be used with caution, it works like *REQUIRED* and *NOT_SUPPORTED* depending on the context.
If we wotk in a transactionnal context it will be used just like *REQUIRED*, in the other case we won't have any transaction and the rollback will fail.

### NOT_SUPPORTED
The option *NOT_SUPPORTED* will ignore any transaction, this option is useless for our issue because the rollback can't be done on all the datas.

### NEVER
The option *NEVER* is absolutely useless for our issue and should not be used. This option is usefull when we use an old MySQL engine like the MyISAM who doesn't have any transactionnal context and we want to make sure that the code will follow this rule.

## The winning combinations
To succesfully rollback our transaction, there's only a few working options.

|       | Applications  | Passwords  | User       |
| :----:|:-------------:| :---------:|-----------:|
| 1     | REQUIRED      | REQUIRED   | REQUIRED   |
| 2     | REQUIRED      | REQUIRED   | MANDATORY  |
| 3     | REQUIRED      | REQUIRED   | SUPPORTS   |
| 4     | REQUIRED      | MANDATORY  | REQUIRED   |
| 5     | REQUIRED      | MANDATORY  | MANDATORY  |
| 6     | REQUIRED      | MANDATORY  | SUPPORTS   |
| 7     | REQUIRED      | SUPPORTS   | REQUIRED   |
| 8     | REQUIRED      | SUPPORTS   | MANDATORY  |
| 9     | REQUIRED      | SUPPORTS   | SUPPORTS   |
| 10    | MANDATORY     | REQUIRED   | REQUIRED   |
| 11    | MANDATORY     | REQUIRED   | MANDATORY  |
| 12    | MANDATORY     | REQUIRED   | SUPPORTS   |
| 13    | MANDATORY     | MANDATORY  | REQUIRED   |
| 14    | MANDATORY     | MANDATORY  | MANDATORY  |
| 15    | MANDATORY     | MANDATORY  | SUPPORTS   |
| 16    | MANDATORY     | SUPPORTS   | REQUIRED   |
| 17    | MANDATORY     | SUPPORTS   | MANDATORY  |
| 18    | MANDATORY     | SUPPORTS   | SUPPORTS   |
| 19    | SUPPORTS      | REQUIRED   | REQUIRED   |
| 20    | SUPPORTS      | REQUIRED   | MANDATORY  |
| 21    | SUPPORTS      | REQUIRED   | SUPPORTS   |
| 22    | SUPPORTS      | MANDATORY  | REQUIRED   |
| 23    | SUPPORTS      | MANDATORY  | MANDATORY  |
| 24    | SUPPORTS      | MANDATORY  | SUPPORTS   |
| 25    | SUPPORTS      | SUPPORTS   | REQUIRED   |
| 26    | SUPPORTS      | SUPPORTS   | MANDATORY  |
| 27    | SUPPORTS      | SUPPORTS   | SUPPORTS   |

Basicaly any combination with *REQUIRED*, *REQUIRES_NEW*, and *SUPPORT* will work, anything else won't.

## Source 
- To test the transactionnal demarcation, we have made a new branch in our project where we use the code from **[the example for the transaction](https://github.com/SoftEng-HEIGVD/Teaching-HEIGVD-AMT-Transactions-with-EJB "EJB transaction example")**, then we replaced the models and DAO by ours.

- To understand the attributes, we have read **[the Java ee 6 datasheet](https://docs.oracle.com/javaee/6/api/javax/ejb/TransactionAttributeType.html "Java EE 6 official documentation")**