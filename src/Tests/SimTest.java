import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import src.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class SimTest {

    @Test
    public void testSolve_SuccessfulPath() {
        // Define input data for the test
        String start = "start";
        String end = "end";
        ArrayList<Character> alphabet = new ArrayList<>(Arrays.asList('a', 'b', 'c'));
        ArrayList<Character> symbols = new ArrayList<>(Arrays.asList('X', 'Y', 'Z'));

        // Create commands
        Command command1 = new Command(start, 'a', "middle", 'X', Direction.RIGHT);
        Command command2 = new Command("middle", 'b', end, 'Y', Direction.STAY);
        ArrayList<Command> commands = new ArrayList<>(Arrays.asList(command1, command2));

        String input = "ab";

        // Create a new instance of Sim
        Sim sim = new Sim(start, end, alphabet, symbols, commands, input);

        // Call the method to be tested
        boolean result = sim.solve();
        System.out.println(sim.getLog());
        // Assert the result
        Assertions.assertTrue(result, "Expected the solve method to return true for successful path.");
    }

    @Test
    public void testSolve_UnsuccessfulPath() {
        // Define input data for the test
        String start = "start";
        String end = "end";
        ArrayList<Character> alphabet = new ArrayList<>(Arrays.asList('a', 'b', 'c'));
        ArrayList<Character> symbols = new ArrayList<>(Arrays.asList('X', 'Y', 'Z'));

        // Create commands
        Command command1 = new Command("start", 'a', "middle", 'X', Direction.RIGHT);
        ArrayList<Command> commands = new ArrayList<>(List.of(command1));

        String input = "ab";

        // Create a new instance of Sim
        Sim sim = new Sim(start, end, alphabet, symbols, commands, input);

        // Call the method to be tested
        boolean result = sim.solve();

        System.out.println(sim.getLog());
        // Assert the result
        assertFalse(result, "Expected the solve method to return false for unsuccessful path.");
    }
    @Test
    public void testSolve_SeveralPaths(){
        // this is a TM for 2 chars
        // it shows "XOR" as an "AND" or "OR"
        String start = "start";
        String end = "end";
        ArrayList<Character> alphabet = new ArrayList<>(Arrays.asList('a', 'b'));
        ArrayList<Character> symbols = new ArrayList<>(alphabet);
        symbols.add('X');

        ArrayList<Command> commands = new ArrayList<>();
        Command command1 = new Command(start, 'a', "q1", 'a', Direction.RIGHT);
        Command command2 = new Command("q1", 'a', "end", 'a', Direction.STAY);
        commands.add(command1);
        commands.add(command2);

        Command command4 = new Command(start, 'a', "q2", 'a', Direction.RIGHT);
        Command command5 = new Command("q2", 'b', end, 'b', Direction.STAY);
        commands.add(command4);
        commands.add(command5);

        Command command6 = new Command(start, 'b', "q3", 'b', Direction.RIGHT);
        Command command7 = new Command("q3", 'a', "end", 'a', Direction.STAY);
        commands.add(command6);
        commands.add(command7);

        Command command8 = new Command(start, 'a' , "qFail", 'a', Direction.RIGHT);
        Command command9 = new Command("qFail", 'a', "qFail2", 'a', Direction.RIGHT);
        Command command10 = new Command("qFail2", 'b', "qFail", 'a' , Direction.RIGHT);
        commands.add(command8);
        commands.add(command9);
        commands.add(command10);
        String input = "ab";

        Sim sim = new Sim(start, end, alphabet, symbols, commands, input);
        sim.setResearchDepth(3);
        boolean result = sim.solve();
        System.out.println(sim.getLog());
        Assertions.assertTrue(result, "Success");
    }


    @Test
    // this test should return fail or succeed randomly
    // reason: research depth = 1 and there are 2 ways the TM could use
    public void testSolve_SeveralPaths_Copy(){
        // this is a TM for 2 chars
        // it shows "XOR" as an "AND" or "OR"
        String start = "start";
        String end = "end";
        ArrayList<Character> alphabet = new ArrayList<>(Arrays.asList('a', 'b'));
        ArrayList<Character> symbols = new ArrayList<>(alphabet);
        symbols.add('X');

        ArrayList<Command> commands = new ArrayList<>();
        Command command1 = new Command(start, 'a', "q1", 'a', Direction.RIGHT);
        Command command2 = new Command("q1", 'a', "end", 'a', Direction.STAY);
        commands.add(command1);
        commands.add(command2);

        Command command4 = new Command(start, 'a', "q2", 'a', Direction.RIGHT);
        Command command5 = new Command("q2", 'b', end, 'b', Direction.STAY);
        commands.add(command4);
        commands.add(command5);

        Command command6 = new Command(start, 'b', "q3", 'b', Direction.RIGHT);
        Command command7 = new Command("q3", 'a', "end", 'a', Direction.STAY);
        commands.add(command6);
        commands.add(command7);

        String input = "ab";

        Sim sim = new Sim(start, end, alphabet, symbols, commands, input);
        sim.setResearchDepth(1);
        boolean result = sim.solve();

        System.out.println("This test should fail 50% of the times: " + sim.getLog());
        Assertions.assertTrue(result, "Success");
    }
}
