package model.tiles.units;

public interface HeroicUnit {
    void castAbility();
    String getAbilityName();
    void onCastMsg(String targetName);
}
