package com.springboot.smartnutri.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.springboot.smartnutri.service.ExpiryProductService;

@Component
public class ExpiryProductScheduler {

	@Autowired
	ExpiryProductService expiryProductServiceImpl;

	@Scheduled(cron = "0 0 0 * * *")
	public void cronJob() {
		try {
			expiryProductServiceImpl.checkIfProductExpired();
		} catch (Exception e) {
			System.out.print(e+"\n"+e.getMessage());
		}
	}
}
