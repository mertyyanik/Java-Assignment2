public class Main {
    public static void main(String[] args) {
        Commands commands = new Commands();
        commands.readText(args[0]);
        commands.command();
    }
}