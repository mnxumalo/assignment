package org.mthu.interstellar.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

@Component
public class Listener implements ApplicationListener<ContextStartedEvent>{

	@Autowired
	FileReader fileReader;
	
	@Override	
	public void onApplicationEvent(ContextStartedEvent event) {
		System.out.println("ContextStartedEvent START");
		fileReader.ReadXslxFile();
		System.out.println("ContextStartedEvent END");
	}

}
