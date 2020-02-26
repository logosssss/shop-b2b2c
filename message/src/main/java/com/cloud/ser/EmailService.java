package com.cloud.ser;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.cloud.adapter.MessageAdapter;
import com.cloud.util.EmailUtil;

import lombok.extern.slf4j.Slf4j;

//处理第三方发送邮件
@Slf4j
@Service
public class EmailService implements MessageAdapter {
   
	@Autowired
	private EmailUtil emailUtil;//邮箱工具类
	
	@Override
	public void sendMsg(JSONObject body) {
		// 处理发送邮件
		String email=body.getString("email");
		String subject = body.getString("subject");
		String content = body.getString("text");
		if(StringUtils.isEmpty(email)){
			return ;
		}
		emailUtil.sendSimpleMail(email, subject, content);
		log.info("消息服务平台发送邮件:to：{}，subje：{}，content：{}",email,subject,content);
	}

}
