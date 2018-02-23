package info233v2018.labuke8;

import java.util.ArrayList;

/**
 * Write a description of class Network here. Remember to write 
 * a good class description, and your self as the author.
 *
 * @author Truls Pedersen
 * @version 2017.09.20
 */
public class NetworkTwo
{
    // this variable is instantiated and set by the constructor
    private ArrayList<PersonTwo> persons;
    
    /**
     * Constructor for objects of class Network
     */
    public NetworkTwo()
    {
        // initialise instance variables
        persons = new ArrayList<>();
        persons.add(new PersonTwo("Aage"));
        persons.add(new PersonTwo("Bård"));
        persons.add(new PersonTwo("Christian"));
        persons.add(new PersonTwo("Dag"));
        persons.add(new PersonTwo("Even"));
        persons.add(new PersonTwo("Fridtjof"));
        persons.add(new PersonTwo("Gerda"));
        persons.add(new PersonTwo("Hulda"));
        persons.add(new PersonTwo("Ingrid"));
        persons.add(new PersonTwo("Jorunn"));
        persons.add(new PersonTwo("Kerstin"));
        persons.add(new PersonTwo("Liv"));
    }

    /**
     * Method loadNetwork loads every agents' list of friend. (1.b)
     */
    public void loadNetwork() {
        for (PersonTwo person : persons) {
            ArrayList<String> friendNames = friends(person.getName());
            System.out.println(person.getName() + " has " + friendNames.size() + "friends:");
            for (String friendName : friendNames) {
                System.out.println(" * " + friendName);
            }
        }
    }
    
    /**
     * Method lookupPerson searches through our collection of agents
     * and returns the agent which has the same name as the given parameter.
     * Part of exercise (1.a).
     *
     * @param personName The name of the agent we are searching for
     * @return The agent we searched for, or null if none is found
     */
    public PersonTwo lookupPerson(String personName) {
        for(int i = 0; i <= persons.size(); i++){
            if (personName.equals(persons.get(i).getName())) {
                return persons.get(i);
            }
        }
        return null; // should return null if and only if no person is found
    }
    
    /**
     * Method friends returns a list of a given person's friends.
     *
     * @param personName A name of the person whose friends you want to find
     * @return A list containing the names of the given person's friends
     */
    public ArrayList<String> friends(String personName) {
        ArrayList<String> result = new ArrayList<>();
        if (personName.equals("Aage")) {
            result.add("Bård");
            result.add("Dag");
        } else if (personName.equals("Bård")) {
            result.add("Aage");
            result.add("Christian");
        } else if (personName.equals("Christian")) {
            result.add("Bård");
            result.add("Dag");
            result.add("Even");
        } else if (personName.equals("Dag")) {
            result.add("Aage");
            result.add("Christian");
            result.add("Liv");
        } else if (personName.equals("Even")) {
            result.add("Christian");
            result.add("Fridtjof");
            result.add("Gerda");
            result.add("Hulda");
            result.add("Ingrid");
            result.add("Jorunn");
        } else if (personName.equals("Fridtjof")) {
            result.add("Even");
            result.add("Gerda");
            result.add("Hulda");
        } else if (personName.equals("Gerda")) {
            result.add("Even");
            result.add("Jorunn");
            result.add("Ingrid");
        } else if (personName.equals("Hulda")) {
            result.add("Even");
            result.add("Fridtjof");
        } else if (personName.equals("Gerda")) {
            result.add("Even");
            result.add("Jorunn");
            result.add("Ingrid");
        } else if (personName.equals("Ingrid")) {
            result.add("Even");
            result.add("Gerda");
            result.add("Jorunn");
            result.add("Kerstin");
            result.add("Liv");
        } else if (personName.equals("Jorunn")) {
            result.add("Even");
            result.add("Gerda");
            result.add("Ingrid");
        } else if (personName.equals("Kerstin")) {
            result.add("Ingrid");
            result.add("Liv");
        } else if (personName.equals("Liv")) {
            result.add("Dag");
            result.add("Ingrid");
            result.add("Kerstin");
        }
        return result;
    }
    
    /**
     * Method printNetwork prints the network. It prints the name of every person
     * where each name is immediately preceded by the names of their friends.
     */
    public void printNetwork() {
        for (PersonTwo person : persons) {
            ArrayList<PersonTwo> friends = person.getFriends();
            
            System.out.println(person.getName() + " has " + friends.size() + " friends:");
            for (PersonTwo friend : friends) {
                System.out.println(" * " + friend.getName());
            }
        }
    }
}
