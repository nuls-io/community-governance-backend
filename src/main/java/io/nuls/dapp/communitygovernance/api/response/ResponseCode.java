package io.nuls.dapp.communitygovernance.api.response;

/**
 * rest 返回CODE定义
 * Created by wangkun23 on 2018/8/30.
 */
public enum ResponseCode{

    /***********************************************************************
     * ERROR
     ***********************************************************************/
    ERROR("1000000", "未知错误"),

    ERROR_PASSWORD_EMPTY("1002000", "请输入密码"),
    ERROR_PASSWORD_NOT_MATCH("1002001", "密码不匹配错误"),
    ERROR_ACCOUNT_AUTH("1004009", "不合法的凭证类型"),

    ERROR_VALID("1005000", "参数验证错误"),

    /***********************************************************************
     * SUCCESS
     ***********************************************************************/
    SUCCESS("2000000", "操作成功"),

    OTHER("0000000", "默认");

    private String key;
    private String remark;//备注

    ResponseCode(String key, String remark) {
        this.key = key;
        this.remark = remark;
    }

    /**
     * key along with enum
     *
     * @return
     */
    public String getKey() {
        return this.key;
    }

    public String getRemark() {
        return this.remark;
    }
}
