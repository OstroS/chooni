package pl.akiba.backend.util;

/**
 * @author sobczakt
 */
public class Sql {

    public static final String SELECT_EXPENSES = "select e.id, p.id as profileId, p.name as profileName, k.id as kindId, "
            + "k.name as kindName, e.amount from expense e, kind k, profile p "
            + "where e.id_profile = p.id and e.id_kind = k.id and e.id_user = :userId";

    public static final String SELECT_EXPENSE = "select e.id, p.id as profileId, p.name as profileName, k.id as kindId, "
            + "k.name as kindName, e.amount from expense e, kind k, profile p "
            + "where e.id_profile = p.id and e.id_kind = k.id and e.id_user = :userId and e.id = :expenseId";

    public static final String DELETE_EXPENSE = "delete from expense where id = :expenseId and id_user = :userId";

}
