package org.dhbw.movietunes.model;

import android.database.Cursor;
import android.provider.BaseColumns;
import java.nio.charset.Charset;

public class IsSimilarTo implements BaseColumns {

  public static final String _TabellenName = "IsSimilarTo";
  public static final String _IsId = "isId";
  public static final String _ToId = "toId";
  private String isId;    //this song is similar to toId
  private String toId;

  public IsSimilarTo(String isId, String toId) {
    this.isId = isId;
    this.toId = toId;
  }
  public IsSimilarTo(Cursor cursor) {
    Charset UTF8_CHARSET = Charset.forName("UTF-8");
    this.isId = cursor.getString(cursor.getColumnIndexOrThrow(_IsId));
    this.toId = cursor.getString(cursor.getColumnIndexOrThrow(_ToId));
  }
  public String getIsId() {
    return isId;
  }

  public String getToId() {
    return toId;
  }
}
