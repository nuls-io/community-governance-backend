package io.nuls.dapp.communitygovernance.model;

public class TbAlias {
    private String address;

    private String alias;

    public TbAlias() {
    }

    public TbAlias(String address, String alias) {
        this.address = address;
        this.alias = alias;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias == null ? null : alias.trim();
    }
}