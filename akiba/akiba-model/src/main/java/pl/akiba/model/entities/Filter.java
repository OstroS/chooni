package pl.akiba.model.entities;

import java.util.Date;

/**
 * Entity filter model.
 * 
 * @author sobczakt
 */
public class Filter {

    private Date startDate;
    private Date endDate;
    private int limit;
    private int profileId;
    private int kindId;

    public Filter() {
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

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public int getKindId() {
        return kindId;
    }

    public void setKindId(int kindId) {
        this.kindId = kindId;
    }

    @Override
    public String toString() {
        return "Filter [startDate=" + startDate + ", endDate=" + endDate + ", limit=" + limit + ", profileId="
                + profileId + ", kindId=" + kindId + "]";
    }

}