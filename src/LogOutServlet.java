import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class LogOutServlet extends HttpServlet {
    private UserList userList = UserList.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null) {
            String userCookie = String.valueOf(session.getAttribute("userlogin"));
            for (User user : userList.getUserList()) {
                if (userCookie.equals(user.getLogin())) {
                    userList.getUserList().remove(user);
                    response.setStatus(200);
                }
            }
            session.invalidate();
        }
    }
}