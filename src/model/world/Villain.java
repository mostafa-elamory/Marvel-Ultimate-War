package model.world;

import java.util.ArrayList;

public class Villain extends Champion {

	public Villain(String name, int maxHP, int maxMana, int actions, int speed, int attackRange, int attackDamage) {
		super(name, maxHP, maxMana, actions, speed, attackRange, attackDamage);

	}

	@Override
	public void useLeaderAbility(ArrayList<Champion> targets) {
		for (Champion c : targets) {

			c.setCurrentHP(0);
			
		}

	}
	public String toString() {
		String s = "";
		for(int i =1;i<=this.getAbilities().size();i++) {
			
			s=s+""+i+"-"+this.getAbilities().get(i-1).getName()+"\n";
		}
		return "Class:" + "Villain" + "\n"+"\n" + "Name:" + this.getName() + "\n"+"\n" + "HP:" +this.getMaxHP()+"\n" +"\n"+"Mana:"+ this.getMana() +"\n"+"\n"+"Action points:"+ this.getMaxActionPointsPerTurn() 
				 +"\n"+"\n"+"Attack Range:"+ this.getAttackRange()  +"\n"+"\n"+"Attack Damage:"+ this.getAttackDamage()  +"\n"+"\n"+"Speed:"+ this.getSpeed()+"\n"
				 +"Abilities:" +"\n"+s;
	}
	public String toString2() {
		String s = "";
		for(int i =1;i<=this.getAppliedEffects().size();i++) {
			
			s=s+""+i+"-"+this.getAppliedEffects().get(i-1).getName()+"\n"+"Duration:"+this.getAppliedEffects().get(i-1).getDuration()+"\n"+"\n";
		}
		return "Class:" + "Villain" + "\n"+"\n" +"Name:" + this.getName() + "\n"+"\n" + "HP:" +this.getCurrentHP()+"/"+this.getMaxHP()+"\n" +"\n"+"Mana:"+ this.getMana() +"\n"+"\n"+"Action points:"+ this.getCurrentActionPoints()+"/"+this.getMaxActionPointsPerTurn() 
				 +"\n"+"\n"+"Attack Range:"+ this.getAttackRange()  +"\n"+"\n"+"Attack Damage:"+ this.getAttackDamage()  +"\n"+"\n"+"Speed:"+ this.getSpeed()+"\n"
				 +"effects:" +"\n"+s;
	}

}
