package com.gscape.sdp.galacticescape;

public class User {
    private String Username;
    private String achievements;
    private String currentPos;
    private String lives;
    private String score;


    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getAchievements() {
        return achievements;
    }

    public void setAchievements(String achievements) {
        this.achievements = achievements;
    }

    public String getCurrentPos() {
        return currentPos;
    }

    public void setCurrentPos(String currentPos) {
        this.currentPos = currentPos;
    }

    public String getLives() {
        return lives;
    }

    public void setLives(String lives) {
        this.lives = lives;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "User{" +
                "Username='" + Username + "\n" +
                ", achievements='" + achievements + "\n" +
                ", currentPos='" + currentPos + "\n" +
                ", lives='" + lives + "\n" +
                ", score='" + score + "\n" +
                '}';
    }
}
