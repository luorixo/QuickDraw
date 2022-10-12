package nz.ac.auckland.se206.words;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.User;
import nz.ac.auckland.se206.UserHomeController;

/**
 * Class that provides drawing category selection functionality
 *
 * @author luorixo
 * @date 10/08/2022
 */
public class CategorySelector {

  public enum Difficulty {
    E,
    M,
    H
  }

  // Maps difficulty to a list of drawing categories
  private Map<Difficulty, List<String>> difficultyToCategories;

  public CategorySelector() {
    difficultyToCategories = new HashMap<>();

    // iterates through the Difficulty enum and maps each Difficulty to a list
    for (Difficulty difficulty : Difficulty.values()) {
      difficultyToCategories.put(difficulty, new ArrayList<>());
    }

    try {
      for (String[] line : getLines()) {
        difficultyToCategories.get(Difficulty.valueOf(line[1])).add(line[0]);
      }
    } catch (IOException | CsvException | URISyntaxException e) {
      e.printStackTrace();
    }
  }

  /**
   * Chooses a random category based on input difficulty
   *
   * @param difficulty The selected game difficulty
   * @return A random category
   */
  public String getRandomCategory(User.Difficulty difficulty) {

    List<String> wordsToChooseFrom = difficultyToCategories.get(Difficulty.E);
    if (difficulty == User.Difficulty.MEDIUM || difficulty == User.Difficulty.HARD) {
      wordsToChooseFrom.addAll(difficultyToCategories.get(Difficulty.M));
      if (difficulty == User.Difficulty.HARD) {
        wordsToChooseFrom.addAll(difficultyToCategories.get(Difficulty.H));
      }
    } else if (difficulty == User.Difficulty.MASTER) {
      wordsToChooseFrom = difficultyToCategories.get(Difficulty.H);
    }

    // get random category
    String randomCategory = wordsToChooseFrom.get(new Random().nextInt(wordsToChooseFrom.size()));

    User user = User.getUser(UserHomeController.id); // gets current user
    if (user.getWordsSeen() == wordsToChooseFrom) {
      user.setHasSeenAllWords(true); // set user flag to indicate has seen all words
    }

    while (true) {
      if (user.getWordsSeen().contains(randomCategory.replaceAll("\\s", "-"))
          && !user.hasSeenAllWords()) { // loops until new word is chosen
        randomCategory =
            wordsToChooseFrom.get(
                new Random().nextInt(wordsToChooseFrom.size())); // selects new topic
        continue;
      } else {
        break;
      }
    }
    return randomCategory;
  }

  /**
   * Gets a list of lines (strings) in the category_difficulty.csv file
   *
   * @return A list of lines (strings) in the category_difficulty.csv file
   * @throws IOException
   * @throws CsvException
   * @throws URISyntaxException
   */
  protected List<String[]> getLines() throws IOException, CsvException, URISyntaxException {
    File file = new File(App.class.getResource("/category_difficulty.csv").toURI());

    try (var fr = new FileReader(file, StandardCharsets.UTF_8);
        CSVReader reader = new CSVReader(fr)) {
      return reader.readAll();
    }
  }
}
