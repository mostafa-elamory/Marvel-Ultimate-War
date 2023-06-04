package model.world;

import java.util.ArrayList;

import model.effects.Effect;
import model.effects.EffectType;
import model.effects.Embrace;

public class Hero extends Champion {

	public Hero(String name, int maxHP, int maxMana, int actions, int speed, int attackRange, int attackDamage) {
		super(name, maxHP, maxMana, actions, speed, attackRange, attackDamage);

	}

	@Override
	public void useLeaderAbility(ArrayList<Champion> targets) {
		for (Champion c : targets) {
			int i = 0;
			while (i < c.getAppliedEffects().size()) {
				Effect e = c.getAppliedEffects().get(i);
				if (e.getType() == EffectType.DEBUFF) {
					e.remove(c);
					c.getAppliedEffects().remove(e);

				} else
					i++;
			}
				Embrace em = new Embrace(2);
				
				em.apply(c);
				c.getAppliedEffects().add(em);
				
			}
		}
	public String toString() {
		String s = "";
		for(int i =1;i<=this.getAbilities().size();i++) {
			
			s=s+""+i+"-"+this.getAbilities().get(i-1).getName()+"\n";
		}
		return "Class:" + "hero" + "\n"+"\n" + "Name:" + this.getName() + "\n"+"\n" + "HP:" +this.getMaxHP()+"\n" +"\n"+"Mana:"+ this.getMana() +"\n"+"\n"+"Action points:"+ this.getMaxActionPointsPerTurn() 
				 +"\n"+"\n"+"Attack Range:"+ this.getAttackRange()  +"\n"+"\n"+"Attack Damage:"+ this.getAttackDamage()  +"\n"+"\n"+"Speed:"+ this.getSpeed()+"\n"
				 +"Abilities:" +"\n"+s;
	}
	public String toString2() {
		String s = "";
		for(int i =1;i<=this.getAppliedEffects().size();i++) {
			
			s=s+""+i+"-"+this.getAppliedEffects().get(i-1).getName()+"\n"+"Duration:"+this.getAppliedEffects().get(i-1).getDuration()+"\n"+"\n";
		}
		return "Class:" + "hero" + "\n"+"\n" +"Name:" + this.getName() + "\n"+"\n" + "HP:" +this.getCurrentHP()+"/"+this.getMaxHP()+"\n" +"\n"+"Mana:"+ this.getMana() +"\n"+"\n"+"Action points:"+ this.getCurrentActionPoints()+"/"+this.getMaxActionPointsPerTurn() 
				 +"\n"+"\n"+"Attack Range:"+ this.getAttackRange()  +"\n"+"\n"+"Attack Damage:"+ this.getAttackDamage()  +"\n"+"\n"+"Speed:"+ this.getSpeed()+"\n"
				 +"effects:" +"\n"+s;
	}

	}

