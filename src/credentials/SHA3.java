package credentials;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SHA3 {

	// Hashes the plaintext using the SHA3-256 algorithm and returns the hash as a byte array.
	public static byte[] hash(String plaintext) {
		// Specify the hashing algorithm as SHA3-256.
		String algorithm = "SHA3-256";

		MessageDigest digest = null;

		try {
			// Create a MessageDigest object with the specified algorithm.
			digest = MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		// Convert the plaintext to bytes and hash it using the digest.
		final byte[] hashBytes = digest.digest(plaintext.getBytes(StandardCharsets.UTF_8));

		return hashBytes;
	}

	// Generates a random salt (nonce) of the specified size and returns it as a hexadecimal string.
	public static String getSalt(int nonceSize) {
		// Create a byte array to store the salt.
		byte[] nonce = new byte[nonceSize];

		// Generate random bytes and store them in the nonce array.
		new SecureRandom().nextBytes(nonce);

		// Convert the nonce to a hexadecimal string representation.
		String nonceString = bytesToHex(nonce);

		return nonceString.toString();
	}

	// Converts a byte array to a hexadecimal string representation.
	public static String bytesToHex(byte[] bytes) {
		StringBuilder hexString = new StringBuilder(2 * bytes.length);

		// Iterate over each byte in the array and convert it to a two-digit hexadecimal representation.
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(0xff & bytes[i]);

			// If the hexadecimal representation has only one digit, add a leading zero.
			if (hex.length() == 1) {
				hexString.append('0');
			}

			// Append the hexadecimal representation to the hexString.
			hexString.append(hex);
		}

		return hexString.toString();
	}
}
