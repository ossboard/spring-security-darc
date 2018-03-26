package com.konantech.spring.darcLib.query.search;

public class BooleanTerm extends PropertyTerm {

    private static final long serialVersionUID = 4623870852253980386L;

    private final boolean set;

    public BooleanTerm(String propertyName, boolean set) {
        super(propertyName, EQ);
        this.set = set;
    }

    public boolean isSet() {
        return set;
    }

    @Override
    public String toString() {
        return new StringBuilder(propertyName).append(getOperator())
                .append(set).toString();
    }

}
