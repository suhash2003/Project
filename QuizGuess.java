package com.project;
import java.util.*;

class QuizQuestion {
    private String question;
    private List<String> options;
    private int correctAnswerIndex;

    public QuizQuestion(String question, List<String> options, int correctAnswerIndex) {
        this.question = question;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public boolean isCorrectAnswer(int userAnswerIndex) {
        return userAnswerIndex == correctAnswerIndex;
    }
}

class Quiz {
    private List<QuizQuestion> questions;
    private int score;
    private int totalQuestions;
    private Scanner scanner;

    public Quiz(List<QuizQuestion> questions) {
        this.questions = questions;
        this.totalQuestions = questions.size();
        this.score = 0;
        this.scanner = new Scanner(System.in);
    }

    public void startQuiz() {
        for (QuizQuestion question : questions) {
            displayQuestion(question);
            int userAnswerIndex = getUserAnswer();
            if (question.isCorrectAnswer(userAnswerIndex)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect!");
            }
            System.out.println();
        }
        displayResult();
        scanner.close();
    }

    private void displayQuestion(QuizQuestion question) {
        System.out.println(question.getQuestion());
        List<String> options = question.getOptions();
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }

    private int getUserAnswer() {
        System.out.print("Your answer (enter option number): ");
        return scanner.nextInt() - 1;
    }

    private void displayResult() {
        System.out.println("Quiz Over!");
        System.out.println("Your Score: " + score + "/" + totalQuestions);
    }
}

public class QuizGuess {
    public static void main(String[] args) {
        List<QuizQuestion> questions = new ArrayList<>();
        // Add quiz questions
        questions.add(new QuizQuestion("What is the capital of France?",
                Arrays.asList("London", "Paris", "Berlin", "Madrid"), 1));
        questions.add(new QuizQuestion("What is the tallest mountain in the world?",
                Arrays.asList("Mount Everest", "K2", "Kangchenjunga", "Lhotse"), 0));
        questions.add(new QuizQuestion("Who wrote 'Romeo and Juliet'?",
                Arrays.asList("William Shakespeare", "Charles Dickens", "Jane Austen", "Mark Twain"), 0));
        questions.add(new QuizQuestion("What is the chemical symbol for water?",
                Arrays.asList("H2O", "CO2", "O2", "N2"), 0));

        Quiz quiz = new Quiz(questions);
        quiz.startQuiz();
    }
}
