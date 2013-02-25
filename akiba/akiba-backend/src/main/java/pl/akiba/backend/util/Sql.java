package pl.akiba.backend.util;

/**
 * @author sobczakt
 */
public class Sql {

    /* expenses */

    public static final String SELECT_EXPENSES = "select e.id, e.amount, e.add_date as addDate, "
            + "p.id as profileId, p.name as profileName, p.def, p.active, "
            + "k.id as kindId, k.name as kindName from expense e, kind k, profile p "
            + "where e.id_profile = p.id and e.id_kind = k.id and e.id_user = :userId";

    public static final String SELECT_EXPENSE = "select e.id, e.amount, e.add_date as addDate, "
            + "p.id as profileId, p.name as profileName, p.def, p.active, "
            + "k.id as kindId, k.name as kindName from expense e, kind k, profile p "
            + "where e.id_profile = p.id and e.id_kind = k.id and e.id_user = :userId and e.id = :expenseId";

    public static final String SELECT_TOTAL_EXPENSE = "select sum(amount) from expense where id_user = :userId";

    public static final String DELETE_EXPENSE = "delete from expense where id = :expenseId and id_user = :userId";

    public static final String INSERT_EXPENSE = "insert into expense (id_user, id_profile, id_kind, amount, add_date) values "
            + "(:userId, :profileId, :kindId, :amount, :addDate)";

    public static final String UPDATE_EXPENSE = "update expense set id_profile = :profileId, id_kind = :kindId, amount = :amount "
            + "where id = :id and id_user = :userId";

    /* kinds */

    public static final String SELECT_KIND = "select id, name from kind where id = :kindId and id_user = :userId";

    public static final String SELECT_KINDS = "select id, name from kind where id_user = :userId";

    public static final String INSERT_KIND = "insert into kind (name, id_user) values (:name, :userId)";

    public static final String UPDATE_KIND = "update kind set name = :name where id = :kindId and id_user = :userId";

    public static final String DELETE_KIND = "delete from kind where id = :kindId and id_user = :userId";

    /* profiles */

    public static final String SELECT_PROFILES = "select id, name, def, active from profile "
            + "where id_user = :userId";

    public static final String SELECT_DEFAULT_PROFILE = "select id, name, active from profile "
            + "where id_user = :userId and def = 1";

    public static final String INSERT_PROFILE = "insert into profile (name, id_user) values (:name, :userId)";

    public static final String UPDATE_PROFILE = "update profile set name = :name, def = :def, active = :active "
            + "where id = :profileId and id_user = :userId";

    public static final String DELETE_PROFILE = "delete from profile where id = :profileId and id_user = :userId";

    public static final String DELETE_PROFILE_EXPENSES = "delete from expense where id_profile = :profileId and id_user = :userId";

    /* users */

    public static final String INSERT_FACEBOOK_USER = "insert into user (id_facebook, role) values (:facebookId, :role)";

    public static final String SELECT_FACEBOOK_USER = "select id, role from user where id_facebook = :facebookId";

}
