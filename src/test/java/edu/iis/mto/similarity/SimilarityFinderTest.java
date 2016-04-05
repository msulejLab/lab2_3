package edu.iis.mto.similarity;

import edu.iis.mto.search.SequenceSearcher;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class SimilarityFinderTest {
    private static final double DELTA = 0.001;

    private SequenceSearcher searcher;
    private SimilarityFinder similarityFinder;

    int[] seq1, seq2;

    @Test
    public void emptySequencesShouldReturnOne() {
        searcher = new SequenceSearcherStub();
        similarityFinder = new SimilarityFinder(searcher);
        seq1 = new int[] {};
        seq2 = new int[] {};

        double result = similarityFinder.calculateJackardSimilarity(seq1, seq2);
        assertThat(result, closeTo(1, DELTA));
    }

    @Test
    public void equalSequencesShouldReturnOne() {
        searcher = new SequenceSearcherStub();
        similarityFinder = new SimilarityFinder(searcher);
        seq1 = new int[] {1, 2, 3, 4};
        seq2 = new int[] {1, 2, 3, 4};

        double result = similarityFinder.calculateJackardSimilarity(seq1, seq2);
        assertThat(result, closeTo(1, DELTA));
    }

    @Test
    public void completelyDifferentSequencesShouldReturnZero() {
        searcher = new SequenceSearcherStub();
        similarityFinder = new SimilarityFinder(searcher);
        seq1 = new int[] {1, 2, 3, 4};
        seq2 = new int[] {5, 6, 7, 8};

        double result = similarityFinder.calculateJackardSimilarity(seq1, seq2);
        assertThat(result, closeTo(0, DELTA));
    }

    @Test
    public void specificSequencesTest() {
        searcher = new SequenceSearcherStub();
        similarityFinder = new SimilarityFinder(searcher);
        seq1 = new int[] {7, 3, 2, 4, 1};
        seq2 = new int[] {4, 1, 9, 7, 5};

        double result = similarityFinder.calculateJackardSimilarity(seq1, seq2);
        assertThat(result, closeTo(0.429, DELTA));
    }

    @Test
    public void emptySequencesShouldNotCallSearch() {
        searcher = Mockito.mock(SequenceSearcher.class);
        similarityFinder = new SimilarityFinder(searcher);
        seq1 = new int[] {};
        seq2 = new int[] {};

        similarityFinder.calculateJackardSimilarity(seq1, seq2);
        verify(searcher, never()).search(anyInt(), any(int[].class));
    }
}
