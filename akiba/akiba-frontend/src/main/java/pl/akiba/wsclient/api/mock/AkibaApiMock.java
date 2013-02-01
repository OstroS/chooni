package pl.akiba.wsclient.api.mock;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;

import pl.akiba.frontend.model.entities.FacebookUser;
import pl.akiba.model.entities.Expense;
import pl.akiba.model.entities.Kind;
import pl.akiba.model.entities.Profile;
import pl.akiba.model.entities.User;
import pl.akiba.wsclient.api.AkibaApi;
import pl.akiba.wsclient.api.Criteria;
import pl.akiba.wsclient.api.CriteriaBuilder;
import pl.akiba.wsclient.api.CrudApi;
import pl.akiba.wsclient.api.UserApi;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

@Component("akibaApiMock")
public class AkibaApiMock implements AkibaApi {

    private static final Logger logger = Logger.getLogger(AkibaApiMock.class.toString());

    private CrudApi<Expense> expenseApi;
    private CrudApi<Kind> kindApi;
    private CrudApi<Profile> profileApi;

    public AkibaApiMock() {
        kindApi = instantiateKindApi();
        profileApi = instantiateProfileApi();
        expenseApi = instantiateExpenseApi();
    }

    private CrudApi<Profile> instantiateProfileApi() {
        CrudApi<Profile> api = new CrudApi<Profile>() {

            private final List<Profile> profiles = Lists.newArrayList(new Profile(0, "Prywatny"), new Profile(1,
                    "Biznes"), new Profile(2, "Wakacje"));

            @Override
            public void add(Profile entity, User user) {
                profiles.add(entity);
                logger.info("AkibaApiMock: AddProfile " + entity);

            }

            @Override
            public List<Profile> getAll(User user) {
                logger.info("AkibaApiMock: GetAllProfiles");
                return profiles;
            }

            @Override
            public Profile get(Long entityId) {
                logger.info("AkibaApiMock: GetProfile " + entityId);
                long id = entityId;
                return profiles.get((int) id);
            }

            @Override
            public Profile update(Profile entity) {
                logger.info("AkibaApiMock: Update Profile - no action");
                return null;
            }

            @Override
            public void delete(Profile entity) {
                logger.info("AkibaApiMock: DeleteProfile - no action" + entity);

            }

            @Override
            public List<Profile> get(User user, Criteria criteria) {
                // TODO Auto-generated method stub
                return null;
            }

        };

        return api;

    }

    private CrudApi<Kind> instantiateKindApi() {
        CrudApi<Kind> api = new CrudApi<Kind>() {

            List<Kind> kinds = Lists.newArrayList(new Kind(0, "Alkohol"), new Kind(1, "Jedzenie"), new Kind(2, "Kawa"),
                    new Kind(3, "Mieszkanie"), new Kind(4, "Imprezy"));

            @Override
            public void add(Kind entity, User user) {
                entity.setId(kinds.size());
                logger.info("AkibaApiMock: AddKind " + entity);
                kinds.add(entity);

            }

            @Override
            public List<Kind> getAll(User user) {
                logger.info("AkibaApiMock: GetAllKinds");
                return kinds;
            }

            @Override
            public Kind get(Long entityId) {
                logger.info("AkibaApiMock: GetKind");
                long id = entityId;
                return kinds.get((int) id);
            }

            @Override
            public Kind update(Kind entity) {
                logger.info("AkibaApiMock: KindUpdate - no action");
                return entity;
            }

            @Override
            public void delete(Kind entity) {
                logger.info("AkibaApiMock: DeleteKind - no action");

            }

            @Override
            public List<Kind> get(User user, Criteria criteria) {
                // TODO Auto-generated method stub
                return null;
            }

        };

        return api;
    }

    private CrudApi<Expense> instantiateExpenseApi() {
        return new CrudApi<Expense>() {

            List<Expense> expenses = Lists.newArrayList(new Expense(0, 123.0, AkibaApiMock.this.kindApi.get(0L),
                    AkibaApiMock.this.profileApi.get(0L), new Date()),
                    new Expense(1, 456.0, AkibaApiMock.this.kindApi.get(1L), AkibaApiMock.this.profileApi.get(1L),
                            new Date()));

            @Override
            public void add(Expense entity, User user) {
                logger.info("AkibaApiMock: AddExpense " + entity);
                expenses.add(entity);

            }

            @Override
            public List<Expense> getAll(User user) {
                logger.info("AkibaApiMock: GetAllExpenses");
                return expenses;
            }

            @Override
            public Expense get(Long entityId) {
                long id = entityId;
                return expenses.get((int) id);
            }

            @Override
            public Expense update(Expense entity) {
                logger.info("AkibaApiMock: UpdateExpense - no action");
                return entity;
            }

            @Override
            public void delete(Expense entity) {
                logger.info("AkibaApiMock: DeleteExpense - no action");

            }

            @Override
            public List<Expense> get(User user, Criteria criteria) {
                return this.filter(expenses, criteria);
            }

            /**
             * TODO Method should filter list given as a list to specific criteria <br/>
             * In final solution this filtering should be done on BE side
             * 
             * @param list
             *            List to be filtered
             * @param criteria
             *            Filtering criteria
             * @return Filtered list
             */
            private List<Expense> filter(List<Expense> list, final Criteria criteria) {
                List<Expense> toReturn = list;

                toReturn = sortListByDate(list, criteria);

                // filtered by date - TODO

                // get first X elements
                if (criteria.getAmount() != null) {
                    long firstIndex = 0;
                    long lastIndex = criteria.getAmount() - 1;

                    if (lastIndex >= toReturn.size())
                        lastIndex = toReturn.size() - 1;
                    logger.info("First index=" + firstIndex + ", last index=" + lastIndex);
                    toReturn = toReturn.subList((int) firstIndex, (int) lastIndex);
                }
                return toReturn;
            }

        };

    }

    @Override
    public CrudApi<Expense> getExpenseApi() {
        return expenseApi;
    }

    @Override
    public CrudApi<Kind> getKindApi() {
        return kindApi;
    }

    @Override
    public CrudApi<Profile> getProfileApi() {
        return profileApi;
    }

    private List<Expense> sortListByDate(List<Expense> list, final Criteria criteria) {
        List<Expense> toReturn;
        // order
        Comparator<Expense> expenseByDate = new Comparator<Expense>() {

            @Override
            public int compare(Expense o1, Expense o2) {
                long result = (o2.getDate().getTime() - o1.getDate().getTime());
                if (result > 0) {
                    return 1;
                } else if (result < 0) {
                    return -1;
                }
                return 0;
            }
        };
        Ordering<Expense> ordering;
        if (criteria.getSort().equals(CriteriaBuilder.SORT_ASCENDING_ORDER)) {
            ordering = Ordering.from(expenseByDate);
        } else {
            ordering = Ordering.from(expenseByDate).reverse();
        }

        toReturn = ordering.sortedCopy(list);
        return toReturn;
    }

    @Override
    public UserApi getUserApi() {

        return new UserApi() {

            @Override
            public FacebookUser getByFacebookId(Long facebookId) {
                logger.info("CreateDefaultFacebookUser");
                FacebookUser fUser = new FacebookUser();
                fUser.setFacebookId(facebookId);
                fUser.setId(new Random().nextLong());
                return fUser;
            }
        };
    }
}
