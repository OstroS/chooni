package pl.akiba.backend.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import pl.akiba.backend.util.Sql;
import pl.akiba.model.entities.Expense;
import pl.akiba.model.entities.Filter;
import pl.akiba.model.entities.Kind;
import pl.akiba.model.entities.Profile;

/**
 * 
 * @author sobczakt
 */
@Repository
public class JdbcExpenseDao implements ExpenseDao, InitializingBean {

    @Autowired
    @Qualifier("mysqlAkibaDataSource")
    private DataSource dataSource;

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Expense get(int userId, int expenseId) {
        MapSqlParameterSource parameterMap = new MapSqlParameterSource();
        parameterMap.addValue("userId", userId);
        parameterMap.addValue("expenseId", expenseId);

        return jdbcTemplate.queryForObject(Sql.SELECT_EXPENSE, parameterMap, new RowMapper<Expense>() {

            @Override
            public Expense mapRow(ResultSet rs, int rowNum) throws SQLException {

                return new Expense(rs.getInt("id"), rs.getDouble("amount"), new Kind(rs.getInt("kindId"), rs
                        .getString("kindName")), new Profile(rs.getInt("id"), rs.getString("name"), rs
                        .getBoolean("def"), rs.getBoolean("active")), rs.getTimestamp("addDate"));
            }

        });
    }

    @Override
    public List<Expense> getAll(int userId, Filter filter) {
        StringBuilder sqlBuilder = new StringBuilder(Sql.SELECT_EXPENSES);
        MapSqlParameterSource parameterMap = new MapSqlParameterSource("userId", userId);

        addFilters(filter, sqlBuilder, parameterMap);

        return jdbcTemplate.query(sqlBuilder.toString(), parameterMap, new RowMapper<Expense>() {

            @Override
            public Expense mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Expense(rs.getInt("id"), rs.getDouble("amount"), new Kind(rs.getInt("kindId"), rs
                        .getString("kindName")), new Profile(rs.getInt("id"), rs.getString("name"), rs
                        .getBoolean("def"), rs.getBoolean("active")), rs.getTimestamp("addDate"));
            }

        });
    }

    @Override
    public Expense create(int userId, Expense expense) {
        Date now = new Date();
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource parameterMap = new MapSqlParameterSource();
        parameterMap.addValue("userId", userId);
        parameterMap.addValue("profileId", expense.getProfile().getId());
        parameterMap.addValue("kindId", expense.getKind().getId());
        parameterMap.addValue("amount", expense.getAmount());
        parameterMap.addValue("addDate", now);

        jdbcTemplate.update(Sql.INSERT_EXPENSE, parameterMap, keyHolder);

        expense.setDate(now);
        expense.setId(keyHolder.getKey().intValue());

        return expense;
    }

    @Override
    public void update(int userId, Expense expense) {
        MapSqlParameterSource parameterMap = new MapSqlParameterSource();
        parameterMap.addValue("userId", userId);
        parameterMap.addValue("id", expense.getId());
        parameterMap.addValue("profileId", expense.getProfile().getId());
        parameterMap.addValue("kindId", expense.getKind().getId());
        parameterMap.addValue("amount", expense.getAmount());

        jdbcTemplate.update(Sql.UPDATE_EXPENSE, parameterMap);
    }

    @Override
    public void delete(int userId, int expenseId) {
        MapSqlParameterSource parameterMap = new MapSqlParameterSource();
        parameterMap.addValue("userId", userId);
        parameterMap.addValue("expenseId", expenseId);

        jdbcTemplate.update(Sql.DELETE_EXPENSE, parameterMap);
    }

    private void addFilters(Filter filter, StringBuilder sqlBuilder, MapSqlParameterSource parameterMap) {
        if (filter.getKindId() > 0) {
            sqlBuilder.append(" and id_kind = :kindId");
            parameterMap.addValue("kindId", filter.getKindId());
        }
        if (filter.getProfileId() > 0) {
            sqlBuilder.append(" and id_profile = :profileId");
            parameterMap.addValue("profileId", filter.getProfileId());
        }
        if (filter.getStartDate() != null && filter.getEndDate() != null) {
            sqlBuilder.append(" and add_date between :startDate and :endDate");
            parameterMap.addValue("startDate", filter.getStartDate());
            parameterMap.addValue("endDate", filter.getEndDate());
        } else if (filter.getStartDate() != null) {
            sqlBuilder.append(" and add_date >= :startDate");
            parameterMap.addValue("startDate", filter.getStartDate());
        } else if (filter.getEndDate() != null) {
            sqlBuilder.append(" and add_date <= :endDate");
            parameterMap.addValue("endDate", filter.getEndDate());
        }
        if (filter.getLimit() > 0) {
            sqlBuilder.append(" limit " + filter.getLimit());
        }
    }

}
