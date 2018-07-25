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
        wholeText = TextParser.parseToSentences("Hello world... My name is Max! How's your doing!? ");

        for (int i = 0; i < ((CompositeObject)wholeText).size(); i++) {
            LOG.info(i + 1 + " sentence is: " + wholeText.get(i));
        }
    }
}
