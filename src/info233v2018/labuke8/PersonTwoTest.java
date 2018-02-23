package info233v2018.labuke8;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PersonTwoTest {

    private PersonTwo aage;
    private PersonTwo baard;
    private PersonTwo christian;
    private PersonTwo dag;
    private PersonTwo even;
    private PersonTwo fridtjof;
    private PersonTwo gerda;
    private PersonTwo hulda;
    private PersonTwo ingrid;
    private PersonTwo jorunn;
    private PersonTwo kerstin;
    private PersonTwo liv;

    @BeforeEach
    void setup() {
        aage = new PersonTwo("Aage");
        baard = new PersonTwo("Bård");
        christian = new PersonTwo("Christian");
        dag = new PersonTwo("Dag");
        even = new PersonTwo("Even");
        fridtjof = new PersonTwo("Fridtjof");
        gerda = new PersonTwo("Gerda");
        hulda = new PersonTwo("Hulda");
        ingrid = new PersonTwo("Ingrid");
        jorunn = new PersonTwo("Jorunn");
        kerstin = new PersonTwo("Kerstin");
        liv = new PersonTwo("Liv");

        aage.addFriend(baard);
        aage.addFriend(dag);
        dag.addFriend(christian);
        dag.addFriend(even);
        dag.addFriend(baard);
        dag.addFriend(aage);
        baard.addFriend(aage);
        baard.addFriend(dag);
    }

    @Test
    void getName() {
        assertEquals("Aage", aage.getName());
    }

    @Test
    void getFriends() {
        ArrayList<PersonTwo> friends = aage.getFriends();
        assertEquals(baard, friends.get(0));
    }

    @Test
    void addFriend() {
        assertEquals(aage.getFriends().size(), 2);
    }

    @Test
    void mostConnectedFriend() {
        PersonTwo friend = aage.mostConnectedFriend();
        assertEquals(friend, dag);
    }
}