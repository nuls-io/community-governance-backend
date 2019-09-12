package io.nuls.dapp.communitygovernance.event.vote;

import io.nuls.dapp.communitygovernance.model.vote.VoteItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteCreateEvent {
    private Long voteId;
    private String title;
    private String desc;
    private int status;
    private String owner;
    private BigInteger recognizance;
    private List<VoteItem> items;

}
