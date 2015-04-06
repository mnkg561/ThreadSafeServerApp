package com.naveen.mobileiron_test.threadsafeserverapp.web.utilities;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import com.naveen.mobileiron_test.threadsafeserverapp.web.daoImpl.RetrieveDataDAOImpl;

@Component
public class ProducerUtility {
    
    @Autowired
    RetrieveDataDAOImpl retriveData;
    
    final static Logger LOG = LoggerFactory
            .getLogger(ProducerUtility.class);
    
    /*
     * 
     * Let producer utility create the random number and place it in file system
     * Path of the file in the file system
     */
    final File file = new File(
            "C:/Users/464031/Desktop/MFA/ThreadSafeServerApp/ThreadSafeServerApp-war/src/main/resources/MyFile.txt");
   
    /*
     * ProducerMethod where it generates random strings in random intervals
     */
    @Scheduled(fixedDelay = 60000)
    protected void randomProducer() {

        try {
            LOG.info(" Adding content to file dynamically");

            String randomString = RandomStringUtils.randomAlphanumeric(450)
                    + "\n";
            retriveData.addToQue(randomString);
            FileUtils.writeStringToFile(file, randomString, true);
            Random random = new Random();
            int randomNumber = random.nextInt(60);
            LOG.info("Waiting for " + randomNumber + "seconds");
            TimeUnit.SECONDS.sleep(randomNumber);
        } catch (IOException e) {
            LOG.error(" Unable to write into file ");
            e.printStackTrace();
        } catch (InterruptedException e) {
            LOG.error("TimeUnit sleep thread got Interrupted");
            e.printStackTrace();
        }

    }
    



}
