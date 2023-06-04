package model.abilities;

import java.util.ArrayList;

import model.world.Damageable;

public  class HealingAbility extends Ability {
	private int healAmount;

	public HealingAbility(String name,int cost, int baseCoolDown, int castRadius, AreaOfEffect area,int required, int healingAmount) {
		super(name,cost, baseCoolDown, castRadius, area,required);
		this.healAmount = healingAmount;
	}

	public int getHealAmount() {
		return healAmount;
	}

	public void setHealAmount(int healAmount) {
		this.healAmount = healAmount;
	}

	
	@Override
	public void execute(ArrayList<Damageable> targets) {
		for (Damageable d : targets)

			d.setCurrentHP(d.getCurrentHP() + healAmount);

	}
	public String toString() {
		return "Name:"+this.getName()+"\n"+"\n"+"Type: Healing"+"\n"+"\n"+"area of effect: "+this.getCastArea()+"\n"+"\n"+"cast range: "
	+this.getCastRange()+"\n"+"\n"+"mana cost: "+this.getManaCost()+"\n"+"\n"+"action points: "+this.getRequiredActionPoints()+"\n"+"\n"+
				"cooldown: "+this.getCurrentCooldown()+"/"+this.getBaseCooldown()+"\n"+"\n"+"effect: "+this.getHealAmount();
	}
	

}
