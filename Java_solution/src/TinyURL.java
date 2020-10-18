public class TinyUrl {
    private HashMap<String, String> shortToLong = new HashMap<>();
    private HashMap<String, String> longToShort = new HashMap<>();
    private List<Character> alphabet;
    
    public TinyUrl() {
        alphabet = new ArrayList<>();
        for (char c = '0'; c <= '9'; c++) alphabet.add(c);
        for (char c = 'A'; c <= 'Z'; c++) alphabet.add(c);
        for (char c = 'a'; c <= 'z'; c++) alphabet.add(c);
    }
    
    /*
     * @param url: a long url
     * @return: a short url starts with http://tiny.url/
     */
    public String longToShort(String url) {
        while (true) {
            String randomStr = genRandStr();
            if (shortToLong.containsKey(randomStr)) continue;

            shortToLong.put(randomStr, url);
            longToShort.put(url, randomStr);

            return "http://tiny.url/" + randomStr;
        }
    }

    /*
     * @param url: a short url starts with http://tiny.url/
     * @return: a long url
     */
    public String shortToLong(String url) {
        return shortToLong.get(url.substring(16));
    }

    private String genRandStr() {
        StringBuilder sb = new StringBuilder();
        Random rn = new Random();
        for (int i = 0; i < 6; i++) {
            int r = rn.nextInt(62);
            sb.append(alphabet.get(r));
        }

        return sb.toString();
    }
}
