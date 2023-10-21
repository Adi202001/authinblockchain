import java.util.ArrayList;
import java.security.MessageDigest;

public class DataBlockchain {
    private ArrayList<DataBlock> blockchain;
    
    public DataBlockchain() {
        this.blockchain = new ArrayList<>();
        // Initialize the blockchain with a Genesis block.
        createGenesisBlock();
    }

    // Create the Genesis block with an arbitrary previous hash.
    private void createGenesisBlock() {
        DataBlock genesisBlock = new DataBlock("Genesis Block Data", "0");
        addBlock(genesisBlock);
    }

    // Add a new data block to the blockchain.
    public void addBlock(DataBlock newBlock) {
        if (newBlock != null) {
            newBlock.mineBlock(); // Implement the mining process in DataBlock.
            blockchain.add(newBlock);
        }
    }

    // Get the latest block in the blockchain.
    public DataBlock getLatestBlock() {
        if (!blockchain.isEmpty()) {
            return blockchain.get(blockchain.size() - 1);
        }
        return null;
    }

    // Validate the blockchain by checking hashes and previous hash values.
    public boolean isChainValid() {
        DataBlock currentBlock;
        DataBlock previousBlock;

        for (int i = 1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);

            // Check if the stored hash matches the recalculated hash.
            if (!currentBlock.calculateHash().equals(currentBlock.getHash())) {
                return false;
            }

            // Check if the previous hash matches the hash of the previous block.
            if (!previousBlock.getHash().equals(currentBlock.getPreviousHash())) {
                return false;
            }
        }

        return true;
    }
    
    // Other methods for managing and interacting with the data blockchain.

    public static void main(String[] args) {
        DataBlockchain dataBlockchain = new DataBlockchain();

        // Example usage:
        DataBlock newBlock = new DataBlock("Password Record Data", dataBlockchain.getLatestBlock().getHash());
        dataBlockchain.addBlock(newBlock);

        // Verify the blockchain's integrity:
        if (dataBlockchain.isChainValid()) {
            System.out.println("Data Blockchain is valid.");
        } else {
            System.out.println("Data Blockchain is not valid.");
        }
    }
}
