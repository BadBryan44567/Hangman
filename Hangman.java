package Hangman;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
public class Hangman {
    public static void main(String[] args) {
        System.out.println("\u001B[2J");
        Scanner sc = new Scanner(System.in);
        Hangman hangman = new Hangman();
        Random rand = new Random();
        System.out.printf("%70s%n", colors.BOLD_BLUE + "Welcome To Hangman! ~uwu~" + colors.RESET);
        System.out.println("Here are the Categories: \n  1. Chemistry\n  2. Computers\nInput the category number: ");
        int category = sc.nextInt();
        String topic = switch(category){
            case 1 -> "chemistry";
            case 2 -> "computers";
            default -> throw new IllegalStateException(colors.BOLD_RED+ "Unexpected value: " + colors.RESET + category);
        };

        String[] words = new String[0];
        try{
            words = Dictionary(category);
        }catch (FileNotFoundException e){
            System.out.println(colors.BOLD_RED + "An error occurred" + colors.RESET);
            e.printStackTrace();
        }

        String word = words[rand.nextInt(99)].toLowerCase();
        System.out.println(word);

        hangman.Play(word, topic);
        sc.close();
    }

    void Play(String word, String category){
        System.out.println("\u001B[2J");
        System.out.printf("%70s%n", colors.BOLD_BLUE + "Hello Player ! " + colors.RESET);
        System.out.println("Your Category is: " + category);
        System.out.println("Your Word is: " + word.length() + " letters long");
        Scanner sc = new Scanner(System.in);
        char[] temp = new char[word.length()];

        underscores(temp);

        int count = 0;
        String origWord = word;

        while (true) {
            System.out.print("\f");
            System.out.println("Enter your guess ( in lower case ONLY ): ");
            char letter = sc.next().charAt(0);
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
                System.out.println(colors.RED_BACKGROUND + colors.BOLD_WHITE + "Game Lost. Try again. The word was : " + origWord + colors.RESET);
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

    static String[] Dictionary(int category) throws FileNotFoundException {
        File dict = switch (category) {
	        case 1 -> new File("chemistry.txt");
	        case 2 -> new File("computers.txt");
	        default -> throw new IllegalStateException(colors.BOLD_RED+ "Unexpected value: " + colors.RESET + category);
        };

	    Scanner reader  = new Scanner(dict);
        int count = 0;
        while(reader.hasNextLine()){
            String tempData  = reader.nextLine();
            count++;
        }
        reader.close();
        String[] words = new String[count];
        Scanner newReader = new Scanner(dict);
        for(int i = 0; i < count; i++ ){
            words[i] = newReader.nextLine();
        }

        newReader.close();
        return words;
    }

}

