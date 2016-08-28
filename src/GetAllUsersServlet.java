import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;


public class GetAllUsersServlet extends HttpServlet {

    private UserList userList = UserList.getInstance();

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String json = userList.toJSON();
        if (json != null) {
            OutputStream os = resp.getOutputStream();
            os.write(json.getBytes());
        }
    }
}

