package model.abilities;

import java.util.ArrayList;

import model.world.Damageable;

public class DamagingAbility extends Ability {

	private int damageAmount;

	public DamagingAbility(String name, int cost, int baseCoolDown, int castRadius, AreaOfEffect area, int required,
			int damageAmount) {
		super(name, cost, baseCoolDown, castRadius, area, required);
		this.damageAmount = damageAmount;
	}

	public int getDamageAmount() {
		return damageAmount;
	}

	public void setDamageAmount(int damageAmount) {
		this.damageAmount = damageAmount;
	}

	@Override
	public void execute(ArrayList<Damageable> targets) {
		for (Damageable d : targets)

			d.setCurrentHP(d.getCurrentHP() - damageAmount);

	}
	public String toString() {
		return "Name:"+this.getName()+"\n"+"\n"+"Type: Damaging"+"\n"+"\n"+"area of effect: "+this.getCastArea()+"\n"+"\n"+"cast range: "
	+this.getCastRange()+"\n"+"\n"+"mana cost: "+this.getManaCost()+"\n"+"\n"+"action points: "+this.getRequiredActionPoints()+"\n"+"\n"+
				"cooldown: "+this.getCurrentCooldown()+"/"+this.getBaseCooldown()+"\n"+"\n"+"Damage amount: "+this.getDamageAmount();
	}
}
