package nix.edu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet(name = "GET-handler-servlet", urlPatterns = "/GetHandler")
public class GetHandlerServlet extends HttpServlet {

    private ConcurrentHashMap<String, String> headersAndIpOfRequests = new ConcurrentHashMap<>();
    private static final Logger log = LoggerFactory.getLogger(GetHandlerServlet.class);

    @Override
    public void init() {
        log.info("GetHandlerServlet initialized!");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter responseBody = resp.getWriter();
        resp.setContentType("text/html");
        String ipOfRequest = req.getRemoteAddr();
        String headerOfRequest = req.getHeader("User-Agent");
        headersAndIpOfRequests.put(ipOfRequest, headerOfRequest);
        headersAndIpOfRequests.forEach(
                (k, v) -> responseBody.
                        println("<p>" + k + " :: " + v + "</p>")
        );

    }

    @Override
    public void destroy() {
        log.info("GetHandlerServlet destroyed !");
    }

}
