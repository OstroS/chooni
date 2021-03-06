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
import pl.akiba.model.entities.Profile;

@Repository
public class JdbcProfileDao implements ProfileDao, InitializingBean {

    @Autowired
    @Qualifier("mysqlAkibaDataSource")
    private DataSource dataSource;

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Profile> getAll(long userId, String authCode) {
        MapSqlParameterSource parameterMap = new MapSqlParameterSource();
        parameterMap.addValue("userId", userId);
        parameterMap.addValue("authCode", authCode);

        return jdbcTemplate.query(Sql.SELECT_PROFILES, parameterMap, new RowMapper<Profile>() {

            @Override
            public Profile mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Profile(rs.getInt("id"), rs.getString("name"), rs.getBoolean("def"), rs.getBoolean("active"));
            }

        });
    }

    @Override
    public Profile getDefault(long userId, String authCode) {
        MapSqlParameterSource parameterMap = new MapSqlParameterSource();
        parameterMap.addValue("userId", userId);
        parameterMap.addValue("authCode", authCode);

        return jdbcTemplate.queryForObject(Sql.SELECT_DEFAULT_PROFILE, parameterMap, new RowMapper<Profile>() {

            @Override
            public Profile mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Profile(rs.getInt("id"), rs.getString("name"), true, rs.getBoolean("active"));
            }

        });
    }

    @Override
    public Profile create(long userId, String authCode, Profile profile) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource parameterMap = new MapSqlParameterSource();
        parameterMap.addValue("userId", userId);
        parameterMap.addValue("name", profile.getName());
        parameterMap.addValue("authCode", authCode);

        jdbcTemplate.update(Sql.INSERT_PROFILE, parameterMap, keyHolder);
        profile.setId(keyHolder.getKey().intValue());

        return profile;
    }

    @Override
    public void update(long userId, String authCode, Profile profile) {
        MapSqlParameterSource parameterMap = new MapSqlParameterSource();
        parameterMap.addValue("userId", userId);
        parameterMap.addValue("profileId", profile.getId());
        parameterMap.addValue("name", profile.getName());
        parameterMap.addValue("def", profile.isDef());
        parameterMap.addValue("active", profile.isActive());
        parameterMap.addValue("authCode", authCode);

        jdbcTemplate.update(Sql.UPDATE_PROFILE, parameterMap);
    }

    @Override
    public void delete(long userId, String authCode, int profileId) {
        MapSqlParameterSource parameterMap = new MapSqlParameterSource();
        parameterMap.addValue("userId", userId);
        parameterMap.addValue("profileId", profileId);
        parameterMap.addValue("authCode", authCode);

        jdbcTemplate.update(Sql.DELETE_PROFILE, parameterMap);
        jdbcTemplate.update(Sql.DELETE_PROFILE_EXPENSES, parameterMap);
    }

}
