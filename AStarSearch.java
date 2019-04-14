import java.util.ArrayList;

public class AStarSearch {
    ArrayList<PuzzleState> openSet ;
    ArrayList<PuzzleState> closedSet ;
    int min ;
    AStarSearch(PuzzleState initialState){
        openSet = new ArrayList<>() ;
        closedSet = new ArrayList<>() ;
        closedSet.clear();
        openSet.add(initialState) ;
    }

    PuzzleState startAStarSearchHamming(){
        int expandend = 0 ;
        while (!openSet.isEmpty()){
            min = (int) Double.POSITIVE_INFINITY ;
            PuzzleState minState;
            int index = -1 ;
            for(int i=0;i<openSet.size();i++){
                int f = openSet.get(i).getG()+openSet.get(i).calHam();
                if(f<min){
                    min = f ;
                    index = i ;
                }
            }

            minState = openSet.get(index) ;
            if(minState.isFinal()){
                System.out.println("Expanded Nodes : "+expandend);
                System.out.println("Explored Nodes : "+closedSet.size());
                return minState;
            }
            openSet.remove(minState) ;
            closedSet.add(minState) ;
            ArrayList<PuzzleState> neighbor = minState.genStates() ;
            for (PuzzleState n: neighbor) {
                if(!closedSet.contains(n)){
                    n.setParent(minState) ;
                    n.setG(minState.getG()+1) ;
                    if(!openSet.contains(n)){
                        expandend++ ;
                        openSet.add(n) ;
                    }
                }
            }
        }

        return null ;
    }

    PuzzleState startAStarSearchManhatten(){
        int expandend = 0 ;
        while (!openSet.isEmpty()){
            min = (int) Double.POSITIVE_INFINITY ;
            PuzzleState minState;
            int index = -1 ;
            for(int i=0;i<openSet.size();i++){
                int f = openSet.get(i).getG()+openSet.get(i).calMann();
                if(f<min){
                    min = f ;
                    index = i ;
                }
            }
            minState = openSet.get(index) ;
            if(minState.isFinal()){
                System.out.println("Expanded Nodes : "+expandend);
                System.out.println("Explored Nodes : "+closedSet.size());
                return minState;
            }
            openSet.remove(minState) ;
            closedSet.add(minState) ;
            ArrayList<PuzzleState> neighbor = minState.genStates() ;
            for (PuzzleState n: neighbor) {
                if(!closedSet.contains(n)){
                    n.setParent(minState) ;
                    n.setG(minState.getG()+1) ;
                    if(!openSet.contains(n)){
                        expandend++ ;
                        openSet.add(n) ;
                    }
                }
            }
        }

        return null ;
    }

    PuzzleState startAStarSearchLC(){
        int expandend = 0 ;
        while (!openSet.isEmpty()){
            min = (int) Double.POSITIVE_INFINITY ;
            PuzzleState minState;
            int index = -1 ;
            for(int i=0;i<openSet.size();i++){
                int f = openSet.get(i).getG() + openSet.get(i).calLC() ;
                if(f<min){
                    min = f ;
                    index = i ;
                }
            }
            minState = openSet.get(index) ;
            if(minState.isFinal()){
                System.out.println("Expanded Nodes : "+expandend);
                System.out.println("Explored Nodes : "+closedSet.size());
                System.out.println();
                return minState;
            }
            openSet.remove(minState) ;
            closedSet.add(minState) ;
            ArrayList<PuzzleState> neighbor = minState.genStates() ;
            for (PuzzleState n: neighbor) {
                if(!closedSet.contains(n)){
                    n.setParent(minState) ;
                    n.setG(minState.getG()+1) ;
                    if(!openSet.contains(n)){
                        expandend++;
                        openSet.add(n) ;
                    }
                }
            }
        }

        return null ;
    }
}
