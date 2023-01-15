package test;

import game.cards.Card;
import game.cards.Rank;
import game.cards.Suit;
import game.cards.Hand;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * JUnit test framework for the Hand class.
 *
 * @author RIT CS
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestHand {
    /** Used to test that expected System.out print's happen */
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(System.out);
    }

    @Test
    public void test1_createEmpty() {
        Hand hand = new Hand("Test");
        assertEquals(0, hand.getValue());
        assertEquals("Test (0): ", hand.toString());
    }

    @Test
    public void test2_addCards() {
        Hand hand = new Hand("Test");
        hand.addCard(new Card(Rank.TEN, Suit.SPADE));
        hand.addCard(new Card(Rank.FOUR, Suit.HEART));
        assertEquals(4, hand.getValue());
        assertEquals("Test (4): 10♠ 4♥ ", hand.toString());
        hand.addCard(new Card(Rank.NINE, Suit.DIAMOND));
        assertEquals(3, hand.getValue());
        assertEquals("Test (3): 10♠ 4♥ 9♢ ", hand.toString());
    }

    @Test
    public void test3_compareTo() {
        Hand hand1 = new Hand("Test1");
        hand1.addCard(new Card(Rank.TEN, Suit.SPADE));
        hand1.addCard(new Card(Rank.FOUR, Suit.HEART));
        Hand hand2 = new Hand("Test2");
        hand2.addCard(new Card(Rank.THREE, Suit.SPADE));
        hand2.addCard(new Card(Rank.ACE, Suit.DIAMOND));
        assertEquals(0, hand1.compareTo(hand2));

        hand1.addCard(new Card(Rank.TWO, Suit.CLUB));
        assertEquals(2, hand1.compareTo(hand2));
        assertEquals(-2, hand2.compareTo(hand1));
    }

    @Test
    public void test4_getCards() {
        Hand hand1 = new Hand("Test1");
        hand1.addCard(new Card(Rank.TEN, Suit.SPADE));
        hand1.addCard(new Card(Rank.FOUR, Suit.HEART));
        assertEquals("[10♠, 4♥]", hand1.getCards().toString());
        Hand hand2 = new Hand("Test2");
        hand2.addCard(new Card(Rank.THREE, Suit.SPADE));
        hand2.addCard(new Card(Rank.ACE, Suit.DIAMOND));
        assertEquals("[3♠, A♢]", hand2.getCards().toString());
    }

    @Test
    public void test5_clear() {
        Hand hand = new Hand("Test");
        hand.addCard(new Card(Rank.TEN, Suit.SPADE));
        hand.addCard(new Card(Rank.FOUR, Suit.HEART));
        hand.clear();
        assertEquals(0, hand.getValue());
        assertEquals("Test (0): ", hand.toString());
    }
}
