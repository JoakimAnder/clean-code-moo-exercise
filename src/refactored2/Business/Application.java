package refactored2.Business;

public interface Application {
    void setup();
    void execute(String command);

    String getEmptyOutputMessage();
    String getName();
    int getPoints();
    String getOutputMessage();
    String getErrors();
    boolean hasErrors();
    boolean isPlaying();



}
