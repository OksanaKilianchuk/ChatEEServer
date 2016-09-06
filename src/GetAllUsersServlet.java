import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;


public class GetAllUsersServlet extends HttpServlet {

    private UserList userList = UserList.getInstance();

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            String users = userList.toJSON();
            if (users != null) {
                OutputStream os = response.getOutputStream();
                os.write(users.getBytes());
            }
        } else response.setStatus(400);
    }
}

