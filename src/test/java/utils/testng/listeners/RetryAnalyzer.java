package utils.testng.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    Logger logger = LogManager.getLogger(RetryAnalyzer.class);

    private int count = 0;
    private static final int MAX_NUMBER_OF_RETRIES = 2;

    @Override
    public boolean retry(ITestResult result) {
        if(!result.isSuccess()) {
            if (count < MAX_NUMBER_OF_RETRIES) {
                count++;
                logger.info("Retrying test method{}", result.getName());
                return true;
            }
        }
            logger.info("Test method {} will be not retried!", result.getName());
        return false;
    }
}
