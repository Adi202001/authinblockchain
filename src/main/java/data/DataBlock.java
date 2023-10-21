import java.util.Date;

public class DataBlock {
    private String previousHash;
    private String hash;
    private Date timestamp;
    private String data; // You can store password records here

    public DataBlock(String previousHash, String data) {
        this.previousHash = previousHash;
        this.timestamp = new Date();
        this.data = data;
        this.hash = calculateHash();
    }

    // Calculate the hash of the current data block based on its contents
    public String calculateHash() {
        String dataToHash = previousHash + timestamp.toString() + data;
        return BlockchainUtils.applySHA256(dataToHash);
    }

    // Get the previous block's hash
    public String getPreviousHash() {
        return previousHash;
    }

    // Get the current block's hash
    public String getHash() {
        return hash;
    }

    // Get the timestamp of the block
    public Date getTimestamp() {
        return timestamp;
    }

    // Get the stored data (e.g., password records)
    public String getData() {
        return data;
    }
}
