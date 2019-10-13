package playfair;
import playfair.Encryptor;
import org.apache.commons.cli.*;

public class Main {



    public static void main(String[] args) {

        //Usage: playfair {mode} {key} {text}

        Options options = new Options();
        OptionGroup mode = new OptionGroup();
        Option encryptOpt = Option.builder("e").longOpt("encrypt").build();
        Option decryptOpt = Option.builder("d").longOpt("decrypt").build();
        mode.addOption(encryptOpt).addOption(decryptOpt).setRequired(true);
        options.addOptionGroup(mode);
        options.addOption(Option.builder("k").required(true).longOpt("key").hasArg(true).build());
        options.addOption(Option.builder("t").required(true).longOpt("text").hasArg(true).build());

        //TODO: Finish CLI interface
        //TODO: Add support for input/output files.

        Encryptor myEncryptor = new Encryptor("playfair example","Hide the gold in the tree stump");
        System.out.print("hai");

    }


}
