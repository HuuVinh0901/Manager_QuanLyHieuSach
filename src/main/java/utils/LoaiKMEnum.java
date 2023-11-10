package utils;

public enum LoaiKMEnum {
	Giam_10(1,"Giảm 10%"), 
    Giam_20(2,"Giảm 20%"), 
    Giam_30(3,"Giảm 30%");

    private final int value;
    private final String description;

    LoaiKMEnum(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
    public static LoaiKMEnum getById(int value) {
        for (LoaiKMEnum e : values()) {
            if (e.value == value) {
                return e;
            }
        }
        return null;
    }

    public static LoaiKMEnum getByName(String name) {
        for (LoaiKMEnum e : values()) {
            if (e.name().equals(name)) {
                return e;
            }
        }
        return null;
    }
}
