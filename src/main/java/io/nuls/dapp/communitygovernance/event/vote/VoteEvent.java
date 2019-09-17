package io.nuls.dapp.communitygovernance.event.vote;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteEvent {
    private Long voteId;
    private String voterAddress;
    private List<Integer> itemIds;

}