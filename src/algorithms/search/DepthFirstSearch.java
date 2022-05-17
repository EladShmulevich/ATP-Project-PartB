package algorithms.search;
import java.util.*;

public class DepthFirstSearch extends ASearchingAlgorithm{

    public Solution solve(ISearchable domain) {
        return new Solution(dfsAlgorithm(domain));
    }


    private ArrayList<AState> dfsAlgorithm(ISearchable domain){
        if (domain == null){return null;}


        return null;
    }






}
