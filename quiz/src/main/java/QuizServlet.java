import app.Quiz;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "quiz", urlPatterns = "/quiz")
public class QuizServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doQuiz(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doQuiz(req, resp);
    }

    private void doQuiz(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Quiz sessQuiz = (Quiz)req.getSession().getAttribute("quiz");
        if (sessQuiz == null) {
            sessQuiz = new Quiz();
            req.getSession().setAttribute("quiz", sessQuiz);
        }

        if (sessQuiz.getNumCorrect() == sessQuiz.getNumQuestions()) {
            genQuizOverPage(resp.getWriter());
            return;
        }

        String answer = req.getParameter("txtAnswer");
        boolean error = false;
        if (answer != null && answer.length() > 0) {
            if (sessQuiz.isCorrect(answer)) {
                sessQuiz.scoreAnswer();
            }
            else {
                error = true;
            }
        }

        if (sessQuiz.getNumCorrect() == sessQuiz.getNumQuestions()) {
            genQuizOverPage(resp.getWriter());
            return;
        }

        String currQuest = sessQuiz.getCurrentQuestion();
        genQuizPage(sessQuiz, resp.getWriter(), currQuest, error, answer);
    }


    private void genQuizPage(app.Quiz sessQuiz, PrintWriter out, String currQuest, boolean error, String answer) {

        out.print("<html>");
        out.print("<head>");
        out.print("	<title>NumberQuiz</title>");
        out.print("</head>");
        out.print("<body>");
        out.print("	<form method='post'>");
        out.print("		<h3>Have fun with NumberQuiz!</h3>");
        out.print("<p>Your current score is: ");
        out.print(sessQuiz.getNumCorrect() + "</br></br>");
        out.print("<p>Guess the next number in the sequence! ");
        out.print(currQuest + "</p>");

        out.print("<p>Your answer:<input type='text' name='txtAnswer' value='' /></p> ");

        /* if incorrect, then print out error message */
        if (error && (answer != null)) {  //REFACTOR?-- assumes answer null only when first open page
            out.print("<p style='color:red'>Your last answer was not correct! Please try again</p> ");
        }
        out.print("<p><input type='submit' name='btnNext' value='Next' /></p> ");

        out.print("</form>");
        out.print("</body></html>");
    }

    private void genQuizOverPage(PrintWriter out) {
        out.print("<html> ");
        out.print("<head >");
        out.print("<title>NumberQuiz is over</title> ");
        out.print("</head> ");
        out.print("<body> ");
        out.print("<p style='color:red'>The number quiz is over!</p>	</body> ");
        out.print("</html> ");
    }
}
