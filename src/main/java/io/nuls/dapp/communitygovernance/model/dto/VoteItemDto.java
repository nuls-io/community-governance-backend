package io.nuls.dapp.communitygovernance.model.dto;

import io.nuls.core.rpc.model.ApiModel;
import io.nuls.core.rpc.model.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: PierreLuo
 * @date: 2019-09-18
 */
@Data
@ApiModel
public class VoteItemDto implements Serializable {

    @ApiModelProperty(description = "选项ID")
    private Integer itemId;
    @ApiModelProperty(description = "选项内容")
    private String content;
    @ApiModelProperty(description = "投票数")
    private String votesNumber;
}
