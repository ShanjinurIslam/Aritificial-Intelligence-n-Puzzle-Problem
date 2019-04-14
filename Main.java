import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in) ;
        int boardSize ;
        boardSize = sc.nextInt() ;
        int[][] board = new int[boardSize][boardSize] ;
        for(int i=0;i<boardSize;i++){
            for(int j=0;j<boardSize;j++){
                board[i][j] = sc.nextInt() ;
            }
        }
        int[][] finalBoard = new int[boardSize][boardSize];
        int c =1 ;
        for(int i=0;i<boardSize;i++){
            for(int j=0;j<boardSize;j++){
                if(i==boardSize-1 & j==boardSize-1){
                    finalBoard[i][j]=0;
                }
                else {
                    finalBoard[i][j]= c++ ;
                }
            }
        }

        PuzzleState state = new PuzzleState(board,finalBoard) ;
        boolean b = state.checkSolvable() ;
        if(b){
            AStarSearch a = new AStarSearch(state) ;
            System.out.println("1. Hammington 2. Manhattan 3. LinearConflict");
            int choice =  sc.nextInt();
            if(choice==1){
                PuzzleState final_state = a.startAStarSearchHamming() ;
                while (final_state!=null){
                    final_state.printBoard();
                    final_state = final_state.getParent() ;
                    System.out.println();
                }
            }
            if(choice==2){
                PuzzleState final_state = a.startAStarSearchManhatten() ;
                while (final_state!=null){
                    final_state.printBoard();
                    final_state = final_state.getParent() ;
                    System.out.println();
                }
            }
            if(choice==3){
                PuzzleState final_state = a.startAStarSearchLC() ;
                while (final_state!=null){
                    final_state.printBoard();
                    final_state = final_state.getParent() ;
                    System.out.println();
                }
            }
        }
        else{
            System.out.println("No solvable");
        }
    }
}
