package com.konantech.spring.darcLib.query.search;

/**
 * This class models the comparison for number type property.
 * 
 * @author WDO
 * 
 */
public class LongTerm extends PropertyTerm {

    private static final long serialVersionUID = 4055879237528314616L;
    private final long number;
    public LongTerm(String propertyName, long number) {
        this(propertyName, EQ, number);
    }
    public LongTerm(String propertyName, int comparison, long number) {
        super(propertyName, comparison);
        this.number = number;
    }
    public long getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return new StringBuilder(propertyName).append(getOperator())
                .append(number).toString();
    }

}
