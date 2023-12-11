package util;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.annotations.Test;

public class ExtentReport {

    @Test
    public void reportFunction() {
        ExtentReports extent = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/testReport.html");

        //configuration items to change the look and feel
        //add content, manage tests etc
            sparkReporter.config().setTheme(Theme.STANDARD);
            sparkReporter.config().setDocumentTitle("Simple Automation Report");
            sparkReporter.config().setEncoding("utf-8");
            sparkReporter.config().setReportName("My Execution Report");
            sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

        extent.attachReporter(sparkReporter);
        extent.createTest("MyFirstTest").log(Status.PASS, "This is a logging event for MyFirstTest, and it passed!");
        extent.flush();

    }
}
