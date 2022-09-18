package nz.ac.auckland.se206;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that provides JSON saving/retrieving functionality for specific users. Allows the getting
 * and setting of JSON fields
 *
 * @author luorixo
 * @date 17/09/2022
 */
public class User {
  private String name;
  private int id;
  private int gamesPlayed = 0;
  private int gamesWon = 0;
  private int bestTime = 0;
  private int totalTime = 0;
  private List<String> wordsSeen = new ArrayList<>();
  private boolean hasSeenAllWords = false;
  private boolean hasBeenCreated = false;

  public User(String name, int id) {
    this.name = name;
    this.id = id;
    this.hasBeenCreated = true;
    this.saveToJSON();
  }

  /**
   * Return the absolute path of the user's JSON file
   *
   * @param userID The id of the specified user
   * @return The absolute path of the user info JSON file
   */
  private static String getPath(int userID) {
    return System.getProperty("user.dir")
        + "/src/main/resources/users/user"
        + userID
        + "/userInfo.json";
  }

  /**
   * Gets the content of a specified JSON file as a string
   *
   * @param file The path of the JSON file to read
   * @return The content of the JSON file as a string
   * @throws Exception
   */
  private static String readFileAsString(String file) throws Exception {
    return new String(Files.readAllBytes(Paths.get(file)));
  }

  /** Resets all fields associated with user and saves to JSON data file */
  public void resetUser() {
    this.gamesPlayed = 0;
    this.gamesWon = 0;
    this.bestTime = 0;
    this.totalTime = 0;
    this.wordsSeen = new ArrayList<>();
    this.hasSeenAllWords = false;
    this.hasBeenCreated = false;
    this.name = "";
    this.saveToJSON();
  }

  /**
   * Called when the game ends.
   *
   * @param hasWon
   * @param wordSeen
   */
  public void gameOver(boolean hasWon, String wordSeen, int gameTime) {
    gamesPlayed++;
    if (hasWon) {
      gamesWon++;
    }
    wordsSeen.add(wordSeen);

    if (this.bestTime > gameTime) {
      this.bestTime = gameTime;
    }

    this.totalTime = this.totalTime + gameTime;
  }

  /** Saves the fields of the current user to its JSON file */
  public void saveToJSON() {
    try {
      Gson gson = new Gson();
      String json = gson.toJson(this);
      FileWriter jsonWriter = new FileWriter(new File(User.getPath(id)));
      jsonWriter.write(json);
      jsonWriter.close();

    } catch (JsonIOException | IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Gets the specified user (as a User object)
   *
   * @param userID The id of the user
   * @return The User (as a user object) specified by the id
   */
  public static User getUser(int userID) {
    try {
      Gson gson = new Gson();
      String jsonContent = readFileAsString(User.getPath(userID));
      return gson.fromJson(jsonContent, User.class);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  // INSTANCE FIELD GETTERS
  public String getName() {
    return this.name;
  }

  public int getGamesPlayed() {
    return this.gamesPlayed;
  }

  public int getGamesWon() {
    return this.gamesWon;
  }

  public int getGamesLost() {
    return this.gamesPlayed - this.gamesWon;
  }

  public int getBestTime() {
    return this.bestTime;
  }

  public int getTotalTime() {
    return this.totalTime;
  }

  public int getAverageTime() {
    return this.totalTime / this.gamesPlayed;
  }

  public List<String> getWordsSeen() {
    return this.wordsSeen;
  }

  public boolean hasSeenAllWords() {
    return this.hasSeenAllWords;
  }

  public boolean hasBeenCreated() {
    return this.hasBeenCreated;
  }

  // INSTANCE FIELD SETTERS
  public void setName(String name) {
    this.name = name;
  }

  public void setHasSeenAllWords(boolean hasSeenAllWords) {
    this.hasSeenAllWords = hasSeenAllWords;
  }
}
