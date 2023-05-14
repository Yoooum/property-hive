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
    INTERNAL_SERVER_ERROR(5000, "内部服务器错误"),
    NOT_FOUND(5001, "未找到"),
    UPDATE_FAILED(5002, "更新失败，对象不存在"),
    UNAUTHORIZED(401, "未授权"),
    FORBIDDEN(403, "拒绝访问"),
    CREATE_FAILED(5003, "创建失败，参数错误");

    private final int code;
    private final String message;
}
