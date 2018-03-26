package com.konantech.spring.darcLib.query.search;

public abstract class ComparisonTerm extends SearchTerm {

    private static final long serialVersionUID = 3281167196809466963L;

    public static final int LE = 1;
    public static final int LT = 2;
    public static final int EQ = 3;
    public static final int NE = 4;
    public static final int GT = 5;
    public static final int GE = 6;

    protected int comparison;

    protected ComparisonTerm(int comparison) {
        super();
        this.comparison = comparison;
    }

    public int getComparison() {
        return comparison;
    }

    protected String getOperator() {
        switch (comparison) {
        case LE:
            return "<=";
        case LT:
            return "<";
        case NE:
            return "!=";
        case GT:
            return ">";
        case GE:
            return ">=";
        case EQ:
        default:
            return "=";
        }
    }

}
