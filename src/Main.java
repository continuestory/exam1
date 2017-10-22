
import interfaces.FileHandler;
import interfaces.StockSorter;
import vo.StockInfo;

import java.io.*;
import java.util.Formatter;
import java.util.Scanner;

public class Main {

    private static FileHandler fileHandler;

    private static StockSorter stockSorter;

    static{
        //TODO:Initialize fileHandler and stockSorter with your implement class
        fileHandler=new FileHandler() {
            //读入文件数据
            @Override
            public StockInfo[] getStockInfoFromFile(String filePath) {
                StockInfo[] stockInfos=new StockInfo[10002];
                File file=new File(filePath);
                Scanner sc = null;
                try {
                    sc=new Scanner(file);
                    String s = " ";
                    int line=0;
                    while (sc.hasNextLine()) {
                        s=sc.nextLine();
                        String ss[]=s.split("\t");
                        if(ss[7]!=null) {
                            stockInfos[line] = new StockInfo(ss[0], ss[1], ss[2], ss[3], ss[4], ss[5], ss[6], ss[7]);
                        }
                        else{
                            stockInfos[line] = new StockInfo(ss[0], ss[1], ss[2], ss[3], ss[4], ss[5], ss[6]);
                        }
                        line++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (sc != null) {
                        sc.close();
                    }
                }
                return stockInfos;
            }
            @Override
            public int setStockInfo2File(String filePath, StockInfo[] stocks) {
                int i=0;
                try{
                    File file=new File(filePath);
                    FileOutputStream fos=new FileOutputStream(file);
                    if(!file.exists()){
                        file.createNewFile();
                    }
                    for(i=0;stocks[i]!=null;i++){
                        String[] st=new String[8];
                        for(int j=0;j<8;j++){
                            st[j]=stocks[i].getString(j);
                            }
                        if(stocks[i].getString(5)==null){
                            st[5]="此处没有内容。";
                        }
                        
                        String n="\n";
                        String t="\t";
                        byte[] nn=n.getBytes();
                        byte[] tt=t.getBytes();
                        for(int k=0;k<8;k++){
                            byte[] b=st[k].getBytes();
                            fos.write(b);
                            for.write(tt);
                            }
                        fos.write(nn);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return i+1;
            }
        };
        stockSorter=new StockSorter() {
            @Override
            public StockInfo[] sort(StockInfo[] info) {
                for(int j=1;info[j]!=null;j++) {
                    for (int i = 2; info[i]!=null; i++) {
                        if (info[i].getAnswerLength() > info[i - 1].getAnswerLength()) {
                            StockInfo s = new StockInfo(info[i]);
                            info[i] = new StockInfo(info[i - 1]);
                            info[i - 1] = new StockInfo(s);
                        }
                    }
                }
                return info;
            }
        };
    }

    public static void main(String[] args) {
        // write your code here

        //if(args.length < 2){
        //    System.exit(0);
        //}
        String filePath = "F:\\idea projects\\homework_1\\data.txt";
        String targetPath = "F:\\idea projects\\homework_1\\target.txt";

        //数据读取

        StockInfo[] stocks = fileHandler.getStockInfoFromFile(filePath);
        if(stocks != null)
            System.out.println("数据读入成功");
        //数据排序

        StockInfo[] sortedStocks = stockSorter.sort(stocks);
        System.out.println("排序结束");

        int writeLength = fileHandler.setStockInfo2File(targetPath,sortedStocks);
        Formatter formatter = new Formatter(System.out);
        if(writeLength == sortedStocks.length)
            formatter.format("写入操作成功，共写入%d条数据",writeLength);
        else
            formatter.format("写入失败");

    }
}
