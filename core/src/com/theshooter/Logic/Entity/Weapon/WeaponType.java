package com.theshooter.Logic.Entity.Weapon;

public enum WeaponType {
    STONE,
    THROWING_KNIFE,
    BOW,
    NO_TYPE;

    public String toString() {
        switch (this) {
            case STONE:
                return "stone";
            case THROWING_KNIFE:
                return "throwing knife";
            case BOW:
                return "bow";
        }
        return "no type";
    }

    static public WeaponType fromInt(int i) {
        switch (i) {
            case 0:
                return WeaponType.STONE;
            case 1:
                return WeaponType.THROWING_KNIFE;
            case 2:
                return WeaponType.BOW;
        }
        return NO_TYPE;
    }
}

