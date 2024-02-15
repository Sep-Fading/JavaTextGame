/* ************************************************************
  Author: Sepehr Shamloo
  Student ID: 210326709
  
  Date: 28/11/2022
  Version: 1.2
    - Fixed an error with reading files where splitting them using the "|" character
      Would result in splitting first 2 characters of the line due to | being a special regex argument.
      fixed by changing the split char to comma.
  ----------------------------------------------------------------------------------------------------------
    Previous Version's changes (1.1):
        - Fixed naming schemes for methods to better reflect their usage.
        - Created method for printing messages and taking inputs.
        - Swapped try-catch input validation with better alternative.
        - Added accessor methods for the records to meet criteria for Level 8.
        - Added comments for uncommented methods and ADTs.
  ----------------------------------------------------------------------------------------------------------
  This is a castle game where the user selects one of the three available
  professions and gives their character a name. The objective of the game is to 
  beat the three levels by fighting monsters and disarming traps inside the 
  castle. 
  Each level completed will reward the user with a more powerful item added
  to their inventory, and a score which will be finalised and stored once the
  game is over.

************************************************************ */

import java.util.Random; // Used to add random number generator.
import java.util.Scanner; // Used to enable Scanner for taking inputs.

import java.io.BufferedReader; // To make BufferedReader available for file read.
import java.io.FileReader; // To make file reader available
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException; // Handles IOException(s).


class CastleGame{
    
    // Main method
    public static void main(String[] args){
        Player player = Intro();
        player = playRoom(CreateLevel(1), player);
        player = playRoom(CreateLevel(2), player);
        player = playRoom(CreateLevel(3), player);
        endGame(player);
        return;
    }

    // Takes user keyboard inputs whenever called and returns the input.
    public static String Input(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    // Prints a string to the console.
    public static void printMsg(String msg){
        System.out.println(msg);
        return;
    }

    // Generates a random number between a given min and max.
    public static int generateRandom(int min, int max){
        Random random = new Random();
        return random.nextInt(min, max);
    }

    // Converts input to int if possible, if not, asks for input again.
    public static int InputToInt(){
        String input = Input();
        try {
            int inputInt = Integer.parseInt(input);
            return inputInt;
        } catch (NumberFormatException e) {
            printMsg("That is not a number, please try again:");
            return InputToInt();
        }
    }

    // Gets an int and converts it to string.
    public static String IntToString(int intval){
        return Integer.toString(intval);
    }

    // Checks if a string can be converted to integer, if so, returns true.
    public static boolean checkNumberFormatString(String stringval){
        try{
            int checkval = Integer.parseInt(stringval);
            return true;
        } catch(NumberFormatException e){
            printMsg("An error occurred while trying to convert player score to Int.");
            return false;
        }
    }

    /* ****** ADT ACCESSOR METHODS ****** */

    // Creates and sets the player name to a string passed into the method and returns the Player.
    public static Player creatPlayer(String name){
        Player player = new Player();
        player.name = name;
        return player;
    }

    // Gets the player name and returns it as string.
    public static String getPlayerName(Player player){
        return player.name;
    }

    // Sets the player profession to a profession that is passed into the method and returns the Player.
    public static Player setPlayerProfession(Player player, String profession){
        player.profession = profession;
        return player;
    }

    // Sets the player score.
    public static Player setPlayerScore(Player player, int score){
        player.score = score;
        return player;
    }

    // Sets the player's combat status.
    public static Player setPlayerCombatStatus(Player player, String combatStatus){
        player.combatStatus = combatStatus;
        return player;
    }

    // Sets the player's Health.
    public static Player setPlayerHealth(Player player, double health){
        player.health = health;
        return player;
    }
    
    // Sets the player's Trap Status
    public static Player setPlayerTrapStatus(Player player, boolean val){
        player.trapStatus = val;
        return player;
    }

    // Gets the player's trap status
    public static boolean getPlayerTrapStatus(Player player){
        return player.trapStatus;
    }

    // Gets the player profession and returns it as string.
    public static String getPlayerProfession(Player player){
        return player.profession;
    }

    // Gets the player's health.
    public static double getPlayerHealth(Player player){
        return player.health;
    }

    // Gets the player's combat status
    public static String getPlayerCombatStatus(Player player){
        return player.combatStatus;
    }

    // Gets the player's score.
    public static int getPlayerScore(Player player){
        return player.score;
    }

    // Gets player's chance to escape.
    public static int getPlayerEscapeChance(Player player){
        return player.chanceToEscape;
    }

    // Gets player's chance to escape.
    public static int getPlayerBlockChance(Player player){
        return player.chanceToBlock;
    }

    // Gets player's chance to escape.
    public static int getPlayerAttackChance(Player player){
        return player.chanceToAttack;
    }

    // Gets player's maximum possible damage.
    public static int getPlayerDamage(Player player){
        return player.damage;
    }

    // Creates a room and sets values for the total monsters, traps and the level number.
    public static Room createRoom(int level, int totalMonsters, int totalTraps){
        Room room = new Room();
        room.level = level;
        room.totalMonsters = totalMonsters;
        room.totalTraps = totalTraps;
        return room;
    }

    // Gets room level and returns it.
    public static int getRoomLevel(Room room){
        return room.level;
    }
    
    // Gets the total number of monsters in a room and returns it
    public static int getRoomMonsters(Room room){
        return room.totalMonsters;
    }

    // Gets the total number of traps in a room and returns it
    public static int getRoomTraps(Room room){
        return room.totalTraps;
    }

    // Changes the total number of monsters in a room and returns room.
    public static Room changeRoomMonsters(Room room, int newVal){
        room.totalMonsters = newVal;
        return room;
    }

    // Changes the total number of traps in a room and returns room.
    public static Room changeRoomTraps(Room room, int newVal){
        room.totalTraps = newVal;
        return room;
    }

    // Creates and sets attributes for a monster.
    public static Monster createMonster(String name, double health, int damage){
        Monster monster = new Monster();
        monster.name = name;
        monster.damage = damage;
        return monster;
    }

    // Gets monster name.
    public static String getMonsterName(Monster monster){
        return monster.name;
    }

    // Gets the damage of a monster.
    public static int getMonsterDamage(Monster monster){
        return monster.damage;
    }

    // Gets the monster's health.
    public static double getMonsterHealth(Monster monster){
        return monster.health;
    }

    // Creates and sets attributes to fields for the record Trap.
    public static Trap createTrap(String name, int damage){
        Trap trap = new Trap();
        trap.name = name;
        trap.damage = damage;
        return trap;
    }

    // Gets a given trap's name.
    public static String getTrapName(Trap trap){
        return trap.name;
    }

    // Gets a given trap's damage.
    public static int getTrapDamage(Trap trap){
        return trap.damage;
    }

    /* ****** END OF ADT ACCESSOR METHODS ****** */



    /* ****** THE GAME'S INTRODUCTION ****** */
    
    // Prints the information for each profession available
    public static void printProfInfo(){
        printMsg("\nNow, select one of the following professions:");
        printMsg("  Engineer - Takes less damage from traps.");
        printMsg("  Juggernaut - Has bonus health.");
        printMsg("  Medic - Heals at the end of each comabt round.\n");
        return;
    }

    // Checks to make sure the user has selected a valid profession and uses recursion until a valid profession is selected.
    // Sets player profession once valid using the right accessor method and returns player.
    public static Player validateProf(String[] professions, String selectedProf, Player player){
        int arrLength = professions.length;

        for(int i = 0; i < arrLength; i++){
            if(selectedProf.equalsIgnoreCase(professions[i])){
                player = setPlayerProfession(player, professions[i]);
                return player;
            }
        }

        printMsg(selectedProf + " is not a profession.");
        printProfInfo();

        return validateProf(professions, Input(), player);
    }

    // Gives the player their special ability depending on their selected profession.
    public static Player profDetails(Player player){

        if(getPlayerProfession(player).equals("Engineer")){
            printMsg("Mechanical Knowledge - You take 5 less damage from traps.");
        }
        else if (getPlayerProfession(player).equals("Juggernaut")){
            printMsg("Undying - You have 50 more health.");
            player = setPlayerHealth(player, getPlayerHealth(player)+50);
        }
        else if (getPlayerProfession(player).equals("Medic")){
            printMsg("Heal - gain 10 health at the end of each combat round.");
        }
        
        return player;
    }

    // Introduces the player to the game with a dialougue and lets the player pick a name and profession.
    public static Player Intro(){
        final String[] professions = {"Engineer", "Juggernaut", "Medic"};

        printMsg("Prisoner: \nWelcome to the Haunted Castle!\n\nEnter your name: ");
        Player player = creatPlayer(Input());

        printProfInfo();
        player = validateProf(professions, Input(), player);
        player = profDetails(player);
        printMsg("Weclome, " + getPlayerName(player) + ". " + "\nYou have selected to play as the " + getPlayerProfession(player) + ".");

        return player;
    }

    /* ****** END OF THE GAME'S INTRODUCTION ****** */


    /* ****** GAMEPLAY CODE ****** */

    // Prints a label for the level, might include bonus info if the player has a blueprint.
    public static void printRoomLabel(Room room, Boolean hasBlueprint){
        if(hasBlueprint){
            printMsg("===============");
            printMsg("Level " + getRoomLevel(room));
            printMsg("* You got lucky and found a blueprint for this level *");
            printMsg("\nBLUEPRINT BONUS INFO: ");
            printMsg("Total monsters in this room: " + getRoomMonsters(room));
            printMsg("Total traps in this room: " + getRoomTraps(room));
            printMsg("===============");
        }
        else{
            printMsg("===============");
            printMsg("Level " + getRoomLevel(room));
            printMsg("===============");
        }
        
        return;
    }

    // Rolls RNG dice to see if the player finds a level blueprint.
    public static Boolean hasBlueprint(){
        int diceRoll = generateRandom(0, 100);
        
        if (diceRoll >= 75){
            return true;
        }
        else{
            return false;
        }
    }

    // Creates a room, sets the level and generates the monsters and traps inside.
    public static Room CreateLevel(int level){
        int minEncounters = 0;
        int maxEncounters = 0;

        if(level==1){
            minEncounters = 2;
            maxEncounters = 4;
        }
        else if(level==2){
            minEncounters = 4;
            maxEncounters = 6;
        }
        else if(level==3){
            minEncounters = 6;
            maxEncounters = 8;
        }

        int totalEncounters = generateRandom(minEncounters, maxEncounters);
        Room room = createRoom(level, (totalEncounters/2), (totalEncounters-(totalEncounters/2)));
        return room;
    }

    // Creates a random monster from a predefined set of attributes.
    public static Monster randomMonster(int level){
        final String[] name = {"Ghost", "Orc", "Zombie", "Wizard"};
        double health = 100.0;
        int damage = 0;
        
        int randomPicker = generateRandom(0, (name.length-1));
        if(level==1){
            health = health * 0.75;
            damage = generateRandom(1, 2);
        }
        else if(level==2){
            damage = generateRandom(5, 8);
        }
        else if(level==3){
            health = health * 1.25;
            damage = generateRandom(8, 12);
        }

        Monster monster = createMonster(name[randomPicker], health, damage);
        return monster;
    }

    // Creates a random trap from a predefined set of attributes.
    public static Trap randomTrap(int level){
        String[] trapNames = {"Treasure Chest", "Guess Trap"};
        int damage = 0;
        int randomPicker = generateRandom(0, (trapNames.length));

        if(level == 1){
            damage = 5;
        }
        else if(level ==2 ){
            damage = 10;
        }
        else if( level == 3){
            damage = 15;
        }

        Trap trap = createTrap(trapNames[randomPicker], damage);
        return trap;
    }

    // Runs the guessing trap where the player has to guess the right number to disarm the trap.
    public static boolean runGuessTrap(Player player){
        int reward = 250;
        int correct = generateRandom(1, 4);
        printMsg("Guess the right number (1-4) to disarm the trap:");
        int user = InputToInt();

        if(correct == user){
            printMsg("You have guessed correctly, you earned "+ reward +" points!");
            return true;
        }
        else{
            return false;
        }
    }

    // Runs the trasure trap where the player has a 50-50 chance of getting extra points or take damage, (has the option to skip).
    public static boolean runTreasureTrap(Player player){
        final int bonus = 500;
        int dice = generateRandom(0, 2);
        printMsg("You see a treasure chest, would you like to (o)pen it (could be good or bad), or would you like to (s)kip?");
        String user = Input();
        if(user.equalsIgnoreCase("o")){
            if(dice==0){
                printMsg("Failed to open the chest, you can try again.");
                return false;
            }
            else{
                printMsg("You opened the chest and received " + bonus +  " bonus points");
                return true;
            }
        }
        else if(user.equalsIgnoreCase("s")){
            printMsg("You chose to skip the treasure chest.");
            return true;
        }
        else{
            printMsg("Invalid Input, please choose from the following inputs:\nO : Open\nS: Skip");
            return runTreasureTrap(player);
        }
    }

    // Starts a trap encounter.
    public static Player startTrap(Player player, int level, Trap trap){
        String trapType = getTrapName(trap);

        if(trapType.equals("Guess Trap")){
            if(runGuessTrap(player)==true){
                player = setPlayerTrapStatus(player, true);
            }
            else{
                player = setPlayerTrapStatus(player, false);
            }
        }
        else if(trapType.equals("Treasure Chest")){
            if(runTreasureTrap(player)==true){
                player = setPlayerTrapStatus(player, true);
            }
            else{
                player = setPlayerTrapStatus(player, false);
            }
        }
        else{
            player = setPlayerTrapStatus(player, false);
        }

        return player;
    }
    
    // Do damage to the player depending on the level of the room.
    public static Player doTrapDamage(Player player, int damage){
        if(getPlayerProfession(player).equals("Engineer")){
            player = setPlayerHealth(player, (getPlayerHealth(player)-(damage-5)));
        }
        else{
            player = setPlayerHealth(player, (getPlayerHealth(player)-damage));
        }
        return player;
    }

    // Player's options during combat in their turn
    public static Player playerTurn(Player player, Monster monster){
        int dice = generateRandom(0, 100);
        String monsterName = getMonsterName(monster);
        String user = Input();

        if(user.equalsIgnoreCase("E")){
            if(dice <= getPlayerEscapeChance(player)){
                printMsg("You have managed to escape the " + monsterName);
                player = setPlayerCombatStatus(player, "escaped");
            }
            else{
                printMsg("Failed to escape, continuing combat.");
                player = setPlayerCombatStatus(player, "In combat");
            }
        }
        else if(user.equalsIgnoreCase("B")){
            if(dice <= getPlayerBlockChance(player)){
                printMsg("You have blocked the monster's daamge.");
                player = setPlayerCombatStatus(player, "Blocked");
            }
            else{
                printMsg("Failed to block.");
                player = setPlayerCombatStatus(player, "In combat");
            }
        }
        else if(user.equalsIgnoreCase("A")){
            if(dice <= getPlayerAttackChance(player)){
                printMsg("You defeat the monster.");
                player = setPlayerCombatStatus(player, "won");
            }
            else {
                printMsg("Your attempt to kill the monster failed.");
                player = setPlayerCombatStatus(player, "In combat");
            }
        }
        
        return player;
    }

    // Monster's turn to attack during combat
    public static Player monsterTurn(Monster monster, Player player){
        if(getPlayerHealth(player) <= 0){
            player = setPlayerCombatStatus(player, "died");
        }
        else{
            printMsg("The monster dealt " + getMonsterDamage(monster) + " damage to you!");
            player = setPlayerHealth(player, (getPlayerHealth(player)-getMonsterDamage(monster)));
        }
        return player;
    }
    
    // Initiates a round of combat encounter with a player and a monster.
    public static Player startCombat(Player player, Monster monster){
        String monsterName = getMonsterName(monster);

        printMsg("You encounter a "+monsterName+". The " + monsterName + " starts combat with you!");
        printMsg("Your health: " + getPlayerHealth(player));
        printMsg("What would you like to do?");
        printMsg("(E)scape - 25% Success rate - Escape the monster, avoiding combat and gaining 50% of the points.\n(B)lock - 50% Success rate - Block the next attack completely.\n(A)ttack - 20% Success rate - Kill the monster in a powerful attack");

        player = playerTurn(player, monster);
        printMsg("COMBAT STATUS IS " + getPlayerCombatStatus(player));
        
        if(getPlayerCombatStatus(player).equals("In combat")){
            player = monsterTurn(monster, player);
        }

        if(getPlayerProfession(player).equals("Healer")){
            if(getPlayerHealth(player) == 100.0){
                printMsg("You healed but you were already at full health.");
            }
            else if(getPlayerHealth(player) >= 95){
                player = setPlayerHealth(player, getPlayerHealth(player)+(100-(getPlayerHealth(player))));
            }
            else{
                player = setPlayerHealth(player, getPlayerHealth(player)+5);
            }
        }
        return player;
    }

    // Calculates player score and returns it.
    public static int CalcScore(int trapsDisarmed, int monsterEscaped, int monsterKilled){
        final int baseKillReward = 200;
        final int baseTrapReward = 350;
        final int baseEscapeReward = 100;
        int score = (trapsDisarmed*baseTrapReward) + (monsterEscaped*baseEscapeReward) + (monsterKilled*baseKillReward);
        return score;
    }

    // Starts/Runs a room and allows the player to play in it.
    public static Player playRoom(Room room, Player player){
        int trapTries = 3;
        printRoomLabel(room, hasBlueprint());
        int totalTraps = getRoomTraps(room);
        int totalMonsters = getRoomMonsters(room);
        int level = getRoomLevel(room);
        int score = getPlayerScore(player);
        int monsterEscaped = 0;
        int monsterKilled = 0;
        int trapsDisarmed = 0;
        boolean isDisarmed = false;
        boolean combatFlag = true;
        final int totalEncounters = totalMonsters + totalTraps;
        
        
        for(int i = 1; i <= (totalEncounters/2); i++){
            Monster monster = randomMonster(level);
            Trap trap = randomTrap(level);
            isDisarmed = false;

            while((trapTries > 0) && (isDisarmed==false)){
                player = startTrap(player, level, trap);
                isDisarmed = getPlayerTrapStatus(player);
                if(isDisarmed){
                    printMsg("The trap was disarmed!");
                    trapsDisarmed =+1;
                }
                else{
                    if (getPlayerHealth(player) <= 0){
                        printMsg("Disarming the trap was unsucessful... YOU DIED!");
                        player = setPlayerScore(player, score);
                        endGame(player);
                    }
                    else{
                        printMsg("Disarming the trap was unsucessful, " + trapTries + " tries left.");
                        player = doTrapDamage(player, getTrapDamage(trap));
                        trapTries = trapTries - 1;
                    }
                }
            }

            combatFlag = true;
            while(combatFlag){

                player = startCombat(player, monster);
                if (getPlayerHealth(player) <= 0){
                    player = setPlayerScore(player, score);
                    endGame(player);
                }
                else if(getPlayerCombatStatus(player).equals("escaped")){
                    printMsg("You successfully escaped the monster");
                    monsterEscaped =+1;
                    combatFlag = false;
                }
                else if(getPlayerCombatStatus(player).equals("won")){
                    printMsg("You defeated the monster");
                    monsterKilled =+1;
                    combatFlag = false;
                }
            }

            
        }

        player = setPlayerScore(player, getPlayerScore(player)+CalcScore(trapsDisarmed, monsterEscaped, monsterKilled));
        printMsg("Your score: " + getPlayerScore(player));
        return player;
    }

    /* ****** END OF GAMEPLAY CODE ****** */

    /* ****** CODE TO END THE GAME AND SAVE NAME, SCORE, PROFESSION (FILE HANDILING) ****** */

    // Saves the user's name and score by writting to a file.
    public static void SaveToFile(String[] nameScore){
        try (FileWriter dataSaver = new FileWriter("/Users/sepehrshamloo/Desktop/University/Java-Programs/miniproject/GameScores.txt", true)) {
            dataSaver.write("\n" + nameScore[0] + "," + nameScore[1]);
            dataSaver.close();
            printMsg("Data is saved successfully!");
        } catch (IOException e) {
            printMsg("An error has occured trying to write to file: ");
            e.printStackTrace();
        }
    }
    
    // Loads the save file and finds the highest scorer and returns it as an array
    public static String[] findHighestScorer(){
        BufferedReader fileReader;
        int highest = 0;
        String highestName = "";
        try{
            fileReader =  new BufferedReader(new FileReader("/Users/sepehrshamloo/Desktop/University/Java-Programs/miniproject/GameScores.txt"));
            String line = fileReader.readLine();
            while (line != null){
                String[] StrArray = line.split(",");
                if(checkNumberFormatString(StrArray[1])){
                    int temp = Integer.parseInt(StrArray[1]);
                    if(temp > highest){
                        highest = temp;
                        highestName = StrArray[0];
                    }
                    line = fileReader.readLine();
                }
                else{
                    line = fileReader.readLine();
                }
            }
            fileReader.close();
            String[] highestArray = {highestName, IntToString(highest)};
            return highestArray;
        }
        catch (IOException e){
            printMsg("An error has occured trying to load from save file:");
            e.printStackTrace();
            return null;
        }
    }
    // Ends the game displaying user's final results and showing the high score from all games played.
    public static void endGame(Player player){
        String playerName = getPlayerName(player);
        int playerScore = getPlayerScore(player);
        String[] nameScore = {playerName, IntToString(playerScore)};
        String[] highestScorer = findHighestScorer();

        printMsg("The game has ended.");
        printMsg("Your final score: " + getPlayerScore(player));
        printMsg("Highest score was achieved by " + highestScorer[0] + " with a final score of " + highestScorer[1]);
        SaveToFile(nameScore);

        printMsg("Thank you for playing.");
        return;
    }

    /* ****** END OF CODE TO END THE GAME AND SAVE NAME, SCORE, PROFESSION (FILE HANDILING) ****** */

}

/* ************************************************************

  ADT for holding information about the current player.

  - The player's name (String name)
  - The player's chosen profession (String profession)
  - The player's chance to escape comabt (int chanceToEscape)
  - The player's chance to block incoming damage (int chanceToBlock)
  - The player's chance to successfully attack a target.
  - The player's progress on disarming the current trap (boolean trapStatus)

  - The player's current health starting at 100.0 (double health)
  - The player's current armour value starting at 0.0 (double armour)

  - The player's current level (based on the room) in the game starting at 0 (int level)
  - The player's current score in the game starting 0 (int score)

  - The player's combat status (dead, won, escaped etc..) as (String combatStatus)

 *********************************************************** */
class Player{
    String name;
    String profession;
    int chanceToEscape = 25;
    int chanceToBlock = 50;
    int chanceToAttack = 20;
    boolean trapStatus;
    
    double health = 100.0;
    int damage = 12;
    
    int level = 0;
    int score = 0;

    String combatStatus = "not in combat";
}

/* ************************************************************
  
  ADT for holding information about a monster.

  - The monster's name (String name)

  - The monster's health starting at 100.0 (double health)
  - The monster's damage per attack default 0 (int damage)
  
 ************************************************************ */
class Monster{
    String name;

    double health = 100.0;
    int damage = 0;
}

/* ************************************************************
  
  ADT for creating a trap.

  - The trap name (String name)
  - The trap damage dealt per failed disarm (int damage)
  
 ************************************************************ */
class Trap{
    String name;
    int damage;
}

/* ************************************************************
  
  ADT for holding information about a Room.

  - The room number (representing the level) as (int level)
  - The total number of monsters in the room (int totalMonsters)
  - The total number of traps in the room (int totalTraps)

 ************************************************************ */
class Room{
    int level = 0;
    int totalMonsters = 0;
    int totalTraps = 0;
}
