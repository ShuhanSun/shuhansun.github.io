import app.Quiz;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    private void doQuiz(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Quiz sessQuiz = (Quiz)req.getSession().getAttribute("quiz");
        if (sessQuiz == null) {
            sessQuiz = new Quiz();
            req.getSession().setAttribute("quiz", sessQuiz);
        }

        // check age
        if (!checkAge(req)) {
            req.setAttribute("sessQuiz", sessQuiz);
            genQuizPage(req, resp);
            return;
        }

        // check if quiz over
        if (sessQuiz.isOver()) {
            req.setAttribute("finalGrade", sessQuiz.getFinalGrade());
            genQuizOverPage(req, resp);
            return;
        }

        // submit by pushing the btnNext button
        if ("Next".equals(req.getParameter("btnNext"))) {
            // check answer is correct
            String answer = req.getParameter("txtAnswer");
            boolean error = false;
            if (answer != null && answer.length() > 0) {
                boolean correct = sessQuiz.isCorrect(answer);
                if (correct) {
                    sessQuiz.scoreAnswer();
                }
                else {
                    error = true;
                    if (sessQuiz.isDoneTry()) {
                        req.setAttribute("rightAnswer", sessQuiz.getCurrentAnswer());
                    }
                }

                if (correct || sessQuiz.isDoneTry()) {
                    sessQuiz.tryNext();
                }
            }
            req.setAttribute("error", error);

            // check if quiz over
            if (sessQuiz.isOver()) {
                req.setAttribute("finalGrade", sessQuiz.getFinalGrade());
                genQuizOverPage(req, resp);
                return;
            }
        }
        else if ("Hint".equals(req.getParameter("hint"))) {
            req.setAttribute("hint", sessQuiz.getHint());
        }

        req.setAttribute("sessQuiz", sessQuiz);
        genQuizPage(req, resp);
    }

    private boolean checkAge(HttpServletRequest req) {
        String ageStr = req.getParameter("age");
        int age;
        try {
            age = Integer.parseInt(ageStr);
        }
        catch (NumberFormatException e) {
            req.setAttribute("ageErrorMsg", "This age field is required, must enter an integer");
            return false;
        }
        if (age < 4 || age > 100) {
            req.setAttribute("ageErrorMsg", "The age is must between 4 and 100");
            return false;
        }

        req.setAttribute("age", age);
        return true;
    }

    private void genQuizPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/quiz.jsp").forward(req, resp);
    }

    private void genQuizOverPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/over.jsp").forward(req, resp);
    }
}
