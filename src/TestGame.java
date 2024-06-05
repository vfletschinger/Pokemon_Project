import org.junit.Test;
import player.*;
import pokemon.*;
import power.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestGame {
    @Test
    public void testTotalHeal()
    {
        Pokemon p1 = new Pokemon("Pikachu");
        Power totalHeal = new TotalHeal();
        Pokemon p2 = new Pokemon("Charizard",totalHeal);
        Integer initialLife = p2.getLife();
        p1.attackPokemon(p2,0);
        p2.usePower(p2);
        assertEquals(p2.getLife(), initialLife);
    }

    @Test
    public void testKamikaze()
    {
        Pokemon p1 = new Pokemon("Pikachu");
        Power kamikaze = new Kamikaze();
        Pokemon p2 = new Pokemon("Charizard",kamikaze);
        p2.usePower(p1);
        assertTrue(p2.isKO() && p1.isKO());
    }

    @Test
    public void testEtherType()
    {
        Pokemon p1 = new Pokemon("Pikachu");
        Power ether = new EtherType();
        Pokemon p2 = new Pokemon("Charizard",ether);
        p2.usePower(p2);
        assertEquals(p2.getAffinity(), PokemonType.ETHER);
    }

    @Test
    public void testLeadType()
    {
        Pokemon p1 = new Pokemon("Pikachu");
        Power lead = new LeadType();
        Pokemon p2 = new Pokemon("Charizard",lead);
        p2.usePower(p2);
        assertEquals(p2.getAffinity(), PokemonType.LEAD);
    }

    @Test
    public void testAlreadySeen()
    {
        Pokemon p1 = new Pokemon("Pikachu");
        Power alreadyseen = new AlreadySeen();
        Pokemon p2 = new Pokemon("Charizard",alreadyseen);
        p2.usePower(p2);
        assertEquals(p2.getAttacksLeft(), 2);
    }

    @Test
    public void testFear()
    {
        Pokemon p1 = new Pokemon("Pikachu");
        Power fear = new Fear();
        Pokemon p2 = new Pokemon("Charizard",fear);
        p2.usePower(p1);
        assertEquals(p1.getAttack(), p1.getInitialAttack() - 10);
    }

    @Test
    public void testWarriorFervor()
    {
        Pokemon p1 = new Pokemon("Pikachu");
        Power warriorFervor = new WarriorFervor();
        Pokemon p2 = new Pokemon("Charizard",warriorFervor);
        p2.usePower(p2);
        assertEquals(p2.getAttack(), p2.getInitialAttack() + 10);
    }

    @Test
    public void testTerritoryExtension()
    {
        Player cpu = new CPU(null,null);
        Pokemon poke = new Pokemon("Pikachu", new TerritoryExtension());
        cpu.addPokemonOnBattlefield(poke);
        cpu.getPokemonOnBattlefield(0).usePower(cpu.getPokemonOnBattlefield(0));

        assertEquals(cpu.sizeOfBattlefield(), 1);
    }
}
