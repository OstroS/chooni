package pl.akiba.wsclient.api;

import java.util.Date;

/**
 * Class that agregates filtering and ordering criteria <br />
 * If argument equals null - it was not set
 * @author kostrows
 *
 */
public class Criteria {
    private Date startDate;
    private Date endDate;
    private Long amount;
    private String sort;
    
    private Criteria() {
        startDate = null;
        endDate = null;
        amount = null;
        sort = null;
    }
    
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public Long getAmount() {
        return amount;
    }
    public void setAmount(Long amount) {
        this.amount = amount;
    }
    public String getSort() {
        return sort;
    }
    public void setSort(String sort) {
        this.sort = sort;
    }
}
