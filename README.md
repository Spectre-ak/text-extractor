# text-extracter

Link: https://textext.azurewebsites.net/

Web interface for extracting text content from HTML, websites using Jsoup, Selenium chrome headless, mozilla's readability [->](https://github.com/mozilla/readability)

## Default:
Default mode on the application extracts the page source(DOM) and then parses it in either Jsoup or readability then retrieves the readable contenet.[->](https://github.com/Spectre-ak/text-extracter/blob/1e2be572ffe50d0358f3d06d9c7578c1b1a3d980/textExtracter/src/main/java/com/example/textExtracter/HTMLExtracterController.java#L34)

## Jsoup:
Jsoup docs [->](https://jsoup.org/)

Used for parsing source and extracting text/source in application see[->](https://github.com/Spectre-ak/text-extracter/blob/1e2be572ffe50d0358f3d06d9c7578c1b1a3d980/textExtracter/src/main/java/com/example/textExtracter/HTMLExtracterController.java#L61)

## Chrome headless:
Chrome headless is a way of using chrome browser without having an actual UI using Selenium and chrome web driver. 
For this web application chrome headless runs on an AWS EC2 Windows instance (virtual machine). For reading and extracting contents from web pages spring rest controller is used which runs on the same instance using Tomcat as a jar file. [->](https://github.com/Spectre-ak/text-extracter/blob/1e2be572ffe50d0358f3d06d9c7578c1b1a3d980/textExtracter/src/main/java/com/example/textExtracter/HTMLExtracterController.java#L85)


## Docker version
Using selenium standalone-chrome docker image and remote driver in code.
