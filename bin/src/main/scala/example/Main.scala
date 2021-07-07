/*
 * copyright
 * http://timarcher.com/blog/2007/04/simple-java-class-to-des-encrypt-strings-such-as-passwords-and-credit-card-numbers/
 */

package example;

// Java program to perform the
// encryption and decryption
// using asymmetric key
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.xml.bind.DatatypeConverter;

object Main {

    def RSA = "RSA";

    // Generating public & private keys
    // using RSA algorithm.
    @throws(classOf[Exception])
    def generateRSAKkeyPair(): KeyPair =
    {
        val secureRandom: SecureRandom = new SecureRandom();
        val keyPairGenerator: KeyPairGenerator = KeyPairGenerator.getInstance(RSA);

        keyPairGenerator.initialize(2048, secureRandom);

        return keyPairGenerator.generateKeyPair();
    }

    // Encryption function which converts
    // the plainText into a cipherText
    // using private Key.
    @throws(classOf[Exception])
    def doRSAEncryption( plainText: String, privateKey: PrivateKey): Array[Byte] =
    {
        val cipher: Cipher = Cipher.getInstance(RSA);

        cipher.init(Cipher.ENCRYPT_MODE, privateKey);

        return cipher.doFinal(plainText.getBytes());
    }

    // Decryption function which converts
    // the ciphertext back to the
    // original plaintext.
    @throws(classOf[Exception])
    def doRSADecryption(cipherText: Array[Byte], publicKey: PublicKey): String =
    {
        val cipher: Cipher = Cipher.getInstance(RSA);

        cipher.init(Cipher.DECRYPT_MODE, publicKey);

        val result: Array[Byte] = cipher.doFinal(cipherText);

        return new String(result);
    }

  // Driver code
  @throws(classOf[Exception])
  def main(args: Array[String]): Unit =
  {
    val keypair: KeyPair = generateRSAKkeyPair();

    val plainText = "pass";

    val cipherText: Array[Byte] = doRSAEncryption( plainText, keypair.getPrivate());

    println("The Public Key is: " + DatatypeConverter.printHexBinary(keypair.getPublic().getEncoded()));

    println("The Private Key is: " + DatatypeConverter.printHexBinary(keypair.getPrivate().getEncoded()));

    print("The Encrypted Text is: ");

    val rsaText: String = DatatypeConverter.printHexBinary(cipherText);

    println(rsaText);

    println("The Encrypted Text length is: %d".format(rsaText.length));

    val compress: Array[Byte] = GZIPCompression.compress(rsaText);

    if(compress == null)
    {
      println("The Encrypted Compressed Text length is: null");

      return

    } else {
      println("The Encrypted Compressed Text length is: %d".format(compress.length));

    }

    val decompressText: String = GZIPCompression.decompress(compress);

    println("The Encrypted Decompressed Text length is: %d".format(decompressText.length));

    val decryptedText: String = doRSADecryption( cipherText, keypair.getPublic());

    println( "The decrypted text is: " + decryptedText);
  }
}
