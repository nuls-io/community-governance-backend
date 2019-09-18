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
public class ProposalDto {

    @ApiModelProperty(description = "提案ID")
    private Integer proposalId;
    @ApiModelProperty(description = "提案图标URL")
    private String logoUrl;
    @ApiModelProperty(description = "提案标题")
    private String title;
    @ApiModelProperty(description = "提案介绍")
    private String desc;
    @ApiModelProperty(description = "提案人地址")
    private String address;
    @ApiModelProperty(description = "提案类型")
    private String type;
    @ApiModelProperty(description = "提案状态")
    private String status;
    @ApiModelProperty(description = "提案结束时间")
    private long endTime;
}
