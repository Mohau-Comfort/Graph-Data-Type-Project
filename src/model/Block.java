package model;

import java.security.*;
import java.util.*;

public class Block {

	public int index;
	public long timestamp;
	public String currentHash;
	public String previousHash;
	public String data;
	public int nonce;

	public Block(int index, String previousHash, String data) {
		this.index = index;
		this.timestamp = System.currentTimeMillis();
		this.previousHash = previousHash;
		this.data = data;
		nonce = 0;
		currentHash = calculateHash();
		}

	public Block() {
		
	}

	//This method is for calculating the current hash of the block
	public String calculateHash(){
		try {
		String input = index + timestamp + previousHash + data +
		nonce;
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(input.getBytes("UTF-8"));
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < hash.length; i++) {
		String hex = Integer.toHexString(0xff & hash[i]);
		if(hex.length() == 1) hexString.append('0');
		hexString.append(hex);
		}
		return hexString.toString();
		}
		catch(Exception e) {
		throw new RuntimeException(e);
		}
		}
	
	//This method is for mining the block according to the specified difficulty (the number of leading zeros)
		public void mineBlock(int difficulty) {
		nonce = 0;
		String target = new String(new char[difficulty]).replace('\0',
		'0');
		while (!currentHash.substring(0, difficulty).equals(target)) {
		nonce++;
		currentHash = calculateHash();
		}
		}
		//This method validates a block based on its index, the previous hash, and the current hash
				public static boolean validateBlock(Block newBlock, Block previousBlock) 
						{
						if (previousBlock == null){ //The first block
							
						if (newBlock.index != 0) {
						return false;
						}
						if (newBlock.previousHash != null) {
						return false;
						}
						if (newBlock.currentHash == null || !newBlock.calculateHash().equals(newBlock.currentHash)) {
						return false;
						
						}
						return true;
						} else{ //The rest blocks
						if (newBlock != null ) {
						if (previousBlock.index + 1 != newBlock.index) {
						return false;
						
						}
						
						if (newBlock.previousHash == null || !newBlock.previousHash.equals(previousBlock.currentHash)) {
						return false;
						}
						if (newBlock.currentHash == null || !newBlock.calculateHash().equals(newBlock.currentHash))
						{
						return false;
						
						}
						
						return true;
						
						}
						
						return false;
						}
						
						}
		//This method uses a loop to go through all blocks on the chain and validate each block using the previous
				public static boolean validateChain(ArrayList<Block> blockchain) {
				if (!validateBlock(blockchain.get(0), null)) {
				return false;
				}
				for (int i = 1; i < blockchain.size(); i++) {
				Block currentBlock = blockchain.get(i);
				Block previousBlock = blockchain.get(i - 1);
				if (!validateBlock(currentBlock, previousBlock)) {
				return false;
				}
				}
				return true;
				}
		
	
		//This one is for display the information of the block
	public String toString() {
		String s = "Block # : " + index + "\r\n";
		s = s + "PreviousHash : " + previousHash + "\r\n";
		s = s + "Timestamp : " + timestamp + "\r\n";
		s = s + "Data : " + data + "\r\n";
		s = s + "Nonce : " + nonce + "\r\n";
		s = s + "CurrentHash : " +currentHash + "\r\n";
		return s;
		}
	
	
	
	
}
