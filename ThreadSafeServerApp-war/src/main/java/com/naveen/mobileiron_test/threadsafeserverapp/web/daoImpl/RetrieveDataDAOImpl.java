package com.naveen.mobileiron_test.threadsafeserverapp.web.daoImpl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.naveen.mobileiron_test.threadsafeserverapp.web.beans.QueueData;

@Service
public class RetrieveDataDAOImpl {

    final static Logger LOG = LoggerFactory
            .getLogger(RetrieveDataDAOImpl.class);

    /*
     * Create linked list to hold queue data and let it load it during spring
     * bean creation into memory during initialization
     */

    static Queue<String> queImpl = new LinkedList<String>();

    /*
     * Create an atomic integer object to hold thread count. Reason for atomic
     * integer is, its thread safe and using integer data type doesn't give
     * accurate data
     */

    static AtomicInteger counter1 = new AtomicInteger();
    static AtomicInteger counter2 = new AtomicInteger();
    static AtomicInteger counter3 = new AtomicInteger();

    /*
     * 
     * Let producer utility create the random number and place it in file system
     * Path of the file in the file system
     */
    final File file = new File(
            "C:/Users/464031/Desktop/MFA/ThreadSafeServerApp/ThreadSafeServerApp-war/src/main/resources/MyFile.txt");

    /*
     * Let this method execute as soon as application started
     */
    @PostConstruct
    protected final void queImplement() {

        List<String> someList = new ArrayList<String>();
        try {
            LOG.info("Loading file from file system, File path is {} ",
                    file.getAbsolutePath());
            someList = FileUtils.readLines(file);

            for (String item : someList) {
                if (!item.isEmpty()) {
                    queImpl.add(item);
                    LOG.info("Adding item to que {}", queImpl.size());
                }
            }
        } catch (IOException e) {
            LOG.error("Unable to load file, IOException occured");
            e.printStackTrace();
        }

    }

    /*
     * This method is just for testing to show the number of threads waiting to
     * execute thread safe getHeadofTheQueue method
     */

    public QueueData accessThreadSafegetHeadofTheQueueMethod(UUID threadId,
            QueueData queueData) {

        int count = counter1.getAndIncrement();

        LOG.info(
                "{} Number of threads waiting to execute getHeadofTheQueue Method: {}",
                threadId, counter1.get());

        queueData = getHeadofTheQueue(count, threadId, queueData);

        return queueData;
    }

    /*
     * Creating a thread safe method to get head of the queue This method
     * accepts threadId, count as input parameters and respond with QueueData
     * object Count is number of threads waiting to execute this method Thread
     * id is the thread id of the transaction
     */

    protected synchronized QueueData getHeadofTheQueue(int count,
            UUID threadId, QueueData queueData) {
        LOG.info("{} Size of the queue {}", threadId, queImpl.size());
        queueData.setSize(queImpl.size());
        if (queImpl.size() == 0) {
            try {
                TimeUnit.SECONDS.sleep(5);
                LOG.info("{} queue size is zero and waiting for 5 seconds",
                        threadId);
            } catch (InterruptedException e) {
                LOG.error("Thread sleep interval interrupted ");
                e.printStackTrace();
            }
        }
        queueData.setHeadOftheQueue(queImpl.peek());
        LOG.info(" {} thread is trying to access getHeadofTheQueue method",
                threadId);
        LOG.info("{} Head of the queue is {}", threadId, queImpl.peek());

        // Decrement the counter since the thread execution completed.
        counter1.getAndDecrement();
        LOG.info(
                "{} Threads waiting after getHeadofTheQueue method execution ",
                threadId, counter1.get());
        return queueData;
    }

    /*
     * This method is just for testing to show the number of threads waiting to
     * execute thread safe retrieveHeadofTheQueue method
     */

    public QueueData accessThreadSafeRetrieveHeadofTheQueueMethod(
            UUID threadId, QueueData queueData) {

        int count = counter2.getAndIncrement();

        LOG.info(
                "{} Number of threads waiting to execute retrieveHeadofTheQueue Method: {}",
                threadId, counter2.get());

        queueData = retrieveHeadofTheQueue(count, threadId, queueData);

        return queueData;
    }

    /*
     * Create a thread safe method to retrieve head of the queue. This method
     * accepts threadId, count as input parameters and respond with QueueData
     * object Count is number of threads waiting to execute this method Thread
     * id is the thread id of the transaction
     */

    protected synchronized QueueData retrieveHeadofTheQueue(int count,
            UUID threadId, QueueData queueData) {
        int i = 1;
        LOG.info("{} Size of the queue {}", threadId, queImpl.size());

        if (queImpl.size() == 0) {
            try {
                TimeUnit.SECONDS.sleep(5);
                LOG.info("{} queue size is zero and waiting for 5 seconds",
                        threadId);
            } catch (InterruptedException e) {
                LOG.error("Thread sleep interval interrupted ");
                e.printStackTrace();
            }
        }
        queueData.setDataString(queImpl.poll());
        queueData.setSize(queImpl.size());
        for (Iterator<String> stringIterator = queImpl.iterator(); stringIterator
                .hasNext();) {

            switch (i) {

            case 1:
                queueData.setHeadOftheQueue(stringIterator.next());
                break;
            case 2:
                queueData.setSecondElement(stringIterator.next());
                break;
            case 3:
                queueData.setThirdElement(stringIterator.next());
                break;
            case 4:
                queueData.setFourthElement(stringIterator.next());
                break;
            case 5:
                queueData.setFifthElement(stringIterator.next());
                break;
            default:
                break;
            }

            i++;
            if (i > 5) {
                break;
            }
        }

        LOG.info(
                " {} thread is trying to access retrieveHeadofTheQueue method",
                threadId);
        LOG.info("{} Head of the queue is {}", threadId, queImpl.peek());
        // Decrement the counter since the thread execution completed.
        counter2.getAndDecrement();
        LOG.info(
                "{} Threads waiting after retrieveHeadofTheQueue method execution {}",
                threadId, counter2.get());
        return queueData;
    }

    /*
     * This method is just for testing to show the number of threads waiting to
     * execute thread safe retrieveHeadofTheQueue method
     */

    public QueueData accessThreadSafeRetrieveFirstFiveTheQueueMethod(
            UUID threadId, QueueData queueData) {

        int count = counter3.getAndIncrement();

        LOG.info(
                "{} Number of threads waiting to execute retrieveFirstFiveTheQueue Method: {}",
                threadId, counter3.get());

        queueData = retrieveFirstFiveTheQueue(count, threadId, queueData);

        return queueData;
    }

    /*
     * Create a thread safe method to retrieve first five of the queue. This
     * method accepts threadId, count as input parameters and respond with
     * QueueData object Count is number of threads waiting to execute this
     * method Thread id is the thread id of the transaction
     */
    protected synchronized QueueData retrieveFirstFiveTheQueue(int count,
            UUID threadId, QueueData queueData) {
        int i = 1;
        LOG.info("{} Size of the queue {}", threadId, queImpl.size());
        queueData.setSize(queImpl.size());
        if (queImpl.size() == 0) {
            try {
                TimeUnit.SECONDS.sleep(5);
                LOG.info("{} queue size is zero and waiting for 5 seconds",
                        threadId);
            } catch (InterruptedException e) {
                LOG.error("Thread sleep interval interrupted ");
                e.printStackTrace();
            }
        }
        for (Iterator<String> stringIterator = queImpl.iterator(); stringIterator
                .hasNext();) {

            switch (i) {
            case 1:
                queueData.setHeadOftheQueue(stringIterator.next());
                break;
            case 2:
                queueData.setSecondElement(stringIterator.next());
                break;
            case 3:
                queueData.setThirdElement(stringIterator.next());
                break;
            case 4:
                queueData.setFourthElement(stringIterator.next());
                break;
            case 5:
                queueData.setFifthElement(stringIterator.next());
                break;
            default:
                break;
            }
            i++;
            if (i > 5) {
                break;
            }
        }

        LOG.info(
                " {} thread is trying to access retrieveHeadofTheQueue method",
                threadId);
        // Decrement the counter since the thread execution completed.
        counter3.getAndDecrement();
        LOG.info(
                "{} Threads waiting after retrieveHeadofTheQueue method execution: {} ",
                threadId, counter3.get());
        return queueData;
    }

    /*
     * Add randomStrings to queue as soon as generated by Producer
     */
    public void addToQue(String randomString) {

        queImpl.add(randomString);

    }

    /*
     * 
     * Delete old file from file system and Copy updated queue info into file
     */

    @PreDestroy
    protected void copyUpdatedContentIntoFile() {
        LOG.info("Queue Size before deleting file {}", queImpl.size());
        file.delete();
        try {
            for (String item : queImpl) {
                LOG.info("Queue Size before destroy {}", queImpl.size());
                FileUtils.writeStringToFile(file, item + "\n", "UTF-8", true);

            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
