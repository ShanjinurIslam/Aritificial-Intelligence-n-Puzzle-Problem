import java.util.ArrayList;
import java.util.Arrays;

public class PuzzleState{
    private int boardSize ;
    private int[][] board ;
    private int[][] finalBoard ;
    private PuzzleState parent ;
    private int g ;


    @Override
    public boolean equals(Object o){
        int[][] board1 = this.board ;
        PuzzleState state = (PuzzleState) o ;
        int[][] board2 = state.board ;

        for(int i=0;i<boardSize;i++){
            for(int j=0;j<boardSize;j++){
                if(board1[i][j]!=board2[i][j]){
                    return false ;
                }
            }
        }
        return true;
    }

    PuzzleState(int[][] b,int[][] finalBoard){
        g = 0 ;
        parent=null ;
        this.boardSize = b.length ;
        board = new int[boardSize][boardSize] ;
        for(int i=0;i<boardSize;i++){
            System.arraycopy(b[i], 0, board[i], 0, boardSize);
        }
        this.finalBoard = finalBoard ;
    }

    PuzzleState(int[][] b,int[][] finalBoard,PuzzleState parent,int g){
        this.g = g ;
        this.boardSize = b.length ;
        board = new int[boardSize][boardSize] ;
        for(int i=0;i<boardSize;i++){
            System.arraycopy(b[i], 0, board[i], 0, boardSize);
        }
        this.finalBoard = finalBoard ;
    }

    private int calDist(int v,int x,int y){
        int val = 0 ;
        for(int i=0;i<boardSize;i++){
            for(int j=0;j<boardSize;j++){
                if(board[i][j]==v){
                    val = (Math.abs(x-i) + Math.abs(y-j)) ;
                }
            }
        }
        return val ;
    }

    int calHam(){
        int c = 1 ;
        int ham = 0 ;
        for(int i=0;i<boardSize;i++){
            for(int j=0;j<boardSize;j++){
                if(board[i][j]!=c){
                    ham++ ;
                }
                c++ ;
            }
        }
        return ham-1 ;
    }

    int calMann(){
        int mann = 0;
        int c = 1 ;
        for(int i=0;i<boardSize;i++){
            for(int j=0;j<boardSize;j++){
                if(finalBoard[i][j]!=0){
                    if(finalBoard[i][j]!=board[i][j]){
                        mann += calDist(finalBoard[i][j],i,j) ;
                    }
                }
            }
        }
        return mann ;
    }

    int retRow(int v){
        for(int i=0;i<boardSize;i++){
            for(int j=0;j<boardSize;j++){
                if(finalBoard[i][j]==v){
                    return i ;
                }
            }
        }
        return -1 ;
    }

    int retColumn(int v){
        for(int i=0;i<boardSize;i++){
            for(int j=0;j<boardSize;j++){
                if(finalBoard[i][j]==v){
                    return j ;
                }
            }
        }
        return -1 ;
    }

    int calLC(){
        int lc = 0 ;
        ArrayList<Integer> values = new ArrayList<>() ;
        for(int i=0;i<boardSize;i++){
            values.clear();
            for(int j=0;j<boardSize;j++){
                if(board[i][j]!=0 & i == retRow(board[i][j])){
                    values.add(board[i][j]) ;
                }
            }
            for(int k=0;k<values.size()-1;k++){
                for(int m=k+1;m<values.size();m++){
                    if(values.get(k)>values.get(m)){
                        lc++ ;
                    }
                }
            }
        }

        for(int i=0;i<boardSize;i++){
            values.clear();
            for(int j=0;j<boardSize;j++){
                if(board[j][i]!=0 & i == retColumn(board[j][i])){
                    values.add(board[j][i]) ;
                }
            }
            for(int k=0;k<values.size()-1;k++){
                for(int m=k+1;m<values.size();m++){
                    if(values.get(k)>values.get(m)){
                        lc++ ;
                    }
                }
            }
        }
        return ((2*lc) + calMann()) ;
    }

    void printBoard(){
        for(int i=0;i<boardSize;i++){
            for(int j=0;j<boardSize;j++){
                if(board[i][j]==0){
                    System.out.print("  ");
                }
                else{
                    System.out.print(board[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public boolean isFinal() {
        boolean flag = true ;
        for(int i=0;i<boardSize;i++){
            for(int j=0;j<boardSize;j++){
                if(finalBoard[i][j]!=board[i][j]){
                    flag =  false ;
                    break ;
                }
            }
        }
        return flag ;
    }

    public int getG(){
        return g;
    }

    public ArrayList<PuzzleState> genStates() {
        ArrayList<PuzzleState> states = new ArrayList<>() ;
        int[][] tem = new int[boardSize][boardSize] ;
        for(int i=0;i<boardSize;i++){
            for(int j=0;j<boardSize;j++){
                tem[i][j] = board[i][j] ;
            }
        }

        for(int i=0;i<boardSize;i++){
            for(int j=0;j<boardSize;j++){
                if(board[i][j]==0){
                    if(i+1<boardSize){
                        int v = tem[i][j] ;
                        tem[i][j] = tem[i+1][j] ;
                        tem[i+1][j] = v ;
                        states.add(new PuzzleState(tem,finalBoard)) ;
                        v = tem[i][j] ;
                        tem[i][j] = tem[i+1][j] ;
                        tem[i+1][j] = v ;
                    }
                    if(j+1<boardSize){
                        int v = tem[i][j] ;
                        tem[i][j] = tem[i][j+1] ;
                        tem[i][j+1] = v ;
                        states.add(new PuzzleState(tem,finalBoard)) ;
                        v = tem[i][j] ;
                        tem[i][j] = tem[i][j+1] ;
                        tem[i][j+1] = v ;
                    }
                    if(i-1>=0){
                        int v = tem[i][j] ;
                        tem[i][j] = tem[i-1][j] ;
                        tem[i-1][j] = v ;
                        states.add(new PuzzleState(tem,finalBoard)) ;
                        v = tem[i][j] ;
                        tem[i][j] = tem[i-1][j] ;
                        tem[i-1][j] = v ;
                    }
                    if(j-1>=0){
                        int v = tem[i][j] ;
                        tem[i][j] = tem[i][j-1] ;
                        tem[i][j-1] = v ;
                        states.add(new PuzzleState(tem,finalBoard)) ;
                        v = tem[i][j] ;
                        tem[i][j] = tem[i][j-1] ;
                        tem[i][j-1] = v ;
                    }
                }
            }
        }
        return states ;
    }

    public void setParent(PuzzleState minState) {
        this.parent = minState ;
    }

    public void setG(int i) {
        this.g = i ;
    }

    public PuzzleState getParent() {
        return parent ;
    }

    public boolean checkSolvable() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        int zeroRow = -1 ;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != 0) {
                    list.add(board[i][j]);
                }
                else{
                    zeroRow = i ;
                }
            }
        }

        int[] vector = new int[list.size()];
        for (int i = 0; i < vector.length; i++) {
            vector[i] = list.get(i);
        }
        int c = 0 ;
        for (int i = 0; i < vector.length-1; i++) {
            if(vector[i]>vector[i+1]){
                c++ ;
            }
        }

        if(boardSize*boardSize %2 !=0){
            return c%2==0 ;
        }
        else{
            c+= zeroRow ;
            return c%2!=0;
        }
    }
}
