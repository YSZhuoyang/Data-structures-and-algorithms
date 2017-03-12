import java.math.BigInteger;

/**
 * Created by oscar on 12/03/17.
 */
class Dragon
{
    private String name;

    // Tell the capability of can fly or not
    private boolean canFly;

    // Tell the usage:
    // true for carrying passengers
    // false for being used as couriers
    private boolean canCarry;

    // Tell the state of current usage:
    // true for being used on land
    // false for being used in air
    private boolean runningOrFlying;

    private int numCarryOnLand;
    private int numCarryInAir;

    // Tell the number of people carried,
    // and number of vacancies
    private int numCarried;
    private int numRest;

    // Tell the running / flying speed
    private int speedOnLand;
    private int speedInAir;

    public Dragon(String name)
    {
        this.name = name;
    }

    public void setCanFly(boolean f)
    {
        canFly = f;
    }

    public void setCanCarry(boolean c)
    {
        canCarry = c;
    }

    public void setNumCarryOnLand(int n)
    {
        numCarryOnLand = n;
    }

    public void setNumCarryInAir(int n)
    {
        numCarryInAir = n;
    }

    public void setNumCarry(int c)
    {
        numCarried = c;
    }

    public void setNumRest(int r)
    {
        numRest = r;
    }

    public void setSpeedOnLand(int s)
    {
        speedOnLand = s;
    }

    public void setSpeedInAir(int s)
    {
        speedInAir = s;
    }

    // Add a new passenger,
    // return true for success,
    // false for no vacancies available
    public boolean carry()
    {
        if (!canCarry) return false;
        else if (runningOrFlying && numCarried < numCarryOnLand)
        {
            numCarried++;
            numRest--;
            return true;
        }
        else if (!runningOrFlying && numCarried < numCarryInAir)
        {
            numCarried++;
            numRest--;
            return true;
        }

        return false;
    }
}

class BlueDragon extends Dragon
{
    public BlueDragon()
    {
        super("Blue");

        setCanFly(true);
        setCanCarry(false);
        setSpeedOnLand(2);
        setSpeedInAir(100);
    }
}

class PinkDragon extends Dragon
{
    public PinkDragon()
    {
        super("Pink");

        setCanFly(false);
        setCanCarry(true);

        setNumCarry(0);
        setNumRest(6);

        setNumCarryOnLand(6);
        setSpeedOnLand(10);
    }
}

class RainbowSparkleDragon extends Dragon
{
    public RainbowSparkleDragon()
    {
        super("Rainbow Sparkle");

        setCanFly(true);
        setCanCarry(true);

        setNumCarry(0);
        setNumCarryInAir(1);
        setNumCarryOnLand(2);
        setSpeedOnLand(20);
        setSpeedInAir(10);
    }
}

class Pal
{
    int prod;
    int a;
    int b;

    public void setValues(int p, int a, int b)
    {
        prod = p;
        this.a = a;
        this.b = b;
    }

    public int getP()
    {
        return prod;
    }
}

class FindLargestPal
{
    int[] hashmap = new int[1000 * 1000];

    public Pal find()
    {
        Pal max = new Pal();

        for (int i = 0; i < 1000; i++)
        {
            for (int j = 0; j < 1000; j++)
            {
                int x = i * j;
                if (hashmap[x] != 0) continue;

                String s = Integer.toString(x);

                if (isPal(s) && x > max.getP())
                    max.setValues(x, i, j);
            }
        }

        return max;
    }

    public boolean isPal(String s)
    {
        if (s == null || s.isEmpty())
            return false;

        char[] cArr = s.toCharArray();

        int left = 0;
        int right = cArr.length - 1;

        while (left < right)
        {
            if (left <= right && right >= 0 && cArr[left] != cArr[right])
                return false;

            left++;
            right--;
        }

        return true;
    }
}

class VigenereCipher
{
    public String decrypt(String m, String k)
    {
        String decriptedStr = "";

        char[] kArr = k.toCharArray();
        int[] kmap = new int[kArr.length];

        for (int i = 0; i < kArr.length; i++)
            kmap[i] = kArr[i] - 'A';

        char[] mArr = m.toCharArray();
        int[] mmap = new int[mArr.length];

        for (int i = 0; i < mArr.length; i++)
            mmap[i] = mArr[i] - 'A';

        for (int i = 0; i < mArr.length; i++)
        {
            int kid = i % kmap.length;
            int shift = kmap[kid];
            int res = mmap[i] - shift;

            // Wrap arround if it exceeds boundaries
            if (res < 0) res += 26;

            decriptedStr += "" + (char) ('A' + res);
        }

        return decriptedStr;
    }
}


public class RPTest
{
    BigInteger[] hashmap = new BigInteger[200];

    public void printfb()
    {
        for (int i = 1; i <= 100; i++)
        {
            if (i % 15 == 0)
                System.out.println("FizzBuzz");
            else if (i % 3 == 0)
                System.out.println("Fizz");
            else if (i % 5 == 0)
                System.out.println("Buzz");
        }
    }

    public BigInteger fib()
    {
        BigInteger sum = BigInteger.valueOf(0);
        hashmap[0] = BigInteger.valueOf(0);
        hashmap[1] = BigInteger.valueOf(1);

        for (int i = 2; i < 200; i++)
            hashmap[i] = BigInteger.valueOf(0);

        for (int i = 0; i < 100; i++)
        {
            sum = sum.add(fib(i * 2));
            System.out.println(fib(i * 2));
        }

        return sum;
    }

    private BigInteger fib(int i)
    {
        if (i == 0) return BigInteger.valueOf(0);
        else if (i == 1) return BigInteger.valueOf(1);
        else if (i > 1)
        {
            if (hashmap[i].equals(BigInteger.valueOf(0)))
            {
                hashmap[i] = hashmap[i].add(fib(i - 1));
                hashmap[i] = hashmap[i].add(fib(i - 2));
            }

            return hashmap[i];
        }

        return BigInteger.valueOf(0);
    }

    public static void main(String[] args)
    {
        //Test t = new Test();
        //t.printfb();
        //t.fib();

        //FindLargestPal f = new FindLargestPal();
        //System.out.println(f.find().prod);
        //System.out.println(f.find().a);
        //System.out.println(f.find().b);

        VigenereCipher d = new VigenereCipher();
        System.out.println(d.decrypt("LXFOPVEFRNHR", "LEMON"));
    }
}
