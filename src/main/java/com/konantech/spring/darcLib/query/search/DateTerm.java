package com.konantech.spring.darcLib.query.search;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTerm extends PropertyTerm {

    private static final long serialVersionUID = -347495571610959612L;

    private static DateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    protected Date date;

    public DateTerm(String propertyName, int comparison, Date date) {
        super(propertyName, comparison);
        this.date = date;
    }
    public Date getDate() {
        return new Date(date.getTime());
    }

    @Override
    public String toString() {
        return new StringBuilder("DATE(").append(propertyName).append(")")
                .append(getOperator()).append("DATE(")
                .append(DATETIME_FORMAT.format(date)).append(")").toString();
    }

}
