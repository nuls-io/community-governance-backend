/**
 * MIT License
 * <p>
 * Copyright (c) 2017-2018 nuls.io
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package io.nuls.dapp.communitygovernance.model.dto;

import io.nuls.core.rpc.model.ApiModel;
import io.nuls.core.rpc.model.ApiModelProperty;
import lombok.Data;

/**
 * @author: PierreLuo
 * @date: 2019-09-18
 */
@Data
@ApiModel
public class CouncilMemberDetailDto {

    @ApiModelProperty(description = "地址")
    private String address;
    @ApiModelProperty(description = "名称")
    private String name;
    @ApiModelProperty(description = "票数")
    private String votesNumber;
    @ApiModelProperty(description = "理事类型 1:管理 2:运营 3:技术")
    private Byte type;
    @ApiModelProperty(description = "是否是理事成员")
    private Boolean isCouncil;
    @ApiModelProperty(description = "邮箱")
    private String email;
    @ApiModelProperty(description = "头像URL")
    private String logoUrl;
    @ApiModelProperty(description = "介绍")
    private String desc;
}
