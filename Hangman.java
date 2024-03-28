package Hangman;
import java.util.Arrays;
import java.util.Scanner;
public class Hangman {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Hangman hangman = new Hangman();
        System.out.println("Welcome Hangman! ~uwu~\nEnter a word for player to guess: ");
        String word = sc.nextLine();
        System.out.println("Enter Category of the word: ");
        String category = sc.nextLine();
        hangman.Play(word, category);

        sc.close();
    }

    void Play(String word, String category){
        System.out.println("Hello Player ! ");
        System.out.println("Your Category is: " + category);
        Scanner sc = new Scanner(System.in);
        char[] temp = new char[word.length()];

        underscores(temp);

        int count = 0;
        String origWord = word;

        while (true) {
            System.out.print("\f");
            System.out.println("Enter your guess: ");
            char letter = sc.next().charAt(0);
            System.out.println(letter);
            boolean flag = false;

            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == letter) {
                    flag = true;
                    temp[i] = word.charAt(i);
                    String tempString = new String(temp);
                    System.out.println("Correct Guess. New Word: " + tempString);
                    word = word.substring(0, i) + '6' + word.substring(i + 1);
                    break;
                }
            }
            String finalWord = new String(temp);
            if (!flag) {
                System.out.println("Wrong Guess");
                count++;
                Draw(count);
            }

            if (finalWord.equals(origWord)) {
                System.out.println("You Won!");
                break;
            }
            if(count == 6){
                System.out.println("Game Lost. Try again.");
                break;
            }
        }
        System.out.println("Thank you for playing Hangman");
    }
    static char[] underscores(char[] arr){
	    Arrays.fill(arr, '_');
        return arr;
    }

    static void Draw(int stage){
        switch(stage){
            case 1: System.out.printf("%80s%n","************"); break;
            case 2: System.out.printf("%78s%n", "******");
                    System.out.printf("%80s%n","************");
                    break;
            case 3: System.out.printf("%76s%n", "*");
                    System.out.printf("%76s%n", "*");
                    System.out.printf("%76s%n", "*");
                    System.out.printf("%76s%n", "*");
                    System.out.printf("%76s%n", "*");
                    System.out.printf("%76s%n", "*");
                    System.out.printf("%78s%n", "******");
                    System.out.printf("%80s%n","************");
                    break;
            case 4:
                System.out.printf("%76s%n", "*********");
                System.out.printf("%76s%n", "*");
                System.out.printf("%76s%n", "*");
                System.out.printf("%76s%n", "*");
                System.out.printf("%76s%n", "*");
                System.out.printf("%76s%n", "*");
                System.out.printf("%78s%n", "******");
                System.out.printf("%80s%n","************");
                break;

            case 5:
                System.out.printf("%76s%n", "*********");
                System.out.printf("%76s%n", "*       *");
                System.out.printf("%76s%n", "*       *");
                System.out.printf("%76s%n", "*");
                System.out.printf("%76s%n", "*");
                System.out.printf("%76s%n", "*");
                System.out.printf("%78s%n", "******");
                System.out.printf("%80s%n","************");
                break;

            case 6:
                System.out.printf("%76s%n", "*********");
                System.out.printf("%76s%n", "*       *");
                System.out.printf("%76s%n", "*       *");
                System.out.printf("%76s%n", "X       *");
                System.out.printf("%76s%n", "*");
                System.out.printf("%76s%n", "*");
                System.out.printf("%78s%n", "******");
                System.out.printf("%80s%n","************");
                break;
        }
    }

}

