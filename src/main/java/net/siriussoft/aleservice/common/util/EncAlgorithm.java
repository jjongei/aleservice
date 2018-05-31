package net.siriussoft.aleservice.common.util;

public enum EncAlgorithm {
    MD5("MD5"),
    SHA1("SHA-1"),
    SHA256("SHA-256");

    private final String type;

    private EncAlgorithm(String type) {
        this.type = type;
    }

    public boolean equalsName(String def) {
        return (def == null) ? false : type.equals(def);
    }

    public String toString() {
        return this.type;
    }
}
