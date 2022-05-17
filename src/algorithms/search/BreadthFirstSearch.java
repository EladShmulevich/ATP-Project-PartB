package algorithms.search;

import java.util.ArrayList;

public class BreadthFirstSearch extends ASearchingAlgorithm{

    public Solution solve(ISearchable domain) {
        return new Solution(bfsAlgorithm(domain));
    }


    private ArrayList<AState> bfsAlgorithm(ISearchable domain){
        return null;
    }

}
