package pl.akiba.wsclient.api;

import java.util.Date;

public class CriteriaBuilder {
    private Criteria current;
    
    public String SORT_ASCENDING_ORDER = "asc";
    public String SORT_DESCENDING_ORDER = "desc";
    
    public CriteriaBuilder create() {
        current = new Criteria();
        return this;
    }
    
    public CriteriaBuilder withStartDate(Date startDate) {
        current.setStartDate(startDate);
        return this;
    }
        
    public CriteriaBuilder withEndDate(Date endDate) {
        current.setEndDate(endDate);
        return this;
    }
    
    public CriteriaBuilder withAmountOfResults(Long amountOfResults) {
        current.setAmount(amountOfResults);
        return this;
    }
    
    public CriteriaBuilder withSortMethod(String sortMethod) {
        current.setSort(sortMethod);
        return this;
    }
    
    public Criteria build() {
        return current;
    }
}
