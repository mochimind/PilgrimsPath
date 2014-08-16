package com.pilgrimspath.data;

public class PeopleManager {
	private double population = 0;
	private int maxPopulation = 0;
	private double lastPopulation = 0;
	private boolean populationDecline = false;

	private int lifeSupport = 1000;
	//private float happiness;
	
	private float health = 1f;
	// TODO: make health, population, labor stats all tracked by PeopleManager
	private float lastHealth = 1f;
	private boolean healthDecline = false;
	
	private int availableLabor = 0;
	private int allocatedLabor = 0;	// allocated each tick
	private boolean laborShortage = false;
	private boolean curLaborShortage = false; // updated each tick
	private int curRequestedLabor = 0;
	private int lastRequestedLabor = 0;

	private float birthRate = 0.01f;
	private float deathRate = 0.002f;
	
	private Ship container;
		
	public static final float FOOD_PER_TICK_PER_PERSON = 0.02f;
	public static final float STARVATION_HEALTH_LOSS_PER_PERCENT = 0.01f;
	public static final float SUFFOCATION_HEALTH_LOSS_PER_PERCENT = 0.2f;
	public static final float HEALTH_RECOVER_RATE = 0.02f;
	public static final float OVERCROWDED_MORTALITY_RATE = 0.02f;
	
	public static final float MORTALITY_THRESHOLD = 0.75f;
	public static final float MORTALITY_RATE = 0.25f;
	
	public static final float LABOR_PARTICIPATION = 0.7f;
	
	public static final float MAX_HEALTH = 1f;
	
	public PeopleManager(Ship _container) {
		container = _container;
	}
	
	public void tick() {
		adjustHealth();
		adjustPopulation();
		adjustLabor();
		// adjust happiness based on current max
	}
	
	public void updateLifeSupport(int supporting) {
		
	}
	
	public synchronized void addHousing(int units) { maxPopulation += units; }

	public synchronized void removeHousing(int units) { 
		maxPopulation -= units; 
		if (maxPopulation < 0) { maxPopulation = 0; }
	}
	
	private synchronized void adjustHealth() {
		// try to consume food
		boolean areHealthy = true;
		Resource useFood = new Resource(Resource.FOOD_NAME, FOOD_PER_TICK_PER_PERSON * population);
		if (!container.fleet.resources.remove(useFood)) {
			// could not eat all that we wanted
			areHealthy = false;
			double fleetFood = container.fleet.resources.getResourceCount(Resource.FOOD_NAME);
			int fed = (int) Math.floor(fleetFood / FOOD_PER_TICK_PER_PERSON);
			double percentSick = 1 - (population == 0 ? 1 : fed / population);

			// adjust health based on food consumed
			health -= (float) (percentSick * STARVATION_HEALTH_LOSS_PER_PERCENT);
		} 
		
		// factor in life support
		if (lifeSupport < population) {
			areHealthy = false;
			double percentSick = 1 - lifeSupport / population;
			health -= (float) (percentSick * SUFFOCATION_HEALTH_LOSS_PER_PERCENT);
		}
		
		// if healthy, recover health
		if (areHealthy) { health += HEALTH_RECOVER_RATE; }
		health = Math.max(Math.min(health, MAX_HEALTH), 0);
		if (health > lastHealth || health == MAX_HEALTH) {
			healthDecline = false;
		} else if (health < lastHealth) {
			healthDecline = true;
		}
		lastHealth = health;
	}

	// adjust population based on health
	private synchronized void adjustPopulation() {
		// aside from base death by aging, mortality kicks in only at a certain population level
		float sickModifier = 0f;
		if (health < MORTALITY_THRESHOLD) {
			sickModifier = (MORTALITY_THRESHOLD - health) * MORTALITY_RATE;
		}
		population = Math.floor((1 + (birthRate - sickModifier - deathRate)) * population); 
		if (population > maxPopulation) {
			population -= OVERCROWDED_MORTALITY_RATE * (population - maxPopulation);
		}		

		// limit to feasible numbers
		population = Math.max(population,0);
		
		if (population > lastPopulation) {
			populationDecline = false;
		} else if (population < lastPopulation) {
			populationDecline = true;
		}
		lastPopulation = population;
		// TODO: labor & population colors aren't showing up properly
	}
	
	private synchronized void adjustLabor() {
		availableLabor = (int)Math.ceil(LABOR_PARTICIPATION * population);
	}
	
	public synchronized int checkLabor(int units, int laborPerUnit) {
		int available = availableLabor - allocatedLabor;
		return (int) Math.min(units, Math.floor(available / laborPerUnit));		
	}
	
	// returns the number of units that were supplied with labor
	public synchronized int requestLabor(int units, int laborPerUnit) {
		curRequestedLabor += units * laborPerUnit;
		int available = availableLabor - allocatedLabor;
		int supplied = (int) Math.min(units, Math.floor(available / laborPerUnit));
		if (supplied < units) { curLaborShortage = true; }
				
		allocatedLabor += supplied * laborPerUnit;
		return supplied;
	}
	
	public synchronized void startNewRound() {
		// reset per tick variables
		allocatedLabor = 0;
		laborShortage = curLaborShortage;
		curLaborShortage = false;
		lastRequestedLabor = curRequestedLabor;
		curRequestedLabor = 0;
	}
	
	public synchronized void adjustPopulation(int pop) { population += pop; }
	
	public synchronized int getPopulation() { return (int) Math.floor(population); }
	
	public synchronized int getMaxPopulation() { return maxPopulation; }
	
	public synchronized boolean hasLaborShortage() { return laborShortage; }
	
	public synchronized boolean populationDeclining() { return populationDecline; }
	
	public synchronized int getRequestedLabor() { return lastRequestedLabor; }
	
	public synchronized int getAvailableLabor() { return availableLabor; }
	
	public synchronized float getHealth() { return health; }
	
	public synchronized boolean healthDeclining() { return healthDecline; }
}
