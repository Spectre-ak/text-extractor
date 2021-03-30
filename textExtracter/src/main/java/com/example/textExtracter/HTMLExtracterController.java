package com.example.textExtracter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HTMLExtracterController {
	@PostMapping("/default")
	public String defaultDOMExtracter(@RequestParam(value = "url",defaultValue = "")String url,
			@RequestParam(value = "parser",defaultValue = "mozilla")String parser) {
		try {
			URL urlObject=new URL(url);
			URLConnection con = urlObject.openConnection();
			InputStream is = con.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			String srcPage="";
			while ((line = br.readLine()) != null) {
				srcPage+=line;
			}
			if(parser.equals("jsoup")) {
				System.out.println(Jsoup.parse(srcPage).text());
				return Jsoup.parse(srcPage).wholeText();
			}
			else
				return srcPage;

		} catch (Exception e) {
			// TODO: handle exception
			return e.getMessage();
		}
	
	}
	
	@PostMapping("/jsoup")
	public String JsoupDOMExtracter(@RequestParam(value = "url",defaultValue = "")String url,
			@RequestParam(value = "parser",defaultValue = "mozilla")String parser) {
		if(url.isEmpty())
			return "invalid url";
		try {
			Document document=Jsoup.connect(url).get();
			if(parser.equals("jsoup")) {
				System.out.println(document.text());
				return document.wholeText();
			}
			else {
				return document.html();
			}
		}
		catch (Exception e) {
			return e.getMessage();
		}
		
	}
	
	private final CloseableHttpClient httpClient = HttpClients.createDefault();
	
	
	@PostMapping("/chromeHeadless")
	public String chromeHeadless(@RequestParam(value = "url",defaultValue = "")String url,
			@RequestParam(value = "parser",defaultValue = "mozilla")String parser) {
	  try {
			
			System.out.println("asd "+parser);
			
			ChromeOptions capabilities = new ChromeOptions();
			  
			 capabilities.addArguments("--no-sandbox");
			 capabilities.addArguments("--disable-dev-shm-usage");
			 capabilities.addArguments("--incognito");
			 RemoteWebDriver driver = new RemoteWebDriver
					 (new URL("http://headlesschromet.azurewebsites.net/wd/hub"),capabilities);
			 
			driver.get(url);
			System.out.println(parser);
			
			if(parser.equals("headless")) {
				//System.out.println(driver.findElement(By.tagName("html")).getText());
				String text=driver.findElement(By.tagName("html")).getText();
				driver.close();
				return text;
			}
			else if(parser.equals("jsoup")) {
				String parsed=Jsoup.parse(driver.getPageSource()).wholeText();
				return parsed;
			}
			else {
				String srcString=driver.getPageSource();
				driver.close();
				return srcString;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		
	}
	
	@PostMapping("/jsoupSrcToText")
	public String jsoupSrcToText(@RequestParam(value = "src",defaultValue = "")String src) {
		try {
			return Jsoup.parse(src).text();
		}
		catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
}
