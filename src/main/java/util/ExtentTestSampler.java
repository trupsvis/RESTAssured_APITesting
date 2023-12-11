package util;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.annotations.Test;

public class ExtentTestSampler extends BTest{
    @Test
    public void MainTest(){

        Extent.getTest().log(Status.INFO,"launching Browser");

        Extent.getTest().log(Status.FAIL, "Something is wrong");

        Extent.getTest().log(Status.WARNING, "alert test is incorrect");

        Extent.getTest().pass("");

    }

}
