package com.naveen.mobileiron_test.threadsafeserverapp.web.controller;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.naveen.mobileiron_test.threadsafeserverapp.web.beans.QueueData;
import com.naveen.mobileiron_test.threadsafeserverapp.web.daoImpl.RetrieveDataDAOImpl;

// Controller class and serves all the request from client by talking to actual business logic in RetriveDataDAOImpl.

@Controller
public class ConsumerController {
    
    
   @Autowired
   RetrieveDataDAOImpl retrieveData;
   
   @RequestMapping(value ="/index")
   protected String indexPage(){
       
       return "index";
   }

	///printHeader method is to handle head of the queue
    @RequestMapping(value = "/printHeader")
    protected @ResponseBody
    QueueData printHeadValue() {
        QueueData queueData = new QueueData();
	//Create a threadsafe random number and use it as transaction id for that particular request
        UUID threadId = UUID.randomUUID();
	//Call accessThreadSafegetHeadofTheQueueMethod to retrieve head of the queue with out removing any data from queue
        queueData = processQueueData(retrieveData.accessThreadSafegetHeadofTheQueueMethod(
                threadId, queueData));

        return queueData;
    }
    
    @RequestMapping (value ="/retrieveData")
    public @ResponseBody QueueData retrieveHeadofTheQueue(){
        QueueData queueData = new QueueData();
	//Create a threadsafe random number and use it as transaction id for that particular request
        UUID threadId = UUID.randomUUID();
	//Call accessThreadSafeRetrieveHeadofTheQueueMethod to retrieve head of the queue and top 5 elements in latest queue
        queueData = processQueueData(retrieveData.accessThreadSafeRetrieveHeadofTheQueueMethod(threadId, queueData));
        return queueData;
    }

    @RequestMapping (value ="/topFive")
    public @ResponseBody QueueData retrieveTopFiveOfTheQueue(){
        QueueData queueData = new QueueData();
	//Create a threadsafe random number and use it as transaction id for that particular request
        UUID threadId = UUID.randomUUID();
	//Call accessThreadSafeRetrieveFirstFiveTheQueueMethod to retrieve h top 5 elements of the queue without altering any data in queue
        queueData = processQueueData(retrieveData.accessThreadSafeRetrieveFirstFiveTheQueueMethod(threadId, queueData));
        return queueData;
    }
    
//method to handle null values if there are no or very few elements available. Change null values to empty values and send it to client as per requirement
    public QueueData processQueueData(QueueData queueData){
        if (queueData.getDataString() == null){
            queueData.setDataString("");
        }
        if(queueData.getHeadOftheQueue() == null){
            queueData.setHeadOftheQueue("");
          }
        if(queueData.getSecondElement() == null){
            queueData.setSecondElement("");
          }
        if(queueData.getThirdElement() == null){
            queueData.setThirdElement("");
          }
        if(queueData.getFourthElement() == null){
            queueData.setFourthElement("");
          }
        if(queueData.getFifthElement() == null){
            queueData.setFifthElement("");
          }
        
        return queueData;
        
    }
}
