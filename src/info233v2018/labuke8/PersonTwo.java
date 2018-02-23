package info233v2018.labuke8;

import java.util.ArrayList;

/**
 * Write a description of class PersonTwo here. Remember to write
 * a good class description, and your self as the author.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PersonTwo
{
    // the person's name
    private String name;

    // a list of this person's friends
    private ArrayList<PersonTwo> friends;
    
    /**
     * Constructor for objects of class PersonTwo. When the object
     * is created, the person's list of friends is empty.
     */
    public PersonTwo(String name)
    {
        // initialise instance variables
        this.name = name;
        friends = new ArrayList<>();
    }

    /**
     * Method getName returns the person's name.
     *
     * @return The person's name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Method getFriends returns an ArraList of PersonTwo-objects
     * containing the person's friends. 
     *
     * @return The list of the person's friends
     */
    public ArrayList<PersonTwo> getFriends() {
        return friends;
    }
    
    /**
     * Method addFriend adds the given friend-object to this
     * person's friend list. 
     *
     * @param friend A new friend (added to the list of friends)
     */
    public void addFriend(PersonTwo friend) {
        friends.add(friend);
    }

    public PersonTwo mostConnectedFriend(){
        PersonTwo result = this;
        ArrayList<PersonTwo> friends = getFriends();
        for (PersonTwo friend : friends) {
            if (friend.getFriends().size() > result.getFriends().size()) {
                result = friend;
            }
        }
        return result;
    }
}
