package com.distributedworkflowengine.stateinit.messenger;
 
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.concurrent.CountDownLatch;
import org.springframework.stereotype.Service;

import com.distributedworkflowengine.stateinit.domain.Trigger;
import com.distributedworkflowengine.stateinit.services.RedisoprImpl;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.kafka.annotation.KafkaListener;

//<!----Consumer: consumes String and Report -->
@Service
public class StateInitializerConsumer {
	
	@Autowired
	StateInitializerProducer engine;
	
	@Autowired
	RedisoprImpl redisoprImpl;
	  private CountDownLatch latch = new CountDownLatch(1);

	  public CountDownLatch getLatch() {
	    return latch;
	  }
	
	  private static Logger logger = LogManager.getLogger("MethodLogger.class");
	
	  //<!-- method to get jobid and workflow object from job manager -->
	  
	@KafkaListener(topics = "trigger-engine", 
			  containerFactory = "triggerKafkaListenerContainerFactory")
		public void triggerlistener(Trigger trigger) throws JsonProcessingException {
		
		System.out.println(trigger.getJobId());
		System.out.println(trigger.getWorkFlow());
		System.out.println("sending to save");
		
		redisoprImpl.saveRedis(trigger);

		engine.sendMessage(trigger.getJobId());

			    latch.countDown();
			} 
}