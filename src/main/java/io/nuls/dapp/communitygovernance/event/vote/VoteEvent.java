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
    private List<Long> itemIds;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VoteEvent voteEvent = (VoteEvent) o;

        if (voteId != null ? !voteId.equals(voteEvent.voteId) : voteEvent.voteId != null) {
            return false;
        }
        return itemIds != null ? itemIds.equals(voteEvent.itemIds) : voteEvent.itemIds == null;
    }

    @Override
    public int hashCode() {
        int result = voteId != null ? voteId.hashCode() : 0;
        result = 31 * result + (itemIds != null ? itemIds.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "{" +
                "\"voteId\": " + voteId +
                ", \"itemIds\": " + itemIds +
                "}";
    }
}