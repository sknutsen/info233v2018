package info233v2018.labuke8;

import java.util.ArrayList;

/**
 * Write a description of class Person here. Remember to write 
 * a good class description, and your self as the author.
 *
 * @author Truls Pedersen
 * @version (a version number or a date)
 */
public class Person
{
    // the person's name
    private String name;
    
    // a list of this person's connections
    private ArrayList<Person> connections;
    
    /**
     * Constructor for objects of class Person. When the object
     * is created, the person's list of connections is empty.
     */
    public Person(String name)
    {
        // initialise instance variables
        this.name = name;
        connections = new ArrayList<>();
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
     * Method getFriends returns an ArraList of Person-objects 
     * containing the person's conections. 
     *
     * @return The list of the person's connections
     */
    public ArrayList<Person> getConnections() {
        return connections;
    }
    
    /**
     * Method addConnections adds the given Person-object to this
     * person's connections list. 
     *
     * @param friend A new connection (added to the list of connections)
     */
    public void addConnection(Person connection) {
        connections.add(connection); 
    }
    
    /**
     * Returns a hash code value for the object. (See 11.8 (p. 377) for explanation of this
     * and justification for why we do not discuss it further.)
     *
     * @see Object#hashCode()
     * @return The return value
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
