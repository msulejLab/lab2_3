package edu.iis.mto.similarity;

import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;

public class SequenceSearcherStub implements SequenceSearcher {

    public SearchResult search(int i, int[] ints) {
        SearchResultStub result = new SearchResultStub();

        for (int position = 0; position < ints.length; position += 1) {
            if (ints[position] == i) {
                result.setPosition(position + 1);
                break;
            }
        }

        return result;
    }
}
