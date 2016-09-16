import java.util.HashMap;

/**
 * Created by oscar on 6/9/16.
 */
public class ComboRecognizer
{
	HashMap<String, String> register = new HashMap<>();
	StringBuilder inputSequence = new StringBuilder();

	public void register(String input, String combo)
	{
		register.put(input, combo);
	}

	public String pressKey(char key)
	{
		inputSequence.append(key);

		return register.get(inputSequence.toString());
	}
}
