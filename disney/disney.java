/**
   * Finds the common ASCII score across collections of strings
   * @param jsonInput JSON object with collections of strings
   * @return Single score across all collections, or if none exists -1
   */
static int findCommonASCIIScore(JSONObject jsonInput) {
    // To access all the lists in the collection
    // List<String> keys = new ArrayList<String>(jsonInput.keySet());

    // To access the array of strings in each list
    // JSONArray strings = (JSONArray) leagueData.get(key);
      
    // Javadocs can be found here: https://alex-public-doc.s3.amazonaws.com/json_simple-1.1/index.html
      
    // To get the ASCII value of a character, just cast it to an int
      
    if (jsonInput.size() < 1) {
         return -1;
    }
    List<String> keys = new ArrayList<String>(jsonInput.keySet());
    Map<Integer, Integer> mappp = new HashMap<>();
    int result = 0;
    int si = keys.size();
    for (String k : keys) {
        JSONArray x = (JSONArray) jsonInput.get(k);
        int l = x.size();
        for (int j = 0; j < l; j++) {
            int code = 0;
            char[] keych = x.get(j).toString().toCharArray();
            for (int i = 0; i < keych.length; i++) {
                int n = (int) keych[i];
                  
                code += n;
                  
            }
              
            if (mappp.containsKey(code)) {
                mappp.put(code, mappp.get(code) + 1);
                if (mappp.get(code) == si) {
                    result = code;
                    return result;
                }
            } else {
                mappp.put(code, 1);
            }
        }
    }
      
    return -1;
}