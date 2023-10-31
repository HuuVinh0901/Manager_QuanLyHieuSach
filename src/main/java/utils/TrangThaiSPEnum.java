package utils;

public enum TrangThaiSPEnum {
    DANG_KINH_DOANH(1, "Đang kinh doanh"), 
    NGUNG_KINH_DOANH(0, "Ngừng kinh doanh");

    private final int value;
    private final String description;

    TrangThaiSPEnum(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static TrangThaiSPEnum getById(int value) {
        for (TrangThaiSPEnum e : values()) {
            if (e.value == value) {
                return e;
            }
        }
        return DANG_KINH_DOANH;
    }

    public static TrangThaiSPEnum getByName(String name) {
        for (TrangThaiSPEnum e : values()) {
            if (e.name().equalsIgnoreCase(name)) {
                return e;
            }
        }
        return DANG_KINH_DOANH;
    }
}
