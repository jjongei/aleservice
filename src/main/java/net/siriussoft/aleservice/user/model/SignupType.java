package net.siriussoft.aleservice.user.model;

public enum SignupType {
    general, facebook, google, twitter;

    public static SignupType convert(String name) {
        for(SignupType type : SignupType.values()) {
            if(type.name().equals(name)) {
                return type;
            }
        }
        return null;
    }
}
