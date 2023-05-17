package com.prprv.property.service;

import com.prprv.property.common.response.E;
import com.prprv.property.exception.AppException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * @author phj233
 * @since 2023/5/16 8:29
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class EmailReceiver {
    private final JavaMailSender emailSender;
    private final RedisTemplate<String, String> redisTemplate;
    private final TemplateEngine templateEngine;

    @RabbitListener(queues = "email.queue")
    public void receiveMessage(EmailMessage message){
        log.info("接收到消息：{}", message);
        // 从Redis中获取激活代码
        String activationCode = redisTemplate.opsForValue().get(message.getRecipientEmail()+":code");
        // 创建邮件消息
        try {
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
            messageHelper.setTo(message.getRecipientEmail());
            messageHelper.setSubject("prprv 物业管理系统账户激活邮件");
            messageHelper.setFrom("no_reply<no-reply@prprv.com>");
            // 使用Thymeleaf渲染邮件内容
            Context context = new Context();
            context.setVariable("activationCode", activationCode);
            context.setVariable("email", message.getRecipientEmail());
            String htmlContent = templateEngine.process("activation-email.html", context);
            messageHelper.setText(htmlContent, true);

            // 发送邮件
            emailSender.send(mimeMessage);
        }catch (Exception e){
            log.error("邮件发送失败", e);
            throw new AppException(E.EMAIL_SEND_FAILED);
        }

    }
}
