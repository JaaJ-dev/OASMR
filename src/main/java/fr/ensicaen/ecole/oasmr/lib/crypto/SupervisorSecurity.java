package fr.ensicaen.ecole.oasmr.lib.crypto;

import fr.ensicaen.ecole.oasmr.lib.command.Command;
import sun.security.provider.DSAPrivateKey;

import javax.crypto.SealedObject;
import javax.crypto.spec.DHParameterSpec;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.DSAParameterSpec;
import java.security.spec.DSAPrivateKeySpec;

public final class SupervisorSecurity extends Diffie_Hellman {
    private DHParameterSpec DHSpec;
    private KeyPair keyPair;
    private DSAPrivateKey supervisorPrivateKey;
    private DSAParameterSpec DSASpec;
    private byte[] AESKey;

    public SupervisorSecurity() throws Exception {
        newDHSpec();
        setDSAParameters();
    }

    public SupervisorSecurity(DHParameterSpec spec) throws Exception {
        DHSpec = spec;
        setDSAParameters();
    }

    public void newDHSpec() throws Exception {
        DHSpec = Diffie_Hellman.generateParameters();
    }

    private void setDSAParameters() throws  Exception{
        FileInputStream fis = new FileInputStream("supervisorKeySpec");
        ObjectInputStream ois = new ObjectInputStream(fis);
        DSAPrivateKeySpec ks = new DSAPrivateKeySpec((BigInteger) ois.readObject(), (BigInteger) ois
                .readObject(), (BigInteger) ois.readObject(), (BigInteger) ois.readObject());
        KeyFactory kf = KeyFactory.getInstance("DSA");
        supervisorPrivateKey = (DSAPrivateKey) kf.generatePrivate(ks);
        DSASpec = new DSAParameterSpec(ks.getP(), ks.getQ(), ks.getG());
    }

    private SignedObject sign(KeyInit keyInit) throws Exception {
        Signature dsa = Signature.getInstance("DSA");
        return new SignedObject(keyInit, supervisorPrivateKey, dsa);
    }

    public SignedObject keyExchange() throws Exception {
        keyPair = Diffie_Hellman.createKeys(DHSpec);
        return sign(new KeyInit(DHSpec, keyPair.getPublic()));
    }

    public void setAESKey(PublicKey publicKey) throws Exception {
        AESKey = Diffie_Hellman.newKeyAgreement(publicKey, keyPair.getPrivate());
    }

    public SealedObject encrypt(Command command) {
        return AES.encrypt(command, AESKey);
    }

    public Command decrypt(SealedObject sealedCommand) {
        return AES.decrypt(sealedCommand, AESKey);
    }
}
