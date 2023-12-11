package util;

import com.aventstack.extentreports.Status;
import org.testng.annotations.Test;

public class ExtentTestSampler1 extends BTest{
    @Test
    public void MainTest1(){

        Extent.getTest().log(Status.INFO,"launching Browser in next test");

        Extent.getTest().log(Status.FAIL, "Something is wrong in new test");

        Extent.getTest().log(Status.WARNING, "alert test is incorrect in new test");

        Extent.getTest().pass("");
    }
}
