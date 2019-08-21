package io.nuls.dapp.communitygovernance.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 同步区块的高度
 *
 * @author wangkun23
 */
@Data
public class SimpleBlockHeader implements Serializable {

    private Long height;
    private String hash;
    private String preHash;
    private Long createTime;
}