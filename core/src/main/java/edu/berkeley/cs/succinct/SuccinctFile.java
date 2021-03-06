package edu.berkeley.cs.succinct;

import edu.berkeley.cs.succinct.regex.RegExMatch;
import edu.berkeley.cs.succinct.regex.parser.RegExParsingException;
import edu.berkeley.cs.succinct.util.Source;
import edu.berkeley.cs.succinct.util.container.Range;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;

public interface SuccinctFile extends Serializable {

  /**
   * Get the alphabet for the succinct file.
   *
   * @return The alphabet for the succinct file.
   */
  int[] getAlphabet();

  /**
   * Get the size of the uncompressed file.
   *
   * @return The size of the uncompressed file.
   */
  int getSize();

  /**
   * Get the size of the Succinct compressed file.
   *
   * @return The size of the Succinct compressed file.
   */
  int getCompressedSize();

  /**
   * Get character at ith index in file chunk.
   *
   * @param i Index relative to file chunk.
   * @return Character at specified index.
   */
  char charAt(long i);

  /**
   * Extract data of specified length from Succinct data structures at specified index.
   *
   * @param offset Index into original input to start extracting at.
   * @param len    Length of data to be extracted.
   * @return Extracted data.
   */
  String extract(long offset, int len);

  /**
   * Extract data from Succinct data structures at specified index until specified delimiter.
   *
   * @param offset Index into original input to start extracting at.
   * @param delim  Delimiter at which to stop extracting.
   * @return Extracted data.
   */
  String extractUntil(long offset, int delim);

  /**
   * Extract data of specified length from Succinct data structures at specified index.
   *
   * @param offset Index into original input to start extracting at.
   * @param len    Length of data to be extracted.
   * @return Extracted data.
   */
  byte[] extractBytes(long offset, int len);

  /**
   * Extract data from Succinct data structures at specified index until specified delimiter.
   *
   * @param offset Index into original input to start extracting at.
   * @param delim  Delimiter at which to stop extracting.
   * @return Extracted data.
   */
  byte[] extractBytesUntil(long offset, int delim);

  /**
   * Extract short integer at specified offset.
   *
   * @param offset Offset into the original input to start extracting at.
   * @return Extracted short integer.
   */
  short extractShort(int offset);

  /**
   * Extract integer at specified offset.
   *
   * @param offset Offset into the original input to start extracting at.
   * @return Extracted integer.
   */
  int extractInt(int offset);

  /**
   * Extract long integer at specified offset.
   *
   * @param offset Offset into the original input to start extracting at.
   * @return Extracted long integer.
   */
  long extractLong(int offset);

  /**
   * Perform a range search to obtain SA range between two given queries.
   *
   * @param buf1 The beginning of the range.
   * @param buf2 The end of the range.
   * @return The range into SA.
   */
  Range rangeSearch(char[] buf1, char[] buf2);

  /**
   * Perform a range search to obtain SA range between two given queries.
   *
   * @param buf1 The beginning of the range.
   * @param buf2 The end of the range.
   * @return The range into SA.
   */
  Range rangeSearch(byte[] buf1, byte[] buf2);

  /**
   * Perform a range search to obtain SA range between two given queries.
   *
   * @param buf1 The beginning of the range.
   * @param buf2 The end of the range.
   * @return The range into SA.
   */
  Range rangeSearch(Source buf1, Source buf2);

  /**
   * Perform backward search to obtain SA range for a query.
   *
   * @param buf Input query.
   * @return Range into SA.
   */
  Range bwdSearch(char[] buf);

  /**
   * Perform backward search to obtain SA range for a query.
   *
   * @param buf Input query.
   * @return Range into SA.
   */
  Range bwdSearch(byte[] buf);

  /**
   * Perform backward search to obtain SA range for a query.
   *
   * @param buf Input query.
   * @return Range into SA.
   */
  Range bwdSearch(Source buf);

  /**
   * Continue backward search on query to obtain SA range.
   *
   * @param buf   Input query.
   * @param range Range to start from.
   * @return Range into SA.
   */
  Range continueBwdSearch(char[] buf, Range range);

  /**
   * Continue backward search on query to obtain SA range.
   *
   * @param buf   Input query.
   * @param range Range to start from.
   * @return Range into SA.
   */
  Range continueBwdSearch(byte[] buf, Range range);

  /**
   * Continue backward search on query to obtain SA range.
   *
   * @param buf   Input query.
   * @param range Range to start from.
   * @return Range into SA.
   */
  Range continueBwdSearch(Source buf, Range range);

  /**
   * Compare entire buffer with input starting at specified index.
   *
   * @param buf The buffer to compare with.
   * @param i   The index into input.
   * @return -1 if buf is smaller, 0 if equal and 1 if buf is greater.
   */
  int compare(char[] buf, int i);

  /**
   * Compare entire buffer with input starting at specified index.
   *
   * @param buf The buffer to compare with.
   * @param i   The index into input.
   * @return -1 if buf is smaller, 0 if equal and 1 if buf is greater.
   */
  int compare(byte[] buf, int i);

  /**
   * Compare entire buffer with input starting at specified index.
   *
   * @param buf The buffer to compare with.
   * @param i   The index into input.
   * @return -1 if buf is smaller, 0 if equal and 1 if buf is greater.
   */
  int compare(Source buf, int i);

  /**
   * Compare entire buffer with input starting at specified index and offset
   * into buffer.
   *
   * @param buf    The buffer to compare with.
   * @param i      The index into input.
   * @param offset Offset into buffer.
   * @return -1 if buf is smaller, 0 if equal and 1 if buf is greater.
   */
  int compare(char[] buf, int i, int offset);

  /**
   * Compare entire buffer with input starting at specified index and offset
   * into buffer.
   *
   * @param buf    The buffer to compare with.
   * @param i      The index into input.
   * @param offset Offset into buffer.
   * @return -1 if buf is smaller, 0 if equal and 1 if buf is greater.
   */
  int compare(byte[] buf, int i, int offset);

  /**
   * Compare entire buffer with input starting at specified index and offset
   * into buffer.
   *
   * @param buf    The buffer to compare with.
   * @param i      The index into input.
   * @param offset Offset into buffer.
   * @return -1 if buf is smaller, 0 if equal and 1 if buf is greater.
   */
  int compare(Source buf, int i, int offset);

  /**
   * Perform forward search to obtain SA range for a query.
   *
   * @param buf Input query.
   * @return Range into SA.
   */
  Range fwdSearch(char[] buf);

  /**
   * Perform forward search to obtain SA range for a query.
   *
   * @param buf Input query.
   * @return Range into SA.
   */
  Range fwdSearch(byte[] buf);

  /**
   * Perform forward search to obtain SA range for a query.
   *
   * @param buf Input query.
   * @return Range into SA.
   */
  Range fwdSearch(Source buf);

  /**
   * Continue forward search on query to obtain SA range.
   *
   * @param buf    Input query.
   * @param range  Range to start from.
   * @param offset Offset into input query.
   * @return Range into SA.
   */
  Range continueFwdSearch(char[] buf, Range range, int offset);

  /**
   * Continue forward search on query to obtain SA range.
   *
   * @param buf    Input query.
   * @param range  Range to start from.
   * @param offset Offset into input query.
   * @return Range into SA.
   */
  Range continueFwdSearch(byte[] buf, Range range, int offset);

  /**
   * Continue forward search on query to obtain SA range.
   *
   * @param buf    Input query.
   * @param range  Range to start from.
   * @param offset Offset into input query.
   * @return Range into SA.
   */
  Range continueFwdSearch(Source buf, Range range, int offset);

  /**
   * Get count of pattern occurrences in original input.
   *
   * @param query Input query.
   * @return Count of occurrences.
   */
  long count(char[] query);

  /**
   * Get count of pattern occurrences in original input.
   *
   * @param query Input query.
   * @return Count of occurrences.
   */
  long count(byte[] query);

  /**
   * Get count of pattern occurrences in original input.
   *
   * @param query Input query.
   * @return Count of occurrences.
   */
  long count(Source query);

  /**
   * Converts Succinct index (i.e., Compressed Suffix Array index) to file offset.
   *
   * @param i Compressed Suffix Array index.
   * @return File offset.
   */
  Long succinctIndexToOffset(long i);

  /**
   * Translate range into SA to recordIds in file.
   *
   * @param range Range into SA.
   * @return Offsets corresponding to recordIds.
   */
  Long[] rangeToOffsets(Range range);

  /**
   * Search for locations of pattern occurrences in original input.
   *
   * @param query Input query.
   * @return All locations of pattern occurrences in original input.
   */
  Iterator<Long> searchIterator(char[] query);

  /**
   * Search for locations of pattern occurrences in original input.
   *
   * @param query Input query.
   * @return All locations of pattern occurrences in original input.
   */
  Iterator<Long> searchIterator(byte[] query);

  /**
   * Search for locations of pattern occurrences in original input.
   *
   * @param query Input query.
   * @return All locations of pattern occurrences in original input.
   */
  Iterator<Long> searchIterator(Source query);

  /**
   * Get all locations of pattern occurrences in original input.
   *
   * @param query Input query.
   * @return All locations of pattern occurrences in original input.
   */
  Long[] search(char[] query);

  /**
   * Get all locations of pattern occurrences in original input.
   *
   * @param query Input query.
   * @return All locations of pattern occurrences in original input.
   */
  Long[] search(byte[] query);

  /**
   * Get all locations of pattern occurrences in original input.
   *
   * @param query Input query.
   * @return All locations of pattern occurrences in original input.
   */
  Long[] search(Source query);

  /**
   * Check if the two offsets belong to the same record. This must always true for the
   * SuccinctFile, but may not be true for the SuccinctIndexedFile.
   *
   * @param firstOffset  The first offset.
   * @param secondOffset The second offset.
   * @return True if the two offsets belong to the same record, false otherwise.
   */
  boolean sameRecord(long firstOffset, long secondOffset);

  /**
   * Performs regular expression search for an input expression using Succinct data-structures.
   *
   * @param query Regular expression pattern to be matched. (UTF-8 encoded)
   * @return All locations and lengths of matching patterns in original input.
   * @throws RegExParsingException
   */
  Set<RegExMatch> regexSearch(String query) throws RegExParsingException;

  /**
   * Reads Succinct data structures from a DataInputStream.
   *
   * @param is Stream to read data structures from.
   * @throws IOException
   */
  void readFromStream(DataInputStream is) throws IOException;

  /**
   * Write Succinct data structures to a DataOutputStream.
   *
   * @param os Output stream to write data to.
   * @throws IOException
   */
  void writeToStream(DataOutputStream os) throws IOException;

}
