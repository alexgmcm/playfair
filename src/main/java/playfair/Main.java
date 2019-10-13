package playfair;
import playfair.Encryptor;
import org.apache.commons.cli.*;

import java.io.PrintWriter;

public class Main {



    public static void main(String[] args) {

        //Usage: playfair {mode} {key} {text}
        CommandLine line;
        Encryptor encryptor;
        Decryptor decryptor;
        Options options = new Options();
        OptionGroup mode = new OptionGroup();
        Option encryptOpt = Option.builder("e").longOpt("encrypt").desc("Encryption Mode").build();
        Option decryptOpt = Option.builder("d").longOpt("decrypt").desc("Decryption Mode").build();
        mode.addOption(encryptOpt).addOption(decryptOpt).setRequired(true);
        options.addOptionGroup(mode);
        options.addOption(Option.builder("k").required(true).longOpt("key").desc("Key for encryption/decryption").hasArg(true).build());
        options.addOption(Option.builder("t").required(true).longOpt("text").desc("Text to be encrypted/decrypted").hasArg(true).build());
        options.addOption(Option.builder().longOpt("help").desc("Display this message.").build());


        //TODO: Add support for input/output files.
        HelpFormatter formatter = new HelpFormatter();
        CommandLineParser parser = new DefaultParser();
        //PrintWriter outFile = new PrintWriter(System.out, true);
        try {
            // parse the command line arguments
             line = parser.parse( options, args );
            if (line.hasOption("help")){
                formatter.printHelp("playfair", "Playfair Cipher Implementation", options, "", true);
                return;
            }
        }
        catch( ParseException exp ) {
            // oops, something went wrong
            System.out.println( "Parsing failed.  Reason: " + exp.getMessage() );
            formatter.printHelp("playfair", "Playfair Cipher Implementation", options, "", true);
            return;
        }

        String key = line.getOptionValue("key");
        String text = line.getOptionValue("text");

        System.out.println("Key: " + key);
        System.out.println("Text: " + text);

        if (line.hasOption("e")){
            encryptor = new Encryptor(key,text);
            System.out.println(encryptor.encrypt());
        }
        else if(line.hasOption("d")){
            decryptor = new Decryptor(line.getOptionValue("key"), line.getOptionValue("text"));
            System.out.println(decryptor.decrypt());
        }


    }


}
