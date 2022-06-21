package uz.jl.enums;

public enum Language {
    UZ, RU, EN;

    public static Language findByName(String language) {
        for (Language value : values()) {
            if (value.name().equalsIgnoreCase(language))
                return value;
        }
        return RU;
    }
}
