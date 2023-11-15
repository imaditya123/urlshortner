package com.urlshortner.urlshortner.service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.urlshortner.urlshortner.Model.HashUnknown;

import ch.qos.logback.classic.Logger;

@Service
public class UrlShortService {
	
	private RedisTemplate<String, String> redis;
	private static final Logger logger = (Logger) LoggerFactory.getLogger(UrlShortService.class);
	
	

	public UrlShortService(RedisTemplate<String, String> redis) {
		super();
		this.redis = redis;
	}
	
	
	public String shorten(String url) {
		String hashValue=hash(url);
		redis.opsForValue().set(hashValue, url);
		return hashValue;
	}
	
	public String resolve(String hashVal) {
		
		try {
			return redis.opsForValue().get(hashVal);
		}catch(Exception ex) {
			throw new  HashUnknown(hashVal);
		}
	}
	
	
	private String hash(String url)  {
		
		  MessageDigest   digest= null;
		  try {
			  digest= MessageDigest.getInstance("SHA-256");
			  
			  byte[] key = url.getBytes(StandardCharsets.UTF_8);
			  key = digest.digest(key);
			  String hashVal=String.format("%32x", new BigInteger(1, key) );			  
			  return hashVal.substring(0,8); 
			  
			  
		  }catch(Exception ex) {
			  
		  }
		 return url;
		 
	}
	
	
	
	

}

