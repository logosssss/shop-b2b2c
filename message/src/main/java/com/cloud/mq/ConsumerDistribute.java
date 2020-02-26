package com.cloud.mq;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.cloud.adapter.MessageAdapter;
import com.cloud.constants.Constants;
import com.cloud.ser.EmailService;
import com.cloud.ser.PhoneService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ConsumerDistribute {

	@Autowired
	private EmailService emailService;
	@Autowired
	private PhoneService phoneService;
	private MessageAdapter messageAdapter;
	@Resource
	private RabbitTemplate rabbitTemplate;

	// 监听消息
	@RabbitListener(queues = "message")
	@RabbitHandler
	public void receive(String json) {
		try {
			log.info("#####消息服务平台接受消息内容:{}#####", json);

			
			  if (StringUtils.isEmpty(json)) { return; }
			 
		} catch (Exception e) {
			log.error("#####消息服务平台接受消息{}#####", "失败");
		}
		
		JSONObject rootJSON = new JSONObject().parseObject((String) json);
		JSONObject header = rootJSON.getJSONObject("header");
		String interfaceType = header.getString("interfaceType");

		
		
		if (StringUtils.isEmpty(interfaceType)) {
			return;
		}
		// 判断接口类型是否为发邮件
		if (interfaceType.equals(Constants.MSG_EMAIL)) {
			messageAdapter = emailService;
		}
		if (interfaceType.equals(Constants.MSG_PHONE)) {
			messageAdapter = phoneService;
		}
		if (messageAdapter == null) {
			return;
		}
		JSONObject contentJson = rootJSON.getJSONObject("content");
		messageAdapter.sendMsg(contentJson);
	}

}
