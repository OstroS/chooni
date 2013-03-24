package pl.akiba.backend.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

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
import pl.akiba.model.entities.FacebookUser;
import pl.akiba.model.entities.User;
import pl.akiba.model.entities.User.ROLE;

/**
 * 
 * @author sobczakt
 */
@Repository
public class JdbcUserDao implements UserDao, InitializingBean {

    @Autowired
    @Qualifier("mysqlAkibaDataSource")
    private DataSource dataSource;

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public FacebookUser getFacebookUser(final long facebookId) {
        MapSqlParameterSource parameterMap = new MapSqlParameterSource();
        parameterMap.addValue("facebookId", facebookId);

        return jdbcTemplate.queryForObject(Sql.SELECT_FACEBOOK_USER, parameterMap, new RowMapper<FacebookUser>() {

            @Override
            public FacebookUser mapRow(ResultSet rs, int rowNum) throws SQLException {
                FacebookUser fUser = new FacebookUser(facebookId, rs.getLong("id"), ROLE.getRole(rs.getString("role")));
                fUser.setAuthenticationCode(rs.getString("auth_code"));
                return fUser;
            }

        });
    }

    @Override
    public FacebookUser createFacebookUser(long facebookId) {
        ROLE userRole = User.ROLE.ROLE_USER;
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource parameterMap = new MapSqlParameterSource();
        parameterMap.addValue("facebookId", facebookId);
        parameterMap.addValue("role", userRole.name());
        parameterMap.addValue("auth_code", generateAuthCode());

        jdbcTemplate.update(Sql.INSERT_FACEBOOK_USER, parameterMap, keyHolder);

        return new FacebookUser(facebookId, keyHolder.getKey().longValue(), userRole);
    }

    // TODO
    // Wygenerowanie authcode dla danego uzytkownika - aktualnie jest to timestamp
    private String generateAuthCode() {
        return Long.toString((new java.util.Date()).getTime());
    }

}
