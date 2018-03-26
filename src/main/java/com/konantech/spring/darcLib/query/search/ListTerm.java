package com.konantech.spring.darcLib.query.search;

public class ListTerm extends PropertyTerm {

    private static final long serialVersionUID = -1456827480563232925L;

    protected String[] pattern;
    public ListTerm(String propertyName, String[] pattern) {
        super(propertyName);
        this.pattern = pattern;
    }
    public String[] getPattern() {
        return pattern;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(propertyName);
        sb.append(getOperator());
        for (int i = 0; i < pattern.length; i++) {
            sb.append(pattern[i]);
            if (i < pattern.length - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
