package entitypart.items;

import entitypart.epf.Entity;
import entitypart.parts.DescriptionPart;
import entitypart.parts.FlyingPart;
import entitypart.parts.HealthPart;

public class Weapon {
	
	private String name;
	private float minDamage;
	private float maxDamage;
	private AttackRange attackRange;
	
	public Weapon(String name, float minDamage, float maxDamage, AttackRange attackRange) {
		this.name = name;
		this.minDamage = minDamage;
		this.maxDamage = maxDamage;
		this.attackRange = attackRange;
	}

	public boolean canAttack(Entity target) {
		// check if weapon can attack flying entities
		boolean targetFlying = target.has(FlyingPart.class);
		return !targetFlying || (targetFlying && attackRange == AttackRange.FAR);
	}
	
	/**
	 * Does damage to target in the range of min damage to max damage.
	 * @param target
	 */
	public void attack(Entity target) {
		HealthPart healthPart = target.get(HealthPart.class);
		float damage = minDamage + (int)(Math.random() * (maxDamage - minDamage) + 1); 
		float newHealth = healthPart.getHealth() - damage;
		target.get(HealthPart.class).setHealth(newHealth);
		System.out.println("\tAttacking with " + name + ".  " + damage + " damage dealt to "
				+ target.get(DescriptionPart.class).getName());
	}
	
}
