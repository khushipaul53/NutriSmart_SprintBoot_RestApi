package com.springboot.smartnutri.payload;

public class MacroNutrient {
		private double bMI;
		private double proteinNeeded;
		private double carbsNeeded;
		private double fatsNeeded;
		private double calorie;
		
		public double getbMI() {
			return bMI;
		}
		public void setbMI(double bMI) {
			this.bMI = bMI;
		}
		public double getProteinNeeded() {
			return proteinNeeded;
		}
		public void setProteinNeeded() {
			this.proteinNeeded = this.calorie*0.25/4;
		}
		public double getCarbsNeeded() {
			return carbsNeeded;
		}
		public void setCarbsNeeded() {
			this.carbsNeeded = this.calorie*0.50/4;
		}
		public double getFatsNeeded() {
			return fatsNeeded;
		}
		public void setFatsNeeded() {
			this.fatsNeeded = this.calorie*0.25/9;
		}
		public double getCalorie() {
			return calorie;
		}
		public void setCalorie(double calorie) {
			this.calorie = calorie;
		}		
}
