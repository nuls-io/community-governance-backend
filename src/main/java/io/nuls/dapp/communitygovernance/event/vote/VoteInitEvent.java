package io.nuls.dapp.communitygovernance.event.vote;

import io.nuls.dapp.communitygovernance.model.vote.VoteConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteInitEvent {
    private Long voteId;
    private VoteConfig voteConfig;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VoteInitEvent that = (VoteInitEvent) o;

        if (voteId != null ? !voteId.equals(that.voteId) : that.voteId != null) {
            return false;
        }
        return voteConfig != null ? voteConfig.equals(that.voteConfig) : that.voteConfig == null;
    }

    @Override
    public int hashCode() {
        int result = voteId != null ? voteId.hashCode() : 0;
        result = 31 * result + (voteConfig != null ? voteConfig.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "{" +
                "\"voteId\": " + voteId +
                ", \"voteConfig\": " + voteConfig +
                "}";
    }
}
