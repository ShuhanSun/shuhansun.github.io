import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "calc", urlPatterns = "/calc")
public class Calculator extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.print("<html><head><title>Result</title></head><body>");
        try {
            String add1s = req.getParameter("add1");
            String add2s = req.getParameter("add2");
            Integer add1 = null;
            Integer add2 = null;
            Integer addsum = null;
            if (add1s != null && !add1s.isEmpty() && add2s != null && !add2s.isEmpty()) {
                add1 = Integer.parseInt(add1s);
                add2 = Integer.parseInt(add2s);
                addsum = add1 + add2;
                //                out.print(add1 + "+" + add2 + "=" + (add1 + add2));
                //                out.print("<br/>");
            }

            String multi1s = req.getParameter("multi1");
            String multi2s = req.getParameter("multi2");
            Integer multi1 = null;
            Integer multi2 = null;
            Integer multisum = null;
            if (multi1s != null && !multi1s.isEmpty() && multi2s != null && !multi2s.isEmpty()) {
                multi1 = Integer.parseInt(multi1s);
                multi2 = Integer.parseInt(multi2s);
                multisum = multi1 * multi2;
                //                out.print(p1 + "*" + p2 + "=" + (p1 * p2));
            }

            String r = "  <form id='calc' action='/calc' method='get' >\n" +
                    "    <label for='add1'></label><input type='text' id='add1' name='add1' value='" + getValue(add1) + "'/>\n" +
                    "    +\n" +
                    "    <label for='add2'></label><input type='text' id='add2' name='add2' value='" + getValue(add2) + "'/>\n" +
                    "    =\n" +
                    "    <label for='addsum'></label><input type='text' id='addsum' name='addsum' value='" + getValue(addsum) + "'/>\n" +
                    "    <br/>\n" +
                    "    <label for='multi1'></label><input type='text' id='multi1' name='multi1' value='" + getValue(multi1) + "'/>\n" +
                    "    *\n" +
                    "    <label for='multi2'></label><input type='text' id='multi2' name='multi2' value='" + getValue(multi2) + "'/>\n" +
                    "    =\n" +
                    "    <label for='multisum'></label><input type='text' id='multisum' name='multisum' value='" + getValue(multisum) + "'/>\n" +
                    "    <input type='submit' value='Submit'/>\n" +
                    "  </form>\n";
            out.print(r);
        }
        catch (NumberFormatException e) {
            out.print("Number format is wrong!");
        }
        out.print("</body></html>");

    }

    private static String getValue(Integer i) {
        if (i == null) {
            return "";
        }
        else {
            return i.toString();
        }
    }
}
