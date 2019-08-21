package io.nuls.dapp.communitygovernance.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 服务接口通用返回结果
 * Created by wangkun23 on 2018/8/30.
 *
 * @author wangkun23
 */
@ToString
public class ServiceResult<T> implements Serializable {

    @Setter
    @Getter
    private Boolean success;

    @Setter
    @Getter
    private String code;

    @Setter
    @Getter
    private String message;

    @Setter
    @Getter
    private T result;

    public ServiceResult(boolean success) {
        this.success = success;
    }

    public ServiceResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ServiceResult(boolean success, String code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public ServiceResult(boolean success, String message, T result) {
        this.success = success;
        this.message = message;
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }


    public static <T> ServiceResult<T> success() {
        return new ServiceResult<>(true);
    }

    public static <T> ServiceResult<T> failed() {
        return new ServiceResult<>(false);
    }

    public static <T> ServiceResult<T> of(T result) {
        ServiceResult<T> serviceResult = new ServiceResult<>(true);
        serviceResult.setResult(result);
        return serviceResult;
    }

    public static <T> ServiceResult<T> notFound() {
        return new ServiceResult<>(false, Message.NOT_FOUND.getValue());
    }

    /**
     * 自定义的服务层信息
     */
    public enum Message {
        /**
         * Not Found Resource!
         */
        NOT_FOUND("Not Found Resource!");

        private String value;

        Message(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
