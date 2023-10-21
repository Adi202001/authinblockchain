import authentication.AuthenticationBlockchain;
import data.DataBlockchain;
import user_interface.CommandLineInterface;

public class Main {
    public static void main(String[] args) {
        // Initialize the authentication blockchain
        AuthenticationBlockchain authBlockchain = new AuthenticationBlockchain();
        
        // Initialize the data blockchain
        DataBlockchain dataBlockchain = new DataBlockchain(authBlockchain.getLatestBlockHash());
        
        // Initialize the command-line interface
        CommandLineInterface cli = new CommandLineInterface(authBlockchain, dataBlockchain);
        
        // Start the main application loop
        cli.run();
    }
}
