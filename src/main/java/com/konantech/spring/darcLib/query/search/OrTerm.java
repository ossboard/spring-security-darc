package com.konantech.spring.darcLib.query.search;

public class OrTerm extends CompositeTerm {

    private static final long serialVersionUID = 6240032270835787067L;

    public OrTerm() {
        super();
    }

    public OrTerm(SearchTerm t1, SearchTerm t2) {
        super();
        addTerm(t1);
        addTerm(t2);
    }

    public OrTerm(SearchTerm[] t) {
        super();
        for (int i = 0; i < t.length; i++) {
            addTerm(t[i]);
        }
    }

    @Override
    public String toString() {
        return toString("OR");
    }

}
