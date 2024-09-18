import java.util.Scanner;

public class Testing {
    static int teamIterator = 0;
    static String[] teamNames = new String[100];
    static String[] players = new String[10000];
    static int[] playerCounts = new int[100];
    static int[] teamPlayerOffsets = new int[100];
    static String[] playingElevenTeam1 = new String[11];
    static String[] playingElevenTeam2 = new String[11];
    static int index1 = -1;
    static int index2 = -1;
    static int[] teamScores = new int[100];
    static int[] teamWickets = new int[100];
    static int[] overs = new int[100];
    static int[] balls = new int[100];
    static int currentBowlerIndex = -1;

    public static boolean equalsIgnoreCase(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return false;
        }
        String lowerStr1 = str1.toLowerCase();
        String lowerStr2 = str2.toLowerCase();
        return lowerStr1.equals(lowerStr2);
    }

    public static boolean isPlayerUnique(String playerName) {
        for (int i = 0; i < teamIterator; i++) {
            for (int j = teamPlayerOffsets[i]; j < teamPlayerOffsets[i] + playerCounts[i]; j++) {
                if (equalsIgnoreCase(players[j], playerName)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isPlayerInTeam(String playerName, int teamIndex) {
        for (int i = teamPlayerOffsets[teamIndex]; i < teamPlayerOffsets[teamIndex] + playerCounts[teamIndex]; i++) {
            if (equalsIgnoreCase(players[i], playerName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isTeamNameUnique(String teamName) {
        for (int i = 0; i < teamIterator; i++) {
            if (equalsIgnoreCase(teamNames[i], teamName)) {
                return false;
            }
        }
        return true;
    }

    public static void addPlayer(Scanner scanner) {
        if (teamIterator >= teamNames.length) {
            System.out.println("Maximum number of teams reached.");
            return;
        }

        System.out.println("Enter Team Name: ");
        String teamName = scanner.nextLine();
        if (!isTeamNameUnique(teamName)) {
            System.out.println("Team name already exists. Please choose a different name.");
            return;
        }

        teamPlayerOffsets[teamIterator] = (teamIterator == 0) ? 0 : teamPlayerOffsets[teamIterator - 1] + playerCounts[teamIterator - 1];
        playerCounts[teamIterator] = 0;

        while (true) {
            System.out.println("Enter 'y' to add player to your team otherwise 'exit' to finish adding players");
            String input = scanner.nextLine();

            if (equalsIgnoreCase(input, "exit")) {
                if (playerCounts[teamIterator] < 11) {
                    System.out.println("You need at least 11 players to complete the team.");
                } else {
                    teamNames[teamIterator] = teamName;
                    teamIterator++;
                    System.out.println("Your Team successfully created with team name: " + teamName);
                    break;
                }
            } else if (equalsIgnoreCase(input, "y")) {
                if (playerCounts[teamIterator] < 20) { 
                    System.out.println("Enter player name: ");
                    String playerName = scanner.nextLine();
                    if (!isPlayerUnique(playerName)) {
                        System.out.println("Player already exists in another team. Please enter a unique player name.");
                    } else {
                        if (isPlayerInTeam(playerName, teamIterator)) {
                            System.out.println("Player already added to this team. Please enter a different player name.");
                        } else {
                            players[teamPlayerOffsets[teamIterator] + playerCounts[teamIterator]] = playerName;
                            playerCounts[teamIterator]++;
                            System.out.println("Player " + playerName + " added to the team.");
                        }
                    }
                } else {
                    System.out.println("Cannot add more players to this team. The team has reached the maximum number of players.");
                }
            } else {
                System.out.println("Invalid option. Please enter 'y' or 'exit'.");
            }
        }
    }

    public static void viewTeams() {
        if (teamIterator == 0) {
            System.out.println("No teams to show.");
        } else {
            for (int i = 0; i < teamIterator; i++) {
                System.out.println("Team: " + teamNames[i]);
                for (int j = teamPlayerOffsets[i]; j < teamPlayerOffsets[i] + playerCounts[i]; j++) {
                    System.out.println(" - " + players[j]);
                }
            }
        }
    }

    public static boolean isPresent(String teamName) {
        for (int i = 0; i < teamIterator; i++) {
            if (equalsIgnoreCase(teamNames[i], teamName)) {
                if (index1 == -1) {
                    index1 = i;
                } else {
                    index2 = i;
                }
                return true;
            }
        }
        return false;
    }

    public static void chooseTeam(Scanner scanner) {
        System.out.println("Choose two teams from the list below: ");
        for (int i = 0; i < teamIterator; i++) {
            System.out.println(teamNames[i]);
        }
        System.out.println("Enter the name of the first team:");
        String team1 = scanner.nextLine();
        System.out.println("Enter the name of the second team:");
        String team2 = scanner.nextLine();

        if (isPresent(team1) && isPresent(team2)) {
            System.out.println("Teams selected successfully.");
            choosePlayingEleven(scanner);  
            Toss(scanner); 
        } else {
            System.out.println("One or both of the selected teams do not exist. Please try again.");
            index1 = -1;
            index2 = -1;
        }
    }

    
    
    public static void choosePlayingEleven(Scanner scanner) {
        if (index1 == -1 || index2 == -1) {
            System.out.println("Teams not properly selected. Cannot choose playing eleven.");
            return;
        }

        while (true) {
            System.out.println("Choose playing eleven for team: " + teamNames[index1]);
            for (int i = teamPlayerOffsets[index1]; i < teamPlayerOffsets[index1] + playerCounts[index1]; i++) {
                System.out.println(players[i]);
            }
            System.out.println("Enter 11 players from the above list (separated by commas):");
            String[] playingEleven1 = scanner.nextLine().split(",");
            if (playingEleven1.length != 11) {
                System.out.println("Please select exactly 11 players.");
                continue;
            }
            boolean validTeam1 = true;
            for (String player : playingEleven1) {
                if (!isPlayerInTeam(player.trim(), index1)) {
                    System.out.println("Player " + player.trim() + " is not in the selected team.");
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
            System.out.println("Choose playing eleven for team: " + teamNames[index2]);
            for (int i = teamPlayerOffsets[index2]; i < teamPlayerOffsets[index2] + playerCounts[index2]; i++) {
                System.out.println(players[i]);
            }
            System.out.println("Enter 11 players from the above list (separated by commas):");
            String[] playingEleven2 = scanner.nextLine().split(",");
            if (playingEleven2.length != 11) {
                System.out.println("Please select exactly 11 players.");
                continue;
            }
            boolean validTeam2 = true;
            for (String player : playingEleven2) {
                if (!isPlayerInTeam(player.trim(), index2)) {
                    System.out.println("Player " + player.trim() + " is not in the selected team.");
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


    
    public static void Toss(Scanner scanner) {
        if (index1 == -1 || index2 == -1) {
            System.out.println("Teams not properly selected. Cannot show toss outcome.");
            return;
        }

        System.out.println("Team names: ");
        System.out.println("1. " + teamNames[index1]);
        System.out.println("2. " + teamNames[index2]);
        System.out.println("Enter the team name that wins the toss:");
        String tossWinner = scanner.nextLine();

        if (!equalsIgnoreCase(tossWinner, teamNames[index1]) && !equalsIgnoreCase(tossWinner, teamNames[index2])) {
            System.out.println("Invalid team name. Please try again.");
            return;
        }

        System.out.println("Team " + tossWinner + " won the toss. Do you want to bat or bowl?");
        String choice = scanner.nextLine();

        if (!equalsIgnoreCase(choice, "bat") && !equalsIgnoreCase(choice, "bowl")) {
            System.out.println("Invalid choice. Please enter 'bat' or 'bowl'.");
            return;
        }

        if (equalsIgnoreCase(choice, "bat")) {
            if (equalsIgnoreCase(tossWinner, teamNames[index1])) {
                index1 = index1; 
                index2 = index2;
            } else {
                index1 = index2; 
                index2 = index1; 
            }
        } else {
            if (equalsIgnoreCase(tossWinner, teamNames[index1])) {
                index1 = index2; 
                index2 = index1; 
            } else {
                index1 = index1; 
                index2 = index2; 
            }
        }
        if (equalsIgnoreCase(choice, "bat")) {
            chooseStrikerAndNonStriker(scanner); 
            chooseBowler(scanner); 
        } else {
            chooseBowler(scanner); 
            chooseStrikerAndNonStriker(scanner); 
        }
    }


    public static void chooseStrikerAndNonStriker(Scanner scanner) {
        if (index1 == -1 || index2 == -1) {
            System.out.println("Teams not properly selected. Cannot choose striker and non-striker.");
            return;
        }

        String striker;
        String nonStriker;
        boolean validInput = false;

        int battingTeamIndex = (equalsIgnoreCase(teamNames[index1], teamNames[index1])) ? index1 : index2;
        String[] battingTeamPlayers = (equalsIgnoreCase(teamNames[battingTeamIndex], teamNames[index1])) ? playingElevenTeam1 : playingElevenTeam2;

        while (!validInput) {
            System.out.println("Enter Striker: ");
            striker = scanner.nextLine();
            System.out.println("Enter Non-Striker:");
            nonStriker = scanner.nextLine();

            boolean strikerPresent = false;
            boolean nonStrikerPresent = false;

            for (String player : battingTeamPlayers) {
                if (equalsIgnoreCase(player, striker)) {
                    strikerPresent = true;
                }
                if (equalsIgnoreCase(player, nonStriker)) {
                    nonStrikerPresent = true;
                }
            }

            if (!strikerPresent) {
                System.out.println("Striker not found in the playing eleven of the batting team.");
            }

            if (!nonStrikerPresent) {
                System.out.println("Non-Striker not found in the playing eleven of the batting team.");
            }

            if (strikerPresent && nonStrikerPresent && !equalsIgnoreCase(striker, nonStriker)) {
                validInput = true;
                System.out.println("Striker and Non-Striker are valid and different.");
            } else if (strikerPresent && nonStrikerPresent) {
                System.out.println("Striker and Non-Striker cannot be the same player. Please enter a different non-striker.");
            } else {
                System.out.println("Invalid Striker or Non-Striker. Please try again.");
            }
        }
    }

    

    public static void chooseBowler(Scanner scanner) {
        System.out.println("Enter the name of the bowler for the bowling team:");
        String bowler = scanner.nextLine();
        boolean bowlerPresent = false;

        int bowlingTeamIndex = (equalsIgnoreCase(teamNames[index1], teamNames[index1])) ? index2 : index1;
        String[] bowlingTeamPlayers = (equalsIgnoreCase(teamNames[bowlingTeamIndex], teamNames[index1])) ? playingElevenTeam2 : playingElevenTeam1;

        for (String player : bowlingTeamPlayers) {
            if (equalsIgnoreCase(player, bowler)) {
                bowlerPresent = true;
                break;
            }
        }

        if (!bowlerPresent) {
            System.out.println("Bowler not found in the playing eleven of the bowling team.");
        } else {
            System.out.println("Bowler selected successfully.");
            currentBowlerIndex = bowlingTeamIndex;
        }
    }


    public static void startMatch(Scanner scanner) {
        if (index1 == -1 || index2 == -1) {
            System.out.println("Teams not properly selected. Cannot start the match.");
            return;
        }

        int battingTeamIndex = index1;
        int bowlingTeamIndex = index2;
        int strikerIndex = 0;
        int nonStrikerIndex = 1;
        int currentOver = 0;
        int currentBall = 0;
       
        initializeTeamStats(battingTeamIndex);
        initializeTeamStats(bowlingTeamIndex);

        System.out.println("Innings for team: " + teamNames[battingTeamIndex]);
        startInnings(scanner, battingTeamIndex, bowlingTeamIndex, strikerIndex, nonStrikerIndex, currentOver, currentBall);

        int tempIndex = battingTeamIndex;
        battingTeamIndex = bowlingTeamIndex;
        bowlingTeamIndex = tempIndex;

        strikerIndex = 0;
        nonStrikerIndex = 1;
        currentOver = 0;
        currentBall = 0;
        System.out.println("Innings for team: " + teamNames[battingTeamIndex]);
        startInnings(scanner, battingTeamIndex, bowlingTeamIndex, strikerIndex, nonStrikerIndex, currentOver, currentBall);

        System.out.println("Match ended.");
        System.out.println("Final Score for team " + teamNames[index1] + ": " + teamScores[index1] + " | Wickets: " + teamWickets[index1]);
        System.out.println("Final Score for team " + teamNames[index2] + ": " + teamScores[index2] + " | Wickets: " + teamWickets[index2]);
    }

    public static void initializeTeamStats(int teamIndex) {
        teamScores[teamIndex] = 0;
        teamWickets[teamIndex] = 0;
        overs[teamIndex] = 0;
        balls[teamIndex] = 0;
    }

    public static void startInnings(Scanner scanner, int battingTeamIndex, int bowlingTeamIndex, int strikerIndex, int nonStrikerIndex, int currentOver, int currentBall) {
        while (teamWickets[battingTeamIndex] < 10 && overs[battingTeamIndex] < 20) {
            System.out.println("Over: " + currentOver + "." + currentBall);
            System.out.println("Current Score: " + teamScores[battingTeamIndex] + "/" + teamWickets[battingTeamIndex]);
            System.out.println("Enter type of ball (1. Run, 2. No Ball, 3. Wide Ball, 4. Bye, 5. Leg Bye, 6. Dot Ball, 7. Run Out): ");
            int ballType = Integer.parseInt(scanner.nextLine());

            int runs = 0;
            switch (ballType) {
                case 1:
                    System.out.println("Enter runs scored (0 to 6): ");
                    runs = Integer.parseInt(scanner.nextLine());
                    if (runs < 0 || runs > 6) {
                        System.out.println("Invalid runs. Runs should be between 0 and 6.");
                        continue;
                    }
                    teamScores[battingTeamIndex] += runs;
                    break;
                case 2:
                    System.out.println("No Ball. Enter runs scored (0 to 6): ");
                    runs = Integer.parseInt(scanner.nextLine());
                    if (runs < 0 || runs > 6) {
                        System.out.println("Invalid runs. Runs should be between 0 and 6.");
                        continue;
                    }
                    teamScores[battingTeamIndex] += runs;
                    break;
                case 3:
                    System.out.println("Wide Ball. Runs: ");
                    runs = Integer.parseInt(scanner.nextLine());
                    if (runs < 0) {
                        System.out.println("Invalid runs.");
                        continue;
                    }
                    teamScores[battingTeamIndex] += runs;
                    break;
                case 4:
                    System.out.println("Bye Ball. Runs: ");
                    runs = Integer.parseInt(scanner.nextLine());
                    if (runs < 0) {
                        System.out.println("Invalid runs.");
                        continue;
                    }
                    teamScores[battingTeamIndex] += runs;
                    break;
                case 5:
                    System.out.println("Leg Bye Ball. Runs: ");
                    runs = Integer.parseInt(scanner.nextLine());
                    if (runs < 0) {
                        System.out.println("Invalid runs.");
                        continue;
                    }
                    teamScores[battingTeamIndex] += runs;
                    break;
                case 6:
                    System.out.println("Dot Ball. No runs.");
                    break;
                case 7:
                    System.out.println("Run Out! Enter the name of the player who got out: ");
                    String playerOut = scanner.nextLine();
                    boolean playerFound = false;

                    if (equalsIgnoreCase(playingElevenTeam1[strikerIndex], playerOut)) {
                        playerFound = true;
                        if (teamWickets[battingTeamIndex] < 10) {
                            strikerIndex = getNextAvailableBatsman(scanner, battingTeamIndex);
                        }
                    } else if (equalsIgnoreCase(playingElevenTeam1[nonStrikerIndex], playerOut)) {
                        playerFound = true;
                        if (teamWickets[battingTeamIndex] < 10) {
                            nonStrikerIndex = getNextAvailableBatsman(scanner, battingTeamIndex);
                        }
                    }

                    if (!playerFound) {
                        System.out.println("Player not found in the playing eleven.");
                    }
                    teamWickets[battingTeamIndex]++;
                    break;
                default:
                    System.out.println("Invalid ball type.");
                    continue;
            }

            if (runs == 0 || ballType == 3 || ballType == 4 || ballType == 5) {
                currentBall++;
            }

            if (currentBall == 6) {
                currentBall = 0;
                currentOver++;
                overs[battingTeamIndex]++;
                System.out.println("End of Over " + overs[battingTeamIndex] + ". Runs scored: " + teamScores[battingTeamIndex]);

                if (overs[battingTeamIndex] % 5 == 0) {
                    System.out.println("Change Bowler");
                    chooseBowler(scanner);
                }
            }

            if (runs % 2 != 0) {
                int temp = strikerIndex;
                strikerIndex = nonStrikerIndex;
                nonStrikerIndex = temp;
            }

            if (teamWickets[battingTeamIndex] >= 10) {
                System.out.println("Innings ended. Team " + teamNames[battingTeamIndex] + " lost all their wickets.");
                break;
            }

            if (currentBall == 0) {
                System.out.println("Enter number of wickets fallen (if any): ");
                int wickets = Integer.parseInt(scanner.nextLine());
                teamWickets[battingTeamIndex] += wickets;
            }

            double runRate = (double) teamScores[battingTeamIndex] / overs[battingTeamIndex];
            int projectedScore = (int) (runRate * 20);
            System.out.println("Projected Score after 20 overs: " + projectedScore);
        }
        System.out.println("End of innings for team: " + teamNames[battingTeamIndex]);
        System.out.println("Total Runs: " + teamScores[battingTeamIndex] + " | Wickets: " + teamWickets[battingTeamIndex]);
    }

    public static int getNextAvailableBatsman(Scanner scanner, int teamIndex) {
        System.out.println("Choose a new player from the remaining players in the team: ");
        for (int i = teamPlayerOffsets[teamIndex]; i < teamPlayerOffsets[teamIndex] + playerCounts[teamIndex]; i++) {
            System.out.println(players[i]);
        }

        String newPlayer = scanner.nextLine();
        for (int i = teamPlayerOffsets[teamIndex]; i < teamPlayerOffsets[teamIndex] + playerCounts[teamIndex]; i++) {
            if (equalsIgnoreCase(players[i], newPlayer)) {
                return i; 
            }
        }

        System.out.println("Player not found in the remaining players.");
        return -1; 
    }
    
    
    public static void optionSelection(Scanner scanner) {
        while (true) {
            System.out.println("Choose Option:");
            System.out.println("1. Create Team \n2. View Teams \n3. Start Game \n4. Exit");
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
                            System.out.println("You need at least 2 teams to start the game. Add more teams to start playing.");
                        } else {
                            chooseTeam(scanner);
                            if (index1 != -1 && index2 != -1) {
                                chooseStrikerAndNonStriker(scanner);
                                chooseBowler(scanner);
                                startMatch(scanner);
                          
                                int tempIndex = index1;
                                index1 = index2;
                                index2 = tempIndex;
                                
                                chooseStrikerAndNonStriker(scanner); 
                                chooseBowler(scanner); 
                                startMatch(scanner); 
                            }
                        }
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select 1, 2, 3, or 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        optionSelection(scanner);
    }
}

