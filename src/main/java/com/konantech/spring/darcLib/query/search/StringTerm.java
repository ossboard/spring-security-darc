package com.konantech.spring.darcLib.query.search;

public class StringTerm extends PropertyTerm {

    private static final long serialVersionUID = -7231828182644780526L;

    protected String pattern;
    protected boolean ignoreCase;

    public StringTerm(String propertyName, String pattern) {
        this(propertyName, pattern, true);
    }

    public StringTerm(String propertyName, String pattern, boolean ignoreCase) {
        this(propertyName, EQ, pattern, ignoreCase);
    }

    public StringTerm(String propertyName, int comparson, String pattern,
            boolean ignoreCase) {
        super(propertyName, comparson);
        this.pattern = pattern;
        this.ignoreCase = ignoreCase;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public boolean isIgnoreCase() {
        return ignoreCase;
    }

    public void setIgnoreCase(boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
    }
}
