package com.uzcustomcake.core.domain;

import com.google.firebase.database.DataSnapshot;
import java.util.ArrayList;

/**
 * created at 10/16/17
 *
 * @author 00003130
 * @version 1.0
 */

public class FillingList extends ArrayList<Filling> {

  public FillingList(String type, DataSnapshot input) {
    super();

    for (DataSnapshot child : input.getChildren()) {
      add(createFilling(child, type));
    }
  }

  private static Filling createFilling(DataSnapshot child, String type) {
    return new Filling(
        type,
        (String) child.child("name").getValue(),
        null,
        (String) child.child("ImageUrl").getValue(),
        (long) child.child("price").getValue());
  }
}
