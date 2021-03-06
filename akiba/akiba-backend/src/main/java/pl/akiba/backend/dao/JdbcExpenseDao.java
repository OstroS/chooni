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
    public Expense get(long userId, String authCode, int expenseId) {
        MapSqlParameterSource parameterMap = new MapSqlParameterSource();
        parameterMap.addValue("userId", userId);
        parameterMap.addValue("expenseId", expenseId);
        parameterMap.addValue("authCode", authCode);

        return jdbcTemplate.queryForObject(Sql.SELECT_EXPENSE, parameterMap, new RowMapper<Expense>() {

            @Override
            public Expense mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Expense(rs.getInt("id"), rs.getDouble("amount"), new Kind(rs.getInt("kindId"), rs
                        .getString("kindName")), new Profile(rs.getInt("id"), rs.getString("profileName"), rs
                        .getBoolean("def"), rs.getBoolean("active")), rs.getTimestamp("addDate"));
            }

        });
    }

    @Override
    public List<Expense> getAll(long userId, String authCode, Filter filter) {
        StringBuilder sqlBuilder = new StringBuilder(Sql.SELECT_EXPENSES);
        MapSqlParameterSource parameterMap = new MapSqlParameterSource();
        parameterMap.addValue("userId", userId);
        parameterMap.addValue("authCode", authCode);

        if (filter != null) {
            sqlBuilder.append(filter.getFilterSql());
            parameterMap.addValues(filter.getFilterSqlParams());
        }

        return jdbcTemplate.query(sqlBuilder.toString(), parameterMap, new RowMapper<Expense>() {

            @Override
            public Expense mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Expense(rs.getInt("id"), rs.getDouble("amount"), new Kind(rs.getInt("kindId"), rs
                        .getString("kindName")), new Profile(rs.getInt("id"), rs.getString("profileName"), rs
                        .getBoolean("def"), rs.getBoolean("active")), rs.getTimestamp("addDate"));
            }

        });
    }

    @Override
    public double getTotal(long userId, String authCode, Filter filter) {
        StringBuilder sqlBuilder = new StringBuilder(Sql.SELECT_TOTAL_EXPENSE);
        MapSqlParameterSource parameterMap = new MapSqlParameterSource();
        parameterMap.addValue("userId", userId);
        parameterMap.addValue("authCode", authCode);

        if (filter != null) {
            sqlBuilder.append(filter.getFilterSql());
            parameterMap.addValues(filter.getFilterSqlParams());
        }

        return jdbcTemplate.queryForObject(sqlBuilder.toString(), parameterMap, Double.class);
    }

    @Override
    public Expense create(long userId, String authCode, Expense expense) {
        Date now = new Date();
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource parameterMap = new MapSqlParameterSource();
        parameterMap.addValue("userId", userId);
        parameterMap.addValue("profileId", expense.getProfile().getId());
        parameterMap.addValue("kindId", expense.getKind().getId());
        parameterMap.addValue("amount", expense.getAmount());
        parameterMap.addValue("addDate", now);
        parameterMap.addValue("authCode", authCode);

        jdbcTemplate.update(Sql.INSERT_EXPENSE, parameterMap, keyHolder);

        expense.setDate(now);
        expense.setId(keyHolder.getKey().intValue());

        return expense;
    }

    @Override
    public void update(long userId, String authCode, Expense expense) {
        MapSqlParameterSource parameterMap = new MapSqlParameterSource();
        parameterMap.addValue("userId", userId);
        parameterMap.addValue("id", expense.getId());
        parameterMap.addValue("profileId", expense.getProfile().getId());
        parameterMap.addValue("kindId", expense.getKind().getId());
        parameterMap.addValue("amount", expense.getAmount());
        parameterMap.addValue("authCode", authCode);

        jdbcTemplate.update(Sql.UPDATE_EXPENSE, parameterMap);
    }

    @Override
    public void delete(long userId, String authCode, int expenseId) {
        MapSqlParameterSource parameterMap = new MapSqlParameterSource();
        parameterMap.addValue("userId", userId);
        parameterMap.addValue("expenseId", expenseId);
        parameterMap.addValue("authCode", authCode);

        jdbcTemplate.update(Sql.DELETE_EXPENSE, parameterMap);
    }

}
