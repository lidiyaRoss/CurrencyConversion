package com.example.demo.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="CURRENCYMGMT")
public interface CurrrencyManagementProxyInterface {
	@RequestMapping(value="/currency/getConversion/{countryCode}", method = RequestMethod.GET)
	public double getCoversion(@PathVariable String countryCode);
}
