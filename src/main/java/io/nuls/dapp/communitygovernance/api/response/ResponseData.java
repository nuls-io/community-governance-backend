package io.nuls.dapp.communitygovernance.api.response;


import io.nuls.core.rpc.model.ApiModel;
import io.nuls.core.rpc.model.ApiModelProperty;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 用户ajax提交之后的相应结果
 * <p>
 * Created by wangkun23 on 2018/8/30.
 */
@ApiModel
public class ResponseData implements Serializable {

    @ApiModelProperty(description = "成功或者失败")
    private boolean success;
    @ApiModelProperty(description = "消息code")
    private String code;
    @ApiModelProperty(description = "错误提示")
    private String msg;

    /**
     * 结果数据
     * 如果返回对象或数组直接用setData
     * 如果返回简单的键值对用addData
     */
    private Object data;
    private Map<String, Object> dataShadow;

    public ResponseData() {

    }

    public ResponseData(ResponseCode code, boolean success) {
        this.success = success;
        this.code = code.getKey();
        this.msg = code.getRemark();
    }

    public ResponseData(ResponseCode code) {
        this.code = code.getKey();
        this.msg = code.getRemark();
    }

    public ResponseData(ResponseCode code, String msg) {
        this.success = false;
        this.code = code.getKey();
        this.msg = msg;
    }

    /**
     * 返回成功
     *
     * @return
     */
    public static ResponseData success() {
        return new ResponseData(ResponseCode.SUCCESS, true);
    }

    /**
     * 返回成功消息
     *
     * @param code 消息code
     * @return 成功消息
     */
    public static ResponseData success(ResponseCode code) {
        return new ResponseData(code, true);
    }

    /**
     * 返回错误
     *
     * @return
     */
    public static ResponseData error() {
        return new ResponseData(ResponseCode.ERROR, false);
    }

    /**
     * 返回错误消息
     *
     * @param code 消息code
     * @return 错误消息
     */
    public static ResponseData error(ResponseCode code) {
        return new ResponseData(code, false);
    }

    /**
     * 返回错误消息
     *
     * @param code 消息code
     * @return 错误消息
     */
    public static ResponseData error(String error) {
        return new ResponseData(ResponseCode.ERROR, error);
    }

    /**
     * 返回字段验证不通过错误消息
     *
     * @param bindingResult bindingResult
     * @return 错误消息
     */
    public static ResponseData valid(BindingResult bindingResult) {
        ResponseData result = ResponseData.error(ResponseCode.ERROR_VALID);
        FieldError error = bindingResult.getFieldErrors().get(0);
        String msg = error.getField() + "" + error.getDefaultMessage();
        result.setMsg(msg);
        return result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 添加返回的数据
     *
     * @param key
     * @param value
     */
    public void addData(String key, Object value) {
        if (this.dataShadow == null) {
            this.dataShadow = new LinkedHashMap<String, Object>();

            this.data = this.dataShadow;
        }
        this.dataShadow.put(key, value);
    }

    /**
     * @param key
     * @return
     */
    public Object removeData(String key) {
        return this.dataShadow != null ? this.dataShadow.remove(key) : null;
    }
}
