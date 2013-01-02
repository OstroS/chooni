package pl.akiba.backend.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import pl.akiba.backend.util.Sql;
import pl.akiba.model.entities.Expense;
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
    public Expense get(String userId, int expenseId) {
        return null;
    }

    @Override
    public List<Expense> getAll(String userId) {
        return jdbcTemplate.query(Sql.SELECT_EXPENSES, new MapSqlParameterSource("userId", userId),
                new RowMapper<Expense>() {

                    @Override
                    public Expense mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Expense(rs.getInt("id"), rs.getBigDecimal("amount"), new Kind(rs.getInt("kindId"),
                                rs.getString("kindName")), new Profile(rs.getInt("profileId"), rs
                                .getString("profileName")));
                    }

                });
    }

    @Override
    public void create(String userId, Expense expense) {
        // TODO Auto-generated method stub
    }

    @Override
    public void update(String userId, Expense expense) {
        // TODO Auto-generated method stub
    }

    @Override
    public void delete(String userId, int expenseId) {
        // TODO Auto-generated method stub
    }

}
