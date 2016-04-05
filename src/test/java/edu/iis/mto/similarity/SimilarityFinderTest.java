package edu.iis.mto.similarity;

import org.junit.Test;

import static org.hamcrest.Matchers.closeTo;

import static org.junit.Assert.*;

public class SimilarityFinderTest {
    private static final double DELTA = 0.001;

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
}
