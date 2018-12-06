package lib.listeners;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import reports.Reporter;

public class WebDriverListener extends Reporter implements WebDriverEventListener {

	public RemoteWebDriver webdriver;
	public EventFiringWebDriver driver;
	public int i = 1;
	WebDriverWait wait;

	@Override
	public void beforeAlertAccept(WebDriver driver) {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.alertIsPresent());
	}

	@Override
	public void afterAlertAccept(WebDriver driver) {
		reportStep("The alert is accepted", "pass");

	}

	@Override
	public void afterAlertDismiss(WebDriver driver) {
		reportStep("The alert is dismissed", "pass");

	}

	@Override
	public void beforeAlertDismiss(WebDriver driver) {
		wait.until(ExpectedConditions.alertIsPresent());
	}

	@Override
	public void beforeNavigateTo(String url, WebDriver driver) {
	}

	@Override
	public void afterNavigateTo(String url, WebDriver driver) {
		reportStep("The browser loaded the URL " + url, "pass");


	}

	@Override
	public void beforeNavigateBack(WebDriver driver) {
	}

	@Override
	public void afterNavigateBack(WebDriver driver) {
		reportStep("The browser has loaded the previous page from the history", "pass");
		takeSnap();
	}

	@Override
	public void beforeNavigateForward(WebDriver driver) {

	}

	@Override
	public void afterNavigateForward(WebDriver driver) {
		reportStep("The browser has loaded the next page from the history", "pass");


	}

	@Override
	public void beforeNavigateRefresh(WebDriver driver) {

	}

	@Override
	public void afterNavigateRefresh(WebDriver driver) {
		reportStep("The browser has reloaded successfully", "pass");
		takeSnap();
	}

	@Override
	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
	}

	@Override
	public void afterFindBy(By by, WebElement element, WebDriver driver) {
	}

	@Override
	public void beforeClickOn(WebElement element, WebDriver driver) {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	@Override
	public void afterClickOn(WebElement element, WebDriver driver) {
		reportStep("The element " + element + " is clicked successfully", "pass");
		takeSnap();
	}

	@Override
	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
	}

	@Override
	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		reportStep("The value " + keysToSend[0] + " is entered successfully in element " + element, "pass");
	}

	@Override
	public void beforeScript(String script, WebDriver driver) {

	}

	@Override
	public void afterScript(String script, WebDriver driver) {

	}

	@Override
	public void beforeSwitchToWindow(String windowName, WebDriver driver) {

	}

	@Override
	public void afterSwitchToWindow(String windowName, WebDriver driver) {
		reportStep("The driver is moved to the window with title " + driver.getTitle(), "pass");
	}

	@Override
	public void onException(Throwable throwable, WebDriver driver) {

		if (throwable instanceof NoSuchFrameException) {
			reportStep("No frame found\n" + throwable.getMessage(), "fail");
			throw new RuntimeException();
		}
		if (throwable instanceof NoSuchWindowException) {
			reportStep("No frame found\n" + throwable.getMessage(), "fail");
			throw new RuntimeException();
		}
		if (throwable instanceof NoSuchSessionException) {
			reportStep("NoSuchSessionException" + throwable.getMessage(), "fail");
			throw new RuntimeException();
		} 
		else if (throwable instanceof NullPointerException) {
			reportStep("NullPointerException" + throwable.getMessage(), "fail");
			throw new RuntimeException();
		}
		else if (throwable instanceof NoSuchElementException) {
			reportStep("NoSuchElementException\n" + throwable.getMessage(), "fail");
			throw new RuntimeException();
		} 
		else if (throwable instanceof NoAlertPresentException) {
			reportStep("NoAlertPresentException", "fail");
		}
	}

	@Override
	public <X> void beforeGetScreenshotAs(OutputType<X> target) {
	}

	@Override
	public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {
	}

	@Override
	public long takeSnap() {
		long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L;
		try {
			FileUtils.copyFile(driver.getScreenshotAs(OutputType.FILE),
					new File(file.toString()+"/images/" + number + ".jpg"));
		} catch (WebDriverException e) {

		} catch (IOException e) {

		}
		return number;
	}

	@Override
	public void beforeGetText(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterGetText(WebElement element, WebDriver driver, String text) {
		// TODO Auto-generated method stub
		
	}
}
