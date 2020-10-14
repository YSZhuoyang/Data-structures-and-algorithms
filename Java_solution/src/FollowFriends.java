
public class FriendshipService {
    private HashMap<Integer, HashSet<Integer>> followers;
    private HashMap<Integer, HashSet<Integer>> followees;


    public FriendshipService() {
        followers = new HashMap<>();
        followees = new HashMap<>();
    }

    /*
     * @param user_id: An integer
     * @return: all followers and sort by user_id
     */
    public List<Integer> getFollowers(int user_id) {
        if (!followers.containsKey(user_id)) followers.put(user_id, new HashSet<Integer>());

        List<Integer> res = new ArrayList<Integer>(followers.get(user_id));
        Collections.sort(res);

        return res;
    }

    /*
     * @param user_id: An integer
     * @return: all followings and sort by user_id
     */
    public List<Integer> getFollowings(int user_id) {
        if (!followees.containsKey(user_id)) followees.put(user_id, new HashSet<Integer>());
        
        List<Integer> res = new ArrayList<Integer>(followees.get(user_id));
        Collections.sort(res);

        return res;
    }

    /*
     * @param from_user_id: An integer
     * @param to_user_id: An integer
     * @return: nothing
     */
    public void follow(int to_user_id, int from_user_id) {
        if (!followers.containsKey(to_user_id)) followers.put(to_user_id, new HashSet<Integer>());
        if (!followees.containsKey(from_user_id)) followees.put(from_user_id, new HashSet<Integer>());
        
        followers.get(to_user_id).add(from_user_id);
        followees.get(from_user_id).add(to_user_id);
    }

    /*
     * @param from_user_id: An integer
     * @param to_user_id: An integer
     * @return: nothing
     */
    public void unfollow(int to_user_id, int from_user_id) {
        if (!followers.containsKey(to_user_id)) followers.put(to_user_id, new HashSet<Integer>());
        if (!followees.containsKey(from_user_id)) followees.put(from_user_id, new HashSet<Integer>());
        
        if (followers.get(to_user_id).contains(from_user_id))
            followers.get(to_user_id).remove(from_user_id);
        if (followees.get(from_user_id).contains(to_user_id))
            followees.get(from_user_id).remove(to_user_id);
    }
}
