package edu.berkeley.cs.succinct.util.bitmap;

import edu.berkeley.cs.succinct.util.CommonUtils;

public class BMArray extends BitMap {

  public int bits;
  public int n;

  /**
   * Constructor to initialize bitmap array.
   *
   * @param n    Number of values in bitmap.
   * @param bits width of each value in bits.
   */
  public BMArray(int n, int bits) {
    super(((long) n) * ((long) bits));

    this.n = n;
    this.bits = bits;
  }

  /**
   * Constructor to convert a part of an integer array into a bitmap array.
   *
   * @param input  Input integer array.
   * @param offset Beginning offset into input.
   * @param length Length of sub-array to convert.
   */
  public BMArray(int[] input, int offset, int length) {
    super(((long) length) * ((long) CommonUtils.intLog2(length)));

    this.n = length;
    this.bits = CommonUtils.intLog2(this.n + 1);
    for (int i = offset; i < offset + length; i++) {
      this.setVal(i - offset, input[i]);
    }
  }

  /**
   * Set value in the bitmap array at a certain index.
   *
   * @param i   Index of value to be set.
   * @param val Value to be set.
   */
  public final void setVal(int i, long val) {

    assert (i >= 0 && i < this.n);
    assert (val >= 0);

    long s = (long) (i) * this.bits;
    long e = s + (this.bits - 1);
    if ((s / 64) == (e / 64)) {
      this.data[(int) (s / 64L)] |= (val << (63L - e % 64));
    } else {
      this.data[(int) (s / 64L)] |= (val >>> (e % 64 + 1));
      this.data[(int) (e / 64L)] |= (val << (63L - e % 64));
    }
  }

  /**
   * Get value in the bitmap array at a certain index.
   *
   * @param i Index of the value to get.
   * @return The value at specified index.
   */
  public final long getVal(int i) {

    assert (i >= 0 && i < (this.n));

    long val;
    long s = (long) (i) * this.bits;
    long e = s + (this.bits - 1);

    if ((s / 64) == (e / 64)) {
      val = this.data[(int) (s / 64L)] << (s % 64);
      val = val >>> (63 - e % 64 + s % 64);
    } else {
      long val1 = this.data[(int) (s / 64L)] << (s % 64);
      long val2 = this.data[(int) (e / 64L)] >>> (63 - e % 64);
      val1 = val1 >>> (s % 64 - (e % 64 + 1));
      val = val1 | val2;
    }

    assert (val >= 0);

    return val;
  }

}
