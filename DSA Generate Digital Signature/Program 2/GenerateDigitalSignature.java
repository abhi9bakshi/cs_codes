import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;

public class GenerateDigitalSignature {
    public static void main(String[] args) {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
            keyGen.initialize(1024, random);
            KeyPair keyPair = keyGen.generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();
            Signature signature = Signature.getInstance("SHA1withDSA", "SUN");
            signature.initSign(privateKey);
            byte[] bytes = Files.readAllBytes(Paths.get("D:/Amit.bin"));
            signature.update(bytes);
            byte[] digitalSignature = signature.sign();
            Files.write(Paths.get("signature"), digitalSignature);
            Files.write(Paths.get("publickey"), keyPair.getPublic().getEncoded());
            System.out.println("Signature file in Hex Format:"); 
            byte[] bytes2 = Files.readAllBytes(Paths.get("signature"));
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes2) {
            sb.append(String.format("%02X ", b));
            }
            System.out.println(sb.toString());
            System.out.println("publickey file in Hex Format:");
            byte[] bytes3 = Files.readAllBytes(Paths.get("publickey"));
            for (byte b : bytes3) {
            sb.append(String.format("%02X ", b));
            }
            System.out.println(sb.toString());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
