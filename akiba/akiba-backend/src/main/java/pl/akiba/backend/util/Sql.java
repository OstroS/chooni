package pl.akiba.backend.util;

/**
 * @author sobczakt
 */
public class Sql {

    public static final String SELECT_EXPENSES = "select e.id, p.id as profileId, p.name as profileName, k.id as kindId, "
            + "k.name as kindName, e.amount from expense e, kind k, profile p "
            + "where e.profile_id = p.id and e.kind_id = k.id and e.user_id = :userId";

}
