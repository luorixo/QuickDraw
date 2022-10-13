package nz.ac.auckland.se206;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.io.FileUtils;

/**
 * Class that provides JSON saving/retrieving functionality for specific users. Allows the getting
 * and setting of JSON fields
 *
 * @author luorixo
 * @date 17/09/2022
 */
public class User {

  public enum Difficulty {
    EASY,
    MEDIUM,
    HARD,
    MASTER
  }
  /**
   * Gets the specified user (as a User object)
   *
   * @param userID The id of the user
   * @return The User (as a user object) specified by the id
   */
  public static User getUser(int userId) {
    try {
      Gson gson = new Gson();
      String jsonContent =
          readFileAsString(User.getPath(userId)); // grabs the data from JSON file depending on ID
      return gson.fromJson(jsonContent, User.class);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Return the absolute path of the user's JSON file
   *
   * @param userID The id of the specified user
   * @return The absolute path of the user info JSON file
   */
  private static String getPath(int userId) {
    return System.getProperty("user.dir")
        + "/src/main/resources/users/user"
        + userId
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

  private String name;
  private String currentBackground = "purple_background.png";
  private boolean[] backgrounds = new boolean[6];
  private boolean[] badges = new boolean[20];
  private Difficulty wordDifficulty = Difficulty.EASY;
  private Difficulty accuracyDifficulty = Difficulty.EASY;
  private Difficulty confidenceDifficulty = Difficulty.EASY;
  private Difficulty timeDifficulty = Difficulty.EASY;
  private int id;
  private int coins = 0;
  private int gamesPlayed = 0;
  private int gamesWon = 0;
  private int winStreak = 0;
  private int bestWinStreak = 0;
  private int totalTime = 0;
  private int bestTime = 60;
  private String bestWord = "";
  private List<String> wordsSeen = new ArrayList<>();
  private boolean hasSeenAllWords = false;
  private boolean hasBeenCreated = false;
  private boolean soundEffectsOn = true;
  private boolean textToSpeechOn = true;
  private boolean musicOn = true;

  public User(String name, int id) {
    this.name = name;
    this.id = id;
    this.hasBeenCreated = true;
    Arrays.fill(this.backgrounds, false);
    this.backgrounds[0] = true;

    Arrays.fill(this.badges, false);
    this.saveData();
  }

  /** Resets all fields associated with user and saves to JSON data file */
  public void resetUser() {
    // resets all data
    this.gamesPlayed = this.gamesWon = this.totalTime = this.coins = this.winStreak = 0;
    this.bestTime = 60;
    this.bestWord = this.name = "";
    this.wordsSeen = new ArrayList<>();
    this.hasSeenAllWords = this.hasBeenCreated = false;
    this.soundEffectsOn = this.textToSpeechOn = this.musicOn = true;
    this.wordDifficulty =
        this.accuracyDifficulty = this.confidenceDifficulty = this.timeDifficulty = Difficulty.EASY;
    this.currentBackground = "purple_background";
    // resets backgrounds array
    Arrays.fill(this.backgrounds, false);
    this.backgrounds[0] = true;
    Arrays.fill(this.badges, false);

    this.saveData(); // saves to JSON

    Path path =
        Paths.get(
            System.getProperty("user.dir")
                + "/src/main/resources/users/user"
                + this.id
                + "/images");

    if (Files.exists(path)) {
      File imagesFolder =
          new File(
              System.getProperty("user.dir")
                  + "/src/main/resources/users/user"
                  + this.id
                  + "/images");

      try {
        FileUtils.cleanDirectory(imagesFolder); // delete all images
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Convert difficulty value to an appropriate integer
   *
   * @param difficulty the difficulty of the category
   * @return the difficulty as an integer
   */
  private int difficultyToValue(Difficulty difficulty) {
    int value = 4; // assigns 4 by default (master)
    if (difficulty == Difficulty.EASY) {
      value = 1;
    } else if (difficulty == Difficulty.MEDIUM) {
      value = 2;
    } else if (difficulty == Difficulty.HARD) {
      value = 3;
    }
    return value;
  }

  /**
   * Calculates difficulty modifier and grants coins to player accordingly
   *
   * @param gameTime The time taken to finish the game
   */
  public int grantCoins(int gameTime) {
    int difficultyModifier =
        difficultyToValue(this.accuracyDifficulty)
            + difficultyToValue(this.confidenceDifficulty)
            + difficultyToValue(this.timeDifficulty)
            + difficultyToValue(this.wordDifficulty);

    this.coins += gameTime * difficultyModifier;
    return gameTime * difficultyModifier;
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
      this.gamesWon++;
      this.winStreak++;
      if (this.winStreak > this.bestWinStreak) {
        this.bestWinStreak = this.winStreak;
      }
    } else {
      this.winStreak = 0;
    }
    wordSeen = wordSeen.replaceAll("\\s", "-"); // remove dashes (-)
    wordsSeen.add(wordSeen); // adds word to array

    if (this.bestTime > gameTime) { // checks and updates best game time
      this.bestTime = gameTime;
      this.bestWord = wordSeen;
    }

    this.totalTime = this.totalTime + gameTime;
  }

  /** Saves the fields of the current user to its JSON file */
  public void saveData() {
    try {
      Gson gson = new Gson();
      String json = gson.toJson(this);
      FileWriter jsonWriter =
          new FileWriter(new File(User.getPath(id))); // grabs the JSON file to write to
      jsonWriter.write(json);
      jsonWriter.close();

    } catch (JsonIOException | IOException e) {
      e.printStackTrace();
    }
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
    if (this.gamesPlayed == 0) {
      return 0;
    }
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

  public Difficulty getWordDifficulty() {
    return this.wordDifficulty;
  }

  public Difficulty getAccuracyDifficulty() {
    return this.accuracyDifficulty;
  }

  public Difficulty getConfidenceDifficulty() {
    return this.confidenceDifficulty;
  }

  public Difficulty getTimeDifficulty() {
    return this.timeDifficulty;
  }

  public boolean getMusicState() {
    return this.musicOn;
  }

  public boolean getTextToSpeechState() {
    return this.textToSpeechOn;
  }

  public boolean getSoundEffectState() {
    return this.soundEffectsOn;
  }

  public boolean[] getOwnedBackgrounds() {
    return this.backgrounds;
  }

  public boolean[] getBadges() {
    return this.badges;
  }

  public String getCurrentBackground() {
    return this.currentBackground;
  }

  public int getCoins() {
    return this.coins;
  }

  public int getWinStreak() {
    return this.winStreak;
  }

  public int getBestWinStreak() {
    return this.bestWinStreak;
  }

  public String getBestWord() {
    return this.bestWord;
  }

  // INSTANCE FIELD SETTERS
  public void setName(String name) {
    this.name = name;
  }

  public void setHasSeenAllWords(boolean hasSeenAllWords) {
    this.hasSeenAllWords = hasSeenAllWords;
  }

  public void setWordDifficulty(Difficulty difficulty) {
    this.wordDifficulty = difficulty;
  }

  public void setAccuracyDifficulty(Difficulty difficulty) {
    this.accuracyDifficulty = difficulty;
  }

  public void setConfidenceDifficulty(Difficulty difficulty) {
    this.confidenceDifficulty = difficulty;
  }

  public void setTimeDifficulty(Difficulty difficulty) {
    this.timeDifficulty = difficulty;
  }

  public void setMusic(boolean isOn) {
    this.musicOn = isOn;
  }

  public void setTextToSpeech(boolean isOn) {
    this.textToSpeechOn = isOn;
  }

  public void setSoundEffects(boolean isOn) {
    this.soundEffectsOn = isOn;
  }

  public void setOwnedBackgrounds(int background) {
    this.backgrounds[background] = true;
  }

  public void setCurrentBackground(String currentBackground) {
    this.currentBackground = currentBackground;
  }

  public void setBadges(boolean[] badges) {
    this.badges = badges;
  }

  public void addCoins(int coins) {
    this.coins += coins;
  }
}
