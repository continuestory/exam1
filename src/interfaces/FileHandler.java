package interfaces;

import vo.StockInfo;

public interface FileHandler {

    
    StockInfo[] getStockInfoFromFile(String filePath);

    
    int setStockInfo2File(String filePath,StockInfo[] stocks);

}