package nz.ac.auckland.se206.dict;

public class WordNotFoundException extends Exception {

  private static final long serialVersionUID = 1L;
  private String word;
  private String subMessage;

  /**
   * WordNotFoundException handler constructor that takes in word, message, submessage
   *
   * @param word word
   * @param message message
   * @param subMessage submessage
   */
  WordNotFoundException(String word, String message, String subMessage) {
    super(message);
    this.word = word;
    this.subMessage = subMessage;
  }

  /**
   * getter for the word in exception
   *
   * @return the word
   */
  public String getWord() {
    return word;
  }

  /**
   * getter for the submessage in exception
   *
   * @return the submessage
   */
  public String getSubMessage() {
    return subMessage;
  }
}
