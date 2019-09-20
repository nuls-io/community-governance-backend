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
import io.nuls.core.rpc.model.TypeDescriptor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: PierreLuo
 * @date: 2019-09-18
 */
@Data
@ApiModel
public class VoteDetailDto extends VoteDto{

    @ApiModelProperty(description = "单选或者是多选")
    private Boolean hasMultipleSelect;
    @ApiModelProperty(description = "至少可选几个选项")
    private Integer minSelectCount;
    @ApiModelProperty(description = "最多可选几个选项")
    private Integer maxSelectCount;
    @ApiModelProperty(description = "投票后是否允许改票")
    private Boolean voteCanModify;
    @ApiModelProperty(description = "保证金(押金)")
    private String deposit;
    @ApiModelProperty(description = "关联的提案")
    private ProposalShortDto proposal;
    @ApiModelProperty(description = "选项列表", type = @TypeDescriptor(value = List.class, collectionElement = VoteItemDto.class))
    private List<VoteItemDto> itemList;
}
