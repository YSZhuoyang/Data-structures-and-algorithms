/**
 * Definition of Tweet:
 */
 public class Tweet {
     private static int ITERATOR = 1;

     public int id;
     public int user_id;
     public String text;
     public static Tweet create(int user_id, String tweet_text) {
         // Create a new tweet object, and auto fill id
        Tweet t = new Tweet();
        t.id = ITERATOR++;
        t.user_id = user_id;
        t.text = tweet_text;

        return t;
     }
 }


public class MiniTwitter {
    private HashMap<Integer, List<Tweet>> posts;  // user - tweets
    private HashMap<Integer, HashSet<Integer>> follows; // follower - followees
    private HashMap<Integer, Integer> time; // tweet id - time
    
    
    public MiniTwitter() {
        posts = new HashMap<>();
        follows = new HashMap<>();
        time = new HashMap<>();
    }

    /*
     * @param user_id: An integer
     * @param tweet_text: a string
     * @return: a tweet
     */
    public Tweet postTweet(int user_id, String tweet_text) {
        Tweet t = Tweet.create(user_id, tweet_text);
        if (!posts.containsKey(user_id)) posts.put(user_id, new ArrayList<Tweet>());
        posts.get(user_id).add(t);

        int newT = time.size();
        time.put(t.id, newT);
        
        return t;
    }

    /*
     * @param user_id: An integer
     * @return: a list of 10 new feeds recently and sort by timeline
     */
    public List<Tweet> getNewsFeed(int user_id) {
        PriorityQueue<Tweet> q =
            new PriorityQueue<>((Tweet b, Tweet a) -> time.get(a.id) - time.get(b.id));

        if (!follows.containsKey(user_id)) {
            follows.put(user_id, new HashSet<Integer>());
            follows.get(user_id).add(user_id);
        }
        
        HashSet<Integer> followees = follows.get(user_id);
        HashMap<Integer, Integer> iter = new HashMap<>();
        for (int f : followees) {
            if (!posts.containsKey(f)) posts.put(f, new ArrayList<>());
            iter.put(f, posts.get(f).size() - 1);
        }

        for (int f : followees) {
            int it = iter.get(f);
            if (it < 0) continue;

            q.add(posts.get(f).get(it));
            iter.put(f, it - 1);
        }

        List<Tweet> res = new ArrayList<>();
        int i = 0;
        while (i++ < 10 && !q.isEmpty()) {
            Tweet t = q.poll();
            res.add(t);
            int currIt = iter.get(t.user_id);
            if (currIt < 0) continue;

            q.add(posts.get(t.user_id).get(currIt));
            iter.put(t.user_id, currIt - 1);
        }

        return res;
    }

    /*
     * @param user_id: An integer
     * @return: a list of 10 new posts recently and sort by timeline
     */
    public List<Tweet> getTimeline(int user_id) {
        List<Tweet> res = new ArrayList<Tweet>();

        if (!posts.containsKey(user_id)) posts.put(user_id, new ArrayList<>());
        List<Tweet> tweets = posts.get(user_id);
        int tId = tweets.size() - 1;
        for (int i = 0; i < 10; i++) {
            if (tId < 0) break;
            
            res.add(tweets.get(tId--));
        }

        return res;
    }

    /*
     * @param from_user_id: An integer
     * @param to_user_id: An integer
     * @return: nothing
     */
    public void follow(int from_user_id, int to_user_id) {
        if (!follows.containsKey(from_user_id)) {
            follows.put(from_user_id, new HashSet<Integer>());
            follows.get(from_user_id).add(from_user_id);
        }
        
        follows.get(from_user_id).add(to_user_id);
    }

    /*
     * @param from_user_id: An integer
     * @param to_user_id: An integer
     * @return: nothing
     */
    public void unfollow(int from_user_id, int to_user_id) {
        if (!follows.containsKey(from_user_id)) {
            follows.put(from_user_id, new HashSet<Integer>());
            follows.get(from_user_id).add(from_user_id);
        }
        if (follows.get(from_user_id).contains(to_user_id)) follows.get(from_user_id).remove(to_user_id);
    }
}
