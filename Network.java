/** Represents a social network. The network has users, who follow other uesrs.
 *  Each user is an instance of the User class. */
public class Network {

 // Fields
 private User[] users;  // the users in this network (an array of User objects)
 private int userCount; // actual number of users in this network

 /** Creates a network with a given maximum number of users. */
 public Network(int maxUserCount) {
     this.users = new User[maxUserCount];
     this.userCount = 0;
 }

 /** Creates a network  with some users. The only purpose of this constructor is 
  *  to allow testing the toString and getUser methods, before implementing other methods. */
 public Network(int maxUserCount, boolean gettingStarted) {
     this(maxUserCount);
     users[0] = new User("Foo");
     users[1] = new User("Bar");
     users[2] = new User("Baz");
     userCount = 3;
 }

 public int getUserCount() {
    return this.userCount; 
}

 /** Finds in this network, and returns, the user that has the given name.
  *  If there is no such user, returns null.
  *  Notice that the method receives a String, and returns a User object. */
 public User getUser(String name) {

    if (name == null) {
        return null;
    }

    name = name.substring(0, 1).toUpperCase() + name.substring(1);

     for (int i = 0; i < userCount; i++) {
         if (name.equals(users[i].getName())) {
             return this.users[i];
         }
     }
     return null;
 }

 /** Adds a new user with the given name to this network.
 *  If ths network is full, does nothing and returns false;
 *  If the given name is already a user in this network, does nothing and returns false;
 *  Otherwise, creates a new user with the given name, adds the user to this network, and returns true. */
 public boolean addUser(String name) {
     //// Replace the following statement with your code
     if (getUser(name) != null || this.userCount == users.length) {
         return false;
     }

     this.users[userCount] = new User(name);
     userCount++;
     return true;
 }

 /** Makes the user with name1 follow the user with name2. If successful, returns true.
  *  If any of the two names is not a user in this network,
  *  or if the "follows" addition failed for some reason, returns false. */
 public boolean addFollowee(String name1, String name2) {
     //// Replace the following statement with your code
     if (getUser(name1) == null || getUser(name2) == null) {
         return false;
     }

     if (name1.equals(name2)) {
        return false;
    }
     
     boolean success = getUser(name1).addFollowee(name2);

     return success;
 }
 
 /** For the user with the given name, recommends another user to follow. The recommended user is
  *  the user that has the maximal mutual number of followees as the user with the given name. */
 public String recommendWhoToFollow(String name) {
     //// Replace the following statement with your code
     if (getUser(name) == null) {
         return null;
     }

     int mostMutual = -1;
     String theChoosen = "";

     for (int i = 0; i < userCount; i++) {

        if (getUser(name).getName().equals(users[i].getName())) {
            continue;
        } 

        if (getUser(name).countMutual(users[i]) > mostMutual) {
             mostMutual = getUser(name).countMutual(users[i]);
             theChoosen = users[i].getName();
        }
     }

     return theChoosen;
 }

 /** Computes and returns the name of the most popular user in this network: 
  *  The user who appears the most in the follow lists of all the users. */
 public String mostPopularUser() {
     //// Replace the following statement with your code
     if (userCount == 0) {
        return null;
    }

     String mostPopular = "";
     int maxCount = 0;
    
     for (int i = 0; i < userCount; i++) {
         int currentCount = followeeCount(users[i].getName());

         if (currentCount > maxCount) {
             maxCount = currentCount;
             mostPopular = users[i].getName();
         }
     }
     return mostPopular;
 }

 /** Returns the number of times that the given name appears in the follows lists of all
  *  the users in this network. Note: A name can appear 0 or 1 times in each list. */
 private int followeeCount(String name) {
     //// Replace the following statement with your code
     int counter = 0;

     for (int i = 0; i < userCount; i++) {
         if (users[i].follows(name)){
             counter++;
         }
     }
     return counter;
 }

 // Returns a textual description of all the users in this network, and who they follow.
 public String toString() {
    if (userCount == 0) {
        return "Network:";
    } 
         
    String ans = "Network:" + "\n"; 

    for ( int i = 0; i < this.userCount - 1; i++) { 
        ans = ans + users[i].toString() + "\n"; 
    }

    ans = ans + users[this.userCount - 1].toString();
    
    return ans;
    }
}
