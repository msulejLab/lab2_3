package edu.iis.mto.similarity;

import org.junit.Test;

import static org.hamcrest.Matchers.closeTo;

import static org.junit.Assert.*;

public class SimilarityFinderTest {
    private static final double DELTA = 0.0001;

    private SequenceSearcherStub searcher;
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

}
