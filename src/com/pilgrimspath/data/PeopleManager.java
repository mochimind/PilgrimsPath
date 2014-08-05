package com.pilgrimspath.data;

public class PeopleManager {
	public int population;
	public int maxPopulation;
	private int lifeSupport;
	//private float happiness;
	private float health; 
	
	private int curLabor;
	private int allocatedLabor;	// allocated each tick

	private float birthRate = 0.01f;
	private float deathRate = 0.002f;
	
	private Ship container;
	
	public static final float FOOD_PER_TICK_PER_PERSON = 0.02f;
	public static final float STARVATION_HEALTH_LOSS_PER_PERCENT = 0.01f;
	public static final float SUFFOCATION_HEALTH_LOSS_PER_PERCENT = 0.2f;
	public static final float HEALTH_RECOVER_RATE = 0.02f;
	
	public static final float MORTALITY_THRESHOLD = 0.75f;
	public static final float MORTALITY_RATE = 0.25f;
	
	public static final float LABOR_PARTICIPATION = 0.7f;
	
	public PeopleManager(Ship _container) {
		container = _container;
		
		population = 0;
		maxPopulation = 0;
		lifeSupport = 0;
		//happiness = 1f;
		health = 1f;
		
		curLabor = 0;
		allocatedLabor = 0;
	}
	
	public void tick() {
		adjustHealth();
		adjustPopulation();
		adjustLabor();
		// adjust happiness based on current max
	}
	
	public void updateLifeSupport(int supporting) {
		
	}
	
	private void adjustHealth() {
		// try to consume food
		boolean areHealthy = true;
		Resource useFood = new Resource(Resource.FOOD_NAME, FOOD_PER_TICK_PER_PERSON * population);
		if (!container.fleet.resources.remove(useFood)) {
			// could not eat all that we wanted
			areHealthy = false;
			double fleetFood = container.fleet.resources.getResourceCount(Resource.FOOD_NAME);
			int fed = (int) Math.floor(fleetFood / FOOD_PER_TICK_PER_PERSON);
			double percentSick = 1 - fed / population;

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
	}

	// adjust population based on health
	private void adjustPopulation() {
		// aside from base death by aging, mortality kicks in only at a certain population level
		if (health < MORTALITY_THRESHOLD) {
			// each tick, a certain number of the sick will die
			float percentSick = MORTALITY_THRESHOLD - health;
			population = (int) (1 + (birthRate - (percentSick * MORTALITY_RATE) - deathRate)) * population; 
			// limit to feasible numbers
			population = Math.min(Math.max(population,0), maxPopulation);
		}
	}
	
	private void adjustLabor() {
		curLabor = (int)Math.ceil(LABOR_PARTICIPATION * population);
	}
	
	public int checkLabor(int units, int laborPerUnit) {
		int available = curLabor - allocatedLabor;
		return (int) Math.min(units, Math.floor(available / laborPerUnit));		
	}
	
	// returns the number of units that were supplied with labor
	public int requestLabor(int units, int laborPerUnit) {
		int available = curLabor - allocatedLabor;
		int supplied = (int) Math.min(units, Math.floor(available / laborPerUnit));
				
		allocatedLabor += supplied * laborPerUnit;
		return supplied;
	}
	
	public void startNewRound() {
		// reset per tick variables
		allocatedLabor = 0;	
	}
}
