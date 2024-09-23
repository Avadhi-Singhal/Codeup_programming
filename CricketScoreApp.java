/***
 * Purpose: A Cricket Score Application to manage and score cricket matches.
 * 
 * Features:
 *    Team and player management.
 *    Striker and non-striker selection. 
 *    Over and ball tracking, including runs, extras, and wickets.
 *    Bowler management with limits on overs per bowler.
 *    Handling special scenarios like run-outs and extras (no-balls, wides).
 *    Match summary display with final scores and winner determination.
 *    
 * User Interaction: Menu-driven interface for seamless navigation and input.
 * 
 * Owner: Avadhi-Singhal
 * 
 * Date of Creation: 21-09-2024
 */
import java.util.Scanner;

public class CricketScoreApp {
	
	static Constant constant = new Constant();
	
    static int teamIterator = 0;
    static String[] team = new String[100];
    static String[] player = new String[10000];
    static int[] playerCounts = new int[100];
    static int[] teamPlayerOffsets = new int[100];
    static String[] playingElevenTeam1 = new String[11];
    static String[] playingElevenTeam2 = new String[11];
    static int teamOneIndex = -1;
    static int teamTwoIndex = -1;
    static int[] teamScores = new int[100];
    static int[] teamWickets = new int[11];
    static int[] overs = new int[100];
    static int[] balls = new int[100];
    static int currentBowlerIndex = -1;
    static int battingTeamIndex = -1;
    static int bowlingTeamIndex = -1;
    static boolean[] playerOutStatus = new boolean[11]; 
    static int over;

    // This method compares two strings
    public static boolean equalsIgnoreCase(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return false;
        }
        String lowerStr1 = str1.toLowerCase();
        String lowerStr2 = str2.toLowerCase();
        return lowerStr1.equals(lowerStr2);
    }

    //This method check every player is unique
    public static boolean isPlayerUnique(String playerName) {
        for (int i = 0; i < teamIterator; i++) {
            for (int j = teamPlayerOffsets[i]; j < teamPlayerOffsets[i] + playerCounts[i]; j++) {
                if (equalsIgnoreCase(player[j], playerName)) {
                    return false;
                }
            }
        }
        return true;
    }

    //This method checks whether player already exists in team or not
    public static boolean isPlayerInTeam(String playerName, int teamIndex) {
        for (int i = teamPlayerOffsets[teamIndex]; i < teamPlayerOffsets[teamIndex] + playerCounts[teamIndex]; i++) {
            if (equalsIgnoreCase(player[i], playerName)) {
                return true;
            }
        }
        return false;
    }

    //This method ensures each team is unique
    public static boolean isTeamNameUnique(String teamName) {
        for (int i = 0; i < teamIterator; i++) {
            if (equalsIgnoreCase(team[i], teamName)) {
                return false;
            }
        }
        return true;
    }

    // This method adds player to the team
    public static void addPlayer(Scanner scanner) {
        if (teamIterator >= team.length) {
            System.out.println(constant.MAXIMUM_TEAM);
            return;
        }
        System.out.println(constant.TEAM_NAME);
        String teamName = scanner.nextLine();
        if (!isTeamNameUnique(teamName)) {
            System.out.println(constant.TEAMNAME_EXISTS);
            return;
        }
        teamPlayerOffsets[teamIterator] = (teamIterator == 0) ? 0 : teamPlayerOffsets[teamIterator - 1] + playerCounts[teamIterator - 1];
        playerCounts[teamIterator] = 0;
        
        for (int i = 0; i < 11; i++) {
            System.out.println(constant.PLAYER_NAME); 
            String playerName = scanner.nextLine();
                if (!isPlayerUnique(playerName)) {
                System.out.println(constant.PLAYER_EXIST);
                i--; 
            } else {
                if (isPlayerInTeam(playerName, teamIterator)) {
                    System.out.println(constant.PROMPT_UNIQUE_PLAYER);
                    i--; 
                } else {
                    player[teamPlayerOffsets[teamIterator] + playerCounts[teamIterator]] = playerName;
                    playerCounts[teamIterator]++;
                    System.out.println(constant.PLAYER + playerName + constant.PLAYER_ADDED); 
                }
            }
        }
        while (true) {
            System.out.println(constant.ADD_PLAYER_PROMPT); 
            String input = scanner.nextLine();
            if (equalsIgnoreCase(input, "exit")) {
                if (playerCounts[teamIterator] < 11) {
                    System.out.println(constant.MIN_TEAMSIZE); 
                } else {
                    team[teamIterator] = teamName;
                    teamIterator++;
                    System.out.println(constant.TEAM_CREATED + teamName);
                    break; 
                }
            } else if (equalsIgnoreCase(input, "y")) {
                if (playerCounts[teamIterator] < 50) { 
                    System.out.println(constant.PLAYER_NAME); 
                    String playerName = scanner.nextLine();

                    if (!isPlayerUnique(playerName)) {
                        System.out.println(constant.PLAYER_EXIST); 
                    } else {
                        if (isPlayerInTeam(playerName, teamIterator)) {
                            System.out.println(constant.PROMPT_UNIQUE_PLAYER);
                        } else {
                            player[teamPlayerOffsets[teamIterator] + playerCounts[teamIterator]] = playerName;
                            playerCounts[teamIterator]++;
                            System.out.println(constant.PLAYER + playerName + constant.PLAYER_ADDED); 
                        }
                    }
                } else {
                    System.out.println(constant.MAX_TEAMSIZE); 
                }
            } else {
                System.out.println(constant.PROMPT_VALID_INPUT); 
            }
        }
    }

    //This method show all the teams present
    public static void viewTeams() {
        if (teamIterator == 0) {
            System.out.println(constant.No_TEAM);
        } else {
            for (int i = 0; i < teamIterator; i++) {
                System.out.println(constant.TEAM + team[i]);
                for (int j = teamPlayerOffsets[i]; j < teamPlayerOffsets[i] + playerCounts[i]; j++) {
                    System.out.println(constant.HYPEN + player[j]);
                }
            }
        }
    }

    public static boolean isPresent(String teamName) {
        for (int i = 0; i < teamIterator; i++) {
            if (equalsIgnoreCase(team[i], teamName)) {
                if (teamOneIndex == -1) {
                    teamOneIndex = i;
                } else if (teamTwoIndex == -1) {
                    teamTwoIndex = i; 
                }
                return true;
            }
        }
        return false;
    }

    //This method is used to choose two team for playing match
    public static void chooseTeam(Scanner scanner) {
        System.out.println(constant.ENTER_2_TEAM);
        for (int i = 0; i < teamIterator; i++) {
            System.out.println(team[i]);
        }
        System.out.println(constant.ENTER_FIRST_TEAM);
        String team1 = scanner.nextLine();
        System.out.println(constant.ENTER_SECOND_TEAM);
        String team2 = scanner.nextLine();

        if (isPresent(team1) && isPresent(team2)) {
            System.out.println(constant.TEAM_SELECTED);
            playingEleven(scanner);  
            Toss(scanner); 
        } else {
            System.out.println(constant.TEAM_NOT_EXISTED);
            teamOneIndex = -1;
            teamTwoIndex = -1;
        }
    }

    // This method selects playing 11 of chosen team 
    public static void playingEleven(Scanner scanner) {
        if (teamOneIndex == -1 || teamTwoIndex == -1) {
            System.out.println(constant.NO_PROPER_SELECTION);
            return;
        }
        while (true) {
            System.out.println(constant.PROMPT_PLAYING11 + team[teamOneIndex]);
            for (int i = teamPlayerOffsets[teamOneIndex]; i < teamPlayerOffsets[teamOneIndex] + playerCounts[teamOneIndex]; i++) {
                System.out.println(player[i]);
            }
            System.out.println(constant.COMMA_SEPERATED);
            String[] playingEleven1 = scanner.nextLine().split(",");
            if (playingEleven1.length != 11) {
                System.out.println(constant.EXACT11);
                continue;
            }
            boolean validTeam1 = true;
            for (String player : playingEleven1) {
                if (!isPlayerInTeam(player.trim(), teamOneIndex)) {
                    System.out.println(constant.PLAYER + player.trim() + constant.NOT_IN_TEAM);
                    validTeam1 = false;
                    break;
                }
            }
            if (validTeam1) {
                for (int i = 0; i < 11; i++) {
                    playingElevenTeam1[i] = playingEleven1[i].trim();
                }
                break;
            }
        }

        while (true) {
            System.out.println(constant.PROMPT_PLAYING11  + team[teamTwoIndex]);
            for (int i = teamPlayerOffsets[teamTwoIndex]; i < teamPlayerOffsets[teamTwoIndex] + playerCounts[teamTwoIndex]; i++) {
                System.out.println(player[i]);
            }
            System.out.println(constant.COMMA_SEPERATED);
            String[] playingEleven2 = scanner.nextLine().split(",");
            if (playingEleven2.length != 11) {
                System.out.println(constant.EXACT11);
                continue;
            }
            boolean validTeam2 = true;
            for (String player : playingEleven2) {
                if (!isPlayerInTeam(player.trim(), teamTwoIndex)) {
                    System.out.println(constant.PLAYER + player.trim() + constant.NOT_IN_TEAM);
                    validTeam2 = false;
                    break;
                }
            }
            if (validTeam2) {
                for (int i = 0; i < 11; i++) {
                    playingElevenTeam2[i] = playingEleven2[i].trim();
                }
                break;
            }
        }
    }

    //This method is used to take toss winner
    public static void Toss(Scanner scanner) {
        if (teamOneIndex == -1 || teamTwoIndex == -1) {
            System.out.println(constant.NO_TOSS_OUTCOME);
            return;
        }
        System.out.println(constant.TEAM);
        System.out.println(constant.ONE + team[teamOneIndex]);
        System.out.println(constant.TWO + team[teamTwoIndex]);
        System.out.println(constant.TOSS_WINNER);
        String tossWin = scanner.nextLine();
        if (!equalsIgnoreCase(tossWin, team[teamOneIndex]) && !equalsIgnoreCase(tossWin, team[teamTwoIndex])) {
            System.out.println(constant.INVALID_TEAM );
            return;
        }
        System.out.println(constant.TEAM + tossWin + constant.TOSS_WON);
        String choice = scanner.nextLine();

        if (!equalsIgnoreCase(choice, "bat") && !equalsIgnoreCase(choice, "bowl")) {
            System.out.println(constant.INVALID_CHOICE);
            return;
        }
        if (equalsIgnoreCase(choice, "bat")) {
            if (equalsIgnoreCase(tossWin, team[teamOneIndex])) {
                battingTeamIndex = teamOneIndex;
                bowlingTeamIndex = teamTwoIndex;
            } else {
                battingTeamIndex = teamTwoIndex;
                bowlingTeamIndex = teamOneIndex;
            }
        } else {
            if (equalsIgnoreCase(tossWin, team[teamOneIndex])) {
                bowlingTeamIndex = teamOneIndex;
                battingTeamIndex = teamTwoIndex;
            } else {
                bowlingTeamIndex = teamTwoIndex;
                battingTeamIndex = teamOneIndex;
            }
        }
    }

    //This method is used to choose Striker and Non-Striker
    public static int[] chooseStrikerAndNonStriker(Scanner scanner, int battingTeamIndex) {
        if (battingTeamIndex == -1) {
            System.out.println(constant.INVALID_TEAM);
            return new int[]{-1, -1}; 
        }
        System.out.println(constant.PLAYING_XI_LIST);
        String[] battingTeamPlayers = (battingTeamIndex == teamOneIndex) ? playingElevenTeam1 : playingElevenTeam2;
        for (String player : battingTeamPlayers) {
            System.out.println(player);
        }
        int strikerIndex = -1;
        int nonStrikerIndex = -1;
        boolean validInput = false;
        while (!validInput) {
            System.out.println(constant.ENTER_STRIKER);
            String striker = scanner.nextLine().trim();
            System.out.println(constant.ENTER_NON_STRIKER);
            String nonStriker = scanner.nextLine().trim();
            if (striker.equalsIgnoreCase(nonStriker)) {
                System.out.println(constant.PROMPT_DIFFERENT_PLAYER);
                continue; 
            }
            if (isPlayerInPlayingEleven(striker, battingTeamIndex) && isPlayerInPlayingEleven(nonStriker, battingTeamIndex)) {
                strikerIndex = getPlayerIndex(striker, battingTeamIndex);
                nonStrikerIndex = getPlayerIndex(nonStriker, battingTeamIndex);
                validInput = true; 
            } else {
                if (!isPlayerInPlayingEleven(striker, battingTeamIndex)) {
                    System.out.println(constant.NO_STRIKER);
                }
                if (!isPlayerInPlayingEleven(nonStriker, battingTeamIndex)) {
                    System.out.println(constant.NO_NONSTRIKER);
                }
            }
        }
        return new int[]{strikerIndex, nonStrikerIndex}; 
    }

    //This method checks whether player is in playing 11 of the team or not
    private static boolean isPlayerInPlayingEleven(String playerName, int teamIndex) {
        String[] playingEleven = (teamIndex == teamOneIndex) ? playingElevenTeam1 : playingElevenTeam2;
        for (String player : playingEleven) {
            if (player != null && equalsIgnoreCase(playerName, player)) {
                return true;
            }
        }
        return false;
    }

    //This method is used to get index of chosen player
    private static int getPlayerIndex(String playerName, int teamIndex) {
        String[] playingEleven = (teamIndex == teamOneIndex) ? playingElevenTeam1 : playingElevenTeam2;
        for (int i = 0; i < playingEleven.length; i++) {
            if (playingEleven[i] != null && equalsIgnoreCase(playerName, playingEleven[i])) {
                return i;
            }
        }
        return -1; 
    }

    //This method is used to choose bowler
    public static void chooseBowler(Scanner scanner, int bowlingTeamIndex) {
        if (bowlingTeamIndex == -1) {
            System.out.println(constant.NO_BOWLINGTEAM);
            return;
        }
        String bowler;
        boolean validBowler = false;
        String[] bowlingTeamPlayers = (bowlingTeamIndex == teamOneIndex) ? playingElevenTeam1 : playingElevenTeam2;
        while (!validBowler) {
            System.out.println(constant.CHOOSE_BOWLER + team[bowlingTeamIndex]);
            for (String player : bowlingTeamPlayers) {
                System.out.println(player);
            }
            System.out.println(constant.ENTER_BOWLER);
            bowler = scanner.nextLine().trim();
            if (isPlayerInPlayingEleven(bowler, bowlingTeamIndex)) {
                validBowler = true;
                System.out.println(constant.BOWLER + bowler + constant.CHOSEN + team[bowlingTeamIndex]);
            } else {
                System.out.println(constant.PLAYER + bowler + constant.ENETER_VALID_BOWLER);
            }
        }
    }
    public static void initializePlayerOutStatus() {
        for (int i = 0; i < playerOutStatus.length; i++) {
            playerOutStatus[i] = false;  
        }
    }

    //This method is used to start match
    public static void startMatch(Scanner scanner) {
        if (teamOneIndex == -1 || teamTwoIndex == -1) {
            System.out.println(constant.CANNOT_START_MATCH);
            return;
        }
        System.out.println(constant.PROMPT_OVERS);
        over = scanner.nextInt();
        scanner.nextLine();
        int strikerIndex = 0;
        int nonStrikerIndex = 1;
        initializeTeamStats(battingTeamIndex);
        initializeTeamStats(bowlingTeamIndex);
        initializePlayerOutStatus();
        System.out.println(constant.INNING + team[battingTeamIndex]);
        startInnings(scanner, battingTeamIndex, bowlingTeamIndex, strikerIndex, nonStrikerIndex);
        int tempIndex = battingTeamIndex;
        battingTeamIndex = bowlingTeamIndex;
        bowlingTeamIndex = tempIndex;
        strikerIndex = 0;
        nonStrikerIndex = 1;
        initializePlayerOutStatus();
        System.out.println(constant.INNING + team[battingTeamIndex]);
        startInnings(scanner, battingTeamIndex, bowlingTeamIndex, strikerIndex, nonStrikerIndex);
        System.out.println(constant.MATCH_ENDED);
        System.out.println(constant.FINAL_SCORE + team[teamOneIndex] + constant.COLON + teamScores[teamOneIndex] + constant.WICKET + teamWickets[teamOneIndex]);
        System.out.println(constant.FINAL_SCORE + team[teamTwoIndex] + constant.COLON + teamScores[teamTwoIndex] + constant.WICKET + teamWickets[teamTwoIndex]);
        if (teamScores[teamOneIndex] > teamScores[teamTwoIndex]) {
            System.out.println(constant.WINNER + team[teamOneIndex]);
        } else if (teamScores[teamTwoIndex] > teamScores[teamOneIndex]) {
            System.out.println(constant.WINNER + team[teamTwoIndex]);
        } else {
            System.out.println(constant.MATCH_TIED);
        }
    } public static void initializeTeamStats(int teamIndex) {
        teamScores[teamIndex] = 0;
        teamWickets[teamIndex] = 0;
        overs[teamIndex] = 0;
        balls[teamIndex] = 0;
    }
    private static int lastBowlerIndex = -1;
    private static final int MAX_OVERS_PER_BOWLER = 4; 
    private static int[] bowlerOvers = new int[11]; 

    //This method starts the inning for batting team
    public static void startInnings(Scanner scanner, int battingTeamIndex, int bowlingTeamIndex,
    		int strikerIndex, int nonStrikerIndex) {
        if (overs[battingTeamIndex] == 0 && teamWickets[battingTeamIndex] == 0) {
            int[] strikerAndNonStriker = chooseStrikerAndNonStriker(scanner, battingTeamIndex);
            strikerIndex = strikerAndNonStriker[0];
            nonStrikerIndex = strikerAndNonStriker[1];
        }
        int currentOver = 0;
        int currentBall = 0;
        int currentBowlerIndex = -1;
        String[] bowlingTeamPlayers = (bowlingTeamIndex == teamOneIndex) ? playingElevenTeam1 : playingElevenTeam2;
        while (teamWickets[battingTeamIndex] < 10 && overs[battingTeamIndex] < over) {
            if (currentBall == 0) {
                currentBowlerIndex = nextBowler(scanner, bowlingTeamIndex);
                if (currentBowlerIndex == -1) {
                    System.out.println(constant.NO_AVAILABLE_BOWLER);
                    return; 
                }
                System.out.println(constant.CURRENT_BOWLER + bowlingTeamPlayers[currentBowlerIndex]);
            }

            System.out.println(constant.OVER + currentOver + "." + currentBall);
            String[] battingTeamPlayers = (battingTeamIndex == teamOneIndex) ? playingElevenTeam1 : playingElevenTeam2;
            System.out.println(constant.CURRENT_SCORE + teamScores[battingTeamIndex] + constant.SLASH + teamWickets[battingTeamIndex]);
            System.out.println(constant.STRIK + battingTeamPlayers[strikerIndex] + constant.NO_STRIK + battingTeamPlayers[nonStrikerIndex]);
            System.out.println(constant.PROMPT_BALLTYPE);
            int ballType = Integer.parseInt(scanner.nextLine());
            int runs = 0;
            switch (ballType) {
                case 1: 
                    boolean validInput = false;
                    while (!validInput) {
                      System.out.println(constant.ENTER_RUNS);
                      runs = Integer.parseInt(scanner.nextLine());
                      if (runs >= 0 && runs <= 6) {
                        validInput = true;
                        teamScores[battingTeamIndex] += runs;
                      } else {
                        System.out.println(constant.INVALID_RUNS);
                        }
                    }
                    break;
                
                case 2: 
                    System.out.println(constant.NOBALL);
                    runs = 1;
                    teamScores[battingTeamIndex] += runs;
                    break;

                case 3:  
                    System.out.println(constant.WIDE_BALL);
                    runs = 1;
                    teamScores[battingTeamIndex] += runs;
                    break;

                case 4: 
                    System.out.println(constant.BYE);
                    System.out.println(constant.ENTER_RUNS);
                    runs = Integer.parseInt(scanner.nextLine());
                    teamScores[battingTeamIndex] += runs;
                    break;

                case 5: 
                    System.out.println(constant.LEG_BYE);
                    System.out.println(constant.ENTER_RUNS);
                    runs = Integer.parseInt(scanner.nextLine());
                    teamScores[battingTeamIndex] += runs;
                    break;

                case 6: 
                    System.out.println(constant.DOT_BALL);
                    break;

                case 7: 
                    System.out.println(constant.OUT);
                    int[] strikerArray = {strikerIndex}; 
                    int[] nonStrikerArray = {nonStrikerIndex}; 
                    String[] strikerName = {player[strikerIndex]}; 
                    String[] nonStrikerName = {player[nonStrikerIndex]};
                    playerOut(scanner, battingTeamIndex, strikerArray, nonStrikerArray, strikerName, nonStrikerName);
                    strikerIndex = strikerArray[0]; 
                    nonStrikerIndex = nonStrikerArray[0]; 
                    player[strikerIndex] = strikerName[0];  
                    player[nonStrikerIndex] = nonStrikerName[0];  
                    teamWickets[battingTeamIndex]++; 
                    break;
                    
                default:
                    System.out.println(constant.INVALID_BALL);
                    continue;
            }
            if (ballType != 2 && ballType != 3) {
                currentBall++;
            }
            if (currentBall == 6) {
                currentBall = 0;
                currentOver++;
                overs[battingTeamIndex]++;
                bowlerOvers[currentBowlerIndex]++;
                System.out.println(constant.OVER_END + overs[battingTeamIndex] + constant.RUNS_SCORED + teamScores[battingTeamIndex]);
                lastBowlerIndex = currentBowlerIndex;
            }      
            if (runs % 2 != 0 && ballType != 3 && ballType != 2) {
                int temp = strikerIndex;
                strikerIndex = nonStrikerIndex;
                nonStrikerIndex = temp;
            }           
            if (teamWickets[battingTeamIndex] >= 10) {
                System.out.println(constant.INNING_ENDED + team[battingTeamIndex] + constant.LOST_ALL_WICKETS);
                break;
            }
        }
        System.out.println(constant.INNING_END + team[battingTeamIndex]);
        System.out.println(constant.TOTAL_RUNS + teamScores[battingTeamIndex] + constant.WICKETS + teamWickets[battingTeamIndex]);
    }

    //This method chooses next bowler after every over 
    public static int nextBowler(Scanner scanner, int bowlingTeamIndex) {
        System.out.println(constant.PROMPT_BOWLER);
        String[] bowlingTeamPlayers = (bowlingTeamIndex == teamOneIndex) ? playingElevenTeam1 : playingElevenTeam2;
        boolean bowlerAvailable = false;    
        for (int i = 0; i < 11; i++) {
            if (bowlerOvers[i] < MAX_OVERS_PER_BOWLER) {
                System.out.println(i + constant.DOT + bowlingTeamPlayers[i]); 
                bowlerAvailable = true;
            }
        }
        if (!bowlerAvailable) {
            System.out.println(constant.NO_AVAILABLE_BOWLER);
            return -1;  
        }
        int selectedBowlerIndex = -1;
        while (true) {
            System.out.println(constant.PROMPT_BOWLER_NUMBER);
            String input = scanner.nextLine().trim();
            try {
                selectedBowlerIndex = Integer.parseInt(input);
                if (selectedBowlerIndex >= 0 && selectedBowlerIndex < 11) {
                    if (bowlerOvers[selectedBowlerIndex] < MAX_OVERS_PER_BOWLER) {
                        return selectedBowlerIndex;  
                    } else {
                        System.out.println(constant.INVALID_BOWLER);
                    }
                } else {
                    System.out.println(constant.INVALID_BOWLER);
                }
            } catch (NumberFormatException e) {
                System.out.println(constant.INVALID_INPUT); 
            }
        }
    }
    
    //This method takes next batsman in case when batsman gets out
    public static void playerOut(Scanner scanner, int battingTeamIndex, int[] strikerIndex, int[] nonStrikerIndex, String[] striker, String[] nonStriker) {
        boolean validPlayerOut = false;
        String playerOut = "";
        while (!validPlayerOut) {
            System.out.println(constant.RUN_OUT_PROMPT);
            playerOut = scanner.nextLine().trim();
            String[] battingTeamPlayers = (battingTeamIndex == teamOneIndex) ? playingElevenTeam1 : playingElevenTeam2;
            if (equalsIgnoreCase(battingTeamPlayers[strikerIndex[0]], playerOut)) {
                validPlayerOut = true;
                System.out.println(playerOut + constant.STRIKER_OUT);
                playerOutStatus[strikerIndex[0]] = true;    
                int newBatsmanIndex = -1;
                while (newBatsmanIndex == -1) {
                    newBatsmanIndex = getNextAvailableBatsman(scanner, battingTeamIndex, strikerIndex[0], nonStrikerIndex[0]);
                }
                strikerIndex[0] = newBatsmanIndex;  
                striker[0] = battingTeamPlayers[newBatsmanIndex]; 
            } 
            else if (equalsIgnoreCase(battingTeamPlayers[nonStrikerIndex[0]], playerOut)) {
                validPlayerOut = true;
                System.out.println(playerOut + constant.NONSTRIKER_OUT);
                playerOutStatus[nonStrikerIndex[0]] = true;  

                int newBatsmanIndex = -1;
                while (newBatsmanIndex == -1) {
                    newBatsmanIndex = getNextAvailableBatsman(scanner, battingTeamIndex, strikerIndex[0], nonStrikerIndex[0]);
                }
                nonStrikerIndex[0] = newBatsmanIndex;  
                nonStriker[0] = battingTeamPlayers[newBatsmanIndex];  
            } else {
                System.out.println(constant.PLAYER_NOT_FOUND);
            }
        }

        System.out.println(constant.WICKET_FALLEN + teamWickets[battingTeamIndex]);
        System.out.println(constant.STRIK + striker[0] + constant.NO_STRIK + nonStriker[0]);
    }




    //This method show player available for batting from playing 11 of battng team 
    public static int getNextAvailableBatsman(Scanner scanner, int battingTeamIndex, int currentStrikerIndex, int currentNonStrikerIndex) {
        int newBatsmanIndex = -1;
        boolean playerFound = false;
        String[] battingTeamPlayers = (battingTeamIndex == teamOneIndex) ? playingElevenTeam1 : playingElevenTeam2;
        while (!playerFound) {
            System.out.println(constant.AVAILABLE_BATSMEN);
            for (int i = 0; i < 11; i++) {
                if (i != currentStrikerIndex && i != currentNonStrikerIndex && !isPlayerOut(i)) {
                    System.out.println(i + ". " + battingTeamPlayers[i]);
                }
            }
            System.out.println(constant.NEW_PLAYER_PROMPT);
            String newPlayer = scanner.nextLine().trim();
            
            for (int i = 0; i < 11; i++) {
                if (equalsIgnoreCase(battingTeamPlayers[i], newPlayer) && i != currentStrikerIndex && i != currentNonStrikerIndex && !isPlayerOut(i)) {
                    playerFound = true;
                    newBatsmanIndex = i;
                    break;
                }
            }
            if (!playerFound) {
                System.out.println(constant.INVALID_BATSMAN);  
            }
        }
        playerOutStatus[newBatsmanIndex] = false;  
        return newBatsmanIndex;
    }
        private static boolean isPlayerOut(int playerIndex) {
        return playerOutStatus[playerIndex];  
    }
    
    //This method shows Menu when program starts
    public static void optionSelection(Scanner scanner) {
        while (true) {
            System.out.println(constant.CHOOSE_OPTION);
            System.out.println(constant.OPTIONS);
            String userInput = scanner.nextLine();
            try {
                int choice = Integer.parseInt(userInput);
                switch (choice) {
                    case 1:
                        addPlayer(scanner);
                        break;
                    case 2:
                        viewTeams();
                        break;
                    case 3:
                        if (teamIterator < 2) {
                            System.out.println(constant.MIN_PLAYER);
                        } else {
                            chooseTeam(scanner);
                            if (teamOneIndex != -1 && teamTwoIndex != -1) {
                                startMatch(scanner); 
                            }
                        }
                        break;
                    case 4:
                        System.out.println(constant.EXIT);
                        return;
                    default:
                        System.out.println(constant.INVALID_CHOICE);
                }
            } catch (NumberFormatException e) {
                System.out.println(constant.INVALID);
            }
        }
    }

    //This is the main method 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        optionSelection(scanner);
    }
}

