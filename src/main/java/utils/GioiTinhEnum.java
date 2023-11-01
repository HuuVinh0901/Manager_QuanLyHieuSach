package utils;

public enum GioiTinhEnum {
    Nam(1, "Nam"), 
    Nu(0, "Nữ");

    private final int value;
    private final String description;

    GioiTinhEnum(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static GioiTinhEnum getById(int value) {
        for (GioiTinhEnum e : values()) {
            if (e.value == value) {
                return e;
            }
        }
        return Nam;
    }

    public static GioiTinhEnum getByName(String name) {
        for (GioiTinhEnum e : values()) {
            if (e.name().equalsIgnoreCase(name)) {
                return e;
            }
        }
        return Nam; // hoặc return null nếu không tìm thấy
    }
}
