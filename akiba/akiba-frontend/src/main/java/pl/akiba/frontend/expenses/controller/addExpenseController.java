package pl.akiba.frontend.expenses.controller;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.akiba.model.entities.Kind;
import pl.akiba.model.entities.Profile;
import pl.akiba.model.entities.User;

/**
 * Controller that implements adding new expense logic
 * @author OstroS
 */
@WebServlet(name = "addExpenseController", urlPatterns = {"/expence/add"})
public class addExpenseController extends HttpServlet {

    private final String viewUrl = "/jsp/expenses/addExpense.jsp";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        prepareAndBindCommonData( request);
        
        // forward to jsp
        request.getRequestDispatcher(viewUrl).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // set error msg
        request.setAttribute("errMsg", "Not yet implemented");
        
        prepareAndBindCommonData( request);
        
        // forward to jsp
        request.getRequestDispatcher(viewUrl).forward(request, response);
    }

    /**
     * TODO mock method
     * @param user Current user
     * @return List of kinds of expense for given user
     */
    private List<Kind> prepareKindsforUser(User user) {
        return Lists.newArrayList(new Kind(0, "Alkohol"),
                                  new Kind(1, "Zywnosc"),
                                  new Kind(2, "Prasa"),
                                  new Kind(3, "Ksiazki"),
                                  new Kind(4, "Squash"),
                                  new Kind(5, "Biegowki"));
    }

    /**
     * TODO Mock method
     * 
     * @param user Current user
     * @return List of profiles for given user
     */
    private List<Profile> prepareProfilesForUser(User user) {
        return Lists.newArrayList(new Profile(0, "Prywanty"),
                                  new Profile(1, "Biznes"),
                                  new Profile(2, "Rodzinny"));
    }

    /**
     * TODO Mock method
     * @param request
     * @return Returns current user
     */
    private User getUser(HttpServletRequest request) {
        return new User();
    }

    private void prepareAndBindCommonData(HttpServletRequest request) {
        // prepare data
        User user = getUser(request);
        List<Kind> kinds = prepareKindsforUser(user);
        List<Profile> profiles = prepareProfilesForUser(user);
        
        // bind data into request
        request.setAttribute("kinds", kinds);
        request.setAttribute("profiles", profiles);
    }
}