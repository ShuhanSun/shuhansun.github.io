package app;

public class Quiz {

    private static String[] questions = {
            "3,1,4,1,5",
            "1,1,2,3,5",
            "1,4,9,16,25",
            "2,3,5,7,11",
            "1,2,4,8,16"
    };
    private static int[] answers = {9,8,36,13,32};

    private int score = 0;
    private int questionIndex = 0;

    public String getCurrentQuestion(){
        return questions[questionIndex];
    }

    public boolean isCorrect(String num){
        return answers[questionIndex] == Integer.parseInt(num);
    }

    public int getNumQuestions(){
        return questions.length;
    }

    public int getNumCorrect(){
        return score;
    }

    public void scoreAnswer(){
        score++;
        questionIndex++;
    }

    public int getCurrentQuestionIndex(){
        return questionIndex + 1;
    }
}
