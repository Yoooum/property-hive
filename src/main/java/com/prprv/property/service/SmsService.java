package com.prprv.property.service;

import com.prprv.property.common.response.E;
import com.prprv.property.common.response.R;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.sms.v20210111.models.*;

import java.util.Arrays;


/**
 * 短信服务类
 * @author Christine
 */
@Slf4j
@Service
public class SmsService {

//    验证码生成
    public String authcode = null;
//    电话号码预设
    public String cellphone = null;

    public void Sms(String[] phone) {
        authcode = String.valueOf((int)((Math.random()*9+1)*100000));

        try {
            Credential cred = new Credential("SecretId", "SecretKey");
            cred.setSecretId("AKID4Ch8QVNLH8tX96vYxHQK6kAKH0YHl38x");
            cred.setSecretKey("c5KK4g8PSHyb6PgCkmjkUEtU4Z4Wn3DQ");
//          短信服务端
            SmsClient client = new SmsClient(cred, "ap-guangzhou");
//          请求对象
            SendSmsRequest req = new SendSmsRequest();
//          请求参数
            req.setPhoneNumberSet(phone);

//          短信配置
            req.setSmsSdkAppId("1400811829");
            req.setSignName("浅香的基地公众号");
            req.setTemplateId("1769455");

//          格式化请求
            String[] templateParamSet1 = {authcode, "3"};
            req.setTemplateParamSet(templateParamSet1);

            SendSmsResponse resp = client.SendSms(req);
            System.out.println(SendSmsResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            log.error("[短信服务]发送短信异常");
            e.printStackTrace();
            R.ok(E.INTERNAL_SERVER_ERROR);
        }
        cellphone = Arrays.toString(phone);
        log.info("[短信服务]已为"+ cellphone +"送出验证码"+authcode);
    }

}
