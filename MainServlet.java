import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/// Java EE Servlet entry point - no main() method
/// Deploy to Tomcat/Jetty/etc and access via /farm endpoint
@WebServlet("/farm")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();

        CattleFarm farm = new CattleFarm(1, "Bessie");

        farm.giveBirth(1, 2, "Daisy");
        farm.giveBirth(1, 3, "Rose");
        farm.giveBirth(2, 4, "Luna");
        farm.giveBirth(2, 5, "Star");

        out.println("=== Farm after births ===");
        printFarm(out, farm);

        boolean duplicateResult = farm.giveBirth(1, 4, "Ghost");
        out.println("Duplicate ID accepted: " + duplicateResult);

        farm.endLifeSpan(2);

        out.println("=== Farm after Daisy removed ===");
        printFarm(out, farm);
    }

    private void printFarm(PrintWriter out, CattleFarm farm) {
        CowRecord root = farm.findCow(1);
        printCowRecursive(out, root, 0);
    }

    private void printCowRecursive(PrintWriter out, CowRecord cow, int depth) {
        StringBuilder indent = new StringBuilder();
        int i = 0;
        while (i < depth) {
            indent.append("  ");
            i = i + 1;
        }

        out.println(indent + "Cow(" + cow.getCowId() + ") " + cow.getNickName());

        ChainLink currentChild = cow.getChildren().getHead();
        while (currentChild != null) {
            printCowRecursive(out, currentChild.getData(), depth + 1);
            currentChild = currentChild.getNext();
        }
    }
}
