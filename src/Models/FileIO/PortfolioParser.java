package Models.FileIO;

import Models.Portfolio.Portfolio;

public interface PortfolioParser {
    void setFile(String fileIn, String fileOut);
    Portfolio parseFile();
}
