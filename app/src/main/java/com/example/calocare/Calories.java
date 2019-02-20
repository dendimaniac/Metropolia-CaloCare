package com.example.calocare;

public class Calories {
    private int a= UserInfo.getInstance().getGoalStatus();
    private double calcServingCalo;
    private int caloOfGoal;
    public double maxCalo(){
        if(a==1){ caloOfGoal= -500;}
        else if(a==2){caloOfGoal=0;}
        else if(a==3){caloOfGoal=500;}
        return UserInfo.getInstance().getTDEE()+caloOfGoal;
    }
    public double calcServingCalo(Food input){
        calcServingCalo= input.getCalories()* input.getNumOfServ();
        return calcServingCalo;
    }
    public double calcRemain(){
       return  UserInfo.getInstance().getTDEE()- calcServingCalo;
    }
}
