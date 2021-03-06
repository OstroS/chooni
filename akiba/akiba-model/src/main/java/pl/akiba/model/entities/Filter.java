package pl.akiba.model.entities;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Filter {

    private Date startDate;
    private Date endDate;
    private int limit;
    private int profileId;
    private int kindId;

    private final StringBuilder sqlBuilder = new StringBuilder();
    private final Map<String, Object> sqlParams = new HashMap<String, Object>(5);

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

    public String getFilterSql() {
        if (sqlBuilder.length() < 1 || sqlParams.size() < 1) {
            buildSql();
        }

        return sqlBuilder.toString();
    }

    public Map<String, Object> getFilterSqlParams() {
        if (sqlBuilder.length() < 1 || sqlParams.size() < 1) {
            buildSql();
        }

        return sqlParams;
    }

    public String getFilterString() {
        if (isEmpty()) {
            return "";
        }

        StringBuilder filterBuilder = new StringBuilder("?");
        if (kindId > 0) {
            filterBuilder.append("kindId=").append(kindId).append("&");
        }
        if (profileId > 0) {
            filterBuilder.append("profileId=").append(profileId).append("&");
        }
        if (startDate != null) {
            filterBuilder.append("startDate=").append(startDate).append("&");
        }
        if (endDate != null) {
            filterBuilder.append("endDate=").append(endDate).append("&");
        }
        if (limit > 0) {
            filterBuilder.append("limit=").append(limit).append("&");
        }

        String filter = filterBuilder.toString();
        int ampIndex = filter.lastIndexOf("&");

        return filter.substring(0, ampIndex);
    }

    public boolean isEmpty() {
        if (startDate == null && endDate == null && kindId == 0 && profileId == 0 && limit == 0) {
            return true;
        }

        return false;
    }

    private void buildSql() {
        if (kindId > 0) {
            sqlBuilder.append(" and id_kind = :kindId");
            sqlParams.put("kindId", kindId);
        }
        if (profileId > 0) {
            sqlBuilder.append(" and id_profile = :profileId");
            sqlParams.put("profileId", profileId);
        }
        if (startDate != null && endDate != null) {
            sqlBuilder.append(" and add_date between :startDate and :endDate");
            sqlParams.put("startDate", startDate);
            sqlParams.put("endDate", endDate);
        } else if (startDate != null) {
            sqlBuilder.append(" and add_date >= :startDate");
            sqlParams.put("startDate", startDate);
        } else if (endDate != null) {
            sqlBuilder.append(" and add_date <= :endDate");
            sqlParams.put("endDate", endDate);
        }
        if (limit > 0) {
            sqlBuilder.append(" limit :limit");
            sqlParams.put("limit", limit);
        }
    }

    @Override
    public String toString() {
        return "Filter [startDate=" + startDate + ", endDate=" + endDate + ", limit=" + limit + ", profileId="
                + profileId + ", kindId=" + kindId + "]";
    }

}