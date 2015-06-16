package FlagCriteria;

import twitter4j.Status;
import twitter4j.User;

import java.util.HashSet;
import java.util.Set;

public class BlackListCriteria implements FlagCriteria {
    private Set<Long> blackList;

    public BlackListCriteria(){
        blackList = new HashSet<Long>();
    }

    public void addUser(User user){
        blackList.add(user.getId());
    }

    public void removeUser(User user){
        blackList.remove(user.getId());
    }

    public boolean isFlagged(Status status) {
        long userId = status.getUser().getId();
        return blackList.contains(userId);
    }
}
