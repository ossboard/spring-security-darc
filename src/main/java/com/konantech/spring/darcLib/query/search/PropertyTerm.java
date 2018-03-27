package com.konantech.spring.darcLib.query.search;

public abstract class PropertyTerm extends ComparisonTerm {

    private static final long serialVersionUID = -7476861899192940852L;
    protected String propertyName;

    protected PropertyTerm(String propertyName) {
        this(propertyName, EQ);
    }


    protected PropertyTerm(String propertyName, int comparison) {
        super(comparison);
        this.propertyName = propertyName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }
}
