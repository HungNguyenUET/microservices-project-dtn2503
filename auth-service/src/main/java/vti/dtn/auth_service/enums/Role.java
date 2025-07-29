package vti.dtn.auth_service.enums;

public enum Role {
    USER,
    ADMIN,
    MANAGER;

    public static Role toEnum(String role) {
        for (Role item : Role.values()) {
            if (item.toString().equals(role)) return item;
        }
        return null;
    }
}
