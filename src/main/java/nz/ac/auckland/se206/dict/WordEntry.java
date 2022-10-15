package nz.ac.auckland.se206.dict;

import java.util.List;

public class WordEntry {

  private String partOfSpeech;
  private List<String> definitions;

  /**
   * WordEntry constructor, takes strings and list of definitions
   *
   * @param partOfSpeech part of string
   * @param definitions returned definitions
   */
  public WordEntry(String partOfSpeech, List<String> definitions) {
    this.partOfSpeech = partOfSpeech;
    this.definitions = definitions;
  }

  /**
   * gets part of speech as needed
   *
   * @return part of speech
   */
  public String getPartOfSpeech() {
    return partOfSpeech;
  }

  /**
   * gets definitions as is required
   *
   * @return list of definitions
   */
  public List<String> getDefinitions() {
    return definitions;
  }
}
