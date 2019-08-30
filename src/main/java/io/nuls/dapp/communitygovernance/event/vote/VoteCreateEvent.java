package io.nuls.dapp.communitygovernance.event.vote;

import io.nuls.dapp.communitygovernance.model.vote.VoteItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteCreateEvent {
    private Long voteId;
    private String title;
    private String desc;
    private List<VoteItem> items;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VoteCreateEvent that = (VoteCreateEvent) o;

        if (voteId != null ? !voteId.equals(that.voteId) : that.voteId != null) {
            return false;
        }
        if (title != null ? !title.equals(that.title) : that.title != null) {
            return false;
        }
        if (desc != null ? !desc.equals(that.desc) : that.desc != null) {
            return false;
        }
        return items != null ? items.equals(that.items) : that.items == null;
    }

    @Override
    public int hashCode() {
        int result = voteId != null ? voteId.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (desc != null ? desc.hashCode() : 0);
        result = 31 * result + (items != null ? items.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "{" +
                "\"voteId\": " + voteId +
                ", \"title\": \"" + title + "\"" +
                ", \"desc\": \"" + desc + "\"" +
                ", \"items\": " + items +
                "}";
    }
}
