package com.example.demo.conversionController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.conversionService.CurrencyConversionService;
import com.example.demo.entity.ConvertRequest;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ConversionController {

	@Autowired
	CurrencyConversionService conversionService;
	
	@PostMapping("/convert")
	@CircuitBreaker(name = "conversionRetry", fallbackMethod = "cacluteAmountWithStdFactor")
	public double test(@RequestBody ConvertRequest convertRequest) {
		log.info("\n\n\nRetry\n\n\n");
		return conversionService.convertCurrency(convertRequest);
	}
	
	public double cacluteAmountWithStdFactor(ConvertRequest convertRequest, Throwable t) {
		log.info("\n\nInside fallback method");
		double amt = 0;
		if(null != convertRequest) {
			amt = convertRequest.getAmount() * 75;
		}
		return amt;
	}
}
