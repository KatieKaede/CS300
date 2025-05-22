//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P07 Twitter Feed
// Course:   CS 300 Spring 2023
//
// Author:   Katie Krause
// Email:    klkrause5@wisc.edu
// Lecturer: Mouna Kacem

///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         (identify each by name and describe how they helped)
// Online Sources:  (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////
/**
 * An enum representing the three states of the timeline:
 * - Chronological (in decreasing time order)
 * - Verified only (only tweets from verified users, in decreasing time order)
 * - High engagement (only tweets with a total engagement level over a given threshold, in
 *   decreasing time order)
 */
public enum TimelineMode {
  CHRONOLOGICAL, VERIFIED_ONLY, LIKE_RATIO;
}
