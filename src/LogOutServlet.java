import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class LogOutServlet extends HttpServlet {
    private UserList userList = UserList.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        try (BufferedReader bf = new BufferedReader(new InputStreamReader(request.getInputStream()))) {
            User user = User.fromJSON(bf.readLine());
            for (User user2 : userList.getUserList()) {
                if (user2.getLogin().equals(user.getLogin())) {
                    user2.setStatus("offline");
                    response.setStatus(200);
                }
            }

        }
    }
}