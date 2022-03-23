package fr.lightnew.teams;

public class PointsTeams {
    private static int points_aqua = 0;
    private static int points_red = 0;

    public static int getPointsTeamsAqua(){return points_aqua;}
    public static int getPointsTeamsRed(){return points_red;}

    public static void setPointsAqua(int amount){points_aqua = points_aqua+amount;}
    public static void setPointsRed(int amount){points_red = points_red+amount;}


}
