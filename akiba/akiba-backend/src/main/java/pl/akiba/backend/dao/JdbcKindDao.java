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
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import pl.akiba.backend.util.Sql;
import pl.akiba.model.entities.Kind;

/**
 * 
 * @author sobczakt
 */
@Repository
public class JdbcKindDao implements KindDao, InitializingBean {

    @Autowired
    @Qualifier("mysqlAkibaDataSource")
    private DataSource dataSource;

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Kind get(long userId, int kindId) {
        MapSqlParameterSource parameterMap = new MapSqlParameterSource();
        parameterMap.addValue("userId", userId);
        parameterMap.addValue("kindId", kindId);

        return jdbcTemplate.queryForObject(Sql.SELECT_KIND, parameterMap, new RowMapper<Kind>() {

            @Override
            public Kind mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Kind(rs.getInt("id"), rs.getString("name"));
            }

        });
    }

    @Override
    public List<Kind> getAll(long userId) {
        return jdbcTemplate.query(Sql.SELECT_KINDS, new MapSqlParameterSource("userId", userId), new RowMapper<Kind>() {

            @Override
            public Kind mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Kind(rs.getInt("id"), rs.getString("name"));
            }

        });
    }

    @Override
    public Kind create(long userId, Kind kind) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource parameterMap = new MapSqlParameterSource();
        parameterMap.addValue("userId", userId);
        parameterMap.addValue("name", kind.getName());

        jdbcTemplate.update(Sql.INSERT_KIND, parameterMap, keyHolder);
        kind.setId(keyHolder.getKey().intValue());

        return kind;
    }

    @Override
    public void update(long userId, Kind kind) {
        MapSqlParameterSource parameterMap = new MapSqlParameterSource();
        parameterMap.addValue("userId", userId);
        parameterMap.addValue("kindId", kind.getId());
        parameterMap.addValue("name", kind.getName());

        jdbcTemplate.update(Sql.UPDATE_KIND, parameterMap);
    }

    @Override
    public void delete(long userId, int kindId) {
        MapSqlParameterSource parameterMap = new MapSqlParameterSource();
        parameterMap.addValue("userId", userId);
        parameterMap.addValue("kindId", kindId);

        jdbcTemplate.update(Sql.DELETE_KIND, parameterMap);
    }

}
