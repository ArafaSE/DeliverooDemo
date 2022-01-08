## DeliverooDemo
This repo to test the main basic feature in the Deliveroo website  

## About Project
This is a Demo project to apply Test Automaion with Selenium webDriver and Cucumber
The Project contains 2 E2E Test Scenarios in 1 feature file and 1 Steps Class

## How to run Test Scenarios
1. Clone the repo from GitHub
2. Open terminal and run the above commands
   >mvn clean
   
   >mvn build

   >mvn test
## Outbut
You can check the test outbut from the terminal logs or can check the generated test report from the follwing path 
/target/cucumber-html-report.html

## How to Integrate it with Jenkins/Docker
1. Configure Jenkins for a maven project 
2. Configure GIT plugin in jenkins\
  2.1  Go To Manage Plugins –> Filter list of plugins available with ‘Git Plugin’\
  2.2  Check the Git Plugin and click on the button ‘Install without restart’\
  2.3  After the installation is done, restart Jenkins\
  2.4  Go to Manage Jenkins –> Configure System, please provide the right Path to Git executable
3. Create Maven Project in Jenkins and build a job from Git Project – Follow below steps\
  3.1 Click on New Items –> Enter Project Name (say GitProject) –> Select Freestyle Project –> Click OK\
  3.2 In Source Code Management, Select ‘Git’ option\
  3.3 Give your Git Repository URL(https://github.com/ArafaSE/DeliverooDemo)\
  3.4 In Build – click on Add build step and choose Invoke top-level Maven targets and pass ‘clean install‘ as Goals.\
  3.5 Click on Advanced button and pass POM value as ‘pom.xml‘
  
## Tech stack
JAVA language
Selenium Web-Driver
Page Object Model (POM) design
BDD with Cucumber
TestNg
