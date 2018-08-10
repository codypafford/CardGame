import java.util.Random;
import java.util.Scanner;


public class deck {
    private static int numWon = 0;
    private static int numLost = 0;
    private static int roundNum = 1;
    static boolean inAction = true;


    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        System.out.println("Let's play a game of War ");

        int deck[] = new int[52];

        StackX st1 = new StackX(26); // Player 1 original Deck
        StackX st2 = new StackX(26); // Player 2 original deck

        StackX winStash1 = new StackX(52); // Player 1 winning stash
        StackX winStash2 = new StackX(52); // Player 2 winning stash

        deck[0] = 2; // 0-12 = hearts
        deck[1] = 3;
        deck[2] = 4;
        deck[3] = 5;
        deck[4] = 6;
        deck[5] = 7;
        deck[6] = 8;
        deck[7] = 9;
        deck[8] = 10;
        deck[9] = 11; // jack of hearts
        deck[10] = 12; // queen of hearts
        deck[11] = 13; // king of hearts
        deck[12] = 14; // ace of hearts

        deck[13] = 2; // 13-25 = spades
        deck[14] = 3;
        deck[15] = 4;
        deck[16] = 5;
        deck[17] = 6;
        deck[18] = 7;
        deck[19] = 8;
        deck[20] = 9;
        deck[21] = 10;
        deck[22] = 11; // jack of spades
        deck[23] = 12; // queen of spades
        deck[24] = 13; // king of spades
        deck[25] = 14; // ace of spades

        deck[26] = 2; // 0-12 = diamonds
        deck[27] = 3;
        deck[28] = 4;
        deck[29] = 5;
        deck[30] = 6;
        deck[31] = 7;
        deck[32] = 8;
        deck[33] = 9;
        deck[34] = 10;
        deck[35] = 11; // jack of diamonds
        deck[36] = 12; // queen of diamonds
        deck[37] = 13; // king of diamonds
        deck[38] = 14; // ace of diamonds

        deck[39] = 2; // 39-51 = clubs
        deck[40] = 3;
        deck[41] = 4;
        deck[42] = 5;
        deck[43] = 6;
        deck[44] = 7;
        deck[45] = 8;
        deck[46] = 9;
        deck[47] = 10;
        deck[48] = 11; // jack of clubs
        deck[49] = 12; // queen of clubs
        deck[50] = 13; // king of clubs
        deck[51] = 14; // ace of clubs

        for (int i = 0; i < deck.length - 1; i = i + 2) { // Deals the cards
            st1.push(deck[i]);
            st2.push(deck[i + 1]);

        }
        //st1.print(st1);
        st1.shuffle(st1);
        //st1.print(st1);
        st2.shuffle(st2);

        // System.out.print(st1.isFull());
        // System.out.print(st2.isFull());

        // where the action happens --------------------------------------------
        while (inAction) {
            if (roundNum == 1) {
                System.out.println("Time for round:  " + roundNum);
            }
            System.out.println("Hit 1 to continue, 0 to shuffle your deck, or 5 to view your current deck!");
            int x = input.nextInt();


            if (x == 0) {
                st1.shuffle(st1);
                //st1.print(st1);
                System.out.println("Your deck has been shuffled");
                System.out.println("----------------------------------------");
            } else if(x == 1) {

                int player1 = (int) st1.pop();
                int player2 = (int) st2.pop();
                if(player1>player2) {
                    numWon++;
                }
                splash(numWon, player1, player2, roundNum);

                if (player1 > player2) {
                    //System.out.println("You drew " + player1 + "....");
                    //System.out.println("...While the computer drew " + player2);
                    System.out.println("----------------------------------------");
                    System.out.println("You won this round!!");
                    winStash1.push(player1);
                    winStash1.push(player2);
                    //winStash1.print(winStash1);

                } else if (player1 < player2) {
                    //System.out.println("You drew " + player1 + "....");
                    //System.out.println("...While the computer drew " + player2);
                    System.out.println("----------------------------------------");
                    System.out.println("You lost this round :(");
                    winStash2.push(player1);
                    winStash2.push(player2);
                    numLost++;

                } else if(!st1.isEmpty() && !st2.isEmpty()) {
                    System.out.println("It's time for war...");
                    System.out.println("..Because you both drew " + player1);
                    int b1 = (int) st1.pop();
                    int b2 = (int) st2.pop();
                    System.out.println("Hit 1 to settle the battle: ");
                    int y = input.nextInt();
                    if (y == 1) {
                        if (b1 > b2) {
                            winStash1.push(player1);
                            winStash1.push(player2);
                            splash(numWon, b1, b2, roundNum);

                            System.out.println("Congrats! You won the battle!");
                        } else if (b1 < b2) {
                            winStash2.push(player1);
                            winStash2.push(player2);
                            System.out.println("Sorry! You lost the battle!");
                        }
                    }
                }

            }else if (x == 5) {
                st1.print(st1);
            }
            roundNum++;
            System.out.println("It is time for round  " + roundNum);
            if(st1.isEmpty()) {
                System.out.println("You are out not holding any more cards, time to pick up your stash!");
                pickUpStash(winStash1, st1);
            }
            if(st2.isEmpty()) {
                pickUpStash(winStash2, st2);
            }

        }
        System.out.print("you won " + numWon + " times");
        System.out.println("Your final deck is ");
        st1.print(st1);
        System.out.print("The opponents final deck is ");
        st2.print(st2);
    }

    public static void pickUpStash(StackX winStash, StackX st1) {
        int i = 0;
        if(winStash.isEmpty()) {
            inAction = false;
        }else {
            while(!winStash.isEmpty() && i < 26) {
                int x = (int) winStash.pop();
                st1.push(x);
                i++;
            }
        }
    }


    public static void splash(int numWon, int myCard, int compCard, int roundNum ){
        System.out.println("|------------------------------------------------------------------|");
        System.out.println("|                      |                                           |");
        System.out.println("| Your card: "+myCard+"         |   Round: "+roundNum+"                                |");
        System.out.println("|                      |                                           |");
        System.out.println("|                      |                                           |");
        System.out.println("|                      |                                           |");
        System.out.println("|                      |                                           |");
        System.out.println("|                      |                                           |");
        System.out.println("| Opponents card: "+compCard+"    |                                           |");
        System.out.println("|                      |                                           |");
        System.out.println("|                      |                                           |");
        System.out.println("|______________________|                                           |");
        System.out.println("|                      |                                           |");
        System.out.println("|   Games won: "+numWon+"       |                                           |");
        System.out.println("|                      |                                           |");
        System.out.println("|------------------------------------------------------------------|");
    }

}

// --------------------------------------------------------------------------------------------------------
class StackX {
    private int maxSize; // size of stack array
    private long[] stackArray;
    private int top; // top of stack
    // --------------------------------------------------------------

    public StackX(int s) // constructor
    {
        maxSize = s; // set array size
        stackArray = new long[maxSize]; // create array
        top = -1; // no items yet
    }

    // --------------------------------------------------------------
    public void push(long j) // put item on top of stack
    {
        stackArray[++top] = j; // increment top, insert item
    }

    // --------------------------------------------------------------
    public long pop() // take item from top of stack
    {
        return stackArray[top--]; // access item, decrement top
    }

    // --------------------------------------------------------------
    public long peek() // peek at top of stack
    {
        return stackArray[top];
    }

    // --------------------------------------------------------------
    public boolean isEmpty() // true if stack is empty
    {
        return (top == -1);
    }

    // --------------------------------------------------------------
    public boolean isFull() // true if stack is full
    {
        return (top == maxSize - 1);
    }

    // --------------------------------------------------------------
    public void print(StackX s) {
        for (int i = top; i >= 0; i--)
            System.out.println(stackArray[i]);
    }
    // ---------------------------------------------------------------------------------------

    public void shuffle(StackX deck) {
        Random rangen = new Random(); // Random number generator

        for (int i = 0; i < stackArray.length; i++) { // shuffles the deck
            int randomPosition = rangen.nextInt(stackArray.length);
            int temp = (int) stackArray[i];
            stackArray[i] = stackArray[randomPosition];
            stackArray[randomPosition] = temp;
        }

    }
}
