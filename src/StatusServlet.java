import javax.jms.Session;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StatusServlet extends HttpServlet {
    private UserList userList = UserList.getInstance();
    private MessageList msgList = MessageList.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String userName = request.getParameter("userName");
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = userList.getByLogin(userName);
            if (user != null)
                response.setStatus(200);
            else
                response.setStatus(202);
        } else response.setStatus(400);
    }
}