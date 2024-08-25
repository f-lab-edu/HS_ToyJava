import java.util.ArrayList;
import java.util.List;

public class UserInput {

    private List<Long> userInput;


    public UserInput(List<Long> userInput) {
        this.userInput = userInput;
    }

    public List<Long> getUserInput() {
        //ArrayList<Long> lonhs = new ArrayList<>(userInput);
        return List.copyOf(userInput);
    }



}
