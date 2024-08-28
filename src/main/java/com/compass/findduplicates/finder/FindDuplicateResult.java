package com.compass.findduplicates.finder;

/**
 * A result of a duplicate element search, containing the Id's and accuracy of
 * the matched elements.
 */
public class FindDuplicateResult {

	int sourceId;
	int matchingId;
	Accuracy accuracy;

	public int getSourceId() {
		return sourceId;
	}

	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}

	public int getMatchingId() {
		return matchingId;
	}

	public void setMatchingId(int matchingId) {
		this.matchingId = matchingId;
	}

	public Accuracy getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(Accuracy accuracy) {
		this.accuracy = accuracy;
	}

	@Override
	public String toString() {
		return "FindResult [sourceId=" + sourceId + ", matchingId=" + matchingId + ", accuracy=" + accuracy + "]";
	}
}
