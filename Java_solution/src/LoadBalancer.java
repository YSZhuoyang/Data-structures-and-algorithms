public class LoadBalancer {
    // Idea: do not remove the element from array immediately,
    //       use array list for quick adding and picking,
    //       use hashmap for quick adding and removing
    //
    // Time: O(1) for all "add", "pick" and "remove" operations

    private List<Integer> servers;
    private HashMap<Integer, Integer> map; // server - array index

    public LoadBalancer() {
        servers = new ArrayList<>();
        map = new HashMap<>();
    }

    /*
     * @param server_id: add a new server to the cluster
     * @return: nothing
     */
    public void add(int server_id) {
        if (map.containsKey(server_id)) return;

        map.put(server_id, servers.size());
        servers.add(server_id);
    }

    /*
     * @param server_id: server_id remove a bad server from the cluster
     * @return: nothing
     */
    public void remove(int server_id) {
        Integer i = map.remove(server_id);
        if (i == null) return;

        int s = map.size();
        // Shift last element to index where server is removed, and remove the last element
        int lastServer = servers.get(s);
        servers.set(i, lastServer);
        servers.remove(s);
        if (i < s) map.put(lastServer, i);
    }

    /*
     * @return: pick a server in the cluster randomly with equal probability
     */
    public int pick() {
        int s = map.size();
        Random rn = new Random();
        return servers.get(rn.nextInt(s));
    }
}
