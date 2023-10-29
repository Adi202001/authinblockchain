package com.example.authen.blockchain;

import java.util.ArrayList;
import java.util.List;

public class Blockchain {
    private List<Block> chain;

    public Blockchain() {
        this.chain = new ArrayList<>();
        // Genesis block (the first block in the chain)
        this.chain.add(new Block("Genesis", "0"));
    }

    // Add a new block to the blockchain
    public void addBlock(Block newBlock) {
        Block previousBlock = getLatestBlock();
        newBlock.setPreviousHash(previousBlock.getHash());
        newBlock.mineBlock(0); // Proof of work (mining)
        chain.add(newBlock);
    }

    // Get the latest block in the blockchain
    public Block getLatestBlock() {
        return chain.get(chain.size() - 1);
    }

    // Check if the blockchain is valid
    public boolean isChainValid() {
        for (int i = 1; i < chain.size(); i++) {
            Block currentBlock = chain.get(i);
            Block previousBlock = chain.get(i - 1);

            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
                return false; // Hash is not as expected
            }

            if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())) {
                return false; // Previous hash doesn't match
            }
        }
        return true;
    }
}
