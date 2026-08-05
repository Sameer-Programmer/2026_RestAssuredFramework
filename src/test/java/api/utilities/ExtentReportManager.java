package api.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;

    String repName;

    @Override
    public void onStart(ITestContext testContext) {

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-" + timeStamp + ".html";

        // Report Location
        sparkReporter = new ExtentSparkReporter("./reports/" + repName);

        // Report Configuration
        sparkReporter.config().setDocumentTitle("Rest Assured Automation Report");
        sparkReporter.config().setReportName("Pet Store Users API Automation");
        sparkReporter.config().setTheme(Theme.DARK);

        // Attach Reporter
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // System Information
        extent.setSystemInfo("Application", "Pet Store Users API");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Operating System", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("User Name", System.getProperty("user.name"));

        // Optional TestNG Parameters
        String os = testContext.getCurrentXmlTest().getParameter("os");
        if (os != null) {
            extent.setSystemInfo("Execution OS", os);
        }

        String browser = testContext.getCurrentXmlTest().getParameter("browser");
        if (browser != null) {
            extent.setSystemInfo("Browser", browser);
        }

        // Groups
        List<String> groups = testContext.getCurrentXmlTest().getIncludedGroups();
        if (!groups.isEmpty()) {
            extent.setSystemInfo("Groups", groups.toString());
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        test.assignCategory(result.getMethod().getGroups());
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        test.log(Status.PASS,
                result.getMethod().getMethodName() + " PASSED");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.log(Status.FAIL,
                result.getMethod().getMethodName() + " FAILED");

        if (result.getThrowable() != null) {
            test.log(Status.FAIL, result.getThrowable());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        test.log(Status.SKIP,
                result.getMethod().getMethodName() + " SKIPPED");

        if (result.getThrowable() != null) {
            test.log(Status.SKIP, result.getThrowable());
        }
    }

    @Override
    public void onFinish(ITestContext context) {

        extent.flush();

        String reportPath = System.getProperty("user.dir")
                + "/reports/" + repName;

        File reportFile = new File(reportPath);

        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(reportFile.toURI());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}