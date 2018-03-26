package com.konantech.spring.darcLib.query;


import com.konantech.spring.darcLib.query.search.SearchTerm;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Query implements Serializable {
    private static final long serialVersionUID = 774385655221592161L;
    private String mediaType;
    private String query;
    private Date startDate;
    private Date endDate;
    private String searchBase;
    private String selectMode;
    private List<SortingOrder> sortingOrder = new ArrayList<SortingOrder>();
    private SearchTerm constraint;

    public Query() {
    }
    public void addSortingOrder(SortingOrder ordering) {
        this.sortingOrder.add(ordering);
    }

    @Data
    public class SortingOrder {
        private String ordering;
        private boolean ascending = false;
    }
}