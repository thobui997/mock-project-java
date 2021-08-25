package vn.manage.system.enums;

public enum DataType {
  STRING("string"), FLOAT("float"), DATE("date"), INTEGER("integer"), BOOLEAN("boolean");

  private String value;

  DataType(String value) {
    this.value = value;
  }

  public String getDataType() {
    return value;
  }
}
