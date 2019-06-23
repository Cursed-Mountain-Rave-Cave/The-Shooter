package com.theshooter.Logic.Entity.Weapon;

public enum WeaponType {
    STONE,
    THROWING_KNIFE,
    BOW,
    DAGGER,
    NO_TYPE;

    public String toString() {
        switch (this) {
            case STONE:
                return "stone";
            case THROWING_KNIFE:
                return "throwing knife";
            case BOW:
                return "bow";
            case DAGGER:
                return "dagger";
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
            case 3:
                return WeaponType.DAGGER;
        }
        return NO_TYPE;
    }
}

