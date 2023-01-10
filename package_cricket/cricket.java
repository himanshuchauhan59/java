package package_cricket;
import java.util.Arrays;
import java.util.List;
import java.util.Random; 
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.HashMap;

public class cricket {
    private static String userName;
    private static String userCountry;

    private static final int OVERS = 2;
    private static final int WICKETS = 5;
    private static final int BALLS_PER_OVER = 6;

    public static void main(String[] minho) {
        cricket.start();
    }

    private static void start() {

        Team userTeam = getUserTeam();
        Team opponentTeam = getOpponentTeam();

        Tournament tournament = new Tournament(OVERS, WICKETS, BALLS_PER_OVER);

        System.out.format("\n%12s...Starting match...\n\n", " ");

        cricket.startTournament(tournament, userTeam, opponentTeam);

        System.out.format("\n\n%15s...The End...%s", " ",
                new String(java.util.Base64.getDecoder().decode("CgoKCgogICAgICAgICAgICBDcmVhdGVkIGJ5OiBNaW5obwoKIA")));
    }

    private static Team getUserTeam() {
        try (Scanner sc = new Scanner(System.in)) {

            userName = sc.nextLine().trim().toUpperCase();
            userCountry = sc.nextLine().replaceAll("\\s+", "").toUpperCase();

            if (userCountry.length() < 3)
                userCountry = "SOUTH KOREA";

        } catch (Exception e) {
            System.out.println("Please enter your name and your country name in separate line for input.\n\n\n");
            userName = "MINHO";
            userCountry = "SOUTH KOREA";
        }

        System.out.println("Your name : " + userName + " (Captain)");

        CountryDataBase d = new CountryDataBase(userCountry);

        System.out.println("Your country : " + d.getUserCountry());

        List<String> teammates = new CopyOnWriteArrayList<>(
                Arrays.asList(userName, "CODER KITTEN", "MARTIN TAYLOR", "DAVID CARROLL", "KEVIN", "RIK WITTKOPP",
                        "BELL", "JOHANN DITRICH", "PAWAN MAURYA", "VITALY SOKOL", "IPANG"));

        Team userTeam = new Team(userName, teammates);

        System.out.println("Your teammates are :\n" + userTeam.getTeamTable());

        return userTeam;
    }

    private static Team getOpponentTeam() {

        List<String> teammates = new CopyOnWriteArrayList<>(
                Arrays.asList("CM", "ADITYA", "LUKARTODO", "LOTHAR", "ALEX TUSINEAN", "TIBOR SANTA", "SEBASTIAN KEBLER",
                        "ATUL", "KODE CRASHER", "GORDON", "SUMIT PROGRAMMER"));

        if (teammates.contains(userName)) {
            teammates.remove(userName);
            teammates.add("HYUN-JIN RYU");
        }

        String captain = teammates.get(new Random().nextInt(teammates.size()));

        Team opponentTeam = new Team(captain, teammates);

        System.out.println("\nOpponent team captain is : " + captain);

        CountryDataBase country = new CountryDataBase(userCountry);

        System.out.println("Opponent team country is : " + country.getOpponentCountry());

        System.out.println("\nTheir teammates are : " + opponentTeam.getTeamTable());

        return opponentTeam;
    }

    private static void startTournament(Tournament tournament, Team userTeam, Team opponentTeam) {

        String[] toss = { "HEAD", "TAIL" };
        String tossResult1 = toss[new Random().nextInt(toss.length)];
        String tossResult2 = toss[new Random().nextInt(toss.length)];

        String[] options = { "BAT", "BOWL" };
        String choice = options[new Random().nextInt(options.length)];

        System.out.println(" Toss result : " + tossResult2);

        if (tossResult1.equals(tossResult2)) {

            System.out.println(" You won the toss.");

            if (choice.equals("BAT")) {

                System.out.println(
                        "\nYou choose to bat first."
                                + "\n------------------------\n");

                MatchData user = tournament.getBattingTeam(userTeam);
                System.out.println(user);

                System.out.println(
                        "\nOpponent team will bat now."
                                + "\n---------------------------\n\n");
                MatchData opponent = tournament.getBattingTeam(opponentTeam);
                System.out.println(opponent);

                cricket.getWinner(user, opponent, choice, true);

            } else {

                System.out.println(
                        "\nYou choose to field first."
                                + "\n--------------------------\n"
                                + "\nOpponent team is batting."
                                + "\n-------------------------\n\n");
                MatchData opponent = tournament.getBattingTeam(opponentTeam);
                System.out.println(opponent);

                System.out.println(
                        "\nYour team is now batting."
                                + "\n-------------------------\n\n");

                MatchData user = tournament.getBattingTeam(userTeam);
                System.out.println(user);

                cricket.getWinner(user, opponent, choice, true);
            }

        } else {

            System.out.println(" Opponent team won the toss.");

            if (choice.equals("BAT")) {

                System.out.println(
                        "\n Opponent team choose to bat first."
                                + "\n-----------------------------------\n");

                MatchData opponent = tournament.getBattingTeam(opponentTeam);
                System.out.println(opponent);

                System.out.println(
                        "\nYour team will bat now."
                                + "\n-----------------------\n\n");

                MatchData user = tournament.getBattingTeam(userTeam);
                System.out.println(user);

                cricket.getWinner(user, opponent, choice, false);

            } else {

                System.out.println(
                        "\nOpponent team choose to field first."
                                + "\n------------------------------------\n"
                                + "\n Your team is batting."
                                + "\n----------------------\n\n");

                MatchData user = tournament.getBattingTeam(userTeam);
                System.out.println(user);

                System.out.println(
                        "\nOpponent team is now batting."
                                + "\n-----------------------------\n\n");

                MatchData opponent = tournament.getBattingTeam(opponentTeam);
                System.out.println(opponent);

                cricket.getWinner(user, opponent, choice, false);
            }
        }
    }

    private static void getWinner(MatchData userData, MatchData opponentData, String choice, Boolean user) {

        if (user) {
            if (choice.equals("BAT") &&
                    userData.getRuns() > opponentData.getRuns()) {
                System.out
                        .println("🎉 Your team won by " + (userData.getRuns() - opponentData.getRuns()) + " runs. 🏆");

            } else if (choice.equals("BAT") &&
                    userData.getRuns() < opponentData.getRuns()) {

                System.out.println("Opponent team won by " + (WICKETS - opponentData.getWickets()) + " wickets. 🏆");

            } else if (choice.equals("BOWL") &&
                    userData.getRuns() > opponentData.getRuns()) {

                System.out.println("🎉 Your team won by " + (WICKETS - userData.getWickets()) + " wickets. 🏆");
            } else if (choice.equals("BOWL") &&
                    userData.getRuns() < opponentData.getRuns()) {

                System.out
                        .println("Opponent team won by " + (opponentData.getRuns() - userData.getRuns()) + " runs. 🏆");

            } else {
                System.out.println("Match is draw!");
            }

        } else {

            if (choice.equals("BAT") &&
                    userData.getRuns() > opponentData.getRuns()) {

                System.out.println("🎉 Your team won by " + (WICKETS - userData.getWickets()) + " wickets. 🏆");
            } else if (choice.equals("BAT") &&
                    userData.getRuns() < opponentData.getRuns()) {

                System.out
                        .println("Opponent team won by " + (opponentData.getRuns() - userData.getRuns()) + " runs. 🏆");

            } else if (choice.equals("BOWL") &&
                    userData.getRuns() > opponentData.getRuns()) {

                System.out
                        .println("🎉 Your team won by " + (userData.getRuns() - opponentData.getRuns()) + " runs. 🏆");

            } else if (choice.equals("BOWL") &&
                    userData.getRuns() < opponentData.getRuns()) {

                System.out.println("Opponent team won by " + (WICKETS - opponentData.getWickets()) + " wickets. 🏆");

            } else {

                System.out.println("Match is draw!");
            }
        }
    }
}

/*** CountryDataBase.java ****/
class CountryDataBase extends cricket {
    private String country;

    public CountryDataBase(String country) {
        this.country = country;
    }

    public Map<String, String> database() {
        Map<String, String> database = new HashMap<String, String>() {
            {
                put("AFGHANISTAN", "AFG");
                put("AUSTRALIA", "AUS");
                put("BANGLADESH", "BGD");
                put("ENGLAND", "ENG");
                put("INDIA", "IND");
                put("IRELAND", "IRL");
                put("NEWZEALAND", "NZL");
                put("PAKISTAN", "PAK");
                put("SOUTHAFRICA", "ZAF");
                put("SRILANKA", "LKA");
                put("WESTINDIES", "WI");
                put("ZIMBABWE", "ZWE");
            }
        };

        if (database.isEmpty())
            throw new NullPointerException("ERROR! There is no data of country.");

        if (!database.containsKey(this.country))
            database.put(this.country, this.country.substring(0, 3).toUpperCase());

        return database;
    }

    public String getUserCountry() {
        return this.country + " (" + database().get(this.country) + ")";
    }

    public String getOpponentCountry() {

        List<String> countryList = new ArrayList<>(database().keySet());
        int index = new Random().nextInt(countryList.size());
        String randomCountry = countryList.get(index);

        if (randomCountry.equals(this.country)) {
            countryList.remove(randomCountry);
            return getOpponentCountry();
        } else
            return randomCountry + " (" + database().get(randomCountry) + ")";
    }
}

/*** MatchData.java ****/

class MatchData {

    private int runs;
    private int balls;
    private int fours;
    private int sixes;
    private int wickets;
    private int extraRun;
    private int over;

    public MatchData(int runs, int balls, int fours, int sixes, int wickets, int extraRun, int over) {

        this.runs = runs;
        this.balls = balls;
        this.fours = fours;
        this.sixes = sixes;
        this.wickets = wickets;
        this.extraRun = extraRun;
        this.over = over;
    }

    public int getRuns() {
        return this.runs;
    }

    public int getBalls() {
        return this.balls;
    }

    public int getFours() {
        return this.fours;
    }

    public int getSixes() {
        return this.sixes;
    }

    public int getWickets() {
        return this.wickets;
    }

    public int getExtraRun() {
        return this.extraRun;
    }

    public String getRunRate() {
        return String.format("%.2f", (double) this.runs / this.over);
    }

    @Override
    public String toString() {
        return "\n\n-----------------------------------\n"
                + "\n  Final Result :"
                + "\n-----------------\n"
                + "\nTotal run = " + this.getRuns()
                + "\nTotal balls played = " + this.getBalls()
                + "\nTotal 4s = " + this.getFours()
                + "\nTotal 6s = " + this.getSixes()
                + "\nWickets loss = " + this.getWickets()
                + "\nExtra Run = " + this.getExtraRun()
                + "\nRun rate = " + this.getRunRate()
                + "\n\n-----------------------------------\n";
    }
}

/*** MatchResult.java ****/

enum Result {
    ONE(1), TWO(2), THREE(3), FOUR(4),
    FIVE(5), SIX(6), WIDE(1), NO_RUN(0),
    NO_BALL(1), RUN_OUT(0), CAUGHT_OUT(0), BOWLED(0),
    STUMPED(0), LBW(0);

    private int runs;

    private Result(int runs) {
        this.runs = runs;
    }

    public int getRuns() {
        return this.runs;
    }

    public Boolean isFour() {
        return this == Result.FOUR;
    }

    public Boolean isSix() {
        return this == Result.SIX;
    }

    public Boolean isOut() {
        return (this == Result.RUN_OUT ||
                this == Result.CAUGHT_OUT ||
                this == Result.BOWLED ||
                this == Result.STUMPED ||
                this == Result.LBW);
    }

    public Boolean isWide() {
        return this == Result.WIDE;
    }

    public Boolean isNoBall() {
        return this == Result.NO_BALL;
    }
}

class MatchResult {
    Random rand = new Random();

    public Result result() {
        int prob = (int) (100 * rand.nextDouble());

        if (prob < 10)
            return Result.FOUR;
        else if (prob < 15)
            return Result.TWO;
        else if (prob < 20)
            return Result.FIVE;
        else if (prob < 25)
            return Result.SIX;
        else if (prob < 30)
            return Result.WIDE;
        else if (prob < 35)
            return Result.BOWLED;
        else if (prob < 40)
            return Result.RUN_OUT;
        else if (prob < 45)
            return Result.CAUGHT_OUT;
        else if (prob < 50)
            return Result.NO_RUN;
        else if (prob < 55)
            return Result.THREE;
        else if (prob < 60)
            return Result.NO_BALL;
        else if (prob < 42)
            return Result.LBW;
        else if (prob < 32)
            return Result.STUMPED;
        else
            return Result.ONE;
    }

    public static void show(Result r) {
        switch (r) {
            case NO_RUN ->
                System.out.println(" got no run.");

            case ONE ->
                System.out.println(" got one run.");

            case TWO ->
                System.out.println(" got two runs.");

            case THREE ->
                System.out.println(" got three runs.");

            case FOUR ->
                System.out.println(" got four runs.");

            case FIVE ->
                System.out.println(" got five runs.");

            case SIX ->
                System.out.println(" got six runs.");

            case NO_BALL ->
                System.out.println(" got a no ball.");

            case RUN_OUT ->
                System.out.println(" is run out.\n\n            Changing batsman...\n");

            case CAUGHT_OUT ->
                System.out.println(" is caught out.\n\n            Changing batsman...\n");

            case BOWLED ->
                System.out.println(" is bowled.\n\n            Changing batsman...\n");

            case WIDE ->
                System.out.println(" got a wide ball.");

            case LBW ->
                System.out.println(" is LBW.\n\n            Changing batsman...\n");

            case STUMPED ->
                System.out.println(" is stumped.\n\n            Changing batsman...\n");

        }
    }
}

/*** Team.java ****/

class Team {
    private String captain;
    private List<String> teammates;
    private Map<String, String> teammatesMap;

    public Team(String captain, List<String> teammates) {
        this.captain = captain;
        this.teammates = teammates;
    }

    public String getCaptainName() {
        return this.captain;
    }

    private Map<String, String> teammates() {

        if (this.teammates.isEmpty())
            throw new NullPointerException("Teammates list is empty ");

        Set<String> uniqueSet = new HashSet<>(this.teammates); // taking the teammates in a HashSet to avoid duplicate
                                                               // name.

        List<String> teammateList = new CopyOnWriteArrayList<>(uniqueSet);

        if (uniqueSet.size() < 11) // Since a team should have 11 members.
            teammateList.add("JI-MAN CHOI");
        uniqueSet.clear();

        List<String> position = new CopyOnWriteArrayList<>(Arrays.asList("BATSMAN", "BOWLER"));

        Map<String, String> map = new TreeMap<>();

        for (String name : teammateList)
            map.put(name, position.get(new Random().nextInt(position.size())));

        this.teammatesMap = map;
        return this.teammatesMap;
    }

    public String getNotOutBatsman(Result result, String currentBatsman) {

        if (result.isOut()) {

            this.teammatesMap.remove(currentBatsman);

            List<String> notOutBatsman = new CopyOnWriteArrayList<>(this.teammatesMap.keySet());

            int index = new Random().nextInt(notOutBatsman.size());

            currentBatsman = notOutBatsman.get(index);

            return currentBatsman;
        }

        return currentBatsman;
    }

    public String getTeamTable() {

        StringBuffer teamTable = new StringBuffer();

        teamTable
                .append("\n.-------------------------------.\n")
                .append("|   Player Name   |   Position  |")
                .append("\n|-----------------:-------------|\n");

        for (Map.Entry<String, String> player : teammates().entrySet()) {

            String playerName = player.getKey();
            String positions = player.getValue();

            teamTable.append("| ");

            if (playerName.length() < 14) {
                teamTable.append(playerName);
                for (int i = playerName.length(); i < 14; i++)
                    teamTable.append(" ");

            } else {
                teamTable.append(playerName.substring(0, 12).concat(".."));
            }

            teamTable.append("  |  ");

            if (positions.length() < 8) {
                teamTable.append(positions);
                for (int i = positions.length(); i < 10; i++)
                    teamTable.append(" ");

            } else {
                teamTable.append(positions.substring(0, 6).concat(".."));
            }

            teamTable.append(" |\n");
        }

        teamTable
                .append("'-------------------------------'\n");

        return teamTable.toString();
    }
}

/***    Tournament.java    ****/

 class Tournament {
   private int overs;
   private int maxWicket;
   private int balls_per_over;
   private MatchResult mr = new MatchResult();


   public Tournament(int overs, int maxWicket, int balls_per_over) {
       this.overs = overs;
       this.maxWicket = maxWicket; 
       this.balls_per_over = balls_per_over;
   }


   public MatchData getBattingTeam(Team battingTeam) {
        
       int totalRun = 0;
       int ballsPlayed = 0;
       int wickets = 0;
       int fours = 0;
       int sixes = 0;
       int extraRun = 0;
       int totalBalls = this.balls_per_over * this.overs;
       
      String batsman = battingTeam.getCaptainName();
   
      for(int i = 0; i < this.overs ; i++){      
          for(int j = 0; j < this.balls_per_over ; j++) {
                                            
             System.out.print( (i+1) + "." + (j+1) 
                    + " ball :  " + batsman);
         
             Result result = mr.result();
             MatchResult.show(result);
         
             ballsPlayed += 1;             
             totalRun += result.getRuns();    
             totalBalls -= 1;
               
             if(result.isFour()){
                 fours += 1;
             }
         
             if(result.isSix()){
                 sixes += 1;
             }
         
             if( result.isWide()){                
                 j -= 1;
                 totalBalls += 1;
                 extraRun += 1;
             }
             
             
             if( result.isNoBall()) {
                 totalBalls += 1;
                 extraRun += 1;
             }         
                   
             if (!result.isNoBall() &&
                  result.isOut()) {
                 wickets += 1;
                 batsman  = battingTeam.getNotOutBatsman(result, batsman);
                 
             } 
         
             if( wickets == maxWicket ||
                 totalBalls == 0)  break;        
         }              
      
         if(wickets <= maxWicket)
    
             System.out.println(
                 "\nAfter over " + (i+1)
       + "\n      Total runs = " + totalRun 
       + "\n      Wickets loss = " + wickets 
       + "\n      Balls left = " + totalBalls + "\n" );
       
        if(wickets == maxWicket){ System.out.println("All wickets are taken."); break;}
      
        if( totalBalls == 0){ System.out.println("There are no balls left."); break;}
      
        if((i+1) == overs) {System.out.println("There are no overs left.");}     
       }           
   
       return new MatchData ( totalRun, ballsPlayed, fours, sixes, wickets, extraRun, this.overs);
       
   }
}
