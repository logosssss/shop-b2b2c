package com.cloud.util;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.stereotype.Component;

/**
 * 发送信息工具类
 */
@Component
public class MessageUtil {
    //产品名称：云通道短信API产品
    static final String product = "Dysmsapi";
    //产品域名
    static final String domain = "dysmsapi.aliyuncs.com";

    public Integer sendSms(String mobile,String code) throws ClientException{
        //账户的accessKeyId
        String accessKeyId = "LTAI4Ftdo6w6AVq6FYCMr7oy";
        //账户的accessKeySecret
        String accessKeySecret = "q7KufbIgk9GI0PJdJsbpCg5XArqHNY";
        //模板代码
        String template_code = "SMS_176914810";
        //签名名称
        String sign_name = "追梦工作室";
        //版本
        String version = "2017-05-25";
        //6位随机数
        //Integer code = (int) ((Math.random()*9+1)*100000);

        //阿里云的默认配置文件
        DefaultProfile profile =
                DefaultProfile.getProfile("cn-hangzhou",accessKeyId,accessKeySecret);
        //阿里云客户端设置
        IAcsClient client = new DefaultAcsClient(profile);
        //设置发送请求需要的参数
        CommonRequest request = new CommonRequest();
        //请求的方法
        request.setSysMethod(MethodType.POST);
        //请求的域名
        request.setSysDomain(domain);
        //请求的版本
        request.setSysVersion(version);
        //请求的Action
        request.setSysAction("SendSms");
        //设置请求的具体参数
        request.putQueryParameter("RegionId","cn-hangzhou");
        request.putQueryParameter("PhoneNumbers",mobile);
        request.putQueryParameter("SignName",sign_name);
        request.putQueryParameter("TemplateCode",template_code);
        request.putQueryParameter("TemplateParam","{code:"+code+"}");
        //设置一个response 获取返回的参数
        CommonResponse response = null;
        try {
            //把请求加入返回一个response
            response = client.getCommonResponse(request);
            System.out.println(response.getData());
        }catch(ServerException e) {
            e.printStackTrace();
        }catch (ClientException e){
            e.printStackTrace();
        }

        //返回请求的代码
        System.out.println(response.getHttpStatus());
        return response.getHttpStatus();
    }
}
