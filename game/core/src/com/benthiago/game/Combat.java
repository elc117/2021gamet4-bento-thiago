package com.benthiago.game;

import com.badlogic.gdx.utils.Array;

class Combat{
    private Array<Character> combatants;

    Combat(){
        this.combatants = new Array<Character>();
    }
    public void addCombatant(Character c){
        this.combatants.add(c);
    }
    public void simplifiedCombat(Array<Character> chars){
        if(this.combatants.size > 1){
            Character higherKnowledge = this.combatants.get(0);

            for(int i = 1; i < this.combatants.size; i++)
            {
                if(this.combatants.get(i).getKnowledge() > higherKnowledge.getKnowledge())
                {
                    higherKnowledge = this.combatants.get(i);
                }
            }
        }
    }
}
