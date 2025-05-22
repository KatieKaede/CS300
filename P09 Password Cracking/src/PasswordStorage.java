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

public class PasswordStorage {

  protected PasswordNode root; //the root of this BST that 
  //contains passwords
  private int size; //how many passwords are in the BST
  private final Attribute COMPARISON_CRITERIA; //what password 
  //information to use to determine order in the tree

  /**
   * Constructor that creates an empty BST and sets the comparison criteria.
   * @param comparisonCriteria, the Attribute that 
   * will be used to determine order in the tree
   */
  public PasswordStorage(Attribute comparisonCriteria) {
    this.COMPARISON_CRITERIA = comparisonCriteria;
  }

  /**
   * Getter for this BST's criteria for determining order in the three
   * @return the Attribute that is being used to 
   * make comparisons in the tree
   */
  public Attribute getComparisonCriteria() {
    return COMPARISON_CRITERIA;
  }

  /**
   * Getter for this BST's size.
   * @return the size of this tree
   */
  public int size() {
    return size;
  }

  /**
   * Determines whether or not this tree is empty.
   * @return true if it is empty, false otherwise
   */
  public boolean isEmpty() {
    return root == null;
  }

  /**
   * Provides in-order String representation of this BST, 
   * with each Password on its own line. The
   * String representation ends with a newline character ('\n')
   * @return this BST as a string
   */
  @Override
  public String toString() {
    return toStringHelper(this.root);
  }

  /**
   * Recursive method the uses an in-order traversal to 
   * create the string representation of this tree.
   * @param currentNode, the root of the current tree
   * @return the in-order String representation of the 
   * tree rooted at current node
   */
  private String toStringHelper(PasswordNode currentNode) {
	    if (currentNode == null) {
	        return "";
	    }
	    String leftString = toStringHelper(currentNode.getLeft());
	    String rightString = toStringHelper(currentNode.getRight());
	    String nodeString = currentNode.getPassword().toString() + "\n";
	    return leftString + nodeString + rightString;
	}


  /**
   * Determines whether or not this tree is actually a valid BST.
   * @return true if it is a BST, false otherwise
   */
  public boolean isValidBST() {
    return isValidBSTHelper(this.root, Password.getMinPassword(), Password.getMaxPassword());
  }

  /**
   * Recurisvely determines if the the tree rooted at the 
   * current node is a valid BST. That is, every
   * value to the left of currentNode is "less than" 
   * the value in currentNode and every value to the
   * right of it is "greater than" it.
   * 
   * @param currentNode, the root node of the current tree
   * @param lowerBound, the smallest possible password
   * @param upperBound, the largest possible password
   * @return true if the tree rooted at currentNode is a BST, 
   * false otherwise
   */
  private boolean isValidBSTHelper(PasswordNode currentNode,
		  Password lowerBound, Password upperBound) {
    //MUST BE IMPLEMENTED RECURSIVELY

    // BASE CASE 1: the tree rooted at currentNode is empty, 
	//which does not violate any BST rules
	  if (currentNode == null) {
		  return true;
	  }

    // BASE CASE 2: the current Password is outside of the upper OR 
	// lower bound for this subtree, which is against
    // the rules for a valid BST
	  if (currentNode.getPassword().compareTo(lowerBound, 
			  getComparisonCriteria()) < 0 ||
			  currentNode.getPassword().compareTo(upperBound, 
					  getComparisonCriteria()) > 0) {
		  return false;
	  }

    // If we do not have a base case situation, we must use 
	//recursion to verify currentNode's child subtrees

    // RECURSIVE CASE 1: Check that the left subtree contains only 
	// Passwords greater than lowerBound and less than
    // currentNode's Password; return false if this property is 
	// NOT satisfied
	  
	  boolean leftTreeValidity = isValidBSTHelper
			  (currentNode.getLeft()
			  , lowerBound,
			  currentNode.getPassword());

    // RECURSIVE CASE 2: Check that the right subtree contains 
	// only Passwords greater than currentNode's Password
    // and less than upperBound; return false 
	// if this property is NOT satisfied
	  
	  boolean rightTreeValidity = isValidBSTHelper
			  (currentNode.getRight(),
			  currentNode.getPassword(), upperBound);

    // COMBINE RECURSIVE CASE ANSWERS: this is a valid 
	// BST if and only if both case 1 and 2 are valid
	  return leftTreeValidity && rightTreeValidity;
  }

  /**
   * Returns the password that matches the criteria of the provided key.
   * Ex. if the COMPARISON CRITERIA is OCCURRENCE and the key has an 
   * occurrence of 10
   * it will return the password stored in the tree with occurrence of 10
   * @param key, the password that contains the information for the 
   * password we are searching for
   * @return the Password that matches the search criteria, 
   * if it does not exist in the tree it this will be null
   */
  public Password lookup(Password key) {
    return lookupHelper(key, root);
  } 

  /**
   * Recursive helper method to find the matching password in this BST
   * @param key, password containing the information we are searching for
   * @param currentNode, the node that is the current root of the tree
   * @return the Password that matches the search criteria, 
   * if it does not exist in the tree it this will be null
   */
  private Password lookupHelper(Password key, PasswordNode currentNode) {
	  	if (currentNode == null) {
	        return null;
	    }
	    
	    // If the current node contains the password we are searching for
	  	// , return it
	    if (currentNode.getPassword().equals(key)) {
	        return currentNode.getPassword();
	    }
	    
	    // If the password is less than the current node's password,
	    // search the left subtree
	    if (key.compareTo(currentNode.getPassword(), 
	    		getComparisonCriteria()) < 0) {
	        return lookupHelper(key, currentNode.getLeft());
	    }
	    
	    // If the password is greater than the current node's password,
	    // search the right subtree
	    if (key.compareTo(currentNode.getPassword(), 
	    		getComparisonCriteria()) > 0) {
	        return lookupHelper(key, currentNode.getRight());
	    }
	    
	    // If we haven't found the key yet, we return null
	    return null;
	}

  /**
   * Returns the best (max) password in this BST
   * 
   * @return the best password in this BST
   * @throws NoSuchElementException if the BST is empty
   */
  public Password getBestPassword() {
    if (root == null) {
    	throw new NoSuchElementException("The BST is Empty");
    }
    
    PasswordNode currentNode = root;
 // Traverse the right to find the biggest password
    while (currentNode.getRight() != null) {
    	currentNode = currentNode.getRight();
    }
    return currentNode.getPassword();
  }

  /**Returns the worst password in this BST
   * 
   * @return the worst password in this BST
   * @throws NoSuchElementException if the BST is empty
   */
  public Password getWorstPassword() {
    if (root == null) {
    	throw new NoSuchElementException("The BST is Empty");
    }
   
    PasswordNode currentNode = root;
    // Traverse the left to find the smallest password
    while (currentNode.getLeft() != null) {
    	currentNode = currentNode.getLeft();
    }
    return currentNode.getPassword();
  }

  /**
   * Adds the Password to this BST.
   * @param toAdd, the password to be added to the tree
   * @throws IllegalArgumentException if the (matching) password 
   * object is already in the tree
   */
  public void addPassword(Password toAdd) {
	  if (root == null) {
	        root = new PasswordNode(toAdd);
	        size++;
	    } else {
	    	if (!addPasswordHelper(toAdd, root)) {
	    		throw new IllegalArgumentException("Password "
	    				+ "already exists in tree.");
	        }
	    }
	}

  /**
   * Recursive helper that traverses the tree and adds 
   * the password where it belongs
   * @param toAdd, the password to add to the tree
   * @param currentNode, the node that is the current root of the (sub)tree
   * @return true if it was successfully added, false otherwise
   */
  private boolean addPasswordHelper(Password toAdd, PasswordNode currentNode) {
	  // Checks if the password already exists in the tree
	  if (currentNode.getPassword().equals(toAdd)) {
		  return false;
		  }
	  
	  if (toAdd.compareTo(currentNode.getPassword(), 
			  getComparisonCriteria()) < 0) {
		  if (currentNode.getLeft() == null) {
			  currentNode.setLeft(new PasswordNode(toAdd));
			  size++;
			  return true;
			  } else {
				  return addPasswordHelper(toAdd, 
						  currentNode.getLeft());
				  }
		  } else {
			  if (currentNode.getRight() == null) {
				  currentNode.setRight(new 
						  PasswordNode(toAdd));
				  size++;
				  return true;
				  } else {
					  return addPasswordHelper(toAdd, 
							  currentNode.getRight());
				  }
		  }
  }

  /**
   * Removes the matching password from the tree
   * @param toRemove, the password to be removed from the tree
   * @throws NoSuchElementException if the password is not in the tree
   */
  public void removePassword(Password toRemove) {
	    if (root == null) {
	        throw new NoSuchElementException("Tree is empty,"
	        		+ " password not in tree");
	    }
	    root = removePasswordHelper(toRemove, root);
	    size--;
	}
  
  /**
   * This private method starts from the left child of 
   * the node being removed
   * then keeps moving to the right until a node is reached 
   * without a right child
   * The node found and returned is the predecessor
   * @param node
   * @return the predecessor of the node to be removed
   */
  private PasswordNode findPredecessor(PasswordNode node) {
	    PasswordNode current = node.getLeft();
	    while (current.getRight() != null) {
	        current = current.getRight();
	    }
	    return current;
	}

  /**
   * Recursive helper method to that removes the password from this BST.
   * @param toRemove, the password to be removed from the tree
   * @param currentNode, the root of the tree we are removing from
   * @return the PasswordNode representing the NEW root of 
   * this subtree now that toRemove
   * has been removed. This may still be currentNode, 
   * or it may have changed!
   */
  private PasswordNode removePasswordHelper(Password toRemove, 
		  PasswordNode currentNode) {
	  //BASE CASE: current tree is empty 
	  if (currentNode == null) {
	        throw new NoSuchElementException("Password not "
	        		+ "found in tree");
	    }

    //RECURSIVE CASE: toRemove is in the left subtree, continue searching
	  if (toRemove.compareTo(currentNode.getPassword(),
			  getComparisonCriteria()) < 0) {
		  currentNode.setLeft(removePasswordHelper(toRemove,
				  currentNode.getLeft()));
	    }

    //RECURSIVE CASE: toRemove is in the right subtree, continue searching
	  else if (toRemove.compareTo(currentNode.getPassword()
			  , getComparisonCriteria()) > 0) {
		  currentNode.setRight(removePasswordHelper(toRemove,
				  currentNode.getRight()));
	    }

    //otherwise we found the node to remove!
	  else {
		  //BASE CASE: current node has no children
		  if (currentNode.getLeft() == null &&
				  currentNode.getRight() == null) {
			  size--;
			  return null;
		  }
		  //BASE CASE(S): current node has one child
		  // (one for the left and right respectively)
		  else if (currentNode.getLeft() == null) {
			  size--;
			  return currentNode.getRight();
		  } 
		  else if (currentNode.getRight() == null) {
			  size--;
	          return currentNode.getLeft();
		  }
		  
    //RECURSIVE CASE: currentNode has 2 children
		  else {
			  //1)Find the predecessor password [HINT:
			  // Write a private helper method!] 
			  Password predecessorPassword = findPredecessor
					  (currentNode.getLeft()).getPassword();
			  
			  //2)Make new node for the predecessor password. 
			  // It should have same left and right subtree 
			  // as the current node.
			  PasswordNode predecessorNode = new PasswordNode
					  (predecessorPassword, 
							  currentNode.getLeft(), currentNode.getRight());
			  
			  //3)Replace currentNode with 
			  // the new predecessor node
			  currentNode = predecessorNode;
			  
			  //4)Remove the (duplicate) predecessor 
			  // from the current tree and update the left subtree
			  currentNode.setLeft(removePasswordHelper
					  (predecessorPassword,
							  currentNode.getLeft()));
		  }
	  }
    return currentNode; //LEAVE THIS LINE AS IS
  }
}
