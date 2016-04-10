package Models.FileIO;

import Models.Portfolio.Portfolio;

/**
 * Interface requiring methods necessary for reading in an external portfolio
 */
public interface PortfolioParser {

    /** This method should set up all variables needed before parsing
     * @param fileIn the file path to the file to be read in
     * @param fileOut the file path to export the serialized portfolio to
     */
    void setFile(String fileIn, String fileOut);

    /** Handles parsing the read in file, contructing a portfolio object, and
     * writing it to the specified output path
     * @return the portfolio object created
     */
    Portfolio parseFile();
}
