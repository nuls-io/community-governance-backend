package io.nuls.dapp.communitygovernance.model;

public class TbPlayer {
    private String address;

    public TbPlayer() {
    }

    public TbPlayer(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }
}