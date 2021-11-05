import java.util.*;

// You're creating a website where people could sign up,
// pay a fee and participate in a lottery.
// At any time, any participant could withdraw.
// A winner is determined periodically by getting a random participant
// who hasn't withdrawn.

class Lottery {

    private Map<String, Integer> backup = new HashMap<>();
    private List<String> backupList = new ArrayList<>();

    public void addParticipant(String participant) {
        if (backup.keySet().contains(participant)) {
            throw new IllegalArgumentException("There's a already Peggy");
        }
        backup.put(participant, backupList.size());

        backupList.add(participant);
    }


    public void removeParticipant(String participant) {
        if (!backup.keySet().contains(participant)) {
            throw new IllegalArgumentException("There's no participant");
        }

        //backuplist = {"Peggy": 0, "Brian": 2, "Wanda": 3, "Mike": 1}

        //backuplist = [0:"Peggy", 1:"Mike", 2:"Brian", 3:"Wanda"]
        Integer index = backup.get(participant);
        String tailParticipant = backupList.get(backupList.size() - 1);
        //update list
        backupList.set(index, tailParticipant);
        backupList.remove(backupList.size() - 1);
        //update hashmap
        backup.put(tailParticipant, index);
        backup.remove(participant);
    }

    public String getRandomParticipant() {
        Random r = new Random();
        Integer randomInt = r.nextInt(backup.size());
        return backupList.get(randomInt);
    }
    public static void main(String[] args) {
        Lottery test = new Lottery();
        test.addParticipant("Peggy");
        test.addParticipant("Amy");
        test.addParticipant("Carl");
        test.removeParticipant("Amy");
        System.out.println(test.getRandomParticipant());
    }
}




