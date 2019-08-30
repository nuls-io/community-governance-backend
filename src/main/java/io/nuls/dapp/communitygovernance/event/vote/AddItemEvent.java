package io.nuls.dapp.communitygovernance.event.vote;

import io.nuls.dapp.communitygovernance.model.vote.VoteItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddItemEvent {
    private Long voteId;
    private Long itemId;
    private String itemContent;
    private List<VoteItem> items;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AddItemEvent that = (AddItemEvent) o;

        if (voteId != null ? !voteId.equals(that.voteId) : that.voteId != null) {
            return false;
        }
        if (itemId != null ? !itemId.equals(that.itemId) : that.itemId != null) {
            return false;
        }
        if (itemContent != null ? !itemContent.equals(that.itemContent) : that.itemContent != null) {
            return false;
        }
        return items != null ? items.equals(that.items) : that.items == null;
    }

    @Override
    public int hashCode() {
        int result = voteId != null ? voteId.hashCode() : 0;
        result = 31 * result + (itemId != null ? itemId.hashCode() : 0);
        result = 31 * result + (itemContent != null ? itemContent.hashCode() : 0);
        result = 31 * result + (items != null ? items.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "{" +
                "\"voteId\": " + voteId +
                ", \"itemId\": " + itemId +
                ", \"itemContent\": \"" + itemContent + "\"" +
                ", \"items\": " + items +
                "}";
    }
}