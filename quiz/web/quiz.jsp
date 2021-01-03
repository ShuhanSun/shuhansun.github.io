<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>NumberQuiz</title>
</head>
<body>
<form method='post'>
    <p>Your age:
        <label>
            <input type='text' name='age' value='${age}'/>
        </label>
        ${ageErrorMsg}
    </p>

    <h3>Have fun with NumberQuiz!</h3>

    <%
        Integer rightAnswer = (Integer)request.getAttribute("rightAnswer");
        if (rightAnswer != null ) {
    %>
    <p >You used up your chance in the last question.
        The right answer of last question is ${rightAnswer}
    </p>
    <%
        }
    %>

    <p>Your current score is:
        ${sessQuiz.numCorrect}</p>
    <p>Guess the next number in the sequence:
        ${sessQuiz.currentQuestion}</p>

    <p>Your answer:
        <label>
            <input type='text' name='txtAnswer' value=''/>
        </label>
    </p>

    <%
        Boolean error = (Boolean)request.getAttribute("error");
        String txtAnswer = request.getParameter("txtAnswer");
        if (error != null && error && (txtAnswer != null)) {
    %>
    <p style='color:red'>Your last answer was not correct! Please try again</p>
    <%
        }
    %>

    <p><input type='submit' name='btnNext' value='Next'/></p>
    <p><input type='submit' name='hint' value='Hint'/></p>
    <% String hint = (String)request.getAttribute("hint");
        if (hint != null) {
    %>
    <p>hint: ${hint}</p>
    <%
        }
    %>
</form>
</body>
</html>
