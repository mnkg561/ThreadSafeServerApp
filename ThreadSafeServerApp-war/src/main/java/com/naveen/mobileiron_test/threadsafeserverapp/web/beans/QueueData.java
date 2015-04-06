package com.naveen.mobileiron_test.threadsafeserverapp.web.beans;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueueData {
    private int size;
    private String dataString;
    private String headOftheQueue;
    private String secondElement;
    private String thirdElement;
    private String fourthElement;
    private String fifthElement;
    
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    
    public String getDataString() {
        return dataString;
    }
    public void setDataString(String dataString) {
        this.dataString = dataString;
    }
    public String getHeadOftheQueue() {
        return headOftheQueue;
    }
    public void setHeadOftheQueue(String headOftheQueue) {
        this.headOftheQueue = headOftheQueue;
    }
    public String getSecondElement() {
        return secondElement;
    }
    public void setSecondElement(String secondElement) {
        this.secondElement = secondElement;
    }
    public String getThirdElement() {
        return thirdElement;
    }
    public void setThirdElement(String thirdElement) {
        this.thirdElement = thirdElement;
    }
    public String getFourthElement() {
        return fourthElement;
    }
    public void setFourthElement(String fourthElement) {
        this.fourthElement = fourthElement;
    }
    public String getFifthElement() {
        return fifthElement;
    }
    public void setFifthElement(String fifthElement) {
        this.fifthElement = fifthElement;
    }
    
}
