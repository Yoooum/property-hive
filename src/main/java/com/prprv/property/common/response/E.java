package com.prprv.property.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 返回状态码枚举值
 * @author Yoooum
 */
@Getter
@AllArgsConstructor
public enum E {
    OK(0, null),
    INVALID_TOKEN(1001, "无效令牌"),
    EXPIRED_TOKEN(1002, "过期令牌"),
    INVALID_USERNAME_OR_PASSWORD(1003, "无效的用户名或密码"),
    USERNAME_ALREADY_EXISTS(1004, "用户名已存在"),
    EMAIL_ALREADY_EXISTS(1005, "邮箱已存在"),
    PHONE_ALREADY_EXISTS(1006, "手机号已存在"),
    CODE_EXPIRED(1007, "验证码已过期"),
    ACTIVATION_FAILED(1008, "激活失败，用户不存在"),
    EMAIL_SEND_FAILED(1009, "邮件发送失败"),
    USER_NOT_ACTIVATED(1010, "用户未激活"),
    INTERNAL_SERVER_ERROR(5000, "内部服务器错误"),
    NOT_FOUND(5001, "未找到"),
    ID_NOT_FOUND(5001, "不存在的ID"),
    UPDATE_FAILED(5002, "更新失败，对象不存在"),
    UNAUTHORIZED(401, "未授权"),
    FORBIDDEN(403, "拒绝访问"),
    CREATE_FAILED(5003, "创建失败，参数错误"),
    SMSAUTHCODE_ERROR(5004,"验证码错误");

    private final int code;
    private final String message;
}
