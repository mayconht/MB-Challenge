# Much Better Challenge

## :computer: Getting Started

### Running the project locally:

````docker-compose up````

>**The endPoint references tests are on challenge page provided by MuchBetter team and can be imported to Postman**
>**The system is using REDIS Memory DB. There is a persistence layer in this project provided on Docker-compose file.**

## :pencil: Approach (Dev Review)
The project's main idea is to create a microservice for account management.
4 endpoints were provided, login, Spend, Balance, and Transactions, with 2 POST requests and 2 GET requests respectively.
When calling a non-listed endpoint you should receive a 404 error for page not found or 405 method not allowed.

unfortunately, this project is not using Java Currency or Java Money,  I assumed Double variables as the main currency manipulator even knowing its implications when rounding currency.

There is not much information other than RatPack official page, it was such a challenge to develop using this framework. but was quite fun trying something other than SpringBoot.

### :wrench: Improvement Points (Dev code Review)
* Improve and create all Test cases using Junit / Cucumber
* Create more exception cases and/or generic exceptions
* Improve docker service by adding options to memory management and fixing a few RatPack package management errors.
* Use Swagger 
* Not Commit Env file to repository (This is a test, so I think it is ok)

### :speech_balloon:

It was such a great and insightful challenge.
It really made me get to a few limits that I sometimes was not able to think about it, it is hard to think out of the box (that box named Spring boot).
So I have to say thank you for this opportunity and hope you guys can see this code as a true way to show my knowledge and how much more I can acquire with the right guidance, as I mentioned during the interviews.
