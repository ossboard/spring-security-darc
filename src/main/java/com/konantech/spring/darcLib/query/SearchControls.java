package com.konantech.spring.darcLib.query;

import lombok.Data;

import java.io.Serializable;

@Data
public class SearchControls implements Serializable{

    private static final long serialVersionUID = 2011758319322962733L;

    public static final int ONELEVEL_SCOPE = 1;
    public static final int SUBTREE_SCOPE = 2;
    private int limit;
    private int offset;
    private int scope;
    public SearchControls() {
    }

    public SearchControls(int limit, int offset, int scope) {
        this.limit = limit;
        this.offset = offset;
        this.scope = scope;
    }
}