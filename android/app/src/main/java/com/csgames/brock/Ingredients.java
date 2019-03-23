package com.csgames.brock;
import com.csgames.brock.Drink;

import java.util.ArrayList;

public class Ingredients {
    ArrayList<Drink> juices = null;
    ArrayList<Drink> drinks = null;
    ArrayList<Drink> ingredients = null;
    ArrayList<Drink> alcohols = null;

    public ArrayList<Drink> getJuices() {
        return juices;
    }
    
    //getters and setters

	public ArrayList<Drink> getDrinks() {
		return drinks;
	}

	public ArrayList<Drink> getIngredients() {
		return ingredients;
	}

	public ArrayList<Drink> getAlcohols() {
		return alcohols;
	}

	public void setJuices(ArrayList<Drink> juices) {
		this.juices = juices;
	}

	public void setDrinks(ArrayList<Drink> drinks) {
		this.drinks = drinks;
	}

	public void setIngredients(ArrayList<Drink> ingredients) {
		this.ingredients = ingredients;
	}

	public void setAlcohols(ArrayList<Drink> alcohols) {
		this.alcohols = alcohols;
	}
}
