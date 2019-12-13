package players;

import java.util.Scanner;

public class HumanPlayer extends GenericPlayer {

    public HumanPlayer(){
        Scanner console = new Scanner(System.in);
        System.out.println("What is your name?");
        String input = console.nextLine();
        while (input.length()>10) {
            System.out.println("Keep your name under 10 characters");
            input = console.nextLine();
        }
        name = input;
    }

    @Override
    public boolean getAction() {
        Scanner console = new Scanner(System.in);
        System.out.println("Would you like to HIT or STAY?");
        String input = console.nextLine();
        while (!(input.equalsIgnoreCase("hit") || input.equalsIgnoreCase("stay"))) {
            System.out.println("Error: Invalid Input; enter HIT or STAY");
            input = console.nextLine();
        }
        return input.equalsIgnoreCase("hit");
    }

    @Override
    public int getBet() {
        Scanner console = new Scanner(System.in);
        System.out.println("How much would you like to bet?\nYou have $"+ getBalance());
        String input = console.nextLine();
        while (!input.matches("[0-9]+") || Integer.parseInt(input) < 0 || Integer.parseInt(input) > getBalance()) {
            System.out.println("Bet must be between an integer 0 and "+ getBalance());
            input = console.nextLine();
        }
        return Integer.parseInt(input);
    }
}
