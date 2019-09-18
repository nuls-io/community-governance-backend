package io.nuls.dapp.communitygovernance.model.dto;

import io.nuls.core.rpc.model.ApiModel;
import io.nuls.core.rpc.model.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: PierreLuo
 * @date: 2019-09-18
 */
@Data
@ApiModel
public class VoteItemVoterDto implements Serializable {

    @ApiModelProperty(description = "投票人地址")
    private String address;
    @ApiModelProperty(description = "投票数")
    private String votesNumber;
}
