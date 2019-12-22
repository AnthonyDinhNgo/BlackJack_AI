package players;

import deck.Card;
import deck.Deck;
import deck.Hand;

import java.util.Scanner;

public class Human extends GenericPlayer {

    public Human(){
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
    public int getBet(Deck deck) {
        Scanner console = new Scanner(System.in);
        System.out.println("How much would you like to bet?\nYou have $"+ getBalance());
        String input = console.nextLine();
        while (!input.matches("[0-9]+") || Integer.parseInt(input) < 0 || Integer.parseInt(input) > getBalance()) {
            System.out.println("Bet must be between an integer 0 and "+ getBalance());
            input = console.nextLine();
        }
        return Integer.parseInt(input);
    }

    @Override
    public String getFirstMove(Card dealerCard) {
        Scanner console = new Scanner(System.in);
        System.out.println("Would you like to SURRENDER, DOUBLE, or NEITHER?");
        String input = console.nextLine();
        while (!(input.equalsIgnoreCase("SURRENDER") ||input.equalsIgnoreCase("DOUBLE") ||
                input.equalsIgnoreCase("NEITHER"))) {
            System.out.println("Error: Invalid Input; enter SURRENDER or DOUBLE or NEITHER");
            input = console.nextLine();
        }
        return input;
    }

    @Override
    public boolean getSplit(Hand hand,  Card dealerCard) {
        Scanner console = new Scanner(System.in);
        System.out.println("Would you like to split the hand " + hand.toString());
        String input = console.nextLine();
        while (!(input.equalsIgnoreCase("YES") || input.equalsIgnoreCase("NO"))) {
            System.out.println("Error: Invalid Input; enter YES or NO");
            input = console.nextLine();
        }
        return input.equalsIgnoreCase("YES");
    }

    @Override
    public boolean getAction(Hand h,  Card dealerCard) {
        System.out.println(name + " has " + h.toString());
        System.out.println(name + " has " + h.value());
        Scanner console = new Scanner(System.in);
        System.out.println("Would you like to HIT or STAY?");
        String input = console.nextLine();
        while (!(input.equalsIgnoreCase("hit") || input.equalsIgnoreCase("stay"))) {
            System.out.println("Error: Invalid Input; enter HIT or STAY");
            input = console.nextLine();
        }
        return input.equalsIgnoreCase("hit");
    }
}
