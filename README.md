# GuestBook

The below software/plugins must involve in your system to execute this GuestBook Application

Required Softwares on your system : 
Technologies:SpringBoot,SpringSecurity,Spring Data JPA,Spring Web,Bootstrap,Thymeleaf,MySQL,Maven,SMTP

1.To start the application please clone this git url : git clone https://github.com/sidhukshetri125/GuestBook/tree/master


2.Please add your mysql user name and password in application.properties file


3.Please execute mvn clean install command from GuestBookMaster(where parent pom is present)


4.Jar file will be genarate in target folder. Please execute jar from command line : java -jar ../target/GuestBookMaster-1.0.jar


5.if you are using Eclipse,STS then import the project and run as Spring Boot App


6.then hit the url http://localhost:8080/login page 7. 1.Admin user name :advait@gmail.com ,password : advait2020, 2.Guest User Name: sidkshetri@gmail.com and password sidhu2020



Mysql Database: Table Names : user , role , users_roles, feedback



1.Functionality is developed based on Roles USER and ADMIN 


2.User can register and give the feedbacks 


3.Conatct Us -> is for users , they can send their compliments , wishes using email 


4.Admin users can see all the functionality of the application like User Details,Viewallfeedbacks,emails



