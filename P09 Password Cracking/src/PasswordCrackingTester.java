//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P09 Password Cracking
// Course:   CS 300 Spring 2023
//
// Author:   Katie Krause
// Email:    klkrause5@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         (identify each by name and describe how they helped)
// Online Sources:  (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////
import java.util.NoSuchElementException;

public class PasswordCrackingTester {

  /**
   * Validates the constructor and accessor methods of PasswordStorage, specifically the
   * getComparisonCriteria(), size(), and isEmpty() methods, as well as accessing the
   * protected data field root.
   * 
   * Be sure to try making multiple PasswordStorage objects with different Attributes.
   * @return true if the basic accessor methods work as expected, false otherwise
   */
  public static boolean testBasicPasswordStorageMethods() {
	  PasswordStorage hashedStorage = new 
			  PasswordStorage(Attribute.HASHED_PASSWORD);
	  PasswordStorage occurrenceStorage = new 
			  PasswordStorage(Attribute.OCCURRENCE);
	  PasswordStorage strengthStorage = new 
			  PasswordStorage(Attribute.STRENGTH_RATING);
	  
	  // Test getComparisonCriteria() method
	  if (hashedStorage.getComparisonCriteria() 
			  != Attribute.HASHED_PASSWORD) {
		  return false;
	  }
	  if (occurrenceStorage.getComparisonCriteria() 
			  != Attribute.OCCURRENCE) {
	      return false;
	  }
	  if (strengthStorage.getComparisonCriteria() 
			  != Attribute.STRENGTH_RATING) {
		  return false;
	  }
	  
	  // Test size() and isEmpty()
	  if (hashedStorage.size() != 0 || !hashedStorage.isEmpty()) {
		  return false;
	  }
	  
	  // Testing the data field root
	  if (hashedStorage.root != null) {
		  return false;
	  }
	  return true; // DONE
  }

  /**
   * Validates the Password class compareTo() method. 
   * Create at least two DIFFERENT
   * Password objects and compare them on 
   * each of the Attribute values. See the writeup
   * for details on how the various comparisons are expected to work.
   * 
   * @return true if Password's compareTo() works as expected, 
   * false otherwise
   */
  public static boolean testPasswordCompareTo() {
	  Password password1 = new Password("password", 100);
	  Password password2 = new Password("StronkPass12#", 50);
	  Password equal1 = new Password("equal", 50);
	  Password equal2 = new Password ("equal", 50);
	  
	  // Testing OCCURRENCE
	  int occurrenceComparison = password1.compareTo(password2,
			  Attribute.OCCURRENCE);
	  if (occurrenceComparison <= 0) {
		  return false;
	  }
	  
	  // Testing STRENGTH_RATING
	  int strengthComparison = password1.compareTo(password2,
			  Attribute.STRENGTH_RATING);
	  if (strengthComparison >= 0) {
		  return false;
	  }
	  
	  // Testing HASHED_PASSWORD
	  int hashedComparison = password1.compareTo(password2,
			  Attribute.HASHED_PASSWORD);
	  if (hashedComparison >= 0) {
		  return false;
	  }
	  
	  // Testing that equal passwords return 0
	  int occurrenceEqual = equal1.compareTo(equal2,
			  Attribute.OCCURRENCE);
	  int strengthEqual = equal1.compareTo(equal2,
			  Attribute.STRENGTH_RATING);
	  int hashedEqual = equal1.compareTo(equal2,
			  Attribute.HASHED_PASSWORD);
	  
	  if (occurrenceEqual != 0 || strengthEqual != 0 ||
			  hashedEqual != 0) {
		  System.out.println(occurrenceEqual);
		  return false;
	  }  
    return true; // DONE
  }

  /**
   * Validates the incomplete methods in PasswordNode, 
   * specifically isLeafNode(),
   * numberOfChildren(), hasLeftChild() and hasRightChild(). 
   * Be sure to test all
   * possible configurations of a node in a binary tree!
   * 
   * @return true if the status methods of PasswordNode work as expected, false otherwise
   */
  public static boolean testNodeStatusMethods() {
	  PasswordNode leafNode = new PasswordNode(new Password("leaf", 1));
	  PasswordNode otherLeafNode = new PasswordNode(new Password
			  ("secondLeaf", 8));
	  PasswordNode rightNode = new PasswordNode(
			  new Password("rightNode", 5), null,
			  otherLeafNode);
	  PasswordNode leftNode = new PasswordNode(
			  new Password("left", 2), leafNode, null);
	  PasswordNode rootNode = new PasswordNode(new Password
			  ("rootNode", 4),
			  leftNode, rightNode);
	  
	  // Testing isLeafNode
	  if (!leafNode.isLeafNode() || !otherLeafNode.isLeafNode() ||
			  rightNode.isLeafNode() || leftNode.isLeafNode() ||
			  rootNode.isLeafNode()) {
		  return false;
	  }
	  
	  // Testing hasRightChild
	  if (leafNode.hasRightChild() || otherLeafNode.hasRightChild()
			  || !rightNode.hasRightChild() ||
			  leftNode.hasRightChild() ||
			  !rootNode.hasRightChild()) {
		  return false;
	  }
	  
	  // Testing hasLeftChild
	  if (leafNode.hasLeftChild() || otherLeafNode.hasRightChild() ||
			  rightNode.hasLeftChild() ||
			  !leftNode.hasLeftChild() ||
			  !rootNode.hasLeftChild()) {
		  return false;
	  }
	  
	  // Testing numberOfChildren
	  if (leafNode.numberOfChildren() != 0 ||
			  otherLeafNode.numberOfChildren() != 0 ||
			  rightNode.numberOfChildren() != 1||
			  leftNode.numberOfChildren() != 1|| 
			  rootNode.numberOfChildren() != 2) {
		  return false;
	  }
    return true; // DONE
  }

  // GIVE TO STUDENTS
  public static boolean testToString() {
    try {
      PasswordStorage bst = new PasswordStorage(Attribute.OCCURRENCE);

      // empty is empty string
      String expected = "";
      String actual = bst.toString();
      if (!actual.equals(expected)) {
        System.out.println("toString() does not return the proper "
        		+ "value on an empty tree!");
        return false;
      }

      // size one only returns 1 thing
      Password p = new Password("1234567890", 15000);
      PasswordNode rootNode = new PasswordNode(p);

      bst.root = rootNode; // here I am manually building the 
      //tree by editing the root node
      // directly to be the node of my choosing

      expected = p.toString() + "\n";
      actual = bst.toString();
      if (!actual.equals(expected))
        return false;


      // big tree returns in-order traversal
      Password p2 = new Password("test", 500);
      Password p3 = new Password("iloveyou", 765);
      Password p4 = new Password("qwerty", 250);
      Password p5 = new Password("admin", 1002);
      Password p6 = new Password("password", 2232);
      Password p7 = new Password("abc123", 2090);

      PasswordNode p4Node = new PasswordNode(p4);
      PasswordNode p3Node = new PasswordNode(p3);
      PasswordNode p7Node = new PasswordNode(p7);
      PasswordNode p6Node = new PasswordNode(p6, p7Node, null);
      PasswordNode p5Node = new PasswordNode(p5, null, p6Node);
      PasswordNode p2Node = new PasswordNode(p2, p4Node, p3Node);
      rootNode = new PasswordNode(p, p2Node, p5Node);
      bst.root = rootNode;

      expected = p4.toString() + "\n" + p2.toString() + "\n" + p3.toString() + "\n" + p.toString()
      + "\n" + p5.toString() + "\n" + p7.toString() + "\n" + p6.toString() + "\n";
      actual = bst.toString();

      if (!actual.equals(expected))
        return false;

    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    return true; // Done for me
  }

  // GIVE TO STUDENTS
  public static boolean testIsValidBST() {
    try {
      PasswordStorage bst = new PasswordStorage(Attribute.OCCURRENCE);

      // empty tree is a valid bst
      /*
       * String expected = ""; String actual = bst.toString();
       */
      if (!bst.isValidBST()) {
        System.out.println("isValidBST() says that an empty tree is not a valid BST!");
        return false;
      }

      // size one is a bst
      Password p = new Password("1234567890", 1000);
      PasswordNode rootNode = new PasswordNode(p);

      bst.root = rootNode; // here I am manually building the tree by editing the root node
      // directly to be the node of my choosing

      if (!bst.isValidBST()) {
        System.out.println("isValidBST() says that a tree of size 1 is not a valid BST!");
        return false;
      }

      Password p2 = new Password("test", 500);
      Password p3 = new Password("iloveyou", 765);
      Password p4 = new Password("qwerty", 250);
      Password p5 = new Password("admin", 1002);
      Password p6 = new Password("password", 2232);
      Password p7 = new Password("abc123", 2090);

      // works on indentifying small obviously invalid tree
      PasswordNode p7Node = new PasswordNode(p7);
      PasswordNode p3Node = new PasswordNode(p3);
      rootNode = new PasswordNode(p, p7Node, p3Node);
      bst.root = rootNode;
      if (bst.isValidBST())
        return false;

      // tree with only one subtree being valid, other subtree has a violation a couple layers deep


      PasswordNode p4Node = new PasswordNode(p4);
      p7Node = new PasswordNode(p7);
      p3Node = new PasswordNode(p3);
      PasswordNode p6Node = new PasswordNode(p6, null, p7Node);
      PasswordNode p5Node = new PasswordNode(p5, null, p6Node);
      PasswordNode p2Node = new PasswordNode(p2, p4Node, p3Node);
	  rootNode = new PasswordNode(p, p2Node, p5Node);
      bst.root = rootNode;

      if (bst.isValidBST()) {
        System.out
        .println("isValidBST() says that a tree with only one valid subtree is a valid bst");
        return false;
      }


      // works on valid large tree
      p4Node = new PasswordNode(p4);
      p3Node = new PasswordNode(p3);
      p7Node = new PasswordNode(p7);
      p6Node = new PasswordNode(p6, p7Node, null);
      p5Node = new PasswordNode(p5, null, p6Node);
      p2Node = new PasswordNode(p2, p4Node, p3Node);
      rootNode = new PasswordNode(p, p2Node, p5Node);
      bst.root = rootNode;

      if (!bst.isValidBST())
        return false;

      PasswordNode one = new PasswordNode(p4);
      PasswordNode three = new PasswordNode(p3, one, null);
      PasswordNode two = new PasswordNode(p2, null, three);
      bst.root = two;

      if (bst.isValidBST()) {
        System.out.println("bad bst is valid");
        return false;
      }


    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    return true; // Done for me
  }

  /**
   * Validates the PasswordStorage's method lookup() method
   * By creating a BST that is valid with all three attributes, we test that
   * searching for a password that doesn't exist returns null.
   * Then we test that a password deeper on the left or right side can be found despite
   * the tree
   * @return true if all tests pass, false otherwise
   */
  public static boolean testLookup() {
	  try {
		  // Make three trees of different attributes
		  PasswordStorage bstOccurrence = 
				  new PasswordStorage(Attribute.OCCURRENCE);
	      PasswordStorage bstStrength = 
	    		  new PasswordStorage(Attribute.STRENGTH_RATING);
	      PasswordStorage bstHash = 
	    		  new PasswordStorage(Attribute.HASHED_PASSWORD);

	      // insert passwords into the BST
	      Password p = new Password("example", 1000);
	      Password p2 = new Password("test", 500);
	      Password p3 = new Password("qwerty", 765);
	      Password p4 = new Password("see", 250);
	      Password p5 = new Password("fakePass", 1002);
	      Password p6 = new Password("boredPass", 2232);
	      Password p7 = new Password("myBirthday", 2500);
	      
	      PasswordNode p4Node = new PasswordNode(p4);
	      PasswordNode p3Node = new PasswordNode(p3);
	      PasswordNode p7Node = new PasswordNode(p7);
	      PasswordNode p6Node = new PasswordNode(p6, null, p7Node);
	      PasswordNode p5Node = new PasswordNode(p5, null, p6Node);
	      PasswordNode p2Node = new PasswordNode(p2, p4Node, p3Node);
	      PasswordNode rootNode = new PasswordNode(p, p2Node, p5Node);

	      bstOccurrence.root = rootNode;
	      bstStrength.root = rootNode;
	      bstHash.root = rootNode;
	      
	      // Verify that tree is valid with each criteria
	      if (!bstOccurrence.isValidBST()) {
	          return false;
	      }
	      
	      if (!bstStrength.isValidBST()) {
	          return false;
	      }
	       
	      if (!bstHash.isValidBST()) {
	          return false;
	      }
	      
	      // Try looking up a password not in the BST
	      Password notRealPass = new Password("doesntExist", 1);
	      Password searchFake = bstOccurrence.lookup(notRealPass);
	      if (searchFake != null) {
	    	  return false;
	      }
	      
	      // Search for a password that should be in the right subtree
	      // Strength test
	      Password rightResult = bstStrength.lookup(p6);
	      if (!rightResult.equals(p6)) {
	    	  return false;
	      }
	      
	      // Search for a password that should be in the left subtree
	      // Hash test
	      Password leftResult = bstHash.lookup(p4);
	      if (!leftResult.equals(p4)) {
	    	  return false;
	      }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    } 
	    return true;
	}

  
  /**
   * A variety of tests that ensures PasswordStorage addPassword() works as
   * intended
   * @return
   */
  public static boolean testAddPassword() {
	  try {
		  PasswordStorage newbst = new PasswordStorage
				  (Attribute.HASHED_PASSWORD);
		  
		  // Adding passwords of different attributes
		  Password p1 = new Password("qwerty", 5000);
		  newbst.addPassword(p1);
		  
		  if (newbst.size() != 1 || !newbst.isValidBST()) {
			  return false;
		  }
		  
		  newbst = new PasswordStorage(Attribute.OCCURRENCE);
		  Password p2 = new Password("password", 1000);
		  newbst.addPassword(p2);
		  
		  if (newbst.size() != 1 || !newbst.isValidBST()) {
			  return false;
		  }
		  
		  newbst = new PasswordStorage(Attribute.STRENGTH_RATING);
		  Password p3 = new Password("admin", 50);
		  newbst.addPassword(p3);
		  
		  if (newbst.size() != 1 || !newbst.isValidBST()) {
			  return false;
		  }
		  
		  // Adding a bunch of passwords
		  newbst = new PasswordStorage(Attribute.OCCURRENCE);
		  
		  Password pass1 = new Password("fakePass", 1002);
	      Password pass2 = new Password("boredPass", 2232);
		  newbst.addPassword(pass1);
		  newbst.addPassword(pass2);
		  newbst.addPassword(p1);
		  newbst.addPassword(p2);
		  newbst.addPassword(p3);
		  
		  if (newbst.size() != 5 || !newbst.isValidBST()) {
			  return false;
		  }
		  
		  // Checking if any children are overwritten incorrectly
		  newbst = new PasswordStorage(Attribute.OCCURRENCE);
		  Password pass3 = new Password("ugh", 1500);
		  newbst.addPassword(p1);
		  newbst.addPassword(p2);
		  newbst.addPassword(pass3);
		  if (newbst.root.getLeft().getRight() == null || 
				  !newbst.root.getLeft().getRight().
				  getPassword().equals(pass3)) {
			  return false;
		  }
	  } catch (Exception e) {
	  e.printStackTrace();
	  return false;
	  }
	  return true;
  }

  /**
   * Tests the removePassword() method by creating a BST 
   * with some passwords, removing a password,
   * and then checking that the removed password is no longer in the BST.
   * @return true if the removePassword() 
   * method passes all tests, false otherwise
   */
  public static boolean testRemovePassword() {
	  PasswordStorage bstOccurrence = 
			  new PasswordStorage(Attribute.OCCURRENCE);
      PasswordStorage bstStrength = 
    		  new PasswordStorage(Attribute.STRENGTH_RATING);
      PasswordStorage bstHash = 
    		  new PasswordStorage(Attribute.HASHED_PASSWORD);

      // insert passwords into the BST
      Password p = new Password("example", 1000); //1
      Password p2 = new Password("test", 500); //2
      Password p3 = new Password("qwerty", 765);
      Password p4 = new Password("eye", 250);
      Password p5 = new Password("fakePass", 1002); //3 right of root
      Password p6 = new Password("boredPass", 2232);
      Password p7 = new Password("myBirthday", 2500);
      Password occurrencePass = new Password("er", 100);
      Password strengthPass = new Password("foreSt", 1001);
      Password hashedPass = new Password("core", 260);
      
      PasswordNode p4Node = new PasswordNode(p4);
      PasswordNode p3Node = new PasswordNode(p3);
      PasswordNode p7Node = new PasswordNode(p7);
      PasswordNode p6Node = new PasswordNode(p6, null, p7Node);
      PasswordNode p5Node = new PasswordNode(p5, null, p6Node);
      PasswordNode p2Node = new PasswordNode(p2, p4Node, p3Node);
      PasswordNode rootNode = new PasswordNode(p, p2Node, p5Node);

      bstOccurrence.root = rootNode;
      bstStrength.root = rootNode;
      bstHash.root = rootNode;
      
      // Try removing a non-existent password
      try {
    	  bstOccurrence.removePassword(new Password("notReal", 100));
    	  return false;
      } catch (NoSuchElementException e) {
    	  // exception caught
      }
      
      // remove a node with one right child
      bstStrength.removePassword(p6);
      Password rightParent = bstStrength.lookup(p6);
      Password rightChild = bstStrength.lookup(p7);
      if (rightParent != null) {
    	  return false;
      }
      
      if (!rightChild.equals(p7)) {
    	  return false;
      }
      
      // remove a leaf node
      bstStrength.removePassword(p7);
      Password removed = bstStrength.lookup(p7);
      if (removed != null) {
    	  return false;
      }
      
      // Adding a password and then removing it 
      // when it's a node's only left child
      bstHash.addPassword(hashedPass);
      bstHash.removePassword(hashedPass);
      Password removedForest = bstHash.lookup(hashedPass);
      if (removedForest != null) {
    	  return false;
      }
      
      // Remove the root node
      bstHash.removePassword(p);
      Password rootRemove = bstHash.lookup(p);
      if (rootRemove != null) {
    	  return false;
      }

      
    return true;
  }

  public static void main(String[] args) {
	  runAllTests();
  }

  public static boolean runAllTests() {
    boolean compareToPassed = testPasswordCompareTo();
    boolean nodeStatusPassed = testNodeStatusMethods();
    boolean basicMethodsPassed = testBasicPasswordStorageMethods();
    boolean toStringPassed = testToString();
    boolean isValidBSTPassed = testIsValidBST();
    boolean lookupPassed = testLookup();
    boolean addPasswordPassed = testAddPassword();
    boolean removePasswordPassed = testRemovePassword();

    System.out.println("Password compareTo: " + (compareToPassed ? "PASS" : "FAIL"));
    System.out.println("PasswordNode Status Methods: " + (nodeStatusPassed ? "PASS" : "FAIL"));
    System.out.println("PasswordStorage Basic Methods: " + (basicMethodsPassed ? "PASS" : "FAIL"));
    System.out.println("PasswordStorage toString: " + (toStringPassed ? "PASS" : "FAIL"));
    System.out.println("PasswordStorage isValidBST: " + (isValidBSTPassed ? "PASS" : "FAIL"));
    System.out.println("PasswordStorage lookup: " + (lookupPassed ? "PASS" : "FAIL"));
    System.out.println("PasswordStorage addPassword: " + (addPasswordPassed ? "PASS" : "FAIL"));
    System.out.println("PasswordStorage removePassword: " + (removePasswordPassed ? "PASS" : "FAIL"));

    // AND ANY OTHER ADDITIONAL TEST METHODS YOU MAY WANT TO WRITE!

    return compareToPassed && nodeStatusPassed && basicMethodsPassed && toStringPassed
        && isValidBSTPassed && lookupPassed && addPasswordPassed && removePasswordPassed;
  }

}
