package pl.akiba.wsclient.api.mock;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import pl.akiba.model.entities.Expense;
import pl.akiba.model.entities.Kind;
import pl.akiba.model.entities.Profile;
import pl.akiba.model.entities.User;
import pl.akiba.wsclient.api.AkibaApi;
import pl.akiba.wsclient.api.Criteria;
import pl.akiba.wsclient.api.CrudApi;

@Component("akibaApiMock")
public class AkibaApiMock implements AkibaApi {

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
                System.out.println("AkibaApiMock: AddProfile " + entity);

            }

            @Override
            public List<Profile> getAll(User user) {
                System.out.println("AkibaApiMock: GetAllProfiles");
                return profiles;
            }

            @Override
            public Profile get(Long entityId) {
                System.out.println("AkibaApiMock: GetProfile " + entityId);
                long id = entityId;
                return profiles.get((int) id);
            }

            @Override
            public Profile update(Profile entity) {
                System.out.println("AkibaApiMock: Update Profile - no action");
                return null;
            }

            @Override
            public void delete(Profile entity) {
                System.out.println("AkibaApiMock: DeleteProfile - no action" + entity);

            }

            @Override
            public Profile get(Criteria criteria) {
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
                System.out.println("AkibaApiMock: AddKind " + entity);
                kinds.add(entity);

            }

            @Override
            public List<Kind> getAll(User user) {
                System.out.println("AkibaApiMock: GetAllKinds");
                return kinds;
            }

            @Override
            public Kind get(Long entityId) {
                System.out.println("AkibaApiMock: GetKind");
                long id = entityId;
                return kinds.get((int) id);
            }

            @Override
            public Kind update(Kind entity) {
                System.out.println("AkibaApiMock: KindUpdate - no action");
                return entity;
            }

            @Override
            public void delete(Kind entity) {
                System.out.println("AkibaApiMock: DeleteKind - no action");

            }

            @Override
            public Kind get(Criteria criteria) {
                // TODO Auto-generated method stub
                return null;
            }

        };

        return api;
    }

    private CrudApi<Expense> instantiateExpenseApi() {
        return new CrudApi<Expense>() {

            List<Expense> expenses = Lists.newArrayList(
                    new Expense(0, new BigDecimal("123"), AkibaApiMock.this.kindApi.get(0L),
                            AkibaApiMock.this.profileApi.get(0L)), new Expense(1, new BigDecimal("456"),
                            AkibaApiMock.this.kindApi.get(1L), AkibaApiMock.this.profileApi.get(1L)));

            @Override
            public void add(Expense entity, User user) {
                System.out.println("AkibaApiMock: AddExpense " + entity);
                expenses.add(entity);

            }

            @Override
            public List<Expense> getAll(User user) {
                System.out.println("AkibaApiMock: GetAllExpenses");
                return expenses;
            }

            @Override
            public Expense get(Long entityId) {
                long id = entityId;
                return expenses.get((int) id);
            }

            @Override
            public Expense update(Expense entity) {
                System.out.println("AkibaApiMock: UpdateExpense - no action");
                return entity;
            }

            @Override
            public void delete(Expense entity) {
                System.out.println("AkibaApiMock: DeleteExpense - no action");

            }

            @Override
            public Expense get(Criteria criteria) {
                // TODO Auto-generated method stub
                return null;
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
}
