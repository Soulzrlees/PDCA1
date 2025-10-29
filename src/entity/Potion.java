package entity;
//Potions class is to set the Name, type of the potion, and the buff stats of the potion.
public class Potion{
    private String Name;
    private potionType type;
    private int statsBuff;


    public enum potionType {
        Strength, Range
    }

    public Potion(String name, potionType type, int statsBuff){
        this.Name = name;
        this.type = type;
        this.statsBuff = statsBuff ;
    }

    public String getName() {
        return Name;
    }

    public potionType getType() {
        return type;
    }

    public int getstatsBuff() {
        return statsBuff;
    }


    //Applies the potion buff to the player
    public void usePotion(Entity player){
        switch(type) {
            case Strength:
                //Adds more damage to the base damage (not including the randomized damage) last for 1 battle
                int newBaseDamage = player.getbaseDmg() + (statsBuff * player.getLevel());
                player.setbaseDmg(newBaseDamage);
                break;
            case Range:
                //Adds more attack range to the base attack range last for 1 battle
                int newAttackRange = player.getAttackRange() + statsBuff;
                player.setAttackRange(newAttackRange);
                break;

            default:
                System.out.println("Nothing happens");
        }
    }

    @Override
    public String toString() {
        return getName() + " (" + getType() + " +" + getstatsBuff() + ")";
    }
}
