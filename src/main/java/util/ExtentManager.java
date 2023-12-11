package util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    static ExtentReports report;
    public static ExtentReports getInstance(){

        if(report == null) {

            report = new ExtentReports();
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/testReport.html");

            //configuration items to change the look and feel
            //add content, manage tests etc
            sparkReporter.config().setTheme(Theme.STANDARD);
            sparkReporter.config().setDocumentTitle("Simple Automation Report");
            sparkReporter.config().setEncoding("utf-8");
            sparkReporter.config().setReportName("My Execution Report");
            sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

            report.attachReporter(sparkReporter);
        }

        return report;
    }
}
