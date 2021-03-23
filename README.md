# text-extracter

Link: https://textextracter.azurewebsites.net/

Web interface for extracting text content from HTML, websites using Jsoup, Selenium chrome headless, mozilla's readability

## Default:
Default mode on the application extracts the page source(DOM) and then parses it in either Jsoup or readability then retrieves the readable contenet.

## Jsoup:
Jsoup docs [->](https://jsoup.org/)

Used for parsing source and extracting text/source in application see[->]()

## Chrome headless:
Chrome headless is a way of using chrome browser without having an actual UI using Selenium and chrome web driver. 
For this web application chrome headless runs on an AWS EC2 Windows instance (virtual machine). For reading and extracting contents from web pages spring rest controller is used which runs on the same instance using Tomcat as a jar file. [->]()
