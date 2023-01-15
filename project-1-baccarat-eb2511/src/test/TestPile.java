package test;

import game.cards.Card;
import game.cards.Rank;
import game.cards.Suit;
import game.cards.Pile;

import org.junit.*;
import org.junit.runners.MethodSorters;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * JUnit test framework for the Pile class.
 *
 * @author RIT CS
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestPile {
    /** Used to test that expected System.out print's happen */
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeClass
    public static void seedPile() {
        Pile.setSeed(0);
    }

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
        Pile testPile = new Pile("Test");
        assertEquals(0, testPile.numCards());
        assertEquals("Test pile: ", testPile.toString());
    }

    @Test
    public void test2_addCards() {
        Pile testPile = new Pile("Test");
        testPile.addCard(new Card(Rank.THREE, Suit.CLUB));
        testPile.addCard(new Card(Rank.TEN, Suit.DIAMOND));
        testPile.addCard(new Card(Rank.QUEEN, Suit.HEART));
        testPile.addCard(new Card(Rank.ACE, Suit.SPADE));
        assertEquals(4, testPile.numCards());
        assertEquals("Test pile: 3♧ 10♢ Q♥ A♠ ", testPile.toString());
    }

    @Test
    public void test3_drawCards() {
        Pile testPile = new Pile("Test");
        testPile.addCard(new Card(Rank.THREE, Suit.CLUB));
        testPile.addCard(new Card(Rank.TEN, Suit.DIAMOND));
        testPile.addCard(new Card(Rank.QUEEN, Suit.HEART));
        testPile.addCard(new Card(Rank.ACE, Suit.SPADE));
        assertEquals("3♧", testPile.drawCard().toString());
        assertEquals("Test pile: 10♢ Q♥ A♠ ", testPile.toString());
        assertEquals("10♢", testPile.drawCard().toString());
        assertEquals("Test pile: Q♥ A♠ ", testPile.toString());
        assertEquals("Q♥", testPile.drawCard().toString());
        assertEquals("Test pile: A♠ ", testPile.toString());
        assertEquals("A♠", testPile.drawCard().toString());
        assertEquals("Test pile: ", testPile.toString());
        assertEquals(0, testPile.numCards());
    }

    @Test
    public void test4_shuffle() {
        Pile testPile = new Pile("Test");
        testPile.addCard(new Card(Rank.THREE, Suit.CLUB));
        testPile.addCard(new Card(Rank.TEN, Suit.DIAMOND));
        testPile.addCard(new Card(Rank.QUEEN, Suit.HEART));
        testPile.addCard(new Card(Rank.ACE, Suit.SPADE));
        testPile.shuffle();
        assertEquals("Test pile: A♠ 3♧ 10♢ Q♥ ", testPile.toString());
        testPile.shuffle();
        assertEquals("Test pile: 3♧ A♠ Q♥ 10♢ ", testPile.toString());
        assertEquals(4, testPile.numCards());
    }
}
