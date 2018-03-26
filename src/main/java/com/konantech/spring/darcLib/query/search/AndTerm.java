package com.konantech.spring.darcLib.query.search;

/**
 * This class models the logical AND operator on individual
 * <code>SearchTerm</code>s.
 * 
 * @author WDO
 */
public class AndTerm extends CompositeTerm {

    private static final long serialVersionUID = -6164079012347592536L;

    public AndTerm() {
        super();
    }

    public AndTerm(SearchTerm t1, SearchTerm t2) {
        super();
        addTerm(t1);
        addTerm(t2);
    }
    public AndTerm(SearchTerm[] t) {
        super();
        for (int i = 0; i < t.length; i++) {
            addTerm(t[i]);
        }
    }
    @Override
    public String toString() {
        return toString("AND");
    }

}
