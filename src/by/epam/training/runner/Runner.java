package by.epam.training.runner;

import by.epam.training.model.*;

import by.epam.training.service.TextParser;
import org.apache.log4j.*;

public class Runner {

    private static final Logger LOG = Logger.getLogger(Runner.class);

    public static void main(String[] args) {
        /*
         * Just for testing Composite-pattern !!!
         * TODO: whole project -_-
         */
        Composite wholeText = null;
        wholeText = TextParser.parseToWords("Hello \t world,\tI\tam\tMax...");

        LOG.info(wholeText);

        for (int i = 0; i < ((CompositeObject)wholeText).size(); i++) {
            if (wholeText.get(i) instanceof Leaf) {
                String result = "delimiter is: " + wholeText.get(i);
                if (wholeText.get(i).toString().equals("\t")) {
                    result += " - it's a TAB";
                }
                LOG.info(result);
            } else {
                for (int j = 0; j < ((CompositeObject)wholeText.get(i)).size(); j++) {
                    LOG.info("letter: " + wholeText.get(i).get(j));
                }
            }
        }
    }
}
