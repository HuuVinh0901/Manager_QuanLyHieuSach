package utils;

public enum trangThaiKMEnum {
    Giam_10(10, "Giảm 10%"), 
    Giam_20(20, "Giảm 20%"), 
    Giam_30(30, "Giảm 30%");

    private final int value;
    private final String description;

    trangThaiKMEnum(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

//    public static trangThaiKMEnum getById(int value) {
//        for (trangThaiKMEnum e : values()) {
//            if (e.value == value) {
//                return e;
//            }
//        }
//        return DANG_KINH_DOANH;
//    }
//
//    public static trangThaiKMEnum getByName(String name) {
//        for (trangThaiKMEnum e : values()) {
//            if (e.name().equalsIgnoreCase(name)) {
//                return e;
//            }
//        }
//        return DANG_KINH_DOANH;
//    }
}