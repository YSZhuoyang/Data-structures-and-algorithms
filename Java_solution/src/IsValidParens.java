import java.util.Stack;

/**
 * Created by oscar on 4/4/16.
 */
public class IsValidParens
{

    // My solution which beats 54% java submissions
    // Careful: always check whether stack is empty before calling pop()
    public boolean isValid(String s)
    {
        if (s.isEmpty())
        {
            return true;
        }

        Stack<Character> paren = new Stack<>();

        for (int i = 0; i < s.length(); i++)
        {
            switch (s.charAt(i))
            {
                case '(':
                    paren.push('(');
                    break;

                case '[':
                    paren.push('[');
                    break;

                case '{':
                    paren.push('{');
                    break;

                case ')':
                    if (paren.isEmpty() || paren.pop() != '(')
                    {
                        return false;
                    }

                    break;

                case ']':
                    if (paren.isEmpty() || paren.pop() != '[')
                    {
                        return false;
                    }

                    break;

                case '}':
                    if (paren.isEmpty() || paren.pop() != '{')
                    {
                        return false;
                    }

                    break;

                default:
                    break;
            }
        }

        return paren.isEmpty();
    }
}
