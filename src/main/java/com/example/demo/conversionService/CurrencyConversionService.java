package com.example.demo.conversionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ConvertRequest;
import com.example.demo.proxies.CurrrencyManagementProxyInterface;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CurrencyConversionService {

	@Autowired
	CurrrencyManagementProxyInterface currrencyManagementProxyInterface;
	public double convertCurrency(ConvertRequest convertRequest) {
		double finalAmt = 0;
		if(null != convertRequest) {
			double conversionFactor = currrencyManagementProxyInterface.getCoversion(convertRequest.getCountryCode());
			log.info("amt= ****************{}", conversionFactor);
			finalAmt = convertRequest.getAmount() * conversionFactor;
		}
		return finalAmt;
	}
}
