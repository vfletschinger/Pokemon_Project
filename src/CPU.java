public class CPU extends Player {

    public CPU(Draw draw, Hand hand){
        super(draw,hand);
    }
    @Override
    public void choicePokemonBattlefield(){
        // Index "0" is taken as the top of the list
        for(int i = 0; i < 3; i++){
            if(super._battlefield.getPokemonBattlefieldList().get(i) == null){
                super._battlefield.addPokemonToBattlefield(super._draw.getPokemonDraw().remove(i));
            }
        }
    }

}
