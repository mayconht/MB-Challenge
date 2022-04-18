# Much Better Challenge

## :computer: Getting Started

### Running the project locally:

````docker-compose up````

>**The endPoint references tests are on the challenge page provided by the MuchBetter team and can be imported to Postman. The system is using REDIS** >**Memory DB. In addition, there is a persistence layer in this project provided on the Docker-compose file.**

## :pencil: Approach (Dev Review)
The project's main idea is to create a microservice for account management. 4 endpoints were provided - login, Spend, Balance, and Transactions - with 2 POST requests and 2 GET requests, respectively. When calling a non-listed endpoint, you should receive a 404 error for page not found or 405 method not allowed.

Unfortunately, this project is not using Java Currency or Java Money. Therefore, I assumed Double variables as the main currency manipulator, even knowing its implications when rounding currency.

There is not much information other than RatPack's official page. It was quite a challenge to develop using this framework. But it was pretty fun trying something different than SpringBoot.

### :wrench: Improvement Points (Dev code Review)
* Improve and create all Test cases using Junit / Cucumber
* Create more exception cases or generic exceptions
* Improve docker service by adding options to memory management and fixing a few RatPack package management errors.
* Use Swagger
* Not Commit the Env file to the repository (This is a test, so I think it is ok)

### :speech_balloon:

It was such a tremendous and insightful challenge. It made me get to a few limits that I sometimes could not think about. It is hard to think out of the box (SpringBoot). So I have to say thank you for this opportunity, and I hope you guys can see this code as an accurate way to show my knowledge and how much more I can acquire with the proper guidance, as I mentioned during the interviews.
