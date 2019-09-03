package com.epam.bookscatalog.model;

public enum RatingName {

  RATING_1(1),
  RATING_2(2),
  RATING_3(3),
  RATING_4(4),
  RATING_5(5);

  RatingName(int value) {
    this.value = value;
  }

  public static RatingName getByValue(int rating) {
    for (RatingName rn : values()) {
      if (rn.getValue() == rating) {
        return rn;
      }
    }

    return null;
  }

  private int value;

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }
}
