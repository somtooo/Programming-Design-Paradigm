package view;

import controller.IController;

public interface BashViewInterface {
    void start(BashViewInterface view);

    void setFeatures(IController controller);

    void clearInputString();

    void clearListScreen();

    void setAddToList(String command);

    void throwSuccess(String success, String successType);

    void updateList(String command, int index);

    String getInputValue();

    void setInputString(String text);

    int getIndex();
}
