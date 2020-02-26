package com.cloud.ser;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.exceptions.ClientException;
import com.cloud.adapter.MessageAdapter;
import com.cloud.constants.Constants;
import com.cloud.util.MessageByOrderUtil;
import com.cloud.util.MessageUtil;

import lombok.extern.slf4j.Slf4j;

//处理第三方发送邮件
@Slf4j
@Service
public class PhoneService implements MessageAdapter {
	@Autowired
	private MessageUtil messageUtil;//注册发短信
	@Autowired
	private MessageByOrderUtil messageByOrderUtil;//订单操作发短信

	@Override
	public void sendMsg(JSONObject body){
		// 处理发送短信信息
		String phone = body.getString("phone");
		
		String type = body.getString("type");
		System.out.println("type："+type);
		if (StringUtils.isEmpty(phone)) {
			return;
		}
		/**
		 * 判断是那种类型的短信服务
		 */
		if (type.equals(Constants.MSG_PHONE_REGISTER)) {
			try {
				//System.out.println("phoneCode");
				String code = body.getString("code");
				messageUtil.sendSms(phone, code);
				log.info("消息服务平台发送短信信息:{}code{}", phone, code);
			} catch (ClientException e) {
				e.printStackTrace();
			}
		}
		
		if (type.equals(Constants.MSG_PHONE_ORDER)) {
			try {
				String status = body.getString("status");
				String remark = body.getString("remark");
				messageByOrderUtil.sendSms(phone, status,remark);
				log.info("消息服务平台发送短信信息:{}status{}remark{}", phone, status,remark);
			} catch (ClientException e) {
				e.printStackTrace();
			}
		}
		
		
	}	

}
