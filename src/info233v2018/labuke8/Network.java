package info233v2018.labuke8;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Write a description of class Network here. Remember to write 
 * a good class description, and your self as the author.
 *
 * @author Truls Pedersen
 * @version 2017.10.10
 */
public class Network
{
    public static final String USERS_FILE = "users.tsv";
    public static final String CONNECTIONS_FILE = "connections.tsv";
    
    // This is an implementation of a Singleton-class. We encountered this
    // in the first lecture (the figures-project). This static field
    // contains a reference to the only instance of a network.
    private static Network networkSingleton;
    
    // this variable is instantiated and set by the constructor
    private ArrayList<Person> persons;

    public static Network getNetwork() {
        if(networkSingleton == null) {
            networkSingleton = new Network();
        }
        return networkSingleton;
    }
    
    /**
     * Constructor for objects of class Network
     */
    private Network() {
        persons = new ArrayList<>();
        loadNetwork();
    }

    private void loadNetwork() {
        // load the user names (nodes)
        loadNames();
        
        // load the network (edges)
        loadConnections();
    }
    
    private void loadNames() {
        for(String[] record : TSVStorage.readTSVFile(USERS_FILE, 3)) {
            String name = record[0];
            String role = record[1];
            String info = record[2];
            Person personObject = new Person(name);
            // persons.add(personObject);
            persons.add(personObject);
        }
    }
    
    private void loadConnections() {
        for(String[] names : TSVStorage.readTSVFile(CONNECTIONS_FILE, 2)) {
            Person user1 = lookupPerson(names[0]);
            Person user2 = lookupPerson(names[1]);
            user1.addConnection(user2);
            user2.addConnection(user1);
        }
    }
    
    /**
     * Method lookupPerson searches through our collection of agents
     * and returns the agent which has the same name as the given parameter.
     *
     * @param personName The name of the person we are searching for
     * @return The person we searched for, or null if none is found
     */
    public Person lookupPerson(String personName) {        
        for(Person person : persons) {
            if(person.getName().equals(personName)) {
                return person;
            }
        }
        return null;
    }
    
    /**
     * Method printNetwork prints the network. It prints the name of every person
     * where each name is immediately preceded by the names of their connections.
     */
    public void printNetwork() {
        for (Person person : persons) {
            ArrayList<Person> connections = person.getConnections();
            System.out.println(person.getName() + " has " + connections.size() + " connections:");
            for (Person friend : connections) {
                System.out.println(" * " + friend.getName());
            }
        }
    }
}
