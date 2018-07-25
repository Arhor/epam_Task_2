package by.epam.training.runner;

import by.epam.training.model.*;

import by.epam.training.service.TextParser;
import org.apache.log4j.*;

public class Runner {

    private static final Logger LOG = Logger.getLogger(Runner.class);

    public static void main(String[] args) {
        /*
         * Just for testing IComposite-pattern !!!
         * TODO: whole project -_-
         */
        IComposite wholeText = null;
        wholeText = TextParser.parseToSentences("Lorem ipsum dolor sit amet," +
                " consectetur adipiscing elit, sed do eiusmod tempor incidid" +
                "unt ut labore et dolore magna aliqua. Ut enim ad minim veni" +
                "am, quis nostrud exercitation ullamco laboris nisi ut aliqu" +
                "ip ex ea commodo consequat. Duis aute irure dolor in repreh" +
                "enderit in voluptate velit esse cillum dolore eu fugiat nul" +
                "la pariatur. Excepteur sint occaecat cupidatat non proident" +
                ", sunt in culpa qui officia deserunt mollit anim id est lab" +
                "orum.");

        for (int i = 0; i < ((CompositeObject)wholeText).size(); i++) {
            LOG.info(i + 1 + " sentence is: " + wholeText.get(i));
        }
    }
}
