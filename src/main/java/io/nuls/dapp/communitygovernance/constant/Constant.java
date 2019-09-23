/**
 * MIT License
 *
 * Copyright (c) 2017-2018 nuls.io
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package io.nuls.dapp.communitygovernance.constant;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @author Niels
 * @date 2017/10/9
 */
public interface Constant {

    /**无效,失效 对应数据库数据状态*/
    byte INVALID = 0;
    /** 有效的 */
    byte VALID = 1;

    /** 已退还押金*/
    byte REFUNDED = 0;

    /** 否 对应数据库数据状态*/
    byte NO = 0;
    /** 是 */
    byte YES = 1;

    /** 角色*/
    byte ROLE = 1;
    /** 系统参数*/
    byte SYS_PARAM = 2;
    /** 社区基金*/
    byte COMMUNITY_FUND = 3;
    /** 其他类型*/
    byte OTHER_TYPE = 4;

    /** 支持*/
    byte FAVOUR = 1;
    /** 反对*/
    byte AGAINST = 2;
    /** 弃权*/
    byte ABSTENTION  = 3;

    /** 审核拒绝*/
    byte AUDIT_NO = 0;
    /** 审核通过*/
    byte AUDIT_YES = 1;

    //提案
    /** 审核中*/
    byte INREVIEW = 1;
    /** 审核拒绝*/
    byte UNAPPROVED = 2;
    /** 投票中*/
    byte VOTING = 3;
    /** 通过, 执行中*/
    byte ADOPTED = 4;
    /** 未通过*/
    byte REJECTED = 5;
    /** 已执行*/
    byte COMPLETED  = 6;
    /** 提案通过需要的支持率阈值*/
    BigDecimal ADOPTED_THRESHOLD = new BigDecimal("0.7");

    //普通投票
    /** 投票待确认*/
    byte STATUS_WAIT_INIT = 0;
    /** 投票未开始*/
    byte STATUS_WAIT_VOTE = 1;
    /** 投票中*/
    byte STATUS_VOTEING = 2;
    /** 投票已结束*/
    byte STATUS_CLOSE = 3;

    //理事
    /** 管理*/
    int MANAGEMENT = 1;
    /**
     * 运营理事
     */
    int OPERATIONS = 2;
    /**
     * 技术理事
     */
    int TECHNOLOGY = 3;

    int COUNCIL_MEMBERS = 11;
    BigInteger TECHNOLOGY_ENTRY_MINIMUM = new BigInteger("2500000000000");
    BigInteger NON_TECHNOLOGY_ENTRY_MINIMUM = new BigInteger("5000000000000");
}
