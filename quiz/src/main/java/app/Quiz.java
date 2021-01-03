package app;

public class Quiz {

    private static String[] questions = {
            "3,1,4,1,5",
            "1,1,2,3,5",
            "1,4,9,16,25",
            "2,3,5,7,11",
            "1,2,4,8,16"
    };
    private static String[] hints = {
            "Pi",
            "Fibonacci",
            "Square",
            "Odd",
            "Power"
    };
    private static int[] answers = {9, 8, 36, 13, 32};
    private static int[] scores = {10, 5, 2};

    private int tryCount = 0;
    private int score = 0;
    private int questionIndex = 0;

    public String getCurrentQuestion() {
        return questions[questionIndex];
    }

    public boolean isCorrect(String num) {
        tryCount++;
        return answers[questionIndex] == Integer.parseInt(num);
    }

    public int getCurrentAnswer(){
        return answers[questionIndex];
    }

    public int getTryCount(){
        return tryCount;
    }

    public boolean isDoneTry(){
        return tryCount >= 3;
    }

    public void tryNext(){
        tryCount = 0;
        questionIndex++;
    }

    public int getNumQuestions() {
        return questions.length;
    }

    public int getNumCorrect() {
        return score;
    }



    public void scoreAnswer() {
        score += scores[tryCount-1];
    }

    public int getCurrentQuestionIndex() {
        return questionIndex + 1;
    }

    public boolean isOver() {
        return questionIndex >= questions.length;
    }

    public String getHint() {
        return hints[questionIndex];
    }

    public String getFinalGrade() {
        if (score >= 45 && score <= 50) {
            return "A";
        }
        else if (score >= 35 && score < 45) {
            return "B";
        }
        else if (score >= 25 && score < 35) {
            return "C";
        }
        else if (score < 25) {
            return "NC";
        }

        return null;
    }
}
