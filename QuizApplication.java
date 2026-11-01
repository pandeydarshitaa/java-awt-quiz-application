import java.awt.*;
import java.awt.event.*;

public class QuizApplication extends Frame implements ActionListener {

    Label title, question, result;
    TextField name;
    Button start, next, close;
    Checkbox[] options = new Checkbox[4];

    int q = 0, score = 0;

    String[] questions = {
        "What is the full form of CPU?",
        "Which symbol is used for addition?",
        "Which language is this program written in?",
        "How many bits are in one byte?",
        "Which keyword is used to create a class?"
    };

    String[][] choices = {
        {"Central Processing Unit", "Computer Personal Unit",
         "Central Program Unit", "Control Processing Unit"},

        {"-", "+", "*", "/"},

        {"Python", "C++", "Java", "HTML"},

        {"4", "8", "16", "32"},

        {"public", "static", "class", "void"}
    };

    int[] answers = {0, 1, 2, 1, 2};

    public QuizApplication() {

        setTitle("Java Quiz Application");
        setSize(500, 400);
        setLayout(null);

        title = new Label("JAVA QUIZ");
        title.setBounds(200, 40, 100, 30);
        add(title);

        name = new TextField();
        name.setBounds(150, 90, 200, 25);
        add(name);

        start = new Button("Start Quiz");
        start.setBounds(190, 140, 100, 30);
        start.addActionListener(this);
        add(start);

        question = new Label();
        question.setBounds(30, 70, 440, 30);
        add(question);

        for (int i = 0; i < 4; i++) {
            options[i] = new Checkbox();
            options[i].setBounds(50, 120 + i * 35, 350, 25);
            add(options[i]);
        }

        next = new Button("Next");
        next.setBounds(190, 280, 100, 30);
        next.addActionListener(this);
        add(next);

        close = new Button("Close");
        close.setBounds(350, 320, 80, 30);
        close.addActionListener(this);
        add(close);

        result = new Label();
        result.setBounds(50, 150, 400, 30);
        add(result);

        question.setVisible(false);
        next.setVisible(false);
        result.setVisible(false);

        for (int i = 0; i < 4; i++) {
            options[i].setVisible(false);
        }

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == close) {
            System.exit(0);
        }

        else if (e.getSource() == start) {

            if (name.getText().trim().isEmpty()) {
                result.setText("Enter your name first!");
                result.setVisible(true);
                return;
            }

            title.setVisible(false);
            name.setVisible(false);
            start.setVisible(false);

            question.setVisible(true);
            next.setVisible(true);

            for (int i = 0; i < 4; i++) {
                options[i].setVisible(true);
            }

            result.setVisible(false);
            showQuestion();
        }

        else if (e.getSource() == next) {

            int selected = -1;

            for (int i = 0; i < 4; i++) {

                if (options[i].getState()) {

                    if (selected != -1) {
                        result.setText("Select only one answer!");
                        result.setVisible(true);
                        return;
                    }

                    selected = i;
                }
            }

            if (selected == -1) {
                result.setText("Please select an answer!");
                result.setVisible(true);
                return;
            }

            if (selected == answers[q]) {
                score++;
            }

            q++;

            if (q < questions.length) {
                result.setVisible(false);
                showQuestion();
            }

            else {

                question.setVisible(false);
                next.setVisible(false);

                for (int i = 0; i < 4; i++) {
                    options[i].setVisible(false);
                }

                result.setText("Hello " + name.getText()
                        + "! Score: " + score + "/"
                        + questions.length);

                result.setVisible(true);
            }
        }
    }

    void showQuestion() {

        question.setText((q + 1) + ". " + questions[q]);

        for (int i = 0; i < 4; i++) {
            options[i].setLabel(choices[q][i]);
            options[i].setState(false);
        }
    }

    public static void main(String[] args) {
        new QuizApplication();
    }
}