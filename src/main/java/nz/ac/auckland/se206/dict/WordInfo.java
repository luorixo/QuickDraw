package nz.ac.auckland.se206.dict;

import java.util.List;

public class WordInfo {

  private String word;
  private List<WordEntry> entries;

  /**
   * WordInfo constructor to create instance
   *
   * @param word word
   * @param entries entries no.
   */
  WordInfo(String word, List<WordEntry> entries) {
    this.word = word;
    this.entries = entries;
  }

  /**
   * getter for the desired word
   *
   * @return word
   */
  public String getWord() {
    return word;
  }

  /**
   * getter for the word entries as a list
   *
   * @return word entries list
   */
  public List<WordEntry> getWordEntries() {
    return entries;
  }

  /**
   * gets the number of entries as int
   *
   * @return no. of entries
   */
  public int getNumberOfEntries() {
    return entries.size();
  }
}
