package vo;


public class StockInfo{

    private int AnswerLength;
    private[] String S=new String[7];

    public void getString(int n){
        return S[n];
    }

    public void setString(String s,int n) {
        S[n]=s;
    }

    public StockInfo(String id,String title,String author,String date,String lastupdate,String content,String answerauthor,String answer){
        S[0]=id;
        S[1]=title;
        S[2]=author;
        S[3]=date;
        S[4]=lastupdate;
        S[5]=content;
        S[6]=answerauthor;
        S[7]=answer;
        AnswerLength=S[7].length();
    }
    public StockInfo(String id,String title,String author,String date,String lastupdate,String answerauthor,String answer){
        S[0]=id;
        S[1]=title;
        S[2]=author;
        S[3]=date;
        S[4]=lastupdate;
        S[5]=null;
        S[6]=answerauthor;
        S[7]=answer;
        AnswerLength=S[7].length();
    }
    public StockInfo(StockInfo stock){
        for(int i=0;i<8;i++){
            S[i]=stock.getString(i);
        }
        AnswerLength=S[7].length();
    }
}
