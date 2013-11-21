import static org.junit.Assert.*;

import org.junit.Test;

import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Random;

/**
 * SamR's tests for binary search trees.
 * 
 * This suite includes two kinds of tests.
 * 
 * First, we have tests that correspond to the six or so cases for removing
 * elements. Those are described in a corresponding exam document.
 * 
 * Second, we have some randomized tests to help catch some strange outliers.
 */
public class SamTest {
    // +--------+----------------------------------------------------------
    // | Fields |
    // +--------+

    /**
     * The number of operations we do in each iteration of the randomized test.
     */
    final int NUMOPS = 500;

    /**
     * The number of iterations we do in the randomized test.
     */
    final int ITERATIONS = 20;

    // +-------+-----------------------------------------------------------
    // | Tests |
    // +-------+

    /**
     * Conduct a whole bunch of unpredictable tests. A strength of this approach
     * is that we have a bunch of tests. A weakness is that when something
     * fails, we don't necessarily know what failed.
     */
    @Test
    public void randomTest() throws Exception {
	// The words we'll put in the dictionary. And yes, there are
	// intentionally some missing first letters.
	String[] words = { "aardvark", "anteater", "antelope", "bear", "bison",
		"buffalo", "chinchilla", "cat", "dingo", "elpehant", "eel",
		"flying squirrel", "fox", "goat", "gnu", "goose", "hippo",
		"horse", "iguana", "jackalope", "kestrel", "llama", "moose",
		"mongoose", "nilgai", "orangutan", "opossum", "red fox",
		"snake", "tarantula", "tiger", "vicuna", "vulture", "wombat",
		"yak", "zebra", "zorilla" };
	int wordslen = words.length;

	// The keys we use
	String keys = "abcdefghijklmnopqrstuvwxyz";
	int keyslen = keys.length();

	// A helpful array list
	HashSet<Character> activeKeys = new HashSet<Character>();

	Random rand = new Random();

	for (int i = 0; i < ITERATIONS; i++) {
	    // Create a new tree.
	    BST<Character, String> dict = new BST<Character, String>(
		    new Comparator<Character>() {
			public int compare(Character left, Character right) {
			    return left.compareTo(right);
			} // compare(Character, Character)
		    });

	    // Create a list of operations so that we can report
	    // on the operations that lead to an error.
	    ArrayList<String> ops = new ArrayList<String>();

	    // Set up our set of active keys
	    activeKeys.clear();

	    // Add/delete lots of elements.
	    for (int o = 0; o < NUMOPS; o++) {
		// We delete 1/4 of the time
		if (rand.nextInt(4) == 0) {
		    // Get the key
		    char key = keys.charAt(rand.nextInt(keyslen));
		    // Note that we're removing
		    ops.add("remove(" + key + ")");
		    // Do the actual work
		    dict.remove(key);
		    // Also remove it from our set
		    activeKeys.remove(key);
		    // Is it gone?
		    if (dict.containsKey(key)) {
			// Print out all of the operations to help
			// us understand what led to the failure.
			dict.dump(new PrintWriter(System.err, true));
			System.err.println(ops);
			fail("Dictionary still contains key " + key);
		    } // if the key is still there
		} else {
		    String value = words[rand.nextInt(wordslen)];
		    char key = value.charAt(0);
		    ops.add("add(" + key + "," + value + ")");
		    activeKeys.add(key);
		    dict.set(key, value);
		    if (!dict.containsKey(key)) {
			// Print out all of the operations to help
			// us understand what led to the failure.
			System.err.println(ops);
			fail("Dictionary does not contain key " + key);
		    } // if the key is not there
		    if (!dict.get(key).equals(value)) {
			// Print out all of the operations to help
			// us understand what led to the failure.
			System.err.println(ops);
			fail("dict[" + key + "] != " + value);
		    } // if the key has the wrong the value
		} // add case

		// Are all the active keys still active?
		for (Character active : activeKeys) {
		    if (!dict.containsKey(active)) {
			System.err.println(ops);
			fail("Dictionary no longer contains key " + active);
		    } // if
		} // for
	    } // for (o)
	} // for (i)
    } // randomTest()

    /**
     * All the cases from the documentation.
     */
    @Test
    public void removeTests() throws Exception {
	removeTests("mg"); // 01
	removeTests("mn"); // 02
	removeTests("mgnd"); // 03
	removeTests("mgndji"); // 04
	removeTests("mgndjil"); // 05
	removeTests("mgndjilk"); // 06
    } // removeTests()

    // +-----------+-------------------------------------------------------
    // | Utilities |
    // +-----------+

    /**
     * Build a tree by adding the characters in str, one at a time.
     */
    public BST<Character, Character> buildTree(String str) {
	// Build the empty tree.
	BST<Character, Character> tree = new BST<Character, Character>(
		new Comparator<Character>() {
		    public int compare(Character left, Character right) {
			return left.compareTo(right);
		    } // compare(Character, Character)
		});

	// Add all of the characters in order.
	int len = str.length();
	for (int i = 0; i < len; i++) {
	    char c = str.charAt(i);
	    tree.set(c, c);
	} // for

	// And we're done.
	return tree;
    } // buildTree(String)

    /**
     * Determine if a tree contains all of the characters in str.
     */
    public void check(BST<Character, Character> tree, String str) {
	// Check all of the characters in order
	int len = str.length();
	for (int i = 0; i < len; i++) {
	    char c = str.charAt(i);
	    if (!tree.containsKey(c)) {
		fail("Tree does not contain " + c);
	    } // if (! tree.containsKey(c))
	} // for
    } // check(BST<Character,Character>, String)

    /**
     * Try deletion at various parts of the tree.
     * 
     * @param str
     *            The description of the subtree. Should be one of the
     *            descriptions from the reading (or at least should not contain
     *            a, b, x, and y).
     */
    public void removeTests(String str) {
	// At the root.
	removeTest(str, "");
	// Immediate left subtree.
	removeTest(str, "z");
	// Immediate right subtree.
	removeTest(str, "a");
	// Left/left subtree.
	removeTest(str, "zy");
	// Left/right subtree.
	removeTest(str, "za");
	// Right/left subtree
	removeTest(str, "az");
	// Right/right subtree
	removeTest(str, "ab");
    } // removeTests(String)

    /**
     * One test.
     */
    public void removeTest(String str, String prefix) {
	Character ch = str.charAt(0);
	String remaining = str.substring(1);

	// Build the tree
	BST<Character, Character> tree = buildTree(prefix + str);
	// Remove the character, wherever it is
	tree.remove(ch);
	// Make sure that removal was successful
	assertFalse("removed " + ch, tree.containsKey(ch));
	// And make sure that every other character remains
	check(tree, prefix + remaining);
    } // removeTest(String, String)

} // SamTest
